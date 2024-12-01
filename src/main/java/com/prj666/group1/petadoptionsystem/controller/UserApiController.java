package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.UserApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.model.Attribute;
import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.service.AttributeService;
import com.prj666.group1.petadoptionsystem.service.ImageService;
import com.prj666.group1.petadoptionsystem.service.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class UserApiController implements UserApi {

    @Autowired
    private UserService userService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ImageService imageService;

    @Override
    public ResponseEntity<UserLoginPost200Response> userLoginPost(UserLoginPostRequest userLoginPostRequest) {
        Optional<User> userOptional = userService.login(userLoginPostRequest.getEmail(), userLoginPostRequest.getPassword());

        UserLoginPost200Response resp = new UserLoginPost200Response();

        if (userOptional.isPresent()) {
            // Successful login
            User user = userOptional.get();
            return ResponseEntity.ok(resp.success(true)
                    .message("Login successful")
                    .role(user.getAccountType())
                    .profileSet(user.isProfileSet())
                    .token(user.getToken()));
        } else {
            // Failed login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(resp.success(false).message("Invalid username or password"));
        }
    }

    @Override
    public ResponseEntity<SuccessApiResponse> userRegisterPost(UserRegisterPostRequest userRegisterPostRequest) {
        // Check if the username already exists
        if (userService.existsByEmail(userRegisterPostRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SuccessApiResponse().success(false).message("Username is already taken"));
        }

        // Create and save a new user
        User newUser = new User();
        newUser.setEmail(userRegisterPostRequest.getEmail());
        newUser.setRawPassword(userRegisterPostRequest.getPassword());
        newUser.setAccountType(userRegisterPostRequest.getRole());
        newUser.setProfileSet(false);
        userService.saveUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessApiResponse().success(true).message("User registered successfully"));
    }

    @Override
    public ResponseEntity<SuccessApiResponse> userUpdateProfilePut(UserUpdateProfilePutRequest userUpdateProfilePutRequest) {

        User user;
        try {
            user = userService.getUserFromContext();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new SuccessApiResponse().success(false).message(e.getMessage()));
        }
        user.setName(userUpdateProfilePutRequest.getName());
        user.setAddress(userUpdateProfilePutRequest.getAddress());
        user.setPhone(userUpdateProfilePutRequest.getPhone());
        user.setProfileSet(true);
        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessApiResponse().success(true).message("User updated successfully"));
    }


    @Override
    public ResponseEntity<SuccessApiResponse> userPreferencesPost(Map<String, List<String>> requestBody) {
        User user = userService.getUserFromContext();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SuccessApiResponse().success(false).message("User not found"));
        }

        if(user.getAccountType() != Role.ADOPTER){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SuccessApiResponse().success(false).message("User is not an adopter"));
        }

        ArrayList<String> errors = new ArrayList<>();

        List<Attribute> preferences = requestBody.entrySet().stream().flatMap(a -> {
                    Optional<AttributeGroup> attributeGroup = attributeService.getAttributeGroupByName(a.getKey());
                    if(attributeGroup.isEmpty()) {
                        errors.add("Unknown attribute group: " + a.getKey());
                        return Stream.empty();
                    }
                    AttributeGroup group = attributeGroup.get();
                    List<String> values = a.getValue();
                    if(values == null || values.isEmpty()){
                        return Stream.empty();
                    }
                    if(values.size() > 1 && !group.isMultivalued()){
                        errors.add("Can't have more than one value for this attribute group: " +
                                a.getKey() + " : " + values);
                        return Stream.empty();
                    }

                    Map<String, Attribute> attributes = attributeService.getAttributesByGroup(group.getId())
                            .stream().collect(Collectors.toMap(Attribute::getName, attr -> attr));

                    Map<String, Attribute> selectedAttributes = values.stream()
                            .map(attr -> {
                                Attribute at = attributes.get(attr);
                                if(at == null){
                                    errors.add("Unrecognized attribute value: " + a.getKey() + " : " + attr);
                                }
                                return at;
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toMap(Attribute::getName, attr -> attr));
                    return selectedAttributes.values().stream();
                }).toList();

        if(!errors.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SuccessApiResponse().success(false).message("Errors: " + errors));
        } else {
            userService.updateUserPreferences(user, preferences);
            user.setProfileSet(true);
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessApiResponse().success(true).message("User preferences saved"));
        }
    }

    @Override
    public ResponseEntity<SuccessApiResponse> userUploadImagePost(MultipartFile image) {
        try {
            String filename = imageService.storeImage(image);
            String fileDownloadUri = "/images/" + filename;
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SuccessApiResponse().success(true).message("Image uploaded successfully: " + fileDownloadUri));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SuccessApiResponse().success(false).message("Could not upload the image: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<UserGetProfileGet200Response> userGetProfileGet() {
        return null;
    }

    @Override
    public ResponseEntity<SuccessApiResponse> userDeleteProfileEmailDelete(String email) {
        return null;
    }
}
