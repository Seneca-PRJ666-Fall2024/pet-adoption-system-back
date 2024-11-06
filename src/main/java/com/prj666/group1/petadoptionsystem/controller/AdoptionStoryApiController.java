package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.AdoptionStoryApi;
import com.prj666.group1.petadoptionsystem.dto.AdoptionStoryGet200ResponseInner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdoptionStoryApiController implements AdoptionStoryApi {
    @Override
    public ResponseEntity<List<AdoptionStoryGet200ResponseInner>> adoptionStoryGet(Integer N) {
        return null;
    }
}
