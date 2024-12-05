package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.PetApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.model.Attribute;
import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.repository.AttributeGroupRepository;
import com.prj666.group1.petadoptionsystem.repository.AttributeRepository;
import com.prj666.group1.petadoptionsystem.repository.PetRepository;
import com.prj666.group1.petadoptionsystem.service.AttributeService;
import com.prj666.group1.petadoptionsystem.service.ImageService;
import com.prj666.group1.petadoptionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${openapi.image-base-path}")
    private String imageBasePath;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<ModelApiResponse> petAddProfilePost(PetAddProfilePostRequest petAddProfilePostRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> petDeleteProfilePetIdDelete(String petId) {
        return null;
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
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PetGetProfileGet200Response()
                        .success(true)
                        .message("Shelter pets retrieved")
                        .payload(
                                petRepository.findByShelterUserId(user.getId())
                                        .stream().map(p ->
                                               new Pet()
                                                       .petId(p.getId())
                                                       .petName(p.getName())
                                                       .images(p.getImages())
                                                       .attributes(attributeService.getPetAttributes(p))
                                        ) .toList()
                        )
                );
    }

    @Override
    public ResponseEntity<PetGetProfilePetIdGet200Response> petGetProfilePetIdGet(String petId) {
        return petRepository.findById(petId)
                .map(pet -> ResponseEntity.status(HttpStatus.OK)
                    .body(new PetGetProfilePetIdGet200Response()
                        .success(true)
                        .message("Pet profile loaded successfully")
                        .payload(new Pet()
                            .petId(pet.getId())
                            .petName(pet.getName())
                            .images(pet.getImages())
                            .attributes(attributeService.getPetAttributes(pet))
                        )
                    )).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
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
