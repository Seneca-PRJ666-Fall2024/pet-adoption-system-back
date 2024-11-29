package com.prj666.group1.petadoptionsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AttributeGroup")
public class AttributeGroup {
    @Id
    private String id;
    private String name;
    private boolean mandatory;
    private boolean multivalued;

    // Constructors, Getters, Setters
    public AttributeGroup() {}

    public AttributeGroup(String name, boolean mandatory, boolean multivalued) {
        this.name = name;
        this.mandatory = mandatory;
        this.multivalued = multivalued;
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
}
