package com.bibek.urlshortner.repo;

import com.bibek.urlshortner.entity.ShortUrlEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ShortUrlRepo extends JpaRepository<ShortUrlEntity, Long> {

    @Query(value = "select sue.visit_count from short_url_entity sue where sue.id=?1", nativeQuery = true)
    Integer getTotalVisitCount(Long id);

    @Query(value = """
             select sue.id, sue.original_url as originalUrl, sue.short_code as shortCode, sue.visit_count from short_url_entity sue
            """, nativeQuery = true)
    Page<Map<String, Object>> getAllUrlsDetails(Pageable pageable);

    @Query(value = """
             select sue.id, sue.original_url as originalUrl, sue.short_code as shortCode, sue.visit_count from short_url_entity sue
            """, nativeQuery = true)
    List<Map<String, Object>> getAllUrlsDetails();
}
