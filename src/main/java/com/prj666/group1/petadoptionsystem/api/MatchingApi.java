/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.7.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.prj666.group1.petadoptionsystem.api;

import com.prj666.group1.petadoptionsystem.dto.Recommendation;
import com.prj666.group1.petadoptionsystem.dto.SuccessApiResponse;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-30T19:00:36.896359300-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "matching", description = "Operations about Adopters")
@RequestMapping("${openapi.petAdoptionSystem.base-path:/api/v4}")
public interface MatchingApi {

    /**
     * POST /matching/recommendation/{id}/accept : Accept a recommendation and create an adoption
     *
     * @param id The ID of the recommendation to accept (required)
     * @return Recommendation accepted, adoption created (status code 201)
     */
    @Operation(
        operationId = "matchingRecommendationIdAcceptPost",
        summary = "Accept a recommendation and create an adoption",
        tags = { "matching" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Recommendation accepted, adoption created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessApiResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/matching/recommendation/{id}/accept",
        produces = { "application/json" }
    )
    
    ResponseEntity<SuccessApiResponse> matchingRecommendationIdAcceptPost(
        @Parameter(name = "id", description = "The ID of the recommendation to accept", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    );


    /**
     * GET /matching/recommendation/next : Get the next recommendation for the adopter
     *
     * @return The next recommendation item (status code 200)
     */
    @Operation(
        operationId = "matchingRecommendationNextGet",
        summary = "Get the next recommendation for the adopter",
        tags = { "matching" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The next recommendation item", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Recommendation.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/matching/recommendation/next",
        produces = { "application/json" }
    )
    
    ResponseEntity<Recommendation> matchingRecommendationNextGet(
        
    );


    /**
     * GET /matching/recommendations : Get a list of recommended pets based on preferences
     *
     * @return A list of recommended pets (status code 200)
     */
    @Operation(
        operationId = "matchingRecommendationsGet",
        summary = "Get a list of recommended pets based on preferences",
        tags = { "matching" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of recommended pets", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Recommendation.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/matching/recommendations",
        produces = { "application/json" }
    )
    
    ResponseEntity<List<Recommendation>> matchingRecommendationsGet(
        
    );

}
