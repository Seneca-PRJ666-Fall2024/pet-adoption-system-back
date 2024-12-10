package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
/**
 * AdoptionPostRequest
 */

@JsonTypeName("_adoption_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-10T04:35:15.846336200-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class AdoptionPostRequest {

  private String recommendationId;

  public AdoptionPostRequest recommendationId(String recommendationId) {
    this.recommendationId = recommendationId;
    return this;
  }

  /**
   * Get recommendationId
   * @return recommendationId
   */
  
  @Schema(name = "recommendationId", example = "0adf23ff...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("recommendationId")
  public String getRecommendationId() {
    return recommendationId;
  }

  public void setRecommendationId(String recommendationId) {
    this.recommendationId = recommendationId;
  }
    /**
    * A container for additional, undeclared properties.
    * This is a holder for any undeclared properties as specified with
    * the 'additionalProperties' keyword in the OAS document.
    */
    private Map<String, String> additionalProperties;

    /**
    * Set the additional (undeclared) property with the specified name and value.
    * If the property does not already exist, create it otherwise replace it.
    */
    @JsonAnySetter
    public AdoptionPostRequest putAdditionalProperty(String key, String value) {
        if (this.additionalProperties == null) {
            this.additionalProperties = new HashMap<String, String>();
        }
        this.additionalProperties.put(key, value);
        return this;
    }

    /**
    * Return the additional (undeclared) property.
    */
    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    /**
    * Return the additional (undeclared) property with the specified name.
    */
    public String getAdditionalProperty(String key) {
        if (this.additionalProperties == null) {
            return null;
        }
        return this.additionalProperties.get(key);
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdoptionPostRequest adoptionPostRequest = (AdoptionPostRequest) o;
    return Objects.equals(this.recommendationId, adoptionPostRequest.recommendationId) &&
    Objects.equals(this.additionalProperties, adoptionPostRequest.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recommendationId, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdoptionPostRequest {\n");
    sb.append("    recommendationId: ").append(toIndentedString(recommendationId)).append("\n");
    
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

