package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AttributeGroup
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-11T06:31:19.046953700-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class AttributeGroup {

  private String name;

  private String description;

  private Boolean supportsOther;

  @Valid
  private List<String> values = new ArrayList<>();

  private String question;

  public AttributeGroup name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", example = "petGender", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AttributeGroup description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", example = "Pet Gender", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AttributeGroup supportsOther(Boolean supportsOther) {
    this.supportsOther = supportsOther;
    return this;
  }

  /**
   * Get supportsOther
   * @return supportsOther
   */
  
  @Schema(name = "supportsOther", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("supportsOther")
  public Boolean getSupportsOther() {
    return supportsOther;
  }

  public void setSupportsOther(Boolean supportsOther) {
    this.supportsOther = supportsOther;
  }

  public AttributeGroup values(List<String> values) {
    this.values = values;
    return this;
  }

  public AttributeGroup addValuesItem(String valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<>();
    }
    this.values.add(valuesItem);
    return this;
  }

  /**
   * Get values
   * @return values
   */
  
  @Schema(name = "values", example = "[\"male\",\"female\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("values")
  public List<String> getValues() {
    return values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  public AttributeGroup question(String question) {
    this.question = question;
    return this;
  }

  /**
   * Get question
   * @return question
   */
  
  @Schema(name = "question", example = "What is the preferred Gender for your pet?", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("question")
  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttributeGroup attributeGroup = (AttributeGroup) o;
    return Objects.equals(this.name, attributeGroup.name) &&
        Objects.equals(this.description, attributeGroup.description) &&
        Objects.equals(this.supportsOther, attributeGroup.supportsOther) &&
        Objects.equals(this.values, attributeGroup.values) &&
        Objects.equals(this.question, attributeGroup.question);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, supportsOther, values, question);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttributeGroup {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    supportsOther: ").append(toIndentedString(supportsOther)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
    sb.append("    question: ").append(toIndentedString(question)).append("\n");
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

