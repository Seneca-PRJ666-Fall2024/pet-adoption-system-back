package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.UserApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import org.springframework.http.ResponseEntity;

public class UserApiController implements UserApi {
    @Override
    public ResponseEntity<UserLoginPost200Response> userLoginPost(UserLoginPostRequest userLoginPostRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> userRegisterPost(UserRegisterPostRequest userRegisterPostRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ModelApiResponse> userUpdateContactsPut(UserUpdateContactsPutRequest userUpdateContactsPutRequest) {
        return null;
    }
}
