package com.prj666.group1.petadoptionsystem.model;

import com.prj666.group1.petadoptionsystem.dto.AdoptionStatus;
import com.prj666.group1.petadoptionsystem.dto.RecommendationStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Map;

@Document(collection = "Adoption")
public class Adoption {

    @Id
    private String id;

    @Indexed
    private String petId;

    @Indexed
    private String userId;

    @Indexed
    private String shelterUserId;

    @Indexed
    private String recommendationId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private AdoptionStatus status;

    private Map<String, String> answers;

    // Constructors
    public Adoption() {}

    public Adoption(String petId, String userId, String shelterUserId, LocalDate date, AdoptionStatus status, Map<String, String> answers, String recommendationId) {
        this.petId = petId;
        this.date = date;
        this.status = status;
        this.userId = userId;
        this.shelterUserId = shelterUserId;
        this.answers = answers;
        this.recommendationId = recommendationId;
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

    public String getShelterUserId() {
        return shelterUserId;
    }

    public void setShelterUserId(String shelterUserId) {
        this.shelterUserId = shelterUserId;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }

    public String getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(String recommendationId) {
        this.recommendationId = recommendationId;
    }
}