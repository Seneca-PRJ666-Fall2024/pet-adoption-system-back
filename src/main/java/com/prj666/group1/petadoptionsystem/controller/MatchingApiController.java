package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.MatchingApi;
import com.prj666.group1.petadoptionsystem.dto.Recommendation;
import com.prj666.group1.petadoptionsystem.dto.SuccessApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MatchingApiController implements MatchingApi {
    @Override
    public ResponseEntity<SuccessApiResponse> matchingRecommendationIdAcceptPost(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Recommendation> matchingRecommendationNextGet() {
        return null;
    }

    @Override
    public ResponseEntity<List<Recommendation>> matchingRecommendationsGet() {
        return null;
    }
}
