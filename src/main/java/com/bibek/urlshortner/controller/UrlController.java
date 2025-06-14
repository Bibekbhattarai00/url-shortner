package com.bibek.urlshortner.controller;


import com.bibek.urlshortner.annotation.StandardApiResponses;
import com.bibek.urlshortner.constants.ApiConstants;
import com.bibek.urlshortner.constants.MessageConstants;
import com.bibek.urlshortner.constants.ModuleNameConstants;
import com.bibek.urlshortner.dto.UrlRequestDto;
import com.bibek.urlshortner.dto.UrlResponseDto;
import com.bibek.urlshortner.generic.BaseController;
import com.bibek.urlshortner.generic.GenericResponse;
import com.bibek.urlshortner.generic.PaginationRequest;
import com.bibek.urlshortner.generic.PaginationResponse;
import com.bibek.urlshortner.service.url.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Url.BASE_URL)
@Tag(name = ModuleNameConstants.Url.MODULE_NAME, description = ModuleNameConstants.Url.URL_CONTROLLER_DESC)
public class UrlController extends BaseController {

    private final ShortUrlService shortUrlService;


    @Operation(summary = "Generate Short code", description = "This api generates short code for the provided long urls")
    @StandardApiResponses
    @PostMapping(ApiConstants.Url.SHORTEN)
    public GenericResponse<UrlResponseDto> shortenUrl(@Valid @RequestBody UrlRequestDto urlRequestDto) {
        return successResponse(shortUrlService.shortenUrl(urlRequestDto), MessageConstants.SUCCESS.getCode());
    }

    @Operation(summary = "Redirect to original site", description = "This api redirects to the original site")
    @StandardApiResponses
    @GetMapping(value = ApiConstants.Url.GET)
    public ResponseEntity<?> redirect(@PathVariable(value = "short_code") String shortCode) {
        return shortUrlService.redirectToOriginalUrl(shortCode);
    }

    @Operation(summary = "Analytics of Short Code", description = "This api provides the analytics of visit count of urls")
    @StandardApiResponses
    @GetMapping(ApiConstants.Url.ANALYTICS)
    public GenericResponse<Integer> getAnalytics(@PathVariable(value = "short_code") String shortCode) {
        return successResponse(shortUrlService.getAnalyticsOfUrl(shortCode), MessageConstants.SUCCESS.getCode());
    }

    @Operation(summary = "List of urls paginated", description = "This api provides all the urls paginated")
    @PostMapping(ApiConstants.Url.GET_PAGINATED)
    @StandardApiResponses
    public GenericResponse<PaginationResponse> getAllPaginated(@RequestBody PaginationRequest request) {
        return successResponse(shortUrlService.getAllLinksPaginated(request), MessageConstants.SUCCESS.getCode());
    }

    @Operation(summary = "List of urls non paginated", description = "This api provides all the urls non paginated")
    @GetMapping(ApiConstants.Url.ALL)
    @StandardApiResponses
    public GenericResponse<List<Map<String, Object>>> getAll() {
        return successResponse(shortUrlService.getAllLinks(), MessageConstants.SUCCESS.getCode());
    }

}
