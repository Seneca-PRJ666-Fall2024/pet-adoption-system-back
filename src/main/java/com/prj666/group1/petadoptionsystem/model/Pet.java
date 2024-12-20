package com.prj666.group1.petadoptionsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Pet")
public class Pet {
    @Id
    private String id;
    private String name;
    @Indexed
    private String shelterUserId;
    private String imageUrl;

    private List<String> attributes;

    private List<String> images;

    private com.prj666.group1.petadoptionsystem.dto.Pet.StatusEnum status;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShelterUserId() {
        return shelterUserId;
    }

    public void setShelterUserId(String shelterUserId) {
        this.shelterUserId = shelterUserId;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public com.prj666.group1.petadoptionsystem.dto.Pet.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(com.prj666.group1.petadoptionsystem.dto.Pet.StatusEnum status) {
        this.status = status;
    }
}