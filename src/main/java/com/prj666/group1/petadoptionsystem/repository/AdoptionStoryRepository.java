package com.prj666.group1.petadoptionsystem.repository;

import com.prj666.group1.petadoptionsystem.model.AdoptionStory;
import com.prj666.group1.petadoptionsystem.model.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AdoptionStoryRepository extends MongoRepository<AdoptionStory, String> {
    @Aggregation(pipeline = { "{ $sample: { size: ?0 } }" })
    List<AdoptionStory> findRandomStories(int N);
}