package com.prj666.group1.petadoptionsystem.repository;

import com.prj666.group1.petadoptionsystem.model.Adoption;
import com.prj666.group1.petadoptionsystem.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends MongoRepository<Adoption, String> {

    List<Adoption> findByUserId(String userId);

    List<Adoption> findByPetId(String petId);

    List<Adoption> findByShelterUserId(String petId);
}