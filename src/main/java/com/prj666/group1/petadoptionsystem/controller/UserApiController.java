package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.UserApi;
import com.prj666.group1.petadoptionsystem.dto.ModelApiResponse;
import com.prj666.group1.petadoptionsystem.dto.UserUpdateContactsPutRequest;
import org.springframework.http.ResponseEntity;

public class UserApiController implements UserApi {
    @Override
    public ResponseEntity<ModelApiResponse> userUpdateContactsPut(UserUpdateContactsPutRequest userUpdateContactsPutRequest) {
        return null;
    }
}
