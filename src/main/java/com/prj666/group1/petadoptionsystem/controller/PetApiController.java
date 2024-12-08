package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.PetApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.repository.PetRepository;
import com.prj666.group1.petadoptionsystem.service.AttributeService;
import com.prj666.group1.petadoptionsystem.service.ImageService;
import com.prj666.group1.petadoptionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class PetApiController implements PetApi {

    @Autowired
    private ImageService imageService;

    @Value("${openapi.image-base-path}")
    private String imageBasePath;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<ModelApiResponse> petAddProfilePost(Pet pet) {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.SHELTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ModelApiResponse()
                            .success(false)
                            .message("User is not a shelter account")
                    );
        }
        com.prj666.group1.petadoptionsystem.model.Pet targetPet = new com.prj666.group1.petadoptionsystem.model.Pet();
        targetPet.setName(pet.getPetName());
        targetPet.setImageUrl(pet.getImageUrl());
        targetPet.setShelterUserId(user.getId());
        List<String> newAttributes = attributeService.convertToIds(pet.getAdditionalProperties());
        if(!newAttributes.isEmpty()){
            targetPet.setAttributes(newAttributes);
        }
        petRepository.save(targetPet);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ModelApiResponse()
                        .success(true)
                        .message("Pet profile updated successfully")
                );
    }

    @Override
    public ResponseEntity<ModelApiResponse> petDeleteProfilePetIdDelete(String petId) {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.SHELTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ModelApiResponse()
                            .success(false)
                            .message("User is not a shelter account")
                    );
        }
        if(petId == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ModelApiResponse()
                            .success(false)
                            .message("No petId provided")
                    );
        }
        return petRepository.findById(petId)
                .filter(p -> p.getShelterUserId() != null && p.getShelterUserId().equals(user.getId()))
                .map(p -> {
                    petRepository.deleteById(p.getId());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ModelApiResponse()
                                    .success(true)
                                    .message("Pet deleted: " + petId)
                            );
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ModelApiResponse()
                                .success(false)
                                .message("Pet not found: " + petId)
                        )
                );
    }


    @Override
    public ResponseEntity<PetGetProfileGet200Response> petGetProfileGet() {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.SHELTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new PetGetProfileGet200Response()
                            .success(false)
                            .message("User is not a shelter account")
                    );
        }
        Map<String, Pair<String, String>> attMap =  attributeService.getAttributesMap();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PetGetProfileGet200Response()
                        .success(true)
                        .message("Shelter pets retrieved")
                        .payload(
                                petRepository.findByShelterUserId(user.getId())
                                        .stream().map(p -> {
                                               Pet pet = new Pet()
                                                       .shelterUserId(p.getShelterUserId())
                                                       .petId(p.getId())
                                                       .petName(p.getName())
                                                       .imageUrl( p.getImageUrl() != null ? p.getImageUrl() :
                                                               p.getImages() != null && !p.getImages().isEmpty() ?
                                                                       p.getImages().getFirst() : null
                                                       );
                                               if(p.getAttributes() != null && !p.getAttributes().isEmpty()){
                                                   p.getAttributes()
                                                           .forEach(a -> {
                                                               Pair<String, String> attrName = attMap.get(a);
                                                               if(attrName == null){
                                                                   throw new IllegalArgumentException("Invalid pet attribute: " + p.getId() + " : " + a);
                                                               }
                                                               pet.putAdditionalProperty(attrName.getFirst(), attrName.getSecond());
                                                           });
                                               }
                                               return pet;
                                            }
                                        ) .toList()
                        )
                );
    }

    @Override
    public ResponseEntity<PetGetProfilePetIdGet200Response> petGetProfilePetIdGet(String petId) {
        return petRepository.findById(petId)
                .map(pet -> {
                    Pet targetPet = new Pet()
                            .shelterUserId(pet.getShelterUserId())
                            .petId(pet.getId())
                            .petName(pet.getName())
                            .imageUrl( pet.getImageUrl() != null ? pet.getImageUrl() :
                                    pet.getImages() != null && !pet.getImages().isEmpty() ?
                                            pet.getImages().getFirst() : null
                            );
                    attributeService.getPetAttributes(List.of(pet))
                            .get(pet.getId())
                            .forEach(targetPet::putAdditionalProperty);
                    return ResponseEntity.status(HttpStatus.OK)
                        .body(new PetGetProfilePetIdGet200Response()
                            .success(true)
                            .message("Pet profile loaded successfully")
                            .payload(targetPet)
                        );
                    }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new PetGetProfilePetIdGet200Response()
                            .success(false)
                            .message("Pet not found: " + petId)
                    )
                );
    }

    @Override
    public ResponseEntity<ModelApiResponse> petUpdateProfilePut(Pet pet) {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.SHELTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ModelApiResponse()
                            .success(false)
                            .message("User is not a shelter account")
                    );
        }
        String petId = pet.getPetId();
        if(petId == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ModelApiResponse()
                            .success(false)
                            .message("No petId provided")
                    );
        }
        return petRepository.findById(petId)
                .filter(p -> p.getShelterUserId() != null && p.getShelterUserId().equals(user.getId()))
                .map(targetPet -> {
                    if(pet.getImageUrl() != null){
                        targetPet.setImageUrl(pet.getImageUrl());
                    }
                    if(pet.getPetName() != null){
                        targetPet.setName(pet.getPetName());
                    }
                    List<String> newAttributes = attributeService.convertToIds(pet.getAdditionalProperties());
                    if(!newAttributes.isEmpty()){
                        targetPet.setAttributes(newAttributes);
                    }
                    petRepository.save(targetPet);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ModelApiResponse()
                                    .success(true)
                                    .message("Pet profile updated successfully")
                            );
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ModelApiResponse()
                                .success(false)
                                .message("Pet not found: " + petId)
                        )
                );
    }

    @Override
    public ResponseEntity<UserUploadImagePost200Response> petUploadImagePost(MultipartFile image) {
        try {
            String filename = imageService.storeImage(image);
            String fileDownloadUri = imageBasePath+ "/" + filename;
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UserUploadImagePost200Response()
                            .success(true)
                            .message("Image uploaded successfully")
                            .payload(fileDownloadUri)
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserUploadImagePost200Response().success(false).message("Could not upload the image: " + e.getMessage()));
        }
    }
}
