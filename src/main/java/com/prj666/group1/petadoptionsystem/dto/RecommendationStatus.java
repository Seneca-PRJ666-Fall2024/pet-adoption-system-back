package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets RecommendationStatus
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-11T16:53:22.577455-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public enum RecommendationStatus {
  
  NEW("new"),
  
  ACCEPTED("accepted"),
  
  REJECTED("rejected");

  private String value;

  RecommendationStatus(String value) {
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
  public static RecommendationStatus fromValue(String value) {
    for (RecommendationStatus b : RecommendationStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

