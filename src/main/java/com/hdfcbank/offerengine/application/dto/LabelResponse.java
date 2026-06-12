package com.hdfcbank.offerengine.application.dto;

import com.hdfcbank.offerengine.domain.model.Label;
import com.hdfcbank.offerengine.domain.model.LabelStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LabelResponse(
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
    public static LabelResponse from(Label label) {
        return new LabelResponse(label.labelCode(), label.campaignName(), label.description(), label.status(),
                label.expiryDate(), label.createdAt(), label.createdBy(), label.updatedAt(), label.updatedBy());
    }
}
