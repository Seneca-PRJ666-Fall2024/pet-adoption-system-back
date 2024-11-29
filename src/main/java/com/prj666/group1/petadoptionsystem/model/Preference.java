package com.prj666.group1.petadoptionsystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Preference")
public class Preference {

    @Id
    private String id;

    @NotBlank(message = "User ID is mandatory")
    private String userId;

    @NotNull(message = "Attribute ID is mandatory")
    private String attributeId;

    // Constructors
    public Preference() {}

    public Preference(String userId, String attributeId) {
        this.userId = userId;
        this.attributeId = attributeId;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    // No setter for 'id' as it's managed by MongoDB

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }
}