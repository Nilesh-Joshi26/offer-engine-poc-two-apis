package com.hdfcbank.offerengine.application.dto;

import com.hdfcbank.offerengine.domain.model.MerchantMatch;

public record MerchantMatchResponse(
        Integer merchantSequence,
        String merchantName,
        String merchantId
) {
    public static MerchantMatchResponse from(MerchantMatch merchant) {
        return new MerchantMatchResponse(merchant.merchantSequence(), merchant.merchantName(), merchant.merchantId());
    }
}
