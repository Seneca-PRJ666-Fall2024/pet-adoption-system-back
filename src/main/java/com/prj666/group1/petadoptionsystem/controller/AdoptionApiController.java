package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.AdoptionApi;
import com.prj666.group1.petadoptionsystem.dto.AdoptionStatusGet200Response;
import com.prj666.group1.petadoptionsystem.dto.AdoptionStoryGet200Response;
import com.prj666.group1.petadoptionsystem.dto.AdoptionStoryGet200ResponseAllOfPayloadInner;
import com.prj666.group1.petadoptionsystem.repository.AdoptionStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AdoptionApiController implements AdoptionApi {

    @Autowired
    private AdoptionStoryRepository userRepository;

    @Override
    public ResponseEntity<AdoptionStatusGet200Response> adoptionStatusGet() {
        return null;
    }

    @Override
    public ResponseEntity<AdoptionStoryGet200Response> adoptionStoryGet(Integer N) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new AdoptionStoryGet200Response()
                        .success(true)
                        .message("Adoption stories read")
                        .payload(
                                userRepository.findRandomStories(N).stream()
                                        .map(s ->
                                                new AdoptionStoryGet200ResponseAllOfPayloadInner()
                                                        .author(s.getAuthor())
                                                        .title(s.getTitle())
                                                        .text(s.getStory())
                                        ).toList()
                        )
                );
    }
}
