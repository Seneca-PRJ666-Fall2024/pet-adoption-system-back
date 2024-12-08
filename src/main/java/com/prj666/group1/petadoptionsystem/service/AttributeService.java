package com.prj666.group1.petadoptionsystem.service;

import com.prj666.group1.petadoptionsystem.model.Attribute;
import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import com.prj666.group1.petadoptionsystem.model.Pet;
import com.prj666.group1.petadoptionsystem.repository.AttributeGroupRepository;
import com.prj666.group1.petadoptionsystem.repository.AttributeRepository;
import com.prj666.group1.petadoptionsystem.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeGroupRepository attributeGroupRepository;
    @Autowired
    private PetRepository petRepository;

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

    public Map<String, Pair<String, String>> getAttributesMap(){
        Map<String, String> attributeGroups = attributeGroupRepository.findAll().stream()
                .collect(Collectors.toMap(AttributeGroup::getId, AttributeGroup::getName));
        return attributeRepository.findAll().stream()
                .collect(Collectors.toMap(Attribute::getId,
                        attr -> {
                            String attrGroup = attributeGroups.get(attr.getAttributeGroupId());
                            if(attrGroup == null){
                                throw new RuntimeException("AttributeGroup not found with id: " + attr.getId());
                            }
                            return Pair.of(attrGroup, attr.getName());
                        })
                );
    }

    public Map<String,Map<String, String>> getPetAttributes(Collection<Pet> pets){
        Map<String, Pair<String, String>> attrMap = getAttributesMap();

        return pets.stream()
            .collect(Collectors.toMap(Pet::getId,
                pet -> pet.getAttributes() == null ? Collections.emptyMap() :
                        pet.getAttributes().stream()
                            .map(attrMap::get)
                            .collect(Collectors.groupingBy(Pair::getFirst))
                            .entrySet()
                            .stream()
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    a -> {
                                        List<String> values =  a.getValue().stream().map(Pair::getSecond).toList();
                                        if(values.size() > 1){
                                            throw new IllegalStateException("Multiple values found for attribute: " + a.getKey());
                                        } else {
                                            return values.getFirst();
                                        }
                                    }
                            ))
            ));

    }

    public List<String> convertToIds(Map<String, String> newAttributes) {
        if(newAttributes != null && !newAttributes.isEmpty()) {
             return newAttributes.entrySet().stream()
                    .map(a -> {
                        Optional<AttributeGroup> attGroup = attributeGroupRepository.findByName(a.getKey());
                        if(attGroup.isEmpty()){
                            throw new IllegalArgumentException("AttributeGroup not found with name: " + a.getKey());
                        }
                        Attribute attTarget = attributeRepository.findByAttributeGroupId(attGroup.get().getId())
                                .stream()
                                .collect(Collectors.toMap(Attribute::getName, at -> at))
                                .get(a.getValue());
                        if(attTarget == null){
                            throw new IllegalArgumentException("Attribute not found: " + a.getKey() + " : " + a.getValue());
                        }
                        return attTarget.getId();
                    })
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }
}
