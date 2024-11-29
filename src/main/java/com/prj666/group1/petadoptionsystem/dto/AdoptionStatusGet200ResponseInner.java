package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AdoptionStatusGet200ResponseInner
 */

@JsonTypeName("_adoption_status_get_200_response_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-28T13:05:45.967077-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class AdoptionStatusGet200ResponseInner {

  private Integer adoptionId;

  private Integer petId;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    PENDING("Pending"),
    
    APPROVED("Approved"),
    
    REJECTED("Rejected");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  public AdoptionStatusGet200ResponseInner adoptionId(Integer adoptionId) {
    this.adoptionId = adoptionId;
    return this;
  }

  /**
   * Get adoptionId
   * @return adoptionId
   */
  
  @Schema(name = "adoptionId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adoptionId")
  public Integer getAdoptionId() {
    return adoptionId;
  }

  public void setAdoptionId(Integer adoptionId) {
    this.adoptionId = adoptionId;
  }

  public AdoptionStatusGet200ResponseInner petId(Integer petId) {
    this.petId = petId;
    return this;
  }

  /**
   * Get petId
   * @return petId
   */
  
  @Schema(name = "petId", example = "42", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("petId")
  public Integer getPetId() {
    return petId;
  }

  public void setPetId(Integer petId) {
    this.petId = petId;
  }

  public AdoptionStatusGet200ResponseInner status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  
  @Schema(name = "status", example = "Pending", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public AdoptionStatusGet200ResponseInner date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   */
  @Valid 
  @Schema(name = "date", example = "Sun May 14 20:00:00 EDT 2023", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdoptionStatusGet200ResponseInner adoptionStatusGet200ResponseInner = (AdoptionStatusGet200ResponseInner) o;
    return Objects.equals(this.adoptionId, adoptionStatusGet200ResponseInner.adoptionId) &&
        Objects.equals(this.petId, adoptionStatusGet200ResponseInner.petId) &&
        Objects.equals(this.status, adoptionStatusGet200ResponseInner.status) &&
        Objects.equals(this.date, adoptionStatusGet200ResponseInner.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adoptionId, petId, status, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdoptionStatusGet200ResponseInner {\n");
    sb.append("    adoptionId: ").append(toIndentedString(adoptionId)).append("\n");
    sb.append("    petId: ").append(toIndentedString(petId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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

