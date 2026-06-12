package com.hdfcbank.offerengine.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Label(
        Long id,
        String labelCode,
        String campaignName,
        String description,
        LabelStatus status,
        LocalDate expiryDate,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy
) {
}
