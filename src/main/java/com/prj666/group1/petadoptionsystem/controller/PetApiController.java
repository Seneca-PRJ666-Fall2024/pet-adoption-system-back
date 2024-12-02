package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.PetApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.model.Attribute;
import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import com.prj666.group1.petadoptionsystem.repository.AttributeGroupRepository;
import com.prj666.group1.petadoptionsystem.repository.AttributeRepository;
import com.prj666.group1.petadoptionsystem.repository.PetRepository;
import com.prj666.group1.petadoptionsystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PetApiController implements PetApi {

    @Autowired
    private ImageService imageService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeGroupRepository attributeGroupRepository;

    @Override
    public ResponseEntity<ModelApiResponse> petAddProfilePost(PetAddProfilePostRequest petAddProfilePostRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> petDeleteProfilePetIdDelete(String petId) {
        return null;
    }

    @Override
    public ResponseEntity<PetGetProfilePetIdGet200Response> petGetProfilePetIdGet(String petId) {
        return petRepository.findById(petId)
                .map(pet -> {
                    Map<String, List<String>> attributes = new HashMap<>();
                    if(pet.getAttributes() != null){
                        attributes = pet.getAttributes().stream()
                                .map(a -> attributeRepository.findById(a))
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.groupingBy(Attribute::getAttributeGroupId))
                                .entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        a -> attributeGroupRepository.findById(a.getKey())
                                                .map(AttributeGroup::getName).orElse("Unknown"),
                                        a -> a.getValue()
                                                .stream()
                                                .map(Attribute::getName)
                                                .toList()
                                ));
                    }
                    return ResponseEntity.status(HttpStatus.OK)
                        .body(new PetGetProfilePetIdGet200Response()
                            .success(true)
                            .message("Pet profile loaded successfully")
                            .payload(new Pet()
                                .petId(pet.getId())
                                .name(pet.getName())
                                .images(pet.getImages())
                                .attributes(attributes)
                            )
                        );
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new PetGetProfilePetIdGet200Response()
                            .success(false)
                            .message("Pet not found: " + petId)
                    )
                );
    }

    @Override
    public ResponseEntity<ModelApiResponse> petUpdateProfilePut(PetUpdateProfilePutRequest petUpdateProfilePutRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> petUploadImagePost(String petId, MultipartFile image) {
        try {
            String filename = imageService.storeImage(image);
            String fileDownloadUri = "/images/" + filename;
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ModelApiResponse().success(true).message("Image uploaded successfully: " + fileDownloadUri));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ModelApiResponse().success(false).message("Could not upload the image: " + e.getMessage()));
        }
    }
}
