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

/**
 * AdopterPreferencesPostRequest
 */

@JsonTypeName("_adopter_preferences_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-31T17:36:43.291576-04:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class AdopterPreferencesPostRequest {

  private Integer attributeId;

  private Integer weight;

  public AdopterPreferencesPostRequest attributeId(Integer attributeId) {
    this.attributeId = attributeId;
    return this;
  }

  /**
   * Get attributeId
   * @return attributeId
   */
  
  @Schema(name = "attributeId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attributeId")
  public Integer getAttributeId() {
    return attributeId;
  }

  public void setAttributeId(Integer attributeId) {
    this.attributeId = attributeId;
  }

  public AdopterPreferencesPostRequest weight(Integer weight) {
    this.weight = weight;
    return this;
  }

  /**
   * Get weight
   * @return weight
   */
  
  @Schema(name = "weight", example = "5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("weight")
  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdopterPreferencesPostRequest adopterPreferencesPostRequest = (AdopterPreferencesPostRequest) o;
    return Objects.equals(this.attributeId, adopterPreferencesPostRequest.attributeId) &&
        Objects.equals(this.weight, adopterPreferencesPostRequest.weight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributeId, weight);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdopterPreferencesPostRequest {\n");
    sb.append("    attributeId: ").append(toIndentedString(attributeId)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
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

