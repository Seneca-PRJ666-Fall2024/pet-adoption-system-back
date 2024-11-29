package com.prj666.group1.petadoptionsystem.repository;

import com.prj666.group1.petadoptionsystem.model.Attribute;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends MongoRepository<Attribute, String> {
    List<Attribute> findByAttributeGroupId(String attributeGroupId);
}