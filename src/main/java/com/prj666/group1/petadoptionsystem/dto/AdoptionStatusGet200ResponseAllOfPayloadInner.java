package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.prj666.group1.petadoptionsystem.dto.AdoptionStatus;
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
 * AdoptionStatusGet200ResponseAllOfPayloadInner
 */

@JsonTypeName("_adoption_status_get_200_response_allOf_payload_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-06T17:56:29.361081200-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class AdoptionStatusGet200ResponseAllOfPayloadInner {

  private String adoptionId;

  private String petId;

  private AdoptionStatus status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  public AdoptionStatusGet200ResponseAllOfPayloadInner adoptionId(String adoptionId) {
    this.adoptionId = adoptionId;
    return this;
  }

  /**
   * Get adoptionId
   * @return adoptionId
   */
  
  @Schema(name = "adoptionId", example = "adf23ee...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adoptionId")
  public String getAdoptionId() {
    return adoptionId;
  }

  public void setAdoptionId(String adoptionId) {
    this.adoptionId = adoptionId;
  }

  public AdoptionStatusGet200ResponseAllOfPayloadInner petId(String petId) {
    this.petId = petId;
    return this;
  }

  /**
   * Get petId
   * @return petId
   */
  
  @Schema(name = "petId", example = "adf23ee...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("petId")
  public String getPetId() {
    return petId;
  }

  public void setPetId(String petId) {
    this.petId = petId;
  }

  public AdoptionStatusGet200ResponseAllOfPayloadInner status(AdoptionStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public AdoptionStatus getStatus() {
    return status;
  }

  public void setStatus(AdoptionStatus status) {
    this.status = status;
  }

  public AdoptionStatusGet200ResponseAllOfPayloadInner date(LocalDate date) {
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
    AdoptionStatusGet200ResponseAllOfPayloadInner adoptionStatusGet200ResponseAllOfPayloadInner = (AdoptionStatusGet200ResponseAllOfPayloadInner) o;
    return Objects.equals(this.adoptionId, adoptionStatusGet200ResponseAllOfPayloadInner.adoptionId) &&
        Objects.equals(this.petId, adoptionStatusGet200ResponseAllOfPayloadInner.petId) &&
        Objects.equals(this.status, adoptionStatusGet200ResponseAllOfPayloadInner.status) &&
        Objects.equals(this.date, adoptionStatusGet200ResponseAllOfPayloadInner.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adoptionId, petId, status, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdoptionStatusGet200ResponseAllOfPayloadInner {\n");
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

