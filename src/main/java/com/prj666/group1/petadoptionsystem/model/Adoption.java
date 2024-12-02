package com.prj666.group1.petadoptionsystem.model;

import com.prj666.group1.petadoptionsystem.dto.AdoptionStatus;
import com.prj666.group1.petadoptionsystem.dto.RecommendationStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "Adoption")
public class Adoption {

    @Id
    private String id;

    @Indexed
    private String petId;

    @Indexed
    private String userId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private AdoptionStatus status;

    // Constructors
    public Adoption() {}

    public Adoption(String petId, String userId, LocalDate date, AdoptionStatus status) {
        this.petId = petId;
        this.date = date;
        this.status = status;
        this.userId = userId;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AdoptionStatus getStatus() {
        return status;
    }

    public void setStatus(AdoptionStatus status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}