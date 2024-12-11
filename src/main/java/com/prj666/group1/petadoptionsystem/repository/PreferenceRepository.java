package com.prj666.group1.petadoptionsystem.repository;

import com.prj666.group1.petadoptionsystem.model.Preference;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenceRepository extends MongoRepository<Preference, String> {

    void deleteByUserId(String userId);

    @Query("{ 'userId': { $in: ?0 } }")
    List<Preference> findAllByUserId(List<String> userIds);
}