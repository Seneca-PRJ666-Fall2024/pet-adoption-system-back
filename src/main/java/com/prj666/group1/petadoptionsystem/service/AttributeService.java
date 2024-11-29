package com.prj666.group1.petadoptionsystem.service;

import com.prj666.group1.petadoptionsystem.model.Attribute;
import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import com.prj666.group1.petadoptionsystem.repository.AttributeGroupRepository;
import com.prj666.group1.petadoptionsystem.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeGroupRepository attributeGroupRepository;

    // Create a new AttributeGroup
    public AttributeGroup createAttributeGroup(String name, boolean mandatory, boolean multivalued) {
        AttributeGroup attributeGroup = new AttributeGroup(name, mandatory, multivalued);
        return attributeGroupRepository.save(attributeGroup);
    }

    public Optional<AttributeGroup> getAttributeGroupByName(String name) {
        return attributeGroupRepository.findByName(name);
    }

    // Add an Attribute to an AttributeGroup
    public Attribute addAttributeToGroup(String attributeGroupId, String attributeName) {
        // Check if AttributeGroup exists
        if (!attributeGroupRepository.existsById(attributeGroupId)) {
            throw new RuntimeException("AttributeGroup not found with id: " + attributeGroupId);
        }

        Attribute attribute = new Attribute(attributeName, attributeGroupId);
        return attributeRepository.save(attribute);
    }

    // Retrieve all Attributes for an AttributeGroup
    public List<Attribute> getAttributesByGroup(String attributeGroupId) {
        return attributeRepository.findByAttributeGroupId(attributeGroupId);
    }

}
