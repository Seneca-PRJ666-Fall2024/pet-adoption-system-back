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
 * UserLoginPost200Response
 */

@JsonTypeName("_user_login_post_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-30T19:00:36.896359300-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class UserLoginPost200Response {

  private Boolean success;

  private String message;

  private Role role;

  private Boolean profileSet;

  private String token;

  public UserLoginPost200Response success(Boolean success) {
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

  public UserLoginPost200Response message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @Schema(name = "message", example = "Login successful", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public UserLoginPost200Response role(Role role) {
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

  public UserLoginPost200Response profileSet(Boolean profileSet) {
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

  public UserLoginPost200Response token(String token) {
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
    UserLoginPost200Response userLoginPost200Response = (UserLoginPost200Response) o;
    return Objects.equals(this.success, userLoginPost200Response.success) &&
        Objects.equals(this.message, userLoginPost200Response.message) &&
        Objects.equals(this.role, userLoginPost200Response.role) &&
        Objects.equals(this.profileSet, userLoginPost200Response.profileSet) &&
        Objects.equals(this.token, userLoginPost200Response.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, message, role, profileSet, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserLoginPost200Response {\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

