package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Pet
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-02T01:42:56.763233900-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class Pet {

  private String petId;

  private String shelterId;

  private String name;

  @Valid
  private List<String> images = new ArrayList<>();

  @Valid
  private Map<String, List<String>> attributes = new HashMap<>();

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

  public Pet shelterId(String shelterId) {
    this.shelterId = shelterId;
    return this;
  }

  /**
   * Get shelterId
   * @return shelterId
   */
  
  @Schema(name = "shelterId", example = "0adf23ff...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("shelterId")
  public String getShelterId() {
    return shelterId;
  }

  public void setShelterId(String shelterId) {
    this.shelterId = shelterId;
  }

  public Pet name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "Snooppy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Pet images(List<String> images) {
    this.images = images;
    return this;
  }

  public Pet addImagesItem(String imagesItem) {
    if (this.images == null) {
      this.images = new ArrayList<>();
    }
    this.images.add(imagesItem);
    return this;
  }

  /**
   * Get images
   * @return images
   */
  
  @Schema(name = "images", example = "[\"http://some.site/images/image.jpg\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("images")
  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

  public Pet attributes(Map<String, List<String>> attributes) {
    this.attributes = attributes;
    return this;
  }

  public Pet putAttributesItem(String key, List<String> attributesItem) {
    if (this.attributes == null) {
      this.attributes = new HashMap<>();
    }
    this.attributes.put(key, attributesItem);
    return this;
  }

  /**
   * Get attributes
   * @return attributes
   */
  @Valid 
  @Schema(name = "attributes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attributes")
  public Map<String, List<String>> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, List<String>> attributes) {
    this.attributes = attributes;
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
        Objects.equals(this.shelterId, pet.shelterId) &&
        Objects.equals(this.name, pet.name) &&
        Objects.equals(this.images, pet.images) &&
        Objects.equals(this.attributes, pet.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(petId, shelterId, name, images, attributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pet {\n");
    sb.append("    petId: ").append(toIndentedString(petId)).append("\n");
    sb.append("    shelterId: ").append(toIndentedString(shelterId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
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

