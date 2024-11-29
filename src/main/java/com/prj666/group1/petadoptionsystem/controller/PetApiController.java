package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.PetApi;
import com.prj666.group1.petadoptionsystem.dto.PetAddProfilePostRequest;
import com.prj666.group1.petadoptionsystem.dto.PetUpdateProfilePutRequest;
import com.prj666.group1.petadoptionsystem.dto.SuccessApiResponse;
import com.prj666.group1.petadoptionsystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PetApiController implements PetApi {

    @Autowired
    private ImageService imageService;

    @Override
    public ResponseEntity<SuccessApiResponse> petAddProfilePost(PetAddProfilePostRequest petAddProfilePostRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> petDeleteProfilePetIdDelete(Long petId) {
        return null;
    }

    @Override
    public ResponseEntity<PetAddProfilePostRequest> petGetProfileGet() {
        return null;
    }

    @Override
    public ResponseEntity<SuccessApiResponse> petUpdateProfilePut(PetUpdateProfilePutRequest petUpdateProfilePutRequest) {
        return null;
    }

    @Override
    public ResponseEntity<SuccessApiResponse> petUploadImagePost(Integer petId, MultipartFile image) {
        try {
            String filename = imageService.storeImage(image);
            String fileDownloadUri = "/images/" + filename;
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessApiResponse().success(true).message("Image uploaded successfully: " + fileDownloadUri));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SuccessApiResponse().success(false).message("Could not upload the image: " + e.getMessage()));
        }
    }
}
