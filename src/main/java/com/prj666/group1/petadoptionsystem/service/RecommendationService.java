package com.prj666.group1.petadoptionsystem.service;

import com.prj666.group1.petadoptionsystem.model.Recommendation;
import com.prj666.group1.petadoptionsystem.model.RecommendationList;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.repository.PetRepository;
import com.prj666.group1.petadoptionsystem.repository.RecommendationListRepository;
import com.prj666.group1.petadoptionsystem.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private RecommendationListRepository recommendationListRepository;

    @Autowired
    private PetRepository petRepository;

    public Optional<Recommendation> getRecommendation(User user){

        Optional<RecommendationList> currentList = recommendationListRepository.findByUserId(user.getId());
        if(currentList.isPresent()){
            List<Recommendation> recommend = recommendationRepository
                    .findByRecommendationListId(currentList.get().getId())
                    .stream().filter(r -> r.getStatus().equals("NEW"))
                    .toList();
            if(!recommend.isEmpty()){
                return Optional.of(recommend.getFirst());
            } else {
                recommendationListRepository.delete(currentList.get());
            }
        }
        RecommendationList newList = createNewRecommendationList(user);
        List<Recommendation> recommend = recommendationRepository.findByRecommendationListId(newList.getId());
        if(!recommend.isEmpty()){
            return Optional.of(recommend.getFirst());
        } else {
            return Optional.empty();
        }
    }

    private RecommendationList createNewRecommendationList(User user){
        List<Recommendation> pastRec = recommendationRepository.findByUserId(user.getId());
        Set<String> alreadyConsideredPets = pastRec.stream()
                .filter(r -> r.getStatus().equals("ACCEPTED") || r.getStatus().equals("REJECTED"))
                .map(Recommendation::getPetId)
                .collect(Collectors.toSet());

        RecommendationList newList = new RecommendationList(user.getId(), LocalDate.now());
        recommendationListRepository.save(newList);

        petRepository.findAll().stream().filter(p -> !alreadyConsideredPets.contains(p.getId()))
                .map(p -> new Recommendation(newList.getId(), p.getId(), user.getId(), LocalDate.now(), "NEW"))
                .forEach(recommendationRepository::save);

        return newList;
    }
}
