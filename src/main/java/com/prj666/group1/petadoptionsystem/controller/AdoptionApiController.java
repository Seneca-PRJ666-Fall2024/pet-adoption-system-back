package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.AdoptionApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.repository.AdoptionRepository;
import com.prj666.group1.petadoptionsystem.repository.AdoptionStoryRepository;
import com.prj666.group1.petadoptionsystem.repository.PetRepository;
import com.prj666.group1.petadoptionsystem.repository.UserRepository;
import com.prj666.group1.petadoptionsystem.service.AdoptionService;
import com.prj666.group1.petadoptionsystem.service.AttributeService;
import com.prj666.group1.petadoptionsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdoptionApiController implements AdoptionApi {

    @Autowired
    private AdoptionStoryRepository adoptionStoryRepository;

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ModelApiResponse> adoptionIdCancelPut(String id) {
        com.prj666.group1.petadoptionsystem.model.User user = userService.getUserFromContext();
        return adoptionRepository.findById(id)
                .filter(a -> a.getUserId().equals(user.getId()) || a.getShelterUserId().equals(user.getId()))
                .map(a -> {
                    a.setStatus(AdoptionStatus.CANCELLED);
                    adoptionRepository.save(a);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ModelApiResponse()
                                    .success(true)
                                    .message("Adoption has been canceled: " + id));
                })
                .orElse(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ModelApiResponse()
                        .success(false)
                        .message("Adoption not found: " + id)
                        )
                );
    }

    @Override
    public ResponseEntity<ModelApiResponse> adoptionIdStatusPut(String id, Adoption adoption) {
        com.prj666.group1.petadoptionsystem.model.User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.SHELTER) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ModelApiResponse()
                            .success(false)
                            .message("User is not a shelter")
                    );
        }
        return adoptionRepository.findById(id)
                .filter(a -> a.getShelterUserId().equals(user.getId()))
                .map(a -> {
                    a.setStatus(adoption.getStatus());
                    adoptionRepository.save(a);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ModelApiResponse()
                                    .success(true)
                                    .message("Adoption was updated: " + id));
                }).orElse(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ModelApiResponse()
                                        .success(false)
                                        .message("Adoption not found: " + id)
                                )
                );
    }

    @Override
    public ResponseEntity<AdoptionStatusGet200Response> adoptionStatusGet() {
        com.prj666.group1.petadoptionsystem.model.User user = userService.getUserFromContext();

        List<com.prj666.group1.petadoptionsystem.model.Adoption> userAdoptions = user.getAccountType() == Role.SHELTER ?
                adoptionRepository.findByShelterUserId(user.getId()) : adoptionRepository.findByUserId(user.getId());

        Map<String, com.prj666.group1.petadoptionsystem.model.User> adopters;
        if(user.getAccountType() == Role.SHELTER){
            adopters = userRepository.findAllById(userAdoptions.stream()
                    .map(com.prj666.group1.petadoptionsystem.model.Adoption::getUserId)
                    .collect(Collectors.toSet()))
                    .stream()
                    .collect(Collectors.toMap(com.prj666.group1.petadoptionsystem.model.User::getId, u -> u));
        } else {
            adopters = Collections.emptyMap();
        }

        Set<String> petIds = userAdoptions.stream().map(com.prj666.group1.petadoptionsystem.model.Adoption::getPetId)
                        .collect(Collectors.toSet());

        Map<String, com.prj666.group1.petadoptionsystem.model.Pet> pets = petRepository.findAllById(petIds).stream()
                        .collect(Collectors.toMap(com.prj666.group1.petadoptionsystem.model.Pet::getId, p -> p));

        Map<String,Map<String, String>> petAttributes = attributeService.getPetAttributes(pets.values());

        List<Adoption> adoptions = userAdoptions.stream()
                    .map(a -> {
                        com.prj666.group1.petadoptionsystem.model.Pet pet = pets.get(a.getPetId());
                        Pet targetPet = new Pet()
                                .shelterUserId(pet.getShelterUserId())
                                .petId(pet.getId())
                                .petName(pet.getName())
                                .imageUrl( pet.getImageUrl() != null ? pet.getImageUrl() :
                                        pet.getImages() != null && !pet.getImages().isEmpty() ?
                                                pet.getImages().getFirst() : null
                                );
                        petAttributes.get(a.getPetId()).forEach(targetPet::putAdditionalProperty);

                        com.prj666.group1.petadoptionsystem.model.User adopter = user.getAccountType() == Role.SHELTER ?
                                adopters.get(a.getUserId()) : user;

                        return new Adoption()
                                .adoptionId(a.getId())
                                .adopterId(a.getUserId())
                                .shelterUserId(a.getShelterUserId())
                                .date(a.getDate())
                                .status(a.getStatus())
                                .pet(targetPet)
                                .adopter(new User()
                                        .userId(adopter.getId())
                                        .username(adopter.getName())
                                        .imageUrl(adopter.getImageUrl())
                                        .email(adopter.getEmail())
                                        .phone(adopter.getPhone())
                                        .address(adopter.getAddress())
                                        .city(adopter.getCity())
                                        .province(adopter.getProvince())
                                        .postalCode(adopter.getPostalCode())
                                );
                        }
                    ).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new AdoptionStatusGet200Response()
                        .success(false)
                        .message("Adoptions retrieved successfully")
                        .payload(adoptions)
                );
    }

    @Override
    public ResponseEntity<AdoptionStoryGet200Response> adoptionStoryGet(Integer N) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new AdoptionStoryGet200Response()
                        .success(true)
                        .message("Adoption stories read")
                        .payload(
                                adoptionStoryRepository.findRandomStories(N).stream()
                                        .map(s ->
                                                new AdoptionStoryGet200ResponseAllOfPayloadInner()
                                                        .author(s.getAuthor())
                                                        .title(s.getTitle())
                                                        .text(s.getStory())
                                        ).toList()
                        )
                );
    }
}
