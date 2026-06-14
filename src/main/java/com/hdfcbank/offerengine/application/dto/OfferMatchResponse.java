package com.hdfcbank.offerengine.application.dto;

import com.hdfcbank.offerengine.domain.model.EligibleOfferRecord;
import com.hdfcbank.offerengine.domain.model.OfferStatus;

import java.util.List;

public record OfferMatchResponse(
        String sourceSequenceNumber,
        String labelCode,
        String campaignName,
        String aan,
        String mobileNumber,
        String emailId,
        String keyField3,
        String keyField4,
        String keyField5,
        OfferStatus status,
        List<MerchantMatchResponse> merchants,
        List<OfferDetailResponse> offers
) {
    public static OfferMatchResponse from(EligibleOfferRecord record) {
        return new OfferMatchResponse(
                record.sourceSequenceNumber(),
                record.labelCode(),
                record.campaignName(),
                record.aan(),
                record.mobileNumber(),
                record.emailId(),
                record.keyField3(),
                record.keyField4(),
                record.keyField5(),
                record.status(),
                record.merchants().stream().map(MerchantMatchResponse::from).toList(),
                record.offers().stream().map(OfferDetailResponse::from).toList());
    }
}
