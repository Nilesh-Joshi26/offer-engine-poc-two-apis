package com.hdfcbank.offerengine.domain.model;

import java.time.LocalDate;

public record OfferDetail(
        Integer offerSequence,
        String offerText,
        LocalDate offerStartDate,
        LocalDate offerEndDate,
        String offerTncUrl,
        OfferStatus status
) {
}
