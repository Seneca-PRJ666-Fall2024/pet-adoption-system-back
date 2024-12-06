package com.prj666.group1.petadoptionsystem.model;

import com.prj666.group1.petadoptionsystem.dto.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String email;
    @Transient
    private String rawPassword;
    private String password;
    private Role accountType;
    private String token;
    private String name;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String phone;
    private String imageUrl;
    private boolean profileSet;
    private String recommendationList;

    public String getId() {
        return id;
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

    public Role getAccountType() {
        return accountType;
    }

    public void setAccountType(Role accountType) {
        this.accountType = accountType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isProfileSet() {
        return profileSet;
    }

    public void setProfileSet(boolean profileSet) {
        this.profileSet = profileSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecommendationList() {
        return recommendationList;
    }

    public void setRecommendationList(String recommendationList) {
        this.recommendationList = recommendationList;
    }
}