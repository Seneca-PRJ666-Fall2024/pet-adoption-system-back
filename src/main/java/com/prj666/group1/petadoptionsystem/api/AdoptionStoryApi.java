/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.7.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.prj666.group1.petadoptionsystem.api;

import com.prj666.group1.petadoptionsystem.dto.AdoptionStoryGet200ResponseInner;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-06T19:39:54.912418600-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
@Validated
@Tag(name = "adoptionStory", description = "the adoptionStory API")
@RequestMapping("${openapi.petAdoptionSystem.base-path:/api/v2}")
public interface AdoptionStoryApi {

    /**
     * GET /adoptionStory : Retrieve a list of adoption stories
     * Returns a list of adoption stories with the specified number of objects.
     *
     * @param N The number of adoption story objects to return (optional, default to 5)
     * @return A list of adoption stories (status code 200)
     */
    @Operation(
        operationId = "adoptionStoryGet",
        summary = "Retrieve a list of adoption stories",
        description = "Returns a list of adoption stories with the specified number of objects.",
        responses = {
            @ApiResponse(responseCode = "200", description = "A list of adoption stories", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AdoptionStoryGet200ResponseInner.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/adoptionStory",
        produces = { "application/json" }
    )
    
    ResponseEntity<List<AdoptionStoryGet200ResponseInner>> adoptionStoryGet(
        @Min(1) @Parameter(name = "N", description = "The number of adoption story objects to return", in = ParameterIn.QUERY) @Valid @RequestParam(value = "N", required = false, defaultValue = "5") Integer N
    );

}
