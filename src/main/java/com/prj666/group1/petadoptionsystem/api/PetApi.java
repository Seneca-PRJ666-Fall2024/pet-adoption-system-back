/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.7.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.prj666.group1.petadoptionsystem.api;

import com.prj666.group1.petadoptionsystem.dto.ModelApiResponse;
import com.prj666.group1.petadoptionsystem.dto.PetAddProfilePostRequest;
import com.prj666.group1.petadoptionsystem.dto.PetGetProfilePetIdGet200Response;
import com.prj666.group1.petadoptionsystem.dto.PetUpdateProfilePutRequest;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-02T01:42:56.763233900-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "pet", description = "Operations about Pets")
@RequestMapping("${openapi.petAdoptionSystem.base-path:}")
public interface PetApi {

    /**
     * POST /pet/add-profile : Add a new pet profile to the system
     * Add a new pet profile to the system.
     *
     * @param petAddProfilePostRequest  (required)
     * @return Generic API response (status code 201)
     *         or Generic API response (status code 405)
     */
    @Operation(
        operationId = "petAddProfilePost",
        summary = "Add a new pet profile to the system",
        description = "Add a new pet profile to the system.",
        tags = { "pet" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            }),
            @ApiResponse(responseCode = "405", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            })
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/pet/add-profile",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<ModelApiResponse> petAddProfilePost(
        @Parameter(name = "PetAddProfilePostRequest", description = "", required = true) @Valid @RequestBody PetAddProfilePostRequest petAddProfilePostRequest
    );


    /**
     * DELETE /pet/delete-profile/{petId} : Deletes a pet profile
     * Deletes the pet profile with the specified ID.
     *
     * @param petId The unique ID of the pet profile to delete. (required)
     * @return Generic API response (status code 204)
     *         or Generic API response (status code 400)
     *         or Generic API response (status code 401)
     *         or Generic API response (status code 403)
     *         or Generic API response (status code 404)
     */
    @Operation(
        operationId = "petDeleteProfilePetIdDelete",
        summary = "Deletes a pet profile",
        description = "Deletes the pet profile with the specified ID.",
        tags = { "pet" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            }),
            @ApiResponse(responseCode = "403", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            })
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/pet/delete-profile/{petId}",
        produces = { "application/json" }
    )
    
    ResponseEntity<ModelApiResponse> petDeleteProfilePetIdDelete(
        @Parameter(name = "petId", description = "The unique ID of the pet profile to delete.", required = true, in = ParameterIn.PATH) @PathVariable("petId") String petId
    );


    /**
     * GET /pet/get-profile/{petId} : Retrieve pet profile information
     * Fetches the pet profile details.
     *
     * @param petId The unique ID of the pet profile to delete. (required)
     * @return Profile details retrieved successfully (status code 200)
     *         or Generic API response (status code 401)
     */
    @Operation(
        operationId = "petGetProfilePetIdGet",
        summary = "Retrieve pet profile information",
        description = "Fetches the pet profile details.",
        tags = { "pet" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Profile details retrieved successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PetGetProfilePetIdGet200Response.class))
            }),
            @ApiResponse(responseCode = "401", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            })
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/pet/get-profile/{petId}",
        produces = { "application/json" }
    )
    
    ResponseEntity<PetGetProfilePetIdGet200Response> petGetProfilePetIdGet(
        @Parameter(name = "petId", description = "The unique ID of the pet profile to delete.", required = true, in = ParameterIn.PATH) @PathVariable("petId") String petId
    );


    /**
     * PUT /pet/update-profile : Update pet profile
     *
     * @param petUpdateProfilePutRequest  (required)
     * @return Generic API response (status code 200)
     */
    @Operation(
        operationId = "petUpdateProfilePut",
        summary = "Update pet profile",
        tags = { "pet" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            })
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/pet/update-profile",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    ResponseEntity<ModelApiResponse> petUpdateProfilePut(
        @Parameter(name = "PetUpdateProfilePutRequest", description = "", required = true) @Valid @RequestBody PetUpdateProfilePutRequest petUpdateProfilePutRequest
    );


    /**
     * POST /pet/upload-image : Upload an image for a pet
     * Uploads an image file for a specific pet.
     *
     * @param petId  (required)
     * @param image  (required)
     * @return Generic API response (status code 200)
     *         or Generic API response (status code 400)
     */
    @Operation(
        operationId = "petUploadImagePost",
        summary = "Upload an image for a pet",
        description = "Uploads an image file for a specific pet.",
        tags = { "pet" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Generic API response", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ModelApiResponse.class))
            })
        },
        security = {
            @SecurityRequirement(name = "BearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/pet/upload-image",
        produces = { "application/json" },
        consumes = { "multipart/form-data" }
    )
    
    ResponseEntity<ModelApiResponse> petUploadImagePost(
        @Parameter(name = "petId", description = "", required = true) @Valid @RequestParam(value = "petId", required = true) String petId,
        @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image
    );

}
