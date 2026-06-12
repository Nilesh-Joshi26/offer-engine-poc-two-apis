package com.hdfcbank.offerengine.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "offer-engine")
public record OfferEngineProperties(
        int payloadLogRetentionDays,
        CustomerInquiry customerInquiry
) {
    public record CustomerInquiry(int maxResults) {
    }
}
