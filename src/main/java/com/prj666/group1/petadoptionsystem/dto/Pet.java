package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * Pet
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-08T07:22:15.388559300-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class Pet {

  private String petId;

  private String shelterUserId;

  private String petName;

  private String imageUrl;

  public Pet petId(String petId) {
    this.petId = petId;
    return this;
  }

  /**
   * Get petId
   * @return petId
   */
  
  @Schema(name = "petId", example = "0adf23ff...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("petId")
  public String getPetId() {
    return petId;
  }

  public void setPetId(String petId) {
    this.petId = petId;
  }

  public Pet shelterUserId(String shelterUserId) {
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

  public Pet petName(String petName) {
    this.petName = petName;
    return this;
  }

  /**
   * Get petName
   * @return petName
   */
  
  @Schema(name = "petName", example = "Snooppy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("petName")
  public String getPetName() {
    return petName;
  }

  public void setPetName(String petName) {
    this.petName = petName;
  }

  public Pet imageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * Get imageUrl
   * @return imageUrl
   */
  
  @Schema(name = "imageUrl", example = "[\"http://some.site/images/image.jpg\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("imageUrl")
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
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
    public Pet putAdditionalProperty(String key, String value) {
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
    Pet pet = (Pet) o;
    return Objects.equals(this.petId, pet.petId) &&
        Objects.equals(this.shelterUserId, pet.shelterUserId) &&
        Objects.equals(this.petName, pet.petName) &&
        Objects.equals(this.imageUrl, pet.imageUrl) &&
    Objects.equals(this.additionalProperties, pet.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(petId, shelterUserId, petName, imageUrl, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pet {\n");
    sb.append("    petId: ").append(toIndentedString(petId)).append("\n");
    sb.append("    shelterUserId: ").append(toIndentedString(shelterUserId)).append("\n");
    sb.append("    petName: ").append(toIndentedString(petName)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    
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

