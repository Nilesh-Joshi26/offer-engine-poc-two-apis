package com.hdfcbank.offerengine.application.dto;

import com.hdfcbank.offerengine.domain.model.OfferDetail;
import com.hdfcbank.offerengine.domain.model.OfferStatus;

import java.time.LocalDate;

public record OfferDetailResponse(
        Integer offerSequence,
        String offerText,
        LocalDate offerStartDate,
        LocalDate offerEndDate,
        String offerTncUrl,
        OfferStatus status
) {
    public static OfferDetailResponse from(OfferDetail item) {
        return new OfferDetailResponse(item.offerSequence(), item.offerText(), item.offerStartDate(),
                item.offerEndDate(), item.offerTncUrl(), item.status());
    }
}
