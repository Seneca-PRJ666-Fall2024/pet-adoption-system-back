package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.MatchingApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.model.Adoption;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.repository.RecommendationRepository;
import com.prj666.group1.petadoptionsystem.service.AdoptionService;
import com.prj666.group1.petadoptionsystem.service.RecommendationService;
import com.prj666.group1.petadoptionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class MatchingApiController implements MatchingApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private AdoptionService adoptionService;

    @Override
    public ResponseEntity<MatchingRecommendationIdAcceptPut201Response> matchingRecommendationIdAcceptPut(String id) {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.ADOPTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MatchingRecommendationIdAcceptPut201Response()
                            .success(false)
                            .message("User is not an adopter account")
                    );
        }
        return recommendationRepository.findById(id)
                .map(r -> {
                    if(r.getStatus() != RecommendationStatus.NEW){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new MatchingRecommendationIdAcceptPut201Response()
                                        .success(false)
                                        .message("Wrong recommendation status: " + r.getStatus())
                                );
                    } else {
                        try {
                            Adoption adoption = adoptionService.startAdoption(r);
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new MatchingRecommendationIdAcceptPut201Response()
                                            .success(true)
                                            .message("Recommendation accepted")
                                            .payload(adoption.getId())
                                    );
                        } catch (Exception e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new MatchingRecommendationIdAcceptPut201Response()
                                            .success(false)
                                            .message("Adoption failed: " + e.getMessage())
                                    );
                        }
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MatchingRecommendationIdAcceptPut201Response()
                                .success(false)
                                .message("Recommendation not found" + id)
                        ));
    }

    @Override
    public ResponseEntity<ModelApiResponse> matchingRecommendationIdRejectPut(String id) {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.ADOPTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ModelApiResponse()
                            .success(false)
                            .message("User is not an adopter account")
                    );
        }
        return recommendationRepository.findById(id)
                .map(r -> {
                    if(r.getStatus() != RecommendationStatus.NEW){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ModelApiResponse()
                                        .success(false)
                                        .message("Wrong recommendation status: " + r.getStatus())
                                );
                    } else {
                        r.setStatus(RecommendationStatus.REJECTED);
                        recommendationRepository.save(r);
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ModelApiResponse()
                                        .success(true)
                                        .message("Recommendation rejected")
                                );
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ModelApiResponse()
                                .success(false)
                                .message("Recommendation not found" + id)
                        ));
    }

    @Override
    public ResponseEntity<MatchingRecommendationNextGet200Response> matchingRecommendationNextGet() {
        User user = userService.getUserFromContext();
        if(user.getAccountType() != Role.ADOPTER){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MatchingRecommendationNextGet200Response()
                            .success(false)
                            .message("User is not an adopter account")
                    );
        }
        return recommendationService.getNextRecommendation(user)
                .stream()
                .findFirst()
                .map(r -> ResponseEntity.status(HttpStatus.OK)
                        .body(new MatchingRecommendationNextGet200Response()
                                .success(true)
                                .message("User recommendations")
                                .payload(
                                        new Recommendation()
                                                .id(r.getId())
                                                .petId(r.getPetId())
                                                .date(r.getDate())
                                                .status(r.getStatus())
                                )
                        )
                ).orElse(ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new MatchingRecommendationNextGet200Response()
                                .success(true)
                                .message("Nothing to recommend")
                        )
                );
    }

    @Override
    public ResponseEntity<MatchingRecommendationsGet200Response> matchingRecommendationsGet() {
        return null;
    }
}
