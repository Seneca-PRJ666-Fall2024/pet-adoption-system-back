package com.prj666.group1.petadoptionsystem.repository;

import com.prj666.group1.petadoptionsystem.model.Attribute;
import com.prj666.group1.petadoptionsystem.model.Preference;
import com.prj666.group1.petadoptionsystem.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {

    List<Recommendation> findByRecommendationListId(String recommendationListId);

    List<Recommendation> findByUserId(String recommendationListId);
}