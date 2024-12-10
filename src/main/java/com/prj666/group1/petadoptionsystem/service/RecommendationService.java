package com.prj666.group1.petadoptionsystem.service;

import com.prj666.group1.petadoptionsystem.dto.RecommendationStatus;
import com.prj666.group1.petadoptionsystem.model.Recommendation;
import com.prj666.group1.petadoptionsystem.model.RecommendationList;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.repository.PetRepository;
import com.prj666.group1.petadoptionsystem.repository.RecommendationListRepository;
import com.prj666.group1.petadoptionsystem.repository.RecommendationRepository;
import com.prj666.group1.petadoptionsystem.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
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

    @Autowired
    private UserRepository userRepository;

    public List<Recommendation> getNextRecommendation(User user, int number){

        if(StringUtils.isNotBlank(user.getRecommendationList())){
            Optional<RecommendationList> list = recommendationListRepository.findById(user.getRecommendationList());
            if(list.isPresent()){
                List<Recommendation> recommend = recommendationRepository
                        .findByRecommendationListId(list.get().getId())
                        .stream().filter(r -> r.getStatus() == RecommendationStatus.NEW)
                        .limit(number)
                        .toList();
                if(!recommend.isEmpty()){
                    return recommend;
                } else {
                    recommendationListRepository.delete(list.get());
                }
            }

        }
        RecommendationList newList = createNewRecommendationList(user);
        return recommendationRepository.findByRecommendationListId(newList.getId())
                .stream()
                .limit(number)
                .toList();
    }

    private RecommendationList createNewRecommendationList(User user){
        List<Recommendation> pastRec = recommendationRepository.findByUserId(user.getId());
        Set<String> alreadyConsideredPets = pastRec.stream()
                .filter(r -> r.getStatus() == RecommendationStatus.ACCEPTED || r.getStatus() == RecommendationStatus.REJECTED)
                .map(Recommendation::getPetId)
                .collect(Collectors.toSet());

        recommendationListRepository.findByUserId(user.getId())
                .forEach(l -> {
                    recommendationListRepository.delete(l);
                    recommendationRepository
                            .deleteAll(recommendationRepository.findByRecommendationListId(l.getId())
                                    .stream()
                                    .filter(r -> r.getStatus() == RecommendationStatus.NEW)
                                    .toList()
                            );

                });

        RecommendationList newList = new RecommendationList(user.getId(), LocalDate.now());
        recommendationListRepository.save(newList);

        user.setRecommendationList(newList.getId());
        userRepository.save(user);

        petRepository.findAll().stream().filter(p -> !alreadyConsideredPets.contains(p.getId()))
                .limit(5)
                .map(p -> new Recommendation(newList.getId(), p.getId(), user.getId(), LocalDate.now(), RecommendationStatus.NEW))
                .forEach(recommendationRepository::save);

        return newList;
    }
}
