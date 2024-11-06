/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.7.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.prj666.group1.petadoptionsystem.api;

import com.prj666.group1.petadoptionsystem.dto.ModelApiResponse;
import com.prj666.group1.petadoptionsystem.dto.UserLoginPost200Response;
import com.prj666.group1.petadoptionsystem.dto.UserLoginPostRequest;
import com.prj666.group1.petadoptionsystem.dto.UserRegisterPostRequest;
import com.prj666.group1.petadoptionsystem.dto.UserUpdateContactsPutRequest;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-06T16:27:58.972580500-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "user", description = "the user API")
@RequestMapping("${openapi.petAdoptionSystem.base-path:/api/v2}")
public interface UserApi {

    /**
     * POST /user/login : Login a user
     * Logs in a user using email and password, and returns a JWT token.
     *
     * @param userLoginPostRequest  (required)
     * @return Login successful (status code 200)
     *         or Invalid email or password (status code 401)
     */
    @Operation(
        operationId = "userLoginPost",
        summary = "Login a user",
        description = "Logs in a user using email and password, and returns a JWT token.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Login successful", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserLoginPost200Response.class))
            }),
            @ApiResponse(responseCode = "401", description = "Invalid email or password", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/user/login",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<UserLoginPost200Response> userLoginPost(
        @Parameter(name = "UserLoginPostRequest", description = "", required = true) @Valid @RequestBody UserLoginPostRequest userLoginPostRequest
    );


    /**
     * POST /user/register : Register a new user
     * Registers a new user with an email, password, and account type (Pet Adopter or Pet Shelter).
     *
     * @param userRegisterPostRequest  (required)
     * @return User registered successfully (status code 201)
     *         or Email is already taken (status code 400)
     */
    @Operation(
        operationId = "userRegisterPost",
        summary = "Register a new user",
        description = "Registers a new user with an email, password, and account type (Pet Adopter or Pet Shelter).",
        responses = {
            @ApiResponse(responseCode = "201", description = "User registered successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Email is already taken", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/user/register",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<ModelApiResponse> userRegisterPost(
        @Parameter(name = "UserRegisterPostRequest", description = "", required = true) @Valid @RequestBody UserRegisterPostRequest userRegisterPostRequest
    );


    /**
     * PUT /user/update-contacts : Update user contact information
     *
     * @param userUpdateContactsPutRequest  (required)
     * @return User contacts updated successfully (status code 200)
     */
    @Operation(
        operationId = "userUpdateContactsPut",
        summary = "Update user contact information",
        responses = {
            @ApiResponse(responseCode = "200", description = "User contacts updated successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/user/update-contacts",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<ModelApiResponse> userUpdateContactsPut(
        @Parameter(name = "UserUpdateContactsPutRequest", description = "", required = true) @Valid @RequestBody UserUpdateContactsPutRequest userUpdateContactsPutRequest
    );

}
