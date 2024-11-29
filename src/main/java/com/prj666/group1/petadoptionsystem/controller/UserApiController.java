package com.prj666.group1.petadoptionsystem.controller;

import com.prj666.group1.petadoptionsystem.api.UserApi;
import com.prj666.group1.petadoptionsystem.dto.*;
import com.prj666.group1.petadoptionsystem.model.Attribute;
import com.prj666.group1.petadoptionsystem.model.AttributeGroup;
import com.prj666.group1.petadoptionsystem.model.User;
import com.prj666.group1.petadoptionsystem.service.AttributeService;
import com.prj666.group1.petadoptionsystem.service.ImageService;
import com.prj666.group1.petadoptionsystem.service.UserService;
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
        Optional<User> user = userService.login(userLoginPostRequest.getEmail(), userLoginPostRequest.getPassword());

        UserLoginPost200Response resp = new UserLoginPost200Response();

        if (user.isPresent()) {
            // Successful login
            return ResponseEntity.ok(resp.success(true)
                    .message("Login successful")
                    .role(user.get().getAccountType().getValue())
                    .token(user.get().getToken()));
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
        newUser.setPassword(userRegisterPostRequest.getPassword());
        newUser.setAccountType(userRegisterPostRequest.getRole());
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

        user.setAddress(userUpdateProfilePutRequest.getAddress());
        user.setPhone(userUpdateProfilePutRequest.getPhone());
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

        ArrayList<String> errors = new ArrayList<>();

        List<Attribute> preferences = requestBody.entrySet().stream().map(a -> {
                    Optional<AttributeGroup> attributeGroup = attributeService.getAttributeGroupByName(a.getKey());
                    if(attributeGroup.isEmpty()) {
                        errors.add("Unknown attribute group: " + a.getKey());
                        return Collections.<Attribute>emptyList();
                    }
                    AttributeGroup group = attributeGroup.get();
                    List<String> values = a.getValue();
                    if(values == null || values.size() > 1 && !group.isMultivalued()){
                        errors.add("Wrong attribute group value: " +
                                a.getKey() + " : " + values);
                        return Collections.<Attribute>emptyList();
                    }

                    Map<String, Attribute> attributes = attributeService.getAttributesByGroup(group.getId())
                            .stream().collect(Collectors.toMap(Attribute::getName, attr -> attr));

                    Map<String, Attribute> selectedAttributes = values.stream()
                            .collect(Collectors.toMap(attrName -> attrName, attributes::get));
                    List<String> badAttributes = selectedAttributes.entrySet().stream()
                            .filter(e -> e.getValue() == null)
                            .map(Map.Entry::getKey)
                            .toList();
                    if(!badAttributes.isEmpty()){
                        errors.add("Unrecognized attribute group values: " +
                                a.getKey() + " : " + badAttributes);
                        return Collections.<Attribute>emptyList();
                    }
                    return selectedAttributes.values();
                }).filter(e -> !e.isEmpty())
                .flatMap(Collection::stream)
                .toList();

        if(!errors.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SuccessApiResponse().success(false).message("Errors: " + errors));
        } else {
            userService.updateUserPreferences(user, preferences);
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
    public ResponseEntity<com.prj666.group1.petadoptionsystem.dto.User> userAddProfilePost(com.prj666.group1.petadoptionsystem.dto.User user) {
        return null;
    }

    @Override
    public ResponseEntity<SuccessApiResponse> userDeleteProfileEmailDelete(String email) {
        return null;
    }
}
