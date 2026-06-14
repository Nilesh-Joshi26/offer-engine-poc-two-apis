package com.hdfcbank.offerengine.domain.model;

import java.util.List;

public record EligibleOfferRecord(
        Long customerRecordId,
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
        List<MerchantMatch> merchants,
        List<OfferDetail> offers
) {
}
