package com.prj666.group1.petadoptionsystem.repository.user;

import com.prj666.group1.petadoptionsystem.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}