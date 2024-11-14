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
 * ShelterAddPetPostRequest
 */

@JsonTypeName("_shelter_add_pet_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-06T19:39:54.912418600-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class ShelterAddPetPostRequest {

  private String name;

  private String comments;

  public ShelterAddPetPostRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "Buddy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ShelterAddPetPostRequest comments(String comments) {
    this.comments = comments;
    return this;
  }

  /**
   * Get comments
   * @return comments
   */
  
  @Schema(name = "comments", example = "Friendly and playful", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("comments")
  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShelterAddPetPostRequest shelterAddPetPostRequest = (ShelterAddPetPostRequest) o;
    return Objects.equals(this.name, shelterAddPetPostRequest.name) &&
        Objects.equals(this.comments, shelterAddPetPostRequest.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ShelterAddPetPostRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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

