package com.bibek.urlshortner.entity;

import com.bibek.urlshortner.auditingconfig.AuditingEntity;
import com.bibek.urlshortner.utils.StringEncryptorUtil;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "short_url_entity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortUrlEntity extends AuditingEntity {
    @Id
    @SequenceGenerator(name = "short_url_entity_seq", sequenceName = "short_url_entity_seq", allocationSize = 1)
    @GeneratedValue(generator = "short_url_entity_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "short_code")
    private String shortCode;

    @Builder.Default
    @Column(name = "visit_count", columnDefinition = "INTEGER DEFAULT 0")
    private Integer visitCount = 0;


    @PostPersist
    public void generateShortCodeAfterSave() {
        this.shortCode = generateShortCode(this.id);
    }

    private String generateShortCode(Long id) {
        return StringEncryptorUtil.encrypt(String.valueOf(id));
    }

}
