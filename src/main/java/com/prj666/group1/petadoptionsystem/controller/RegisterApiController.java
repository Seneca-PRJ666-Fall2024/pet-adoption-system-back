package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.RegisterApi;
import com.prj666.group1.petadoptionsystem.dto.ModelApiResponse;
import com.prj666.group1.petadoptionsystem.dto.RegisterPostRequest;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterApiController implements RegisterApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<ModelApiResponse> registerPost(RegisterPostRequest registerPostRequest) {
        ModelApiResponse resp = new ModelApiResponse();

        // Check if the username already exists
        if (userService.existsByEmail(registerPostRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(resp.success(false).message("Username is already taken"));
        }

        // Create and save a new user
        User newUser = new User();
        newUser.setEmail(registerPostRequest.getEmail());
        newUser.setPassword(registerPostRequest.getPassword());
        newUser.setAccountType(registerPostRequest.getAccountType());
        userService.saveUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resp.success(true).message("User registered successfully"));
    }
}
