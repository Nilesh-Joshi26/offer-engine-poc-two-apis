package com.hdfcbank.offerengine.domain.model;

public record CustomerInquiryCriteria(
        String campaignName,
        String aan,
        String mobileNumber,
        String emailId,
        String merchantId
) {
}
