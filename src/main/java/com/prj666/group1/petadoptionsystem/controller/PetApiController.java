package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.PetApi;
import com.prj666.group1.petadoptionsystem.dto.ModelApiResponse;
import com.prj666.group1.petadoptionsystem.dto.PetUpdateInfoPutRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PetApiController implements PetApi {
    @Override
    public ResponseEntity<ModelApiResponse> petUpdateInfoPut(PetUpdateInfoPutRequest petUpdateInfoPutRequest) {
        return null;
    }
}
