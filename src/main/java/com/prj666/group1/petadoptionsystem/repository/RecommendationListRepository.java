package com.prj666.group1.petadoptionsystem.repository;

import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import com.prj666.group1.petadoptionsystem.model.Recommendation;
import com.prj666.group1.petadoptionsystem.model.RecommendationList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendationListRepository extends MongoRepository<RecommendationList, String> {


    Optional<RecommendationList> findByUserId(String userId);
}