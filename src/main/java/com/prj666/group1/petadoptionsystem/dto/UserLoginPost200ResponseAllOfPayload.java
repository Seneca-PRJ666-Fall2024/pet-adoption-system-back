package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.prj666.group1.petadoptionsystem.dto.Role;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UserLoginPost200ResponseAllOfPayload
 */

@JsonTypeName("_user_login_post_200_response_allOf_payload")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-11T16:53:22.577455-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class UserLoginPost200ResponseAllOfPayload {

  private String username;

  private Role role;

  private Boolean profileSet;

  private String token;

  public UserLoginPost200ResponseAllOfPayload username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   */
  
  @Schema(name = "username", example = "John Doe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserLoginPost200ResponseAllOfPayload role(Role role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
   */
  @Valid 
  @Schema(name = "role", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("role")
  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public UserLoginPost200ResponseAllOfPayload profileSet(Boolean profileSet) {
    this.profileSet = profileSet;
    return this;
  }

  /**
   * Get profileSet
   * @return profileSet
   */
  
  @Schema(name = "profileSet", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("profileSet")
  public Boolean getProfileSet() {
    return profileSet;
  }

  public void setProfileSet(Boolean profileSet) {
    this.profileSet = profileSet;
  }

  public UserLoginPost200ResponseAllOfPayload token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
   */
  
  @Schema(name = "token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("token")
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserLoginPost200ResponseAllOfPayload userLoginPost200ResponseAllOfPayload = (UserLoginPost200ResponseAllOfPayload) o;
    return Objects.equals(this.username, userLoginPost200ResponseAllOfPayload.username) &&
        Objects.equals(this.role, userLoginPost200ResponseAllOfPayload.role) &&
        Objects.equals(this.profileSet, userLoginPost200ResponseAllOfPayload.profileSet) &&
        Objects.equals(this.token, userLoginPost200ResponseAllOfPayload.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, role, profileSet, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserLoginPost200ResponseAllOfPayload {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    profileSet: ").append(toIndentedString(profileSet)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

