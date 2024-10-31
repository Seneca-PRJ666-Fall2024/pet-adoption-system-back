package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.AdopterApi;
import com.prj666.group1.petadoptionsystem.dto.AdopterPreferencesPostRequest;
import com.prj666.group1.petadoptionsystem.dto.ModelApiResponse;
import com.prj666.group1.petadoptionsystem.dto.Recommendation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdopterApiController implements AdopterApi {
    @Override
    public ResponseEntity<ModelApiResponse> adopterPreferencesPost(AdopterPreferencesPostRequest adopterPreferencesPostRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<Recommendation>> adopterRecommendationsGet() {
        return null;
    }
}
