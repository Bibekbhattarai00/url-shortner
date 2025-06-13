package com.bibek.urlshortner.service.url.service;

import com.bibek.urlshortner.dto.UrlRequestDto;
import com.bibek.urlshortner.dto.UrlResponseDto;
import com.bibek.urlshortner.generic.PaginationRequest;
import com.bibek.urlshortner.generic.PaginationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ShortUrlService {
    UrlResponseDto shortenUrl(UrlRequestDto urlRequestDto);

    ResponseEntity<?> redirectToOriginalUrl(String shortCode);

    Integer getAnalyticsOfUrl(String shortUrl);

    PaginationResponse getAllLinksPaginated(PaginationRequest request);

    List<Map<String,Object>> getAllLinks();
}
