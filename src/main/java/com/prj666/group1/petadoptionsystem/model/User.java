package com.prj666.group1.petadoptionsystem.model;

import com.prj666.group1.petadoptionsystem.dto.UserRegisterPostRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private UserRegisterPostRequest.AccountTypeEnum accountType;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRegisterPostRequest.AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(UserRegisterPostRequest.AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}