package com.prj666.group1.petadoptionsystem.model;

import com.prj666.group1.petadoptionsystem.dto.UserRegisterPostRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Pet")
public class Pet {
    @Id
    private String id;
    private String name;
    @Indexed
    private String shelterId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShelterId() {
        return shelterId;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }
}