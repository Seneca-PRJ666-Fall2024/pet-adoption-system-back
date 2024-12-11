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
 * Gets or Sets AdoptionStatus
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-11T06:31:19.046953700-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public enum AdoptionStatus {
  
  SUBMITTED("Submitted"),
  
  REVIEWING("Reviewing"),
  
  WAITLISTED("Waitlisted"),
  
  INTERVIEWING("Interviewing"),
  
  DECISION_MAKING("Decision Making"),
  
  ADOPTED("Adopted"),
  
  REJECTED("Rejected"),
  
  CANCELLED("Cancelled");

  private String value;

  AdoptionStatus(String value) {
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
  public static AdoptionStatus fromValue(String value) {
    for (AdoptionStatus b : AdoptionStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

