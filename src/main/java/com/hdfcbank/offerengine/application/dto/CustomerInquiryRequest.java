package com.hdfcbank.offerengine.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerInquiryRequest(
        @NotBlank @Size(max = 100) String campaignName,
        @Size(max = 50) String labelCode,
        @Size(max = 50) String aan,
        @Size(max = 50) String mobileNumber,
        @Size(max = 100) String emailId,
        @Size(max = 50) String merchantId,
        @Size(max = 100) String merchantName,
        @Size(max = 50) String keyField3,
        @Size(max = 50) String keyField4,
        @Size(max = 50) String keyField5
) {
}
