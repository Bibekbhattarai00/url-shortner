package com.bibek.urlshortner.utils;

import com.bibek.urlshortner.generic.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomPaginationHandler {
    public PaginationResponse getPaginatedData(Page<Map<String, Object>> page) {
        return PaginationResponse
                .builder()
                .content(page.getContent())
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .currentPageIndex(page.getNumber() + 1)
                .build();
    }
}
