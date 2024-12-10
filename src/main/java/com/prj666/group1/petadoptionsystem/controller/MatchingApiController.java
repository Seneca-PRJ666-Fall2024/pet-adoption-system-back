package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.MatchingApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.mappers.ModelToDtoMapper;
import com.prj666.group1.petadoptionsystem.model.Adoption;
import com.prj666.group1.petadoptionsystem.model.Recommendation;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.repository.AdoptionRepository;
import com.prj666.group1.petadoptionsystem.repository.PetRepository;
import com.prj666.group1.petadoptionsystem.repository.RecommendationRepository;
import com.prj666.group1.petadoptionsystem.service.AdoptionService;
import com.prj666.group1.petadoptionsystem.service.RecommendationService;
import com.prj666.group1.petadoptionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class MatchingApiController implements MatchingApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private ModelToDtoMapper modelToDtoMapper;

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Override
    public ResponseEntity<MatchingRecommendationAcceptedGet200Response> matchingRecommendationAcceptedGet() {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.ADOPTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MatchingRecommendationAcceptedGet200Response()
                            .success(false)
                            .message("User is not an adopter account")
                    );
        }
        Set<String> alreadyUserRecommendations = adoptionRepository.findByUserId(user.getId())
                .stream().map(Adoption::getRecommendationId)
                .collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MatchingRecommendationAcceptedGet200Response()
                        .success(true)
                        .message("List of accepted recommendations")
                        .payload(
                                modelToDtoMapper.mapRecommendations(
                                        recommendationRepository.findByUserId(user.getId())
                                                .stream()
                                                .filter(r -> r.getStatus() == RecommendationStatus.ACCEPTED)
                                                .filter(r -> !alreadyUserRecommendations.contains(r.getId()))
                                                .toList()
                                )
                        )
                );
    }

    @Override
    public ResponseEntity<MatchingRecommendationIdAcceptPut200Response> matchingRecommendationIdAcceptPut(String id) {
        return recommendationChangeStatus(id, true);
    }

    @Override
    public ResponseEntity<MatchingRecommendationIdAcceptPut200Response> matchingRecommendationIdRejectPut(String id) {
        return recommendationChangeStatus(id, false);
    }

    @Override
    public ResponseEntity<MatchingRecommendationAcceptedGet200Response> matchingRecommendationNextGet(Integer num) {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.ADOPTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MatchingRecommendationAcceptedGet200Response()
                            .success(false)
                            .message("User is not an adopter account")
                    );
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MatchingRecommendationAcceptedGet200Response()
                        .success(true)
                        .message("User recommendations")
                        .payload(
                                modelToDtoMapper.mapRecommendations(recommendationService.getNextRecommendation(user, num))
                        )
                );
    }

    private ResponseEntity<MatchingRecommendationIdAcceptPut200Response> recommendationChangeStatus(String recommendationId, boolean accepted){
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.ADOPTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MatchingRecommendationIdAcceptPut200Response()
                            .success(false)
                            .message("User is not an adopter account")
                    );
        }
        return recommendationRepository.findById(recommendationId)
                .map(r -> {
                    if(r.getStatus() != RecommendationStatus.NEW){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new MatchingRecommendationIdAcceptPut200Response()
                                        .success(false)
                                        .message("Wrong recommendation status: " + r.getStatus())
                                );
                    } else {
                        try {
                            r.setStatus(accepted ? RecommendationStatus.ACCEPTED : RecommendationStatus.REJECTED);
                            recommendationRepository.save(r);
                            List<Recommendation> rec = recommendationService.getNextRecommendation(user, 1);
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new MatchingRecommendationIdAcceptPut200Response()
                                            .success(true)
                                            .message("Recommendation accepted")
                                            .payload(
                                                    rec.isEmpty() ? null : modelToDtoMapper.mapRecommendations(rec).getFirst()
                                            )
                                    );
                        } catch (Exception e) {
                            e.printStackTrace();
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new MatchingRecommendationIdAcceptPut200Response()
                                            .success(false)
                                            .message("Adoption failed: " + e.getMessage())
                                    );
                        }
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MatchingRecommendationIdAcceptPut200Response()
                                .success(false)
                                .message("Recommendation not found" + recommendationId)
                        ));
    }
}
