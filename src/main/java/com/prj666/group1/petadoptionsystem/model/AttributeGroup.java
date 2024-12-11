package com.prj666.group1.petadoptionsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AttributeGroup")
public class AttributeGroup {
    @Id
    private String id;
    private String name;
    private String description;
    private String adopterQuestion;
    private String shelterQuestion;
    private boolean mandatory;
    private boolean multivalued;
    private boolean supportsOther;

    // Constructors, Getters, Setters
    public AttributeGroup() {}

    public AttributeGroup(String name, boolean mandatory, boolean multivalued, String description,
                          String adopterQuestion, String shelterQuestion, boolean supportsOther) {
        this.name = name;
        this.mandatory = mandatory;
        this.multivalued = multivalued;
        this.description = description;
        this.adopterQuestion = adopterQuestion;
        this.shelterQuestion = shelterQuestion;
        this.supportsOther = supportsOther;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isMultivalued() {
        return multivalued;
    }

    public void setMultivalued(boolean multivalued) {
        this.multivalued = multivalued;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdopterQuestion() {
        return adopterQuestion;
    }

    public void setAdopterQuestion(String adopterQuestion) {
        this.adopterQuestion = adopterQuestion;
    }

    public String getShelterQuestion() {
        return shelterQuestion;
    }

    public void setShelterQuestion(String shelterQuestion) {
        this.shelterQuestion = shelterQuestion;
    }

    public boolean isSupportsOther() {
        return supportsOther;
    }

    public void setSupportsOther(boolean supportsOther) {
        this.supportsOther = supportsOther;
    }
}
