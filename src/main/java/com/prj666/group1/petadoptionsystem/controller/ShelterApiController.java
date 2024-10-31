package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.ShelterApi;
import com.prj666.group1.petadoptionsystem.dto.ModelApiResponse;
import com.prj666.group1.petadoptionsystem.dto.ShelterAddPetPostRequest;
import com.prj666.group1.petadoptionsystem.dto.ShelterUpdateContactsPutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ShelterApiController implements ShelterApi {
    @Override
    public ResponseEntity<ModelApiResponse> shelterAddPetPost(ShelterAddPetPostRequest shelterAddPetPostRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> shelterUpdateContactsPut(ShelterUpdateContactsPutRequest shelterUpdateContactsPutRequest) {
        return null;
    }
}
