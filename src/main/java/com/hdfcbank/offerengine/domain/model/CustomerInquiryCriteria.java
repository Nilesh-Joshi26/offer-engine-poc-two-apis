package com.hdfcbank.offerengine.domain.model;

public record CustomerInquiryCriteria(
        String campaignName,
        String labelCode,
        String aan,
        String mobileNumber,
        String emailId,
        String merchantId,
        String merchantName,
        String keyField3,
        String keyField4,
        String keyField5
) {
}
