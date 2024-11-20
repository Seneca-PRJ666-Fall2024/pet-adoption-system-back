package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.UserApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.GrantedAuthority;

import java.util.Optional;

@Controller
public class UserApiController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserLoginPost200Response> userLoginPost(UserLoginPostRequest userLoginPostRequest) {
        Optional<User> user = userService.login(userLoginPostRequest.getEmail(), userLoginPostRequest.getPassword());

        UserLoginPost200Response resp = new UserLoginPost200Response();

        if (user.isPresent()) {
            // Successful login
            return ResponseEntity.ok(resp.success(true).message("Login successful").token(user.get().getToken()));
        } else {
            // Failed login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(resp.success(false).message("Invalid username or password"));
        }
    }

    @Override
    public ResponseEntity<ModelApiResponse> userRegisterPost(UserRegisterPostRequest userRegisterPostRequest) {
        ModelApiResponse resp = new ModelApiResponse();

        // Check if the username already exists
        if (userService.existsByEmail(userRegisterPostRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(resp.success(false).message("Username is already taken"));
        }

        // Create and save a new user
        User newUser = new User();
        newUser.setEmail(userRegisterPostRequest.getEmail());
        newUser.setPassword(userRegisterPostRequest.getPassword());
        newUser.setAccountType(userRegisterPostRequest.getAccountType());
        userService.saveUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resp.success(true).message("User registered successfully"));
    }

    @Override
    public ResponseEntity<ModelApiResponse> userUpdateContactsPut(UserUpdateContactsPutRequest userUpdateContactsPutRequest) {

        ModelApiResponse resp = new ModelApiResponse();
        User user;
        try {
            user = userService.getUserFromContext();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(resp.success(false).message(e.getMessage()));
        }

        user.setAddress(userUpdateContactsPutRequest.getAddress());
        user.setPhone(userUpdateContactsPutRequest.getPhone());
        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(resp.success(true).message("User updated successfully"));
    }
}
