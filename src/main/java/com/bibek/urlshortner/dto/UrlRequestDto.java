package com.bibek.urlshortner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequestDto {
    @NotBlank(message = "Url cannot be Empty")
    @JsonProperty(value = "original_url")
    private String originalUrl;
}
