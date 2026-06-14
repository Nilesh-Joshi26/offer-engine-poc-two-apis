package com.hdfcbank.offerengine.application.dto;

import java.util.List;

public record CustomerInquiryResponse(
        CustomerInquiryRequest inputPayload,
        boolean isEligible,
        int matchCount,
        List<OfferMatchResponse> matches
) {
}
