package com.prj666.group1.petadoptionsystem.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AdoptionStoryGet200ResponseAllOfPayloadInner
 */

@JsonTypeName("_adoption_story_get_200_response_allOf_payload_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-02T01:42:56.763233900-05:00[America/Toronto]", comments = "Generator version: 7.7.0")
public class AdoptionStoryGet200ResponseAllOfPayloadInner {

  private String author;

  private String title;

  private String text;

  public AdoptionStoryGet200ResponseAllOfPayloadInner author(String author) {
    this.author = author;
    return this;
  }

  /**
   * The author of the adoption story
   * @return author
   */
  
  @Schema(name = "author", example = "John Doe", description = "The author of the adoption story", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("author")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public AdoptionStoryGet200ResponseAllOfPayloadInner title(String title) {
    this.title = title;
    return this;
  }

  /**
   * The title of the adoption story
   * @return title
   */
  
  @Schema(name = "title", example = "A Happy Ending for Max", description = "The title of the adoption story", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public AdoptionStoryGet200ResponseAllOfPayloadInner text(String text) {
    this.text = text;
    return this;
  }

  /**
   * The content of the adoption story
   * @return text
   */
  
  @Schema(name = "text", example = "Max was found wandering the streets and was taken in by a local shelter. After weeks of care, he found a loving home with the Doe family.", description = "The content of the adoption story", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdoptionStoryGet200ResponseAllOfPayloadInner adoptionStoryGet200ResponseAllOfPayloadInner = (AdoptionStoryGet200ResponseAllOfPayloadInner) o;
    return Objects.equals(this.author, adoptionStoryGet200ResponseAllOfPayloadInner.author) &&
        Objects.equals(this.title, adoptionStoryGet200ResponseAllOfPayloadInner.title) &&
        Objects.equals(this.text, adoptionStoryGet200ResponseAllOfPayloadInner.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, title, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdoptionStoryGet200ResponseAllOfPayloadInner {\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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

