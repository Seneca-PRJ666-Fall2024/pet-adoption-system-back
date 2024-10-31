package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * RegisterPostRequest
 */

@JsonTypeName("_register_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-31T17:36:43.291576-04:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class RegisterPostRequest {

  private String email;

  private String password;

  /**
   * Gets or Sets accountType
   */
  public enum AccountTypeEnum {
    ADOPTER("Pet Adopter"),
    
    SHELTER("Pet Shelter");

    private String value;

    AccountTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccountTypeEnum fromValue(String value) {
      for (AccountTypeEnum b : AccountTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private AccountTypeEnum accountType;

  public RegisterPostRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  
  @Schema(name = "email", example = "adopter@example.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public RegisterPostRequest password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   */
  
  @Schema(name = "password", example = "P@ssw0rd", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RegisterPostRequest accountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * Get accountType
   * @return accountType
   */
  
  @Schema(name = "accountType", example = "Pet Adopter", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountType")
  public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterPostRequest registerPostRequest = (RegisterPostRequest) o;
    return Objects.equals(this.email, registerPostRequest.email) &&
        Objects.equals(this.password, registerPostRequest.password) &&
        Objects.equals(this.accountType, registerPostRequest.accountType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, accountType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterPostRequest {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
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

