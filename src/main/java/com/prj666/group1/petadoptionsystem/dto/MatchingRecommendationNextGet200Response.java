package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.prj666.group1.petadoptionsystem.dto.Recommendation;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MatchingRecommendationNextGet200Response
 */

@JsonTypeName("_matching_recommendation_next_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-06T17:56:29.361081200-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class MatchingRecommendationNextGet200Response {

  private Boolean success;

  private String message;

  private Recommendation payload;

  public MatchingRecommendationNextGet200Response success(Boolean success) {
    this.success = success;
    return this;
  }

  /**
   * Get success
   * @return success
   */
  
  @Schema(name = "success", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("success")
  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public MatchingRecommendationNextGet200Response message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @Schema(name = "message", example = "Operation completed successfully", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public MatchingRecommendationNextGet200Response payload(Recommendation payload) {
    this.payload = payload;
    return this;
  }

  /**
   * Get payload
   * @return payload
   */
  @Valid 
  @Schema(name = "payload", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("payload")
  public Recommendation getPayload() {
    return payload;
  }

  public void setPayload(Recommendation payload) {
    this.payload = payload;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchingRecommendationNextGet200Response matchingRecommendationNextGet200Response = (MatchingRecommendationNextGet200Response) o;
    return Objects.equals(this.success, matchingRecommendationNextGet200Response.success) &&
        Objects.equals(this.message, matchingRecommendationNextGet200Response.message) &&
        Objects.equals(this.payload, matchingRecommendationNextGet200Response.payload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, payload);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatchingRecommendationNextGet200Response {\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    payload: ").append(toIndentedString(payload)).append("\n");
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

