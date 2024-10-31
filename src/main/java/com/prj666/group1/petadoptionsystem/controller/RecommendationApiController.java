package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.RecommendationApi;
import com.prj666.group1.petadoptionsystem.dto.ModelApiResponse;
import com.prj666.group1.petadoptionsystem.dto.Recommendation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class RecommendationApiController implements RecommendationApi {
    @Override
    public ResponseEntity<ModelApiResponse> recommendationIdAcceptPost(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Recommendation> recommendationNextGet() {
        return null;
    }
}
