package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.LoginApi;
import com.prj666.group1.petadoptionsystem.dto.LoginPost200Response;
import com.prj666.group1.petadoptionsystem.dto.LoginPostRequest;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class LoginApiController implements LoginApi {

    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<LoginPost200Response> loginPost(LoginPostRequest loginPostRequest) {

        Optional<User> user = userService.login(loginPostRequest.getEmail(), loginPostRequest.getPassword());

        LoginPost200Response resp = new LoginPost200Response();

        if (user.isPresent()) {
            // Successful login
            return ResponseEntity.ok(resp.success(true).message("Login successful").token(user.get().getToken()));
        } else {
            // Failed login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(resp.success(false).message("Invalid username or password"));
        }
    }

}
