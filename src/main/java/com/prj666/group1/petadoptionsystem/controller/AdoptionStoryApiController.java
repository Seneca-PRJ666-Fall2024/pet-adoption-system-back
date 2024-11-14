package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.AdoptionStoryApi;
import com.prj666.group1.petadoptionsystem.dto.AdoptionStoryGet200ResponseInner;
import com.prj666.group1.petadoptionsystem.repository.AdoptionStoryRepository;
import com.prj666.group1.petadoptionsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdoptionStoryApiController implements AdoptionStoryApi {

    @Autowired
    private AdoptionStoryRepository userRepository;

    @Override
    public ResponseEntity<List<AdoptionStoryGet200ResponseInner>> adoptionStoryGet(Integer N) {
        List<AdoptionStoryGet200ResponseInner> stories = userRepository.findRandomStories(N).stream()
                .map(s ->
                    new AdoptionStoryGet200ResponseInner()
                            .author(s.getAuthor())
                            .title(s.getTitle())
                            .text(s.getStory())
                ).toList();

        return ResponseEntity.status(HttpStatus.OK).body(stories);
    }
}
