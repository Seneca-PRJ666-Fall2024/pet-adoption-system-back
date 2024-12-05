package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.prj666.group1.petadoptionsystem.dto.AdoptionStoryGet200ResponseAllOfPayloadInner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AdoptionStoryGet200Response
 */

@JsonTypeName("_adoption_story_get_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-04T19:14:42.596594700-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class AdoptionStoryGet200Response {

  private Boolean success;

  private String message;

  @Valid
  private List<@Valid AdoptionStoryGet200ResponseAllOfPayloadInner> payload = new ArrayList<>();

  public AdoptionStoryGet200Response success(Boolean success) {
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

  public AdoptionStoryGet200Response message(String message) {
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

  public AdoptionStoryGet200Response payload(List<@Valid AdoptionStoryGet200ResponseAllOfPayloadInner> payload) {
    this.payload = payload;
    return this;
  }

  public AdoptionStoryGet200Response addPayloadItem(AdoptionStoryGet200ResponseAllOfPayloadInner payloadItem) {
    if (this.payload == null) {
      this.payload = new ArrayList<>();
    }
    this.payload.add(payloadItem);
    return this;
  }

  /**
   * Get payload
   * @return payload
   */
  @Valid 
  @Schema(name = "payload", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("payload")
  public List<@Valid AdoptionStoryGet200ResponseAllOfPayloadInner> getPayload() {
    return payload;
  }

  public void setPayload(List<@Valid AdoptionStoryGet200ResponseAllOfPayloadInner> payload) {
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
    AdoptionStoryGet200Response adoptionStoryGet200Response = (AdoptionStoryGet200Response) o;
    return Objects.equals(this.success, adoptionStoryGet200Response.success) &&
        Objects.equals(this.message, adoptionStoryGet200Response.message) &&
        Objects.equals(this.payload, adoptionStoryGet200Response.payload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, payload);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdoptionStoryGet200Response {\n");
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

