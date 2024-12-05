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
 * UserGetProfileGet200ResponseAllOfPayload
 */

@JsonTypeName("_user_get_profile_get_200_response_allOf_payload")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-04T19:14:42.596594700-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class UserGetProfileGet200ResponseAllOfPayload {

  private String id;

  private String email;

  private Role role;

  private String name;

  private String phone;

  private String address;

  private String imageUrl;

  public UserGetProfileGet200ResponseAllOfPayload id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "12ad3fa...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UserGetProfileGet200ResponseAllOfPayload email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  
  @Schema(name = "email", example = "user@example.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserGetProfileGet200ResponseAllOfPayload role(Role role) {
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

  public UserGetProfileGet200ResponseAllOfPayload name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "John Doe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserGetProfileGet200ResponseAllOfPayload phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Get phone
   * @return phone
   */
  
  @Schema(name = "phone", example = "123-456-7890", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public UserGetProfileGet200ResponseAllOfPayload address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   */
  
  @Schema(name = "address", example = "123 Pet Lane", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public UserGetProfileGet200ResponseAllOfPayload imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
   * @return imageUrl
   */
  
  @Schema(name = "imageUrl", example = "http://some.site/images/img.jpg", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("imageUrl")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserGetProfileGet200ResponseAllOfPayload userGetProfileGet200ResponseAllOfPayload = (UserGetProfileGet200ResponseAllOfPayload) o;
    return Objects.equals(this.id, userGetProfileGet200ResponseAllOfPayload.id) &&
        Objects.equals(this.email, userGetProfileGet200ResponseAllOfPayload.email) &&
        Objects.equals(this.role, userGetProfileGet200ResponseAllOfPayload.role) &&
        Objects.equals(this.name, userGetProfileGet200ResponseAllOfPayload.name) &&
        Objects.equals(this.phone, userGetProfileGet200ResponseAllOfPayload.phone) &&
        Objects.equals(this.address, userGetProfileGet200ResponseAllOfPayload.address) &&
        Objects.equals(this.imageUrl, userGetProfileGet200ResponseAllOfPayload.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, role, name, phone, address, imageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserGetProfileGet200ResponseAllOfPayload {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
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

