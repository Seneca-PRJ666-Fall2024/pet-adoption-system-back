package com.prj666.group1.petadoptionsystem.service;

import com.prj666.group1.petadoptionsystem.dto.AdoptionStatus;
import com.prj666.group1.petadoptionsystem.dto.RecommendationStatus;
import com.prj666.group1.petadoptionsystem.dto.Role;
import com.prj666.group1.petadoptionsystem.model.*;
import com.prj666.group1.petadoptionsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdoptionService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    public Adoption startAdoption(String recommendationId, Map<String, String> answers) {
        Optional<Recommendation> rec = recommendationRepository.findById(recommendationId);
        if(rec.isEmpty()){
            throw new IllegalArgumentException("Wrong recommendationId: " + recommendationId);
        }
        Recommendation recommendation = rec.get();
        if(recommendation.getStatus() != RecommendationStatus.ACCEPTED){
            throw new IllegalArgumentException("Wrong recommendation status: " + recommendation.getStatus());
        }
        Optional<User> user = userRepository.findById(recommendation.getUserId());
        if(user.isEmpty() || user.get().getAccountType() != Role.ADOPTER){
            throw new IllegalArgumentException("Wrong user id: " + recommendation.getUserId());
        }
        Optional<Pet> pet = petRepository.findById(recommendation.getPetId());
        if(pet.isEmpty()){
            throw new IllegalArgumentException("Wrong pet id: " + recommendation.getPetId());
        }
        if(!adoptionRepository.findByRecommendationId(recommendationId).isEmpty()){
            throw new IllegalArgumentException("User has already started adoption for this recommendation: " + recommendationId);
        }
        if(adoptionRepository.findByPetId(pet.get().getId())
                .stream()
                .anyMatch(adoption -> adoption.getStatus() == AdoptionStatus.ADOPTED)){
            throw new IllegalArgumentException("This pet has already been adopted: " + recommendation.getPetId());
        }
        if(adoptionRepository.findByUserId(recommendation.getUserId())
                .stream()
                .anyMatch(adoption -> adoption.getPetId().equals(recommendation.getPetId()))){
            throw new IllegalArgumentException("User already have adoption for this pet: " + recommendation.getPetId());
        }
        Adoption newAdoption = new Adoption();
        newAdoption.setRecommendationId(recommendationId);
        newAdoption.setUserId(recommendation.getUserId());
        newAdoption.setPetId(recommendation.getPetId());
        newAdoption.setShelterUserId(pet.get().getShelterUserId());
        newAdoption.setStatus(AdoptionStatus.SUBMITTED);
        newAdoption.setDate(LocalDate.now());
        newAdoption.setAnswers(answers);
        adoptionRepository.save(newAdoption);
        return newAdoption;
    }
}
