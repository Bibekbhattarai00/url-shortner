package com.bibek.urlshortner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequestDto {
    @JsonProperty(value = "original_url")
    private String originalUrl;
}
