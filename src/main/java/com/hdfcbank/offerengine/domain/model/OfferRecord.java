package com.hdfcbank.offerengine.domain.model;

import java.time.LocalDate;

public record OfferRecord(
        Long id,
        String sequenceNumber,
        String labelCode,
        String campaignName,
        String aan,
        String mobileNumber,
        String emailId,
        String merchantId,
        LocalDate validityDate,
        String offerText,
        String offerTnc,
        OfferStatus status
) {
}
