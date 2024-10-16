package com.prj666.group1.petadoptionsystem.dto.user;


public class LoginResponse {
    private String message;
    private String userId;

    public LoginResponse(String message, String userId) {
        this.message = message;
        this.userId = userId;
    }

    // Getters and setters
}