package com.prj666.group1.petadoptionsystem.mappers;

import com.prj666.group1.petadoptionsystem.dto.Role;
import com.prj666.group1.petadoptionsystem.model.*;
import com.prj666.group1.petadoptionsystem.repository.*;
import com.prj666.group1.petadoptionsystem.service.AttributeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ModelToDtoMapper {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeGroupRepository attributeGroupRepository;

    public List<com.prj666.group1.petadoptionsystem.dto.Recommendation> mapRecommendations(List<Recommendation> recommendations) {
        Set<String> petIds = recommendations.stream().map(Recommendation::getPetId).collect(Collectors.toSet());
        Map<String, com.prj666.group1.petadoptionsystem.dto.Pet> pets = mapPets(petRepository.findAllById(petIds))
                .stream()
                .collect(
                        Collectors.toMap(com.prj666.group1.petadoptionsystem.dto.Pet::getPetId, a -> a)
                );
        return recommendations.stream()
                .map(rec -> new com.prj666.group1.petadoptionsystem.dto.Recommendation()
                        .id(rec.getId())
                        .petId(rec.getPetId())
                        .date(rec.getDate())
                        .status(rec.getStatus())
                        .pet(pets.get(rec.getPetId()))
                ).toList();
    }

    public List<com.prj666.group1.petadoptionsystem.dto.Pet> mapPets(List<Pet> pets) {
        Map<String,Map<String, String>> petAttributes = attributeService.getPetAttributes(pets);
        return pets.stream().map(pet -> {
                    com.prj666.group1.petadoptionsystem.dto.Pet petDto = new com.prj666.group1.petadoptionsystem.dto.Pet()
                            .shelterUserId(pet.getShelterUserId())
                            .petId(pet.getId())
                            .petName(pet.getName())
                            .status(pet.getStatus())
                            .imageUrl( pet.getImageUrl() != null ? pet.getImageUrl() :
                                    pet.getImages() != null && !pet.getImages().isEmpty() ?
                                            pet.getImages().getFirst() : null
                            );
                    petAttributes.get(pet.getId()).forEach(petDto::putAdditionalProperty);
                    return petDto;
                }).toList();
    }

    public List<com.prj666.group1.petadoptionsystem.dto.User> mapUsers(List<User> users) {

        List<Preference> allUserPrefs = preferenceRepository.findAllByUserId(users.stream().map(User::getId).toList());

        Set<String> attributes = allUserPrefs.stream().map(Preference::getAttributeId)
                .collect(Collectors.toSet());

        Map<String, Attribute> attributeMap = attributeRepository.findAllById(attributes)
                .stream()
                .collect(Collectors.toMap(Attribute::getId, a -> a));

        Set<String> attrGroups = attributeMap.values().stream().map(Attribute::getAttributeGroupId)
                .collect(Collectors.toSet());

        Map<String, AttributeGroup> attributeGroupMap = attributeGroupRepository.findAllById(attrGroups)
                .stream()
                .collect(Collectors.toMap(AttributeGroup::getId, a -> a));

        Map<String, Map<String, List<String>>> userPrefs = allUserPrefs.stream()
                .collect(Collectors.groupingBy(Preference::getUserId))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, a ->
                    a.getValue()
                            .stream()
                            .map(attr -> attributeMap.get(attr.getAttributeId()))
                            .filter(Objects::nonNull)
                            .collect(Collectors.groupingBy(Attribute::getAttributeGroupId))
                            .entrySet()
                            .stream()
                            .collect(Collectors.toMap(
                                    ag -> attributeGroupMap.get(ag.getKey()).getName(),
                                    ag -> ag.getValue().stream().map(Attribute::getName).toList()
                            ))
                ));


        return users.stream().map(u -> new com.prj666.group1.petadoptionsystem.dto.User()
                    .userId(u.getId())
                    .username(u.getName())
                    .imageUrl(u.getImageUrl())
                    .email(u.getEmail())
                    .phone(u.getPhone())
                    .address(u.getAddress())
                    .city(u.getCity())
                    .province(u.getProvince())
                    .postalCode(u.getPostalCode())
                    .role(u.getAccountType())
                        .preferences(userPrefs.get(u.getId()))
                ).toList();
    }

    public List<com.prj666.group1.petadoptionsystem.dto.Adoption> mapAdoptions(List<Adoption> adoptions) {
        Set<String> adopterIds = adoptions.stream().map(Adoption::getUserId).collect(Collectors.toSet());
        Map<String, com.prj666.group1.petadoptionsystem.dto.User> adopters = mapUsers(userRepository.findAllById(adopterIds))
                .stream()
                .collect(
                        Collectors.toMap(com.prj666.group1.petadoptionsystem.dto.User::getUserId, a -> a)
                );

        Set<String> petIds = adoptions.stream().map(Adoption::getPetId).collect(Collectors.toSet());
        Map<String, com.prj666.group1.petadoptionsystem.dto.Pet> pets = mapPets(petRepository.findAllById(petIds))
                .stream()
                .collect(
                        Collectors.toMap(com.prj666.group1.petadoptionsystem.dto.Pet::getPetId, a -> a)
                );

        return adoptions.stream()
                .map(a -> {
                    com.prj666.group1.petadoptionsystem.dto.Adoption adoption = new com.prj666.group1.petadoptionsystem.dto.Adoption()
                                    .adoptionId(a.getId())
                                    .adopterId(a.getUserId())
                                    .shelterUserId(a.getShelterUserId())
                                    .date(a.getDate())
                                    .status(a.getStatus())
                                    .pet(pets.get(a.getPetId()))
                                    .adopter(adopters.get(a.getUserId()));
                    a.getAnswers().forEach(adoption::putAdditionalProperty);
                    return adoption;
                }).toList();
    }

    public List<com.prj666.group1.petadoptionsystem.dto.AttributeGroup> mapAttributeGroups(User user, List<AttributeGroup> attributeGroups) {
        Map<String, List<String>> attributes =  attributeRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Attribute::getAttributeGroupId))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        a -> a.getValue()
                                .stream()
                                .map(Attribute::getName).collect(Collectors.toList())
                ));
        return attributeGroups.stream()
                .map(a ->
                        new com.prj666.group1.petadoptionsystem.dto.AttributeGroup()
                                .name(a.getName())
                                .description(a.getDescription())
                                .question(user.getAccountType() == Role.SHELTER ? a.getShelterQuestion() : a.getAdopterQuestion())
                                .supportsOther(a.isSupportsOther())
                                .values(attributes.get(a.getId()))
                        ).toList();
    }
}
