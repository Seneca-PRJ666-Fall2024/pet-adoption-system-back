package com.prj666.group1.petadoptionsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "RecommendationList")
public class RecommendationList {

    @Id
    private String id;

    @Indexed
    private String userId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;


    // Constructors
    public RecommendationList() {}

    public RecommendationList(String userId, LocalDate date) {
        this.userId = userId;
        this.date = date;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}