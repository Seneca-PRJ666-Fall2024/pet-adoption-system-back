package com.prj666.group1.petadoptionsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Attribute")
public class Attribute {
    @Id
    private String id;
    private String name;

    @Indexed
    private String attributeGroupId; // Reference to AttributeGroup

    // Constructors, Getters, Setters
    public Attribute() {}

    public Attribute(String name, String attributeGroupId) {
        this.name = name;
        this.attributeGroupId = attributeGroupId;
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

    public String getAttributeGroupId() {
        return attributeGroupId;
    }

    public void setAttributeGroupId(String attributeGroupId) {
        this.attributeGroupId = attributeGroupId;
    }
}
