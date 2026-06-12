package com.hdfcbank.offerengine.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerInquiryRequest(
        @NotBlank @Size(max = 100) String campaignName,
        @Size(max = 50) String aan,
        @Size(max = 50) String mobileNumber,
        @Size(max = 50) String emailId,
        @Size(max = 50) String merchantId
) {
}
