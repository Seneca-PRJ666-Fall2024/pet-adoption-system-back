package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.prj666.group1.petadoptionsystem.dto.AdoptionStatus;
import com.prj666.group1.petadoptionsystem.dto.Pet;
import com.prj666.group1.petadoptionsystem.dto.User;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
/**
 * Adoption
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-11T16:53:22.577455-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class Adoption {

  private String adoptionId;

  private String adopterId;

  private String shelterUserId;

  private Pet pet;

  private User adopter;

  private AdoptionStatus status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  public Adoption adoptionId(String adoptionId) {
    this.adoptionId = adoptionId;
    return this;
  }

  /**
   * Get adoptionId
   * @return adoptionId
   */
  
  @Schema(name = "adoptionId", example = "0adf23ff...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adoptionId")
  public String getAdoptionId() {
    return adoptionId;
  }

  public void setAdoptionId(String adoptionId) {
    this.adoptionId = adoptionId;
  }

  public Adoption adopterId(String adopterId) {
    this.adopterId = adopterId;
    return this;
  }

  /**
   * Get adopterId
   * @return adopterId
   */
  
  @Schema(name = "adopterId", example = "0adf23ff...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adopterId")
  public String getAdopterId() {
    return adopterId;
  }

  public void setAdopterId(String adopterId) {
    this.adopterId = adopterId;
  }

  public Adoption shelterUserId(String shelterUserId) {
    this.shelterUserId = shelterUserId;
    return this;
  }

  /**
   * Get shelterUserId
   * @return shelterUserId
   */
  
  @Schema(name = "shelterUserId", example = "0adf23ff...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("shelterUserId")
  public String getShelterUserId() {
    return shelterUserId;
  }

  public void setShelterUserId(String shelterUserId) {
    this.shelterUserId = shelterUserId;
  }

  public Adoption pet(Pet pet) {
    this.pet = pet;
    return this;
  }

  /**
   * Get pet
   * @return pet
   */
  @Valid 
  @Schema(name = "pet", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pet")
  public Pet getPet() {
    return pet;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }

  public Adoption adopter(User adopter) {
    this.adopter = adopter;
    return this;
  }

  /**
   * Get adopter
   * @return adopter
   */
  @Valid 
  @Schema(name = "adopter", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adopter")
  public User getAdopter() {
    return adopter;
  }

  public void setAdopter(User adopter) {
    this.adopter = adopter;
  }

  public Adoption status(AdoptionStatus status) {
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

  public Adoption date(LocalDate date) {
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
    /**
    * A container for additional, undeclared properties.
    * This is a holder for any undeclared properties as specified with
    * the 'additionalProperties' keyword in the OAS document.
    */
    private Map<String, String> additionalProperties;

    /**
    * Set the additional (undeclared) property with the specified name and value.
    * If the property does not already exist, create it otherwise replace it.
    */
    @JsonAnySetter
    public Adoption putAdditionalProperty(String key, String value) {
        if (this.additionalProperties == null) {
            this.additionalProperties = new HashMap<String, String>();
        }
        this.additionalProperties.put(key, value);
        return this;
    }

    /**
    * Return the additional (undeclared) property.
    */
    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    /**
    * Return the additional (undeclared) property with the specified name.
    */
    public String getAdditionalProperty(String key) {
        if (this.additionalProperties == null) {
            return null;
        }
        return this.additionalProperties.get(key);
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Adoption adoption = (Adoption) o;
    return Objects.equals(this.adoptionId, adoption.adoptionId) &&
        Objects.equals(this.adopterId, adoption.adopterId) &&
        Objects.equals(this.shelterUserId, adoption.shelterUserId) &&
        Objects.equals(this.pet, adoption.pet) &&
        Objects.equals(this.adopter, adoption.adopter) &&
        Objects.equals(this.status, adoption.status) &&
        Objects.equals(this.date, adoption.date) &&
    Objects.equals(this.additionalProperties, adoption.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adoptionId, adopterId, shelterUserId, pet, adopter, status, date, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Adoption {\n");
    sb.append("    adoptionId: ").append(toIndentedString(adoptionId)).append("\n");
    sb.append("    adopterId: ").append(toIndentedString(adopterId)).append("\n");
    sb.append("    shelterUserId: ").append(toIndentedString(shelterUserId)).append("\n");
    sb.append("    pet: ").append(toIndentedString(pet)).append("\n");
    sb.append("    adopter: ").append(toIndentedString(adopter)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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

