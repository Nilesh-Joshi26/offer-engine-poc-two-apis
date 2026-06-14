package com.hdfcbank.offerengine.domain.model;

public record MerchantMatch(
        Integer merchantSequence,
        String merchantName,
        String merchantId
) {
}
