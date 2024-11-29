package com.prj666.group1.petadoptionsystem.repository;

import com.prj666.group1.petadoptionsystem.model.AdoptionStory;
import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeGroupRepository extends MongoRepository<AttributeGroup, String> {
    Optional<AttributeGroup> findByName(String name);
}