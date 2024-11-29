package com.prj666.group1.petadoptionsystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "Recommendation")
public class Recommendation {

    @Id
    private String id;

    @Indexed
    private String recommendationListId;

    @Indexed
    private String petId;

    @Indexed
    private String userId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private String status;

    // Constructors
    public Recommendation() {}

    public Recommendation(String recommendationListId, String petId, String userId, LocalDate date, String status) {
        this.recommendationListId = recommendationListId;
        this.petId = petId;
        this.date = date;
        this.status = status;
        this.userId = userId;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getRecommendationListId() {
        return recommendationListId;
    }

    public void setRecommendationListId(String recommendationListId) {
        this.recommendationListId = recommendationListId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}