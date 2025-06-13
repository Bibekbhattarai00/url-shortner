package com.bibek.urlshortner.service.url.impl;

import com.bibek.urlshortner.dto.UrlRequestDto;
import com.bibek.urlshortner.dto.UrlResponseDto;
import com.bibek.urlshortner.entity.ShortUrlEntity;
import com.bibek.urlshortner.exception.CustomMessageSource;
import com.bibek.urlshortner.constants.MessageConstants;
import com.bibek.urlshortner.exception.NotFoundException;
import com.bibek.urlshortner.generic.PaginationRequest;
import com.bibek.urlshortner.generic.PaginationResponse;
import com.bibek.urlshortner.repo.ShortUrlRepo;
import com.bibek.urlshortner.service.helper.ShortUrlGeneratorHelper;
import com.bibek.urlshortner.service.url.service.ShortUrlService;
import com.bibek.urlshortner.utils.CustomPaginationHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShortUrlServiceImpl extends ShortUrlGeneratorHelper implements ShortUrlService {

    private final ShortUrlRepo shortUrlRepo;
    private final CustomMessageSource customMessageSource;
    private final CustomPaginationHandler customPaginationHandler;
    private String baseUrl = "http://localhost:9091/url-shortner/api/v1/url/";


    @Override
    @Transactional
    public UrlResponseDto shortenUrl(UrlRequestDto urlRequestDto) {
        ShortUrlEntity url = ShortUrlEntity.builder()
                .originalUrl(urlRequestDto.getOriginalUrl())
                .build();
        shortUrlRepo.save(url);
        url.setShortCode(generateShortCode(url.getId()));
        return UrlResponseDto.builder()
                .shortUrl(baseUrl + URLEncoder.encode(url.getShortCode(), StandardCharsets.UTF_8))
                .build();
    }

    @Override
    public ResponseEntity<?> redirectToOriginalUrl(String shortCode) {
        ShortUrlEntity url = shortUrlRepo.findById(decryptShortCode(URLDecoder.decode(shortCode,StandardCharsets.UTF_8)))
                .orElseThrow(() -> new NotFoundException(customMessageSource.get(MessageConstants.NOT_FOUND.getCode())));
        handleUpdateVisit(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getOriginalUrl()));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    private void handleUpdateVisit(ShortUrlEntity url) {
        url.setVisitCount(url.getVisitCount() + 1);
        shortUrlRepo.save(url);
    }

    @Override
    public Integer getAnalyticsOfUrl(String shortUrl) {
        return shortUrlRepo.getTotalVisitCount(decryptShortCode(URLDecoder.decode(shortUrl,StandardCharsets.UTF_8)));
    }

    @Override
    public PaginationResponse getAllLinksPaginated(PaginationRequest request) {
        return customPaginationHandler.getPaginatedData(shortUrlRepo.getAllUrlsDetails(request.getPageable()));
    }

    @Override
    public List<Map<String,Object>> getAllLinks() {
        return shortUrlRepo.getAllUrlsDetails();
    }

}
