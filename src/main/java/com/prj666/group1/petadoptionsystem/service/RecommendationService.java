package com.prj666.group1.petadoptionsystem.service;

import com.prj666.group1.petadoptionsystem.dto.RecommendationStatus;
import com.prj666.group1.petadoptionsystem.model.*;
import com.prj666.group1.petadoptionsystem.repository.*;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private AttributeGroupRepository attributeGroupRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

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
                    recommendationRepository
                            .deleteAll(recommendationRepository.findByRecommendationListId(list.get().getId())
                                    .stream()
                                    .filter(r -> r.getStatus() == RecommendationStatus.NEW)
                                    .toList()
                            );
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
        List<String> attributeIdList = preferenceRepository.findByUserId(user.getId())
                .stream()
                .map(Preference::getAttributeId)
                .toList();

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

        Map<String, Set<String>> preferredAttributes = attributeRepository.findAllById(attributeIdList)
                .stream()
                .collect(Collectors.groupingBy(Attribute::getAttributeGroupId))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        gr -> gr.getValue().stream()
                                .map(Attribute::getId)
                                .collect(Collectors.toSet()))
                );

        Map<String, Set<String>> allAttributes = attributeRepository.findAll().stream()
                .collect(Collectors.groupingBy(Attribute::getAttributeGroupId))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        gr -> gr.getValue().stream()
                                .map(Attribute::getId)
                                .collect(Collectors.toSet()))
                );

        Set<String> blacklistedAttributes = preferredAttributes.entrySet()
                .stream()
                .flatMap(prefGroup -> {
                        Set<String> targetGroup =  allAttributes.getOrDefault(prefGroup.getKey(), new HashSet<>());
                        targetGroup.removeAll(prefGroup.getValue());
                        return targetGroup.stream();
                    }
                ).collect(Collectors.toSet());

        petRepository.findAll().stream()
                .filter(p -> !alreadyConsideredPets.contains(p.getId()))
                .filter(p -> p.getAttributes().stream()
                        .noneMatch(blacklistedAttributes::contains)
                )
                .limit(5)
                .map(p -> new Recommendation(newList.getId(), p.getId(), user.getId(), LocalDate.now(), RecommendationStatus.NEW))
                .forEach(recommendationRepository::save);

        return newList;
    }
}
