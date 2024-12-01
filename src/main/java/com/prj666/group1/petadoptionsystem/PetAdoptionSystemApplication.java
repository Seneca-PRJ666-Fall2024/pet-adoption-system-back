package com.prj666.group1.petadoptionsystem;

import com.prj666.group1.petadoptionsystem.model.Attribute;
import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import com.prj666.group1.petadoptionsystem.repository.AttributeGroupRepository;
import com.prj666.group1.petadoptionsystem.repository.AttributeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class PetAdoptionSystemApplication {

	@Autowired
	private AttributeGroupRepository attributeGroupRepository;

	@Autowired
	private AttributeRepository attributeRepository;


	@PostConstruct
	public void init(){
		Set<String> existingGroups = attributeGroupRepository.findAll().stream().map(a -> a.getName())
				.collect(Collectors.toSet());

		Map<String, String> groupMap = new HashMap<>();

		String[] attrs = new String[]{
				"gender","petType","breedType","petColour","petSize","petActivityLevel","petEnvironment","petSocial"
		};

		for(String attr : attrs){
			if(!existingGroups.contains(attr)){
				AttributeGroup ag = new AttributeGroup(attr, false, false);
				attributeGroupRepository.save(ag);
				groupMap.put(attr, ag.getId());
			} else {
				groupMap.put(attr, attributeGroupRepository.findByName(attr).get().getId());
			}
		}
		Map<String, String[]> targetMap = new HashMap<>();
		targetMap.put("gender", new String[]{"male", "female"});
		targetMap.put("petType", new String[]{		"cat",
				"dog",
				"rabbit",
				"other"});
		targetMap.put("breedType", new String[]{"Poodle", "Chihuahua", "American Bobtail"});

		targetMap.put("petSize", new String[]{"small", "medium", "large"});
		targetMap.put("petActivityLevel", new String[]{		"very_active",
				"active",
				"quiet"});
		targetMap.put("petEnvironment", new String[]{		"apartment",
				"house",
				"farm_property"});
		targetMap.put("petSocial", new String[]{		"cats",
				"dogs",
				"all animals",
				"other animals"});

		for(Map.Entry<String, String[]> entry : targetMap.entrySet()){
			String groupId = groupMap.get(entry.getKey());
			Set<String> existingAttrs = attributeRepository.findByAttributeGroupId(groupId)
					.stream().map(a -> a.getName())
					.collect(Collectors.toSet());
			for(String attr : entry.getValue()){
				if(!existingAttrs.contains(attr)){
					Attribute a = new Attribute(attr, groupId);
					attributeRepository.save(a);
				}
			}
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(PetAdoptionSystemApplication.class, args);
	}

}
