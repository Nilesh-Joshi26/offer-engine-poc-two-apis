package com.hdfcbank.offerengine.application.dto;

import com.hdfcbank.offerengine.domain.model.OfferRecord;
import com.hdfcbank.offerengine.domain.model.OfferStatus;

import java.time.LocalDate;

public record OfferMatchResponse(
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
    public static OfferMatchResponse from(OfferRecord record) {
        return new OfferMatchResponse(record.sequenceNumber(), record.labelCode(), record.campaignName(),
                record.aan(), record.mobileNumber(), record.emailId(), record.merchantId(), record.validityDate(),
                record.offerText(), record.offerTnc(), record.status());
    }
}
