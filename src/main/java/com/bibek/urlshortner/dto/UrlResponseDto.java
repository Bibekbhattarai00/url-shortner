package com.bibek.urlshortner.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlResponseDto {
    @JsonProperty(value = "short_url")
    private String shortUrl;
}
