package com.hdfcbank.offerengine.adapter.in.web;

import com.hdfcbank.offerengine.application.dto.ApiResponse;
import com.hdfcbank.offerengine.application.dto.CustomerInquiryRequest;
import com.hdfcbank.offerengine.application.dto.CustomerInquiryResponse;
import com.hdfcbank.offerengine.application.service.CustomerInquiryApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer-inquiry")
public class CustomerInquiryController {

    private final CustomerInquiryApplicationService customerInquiryApplicationService;

    public CustomerInquiryController(CustomerInquiryApplicationService customerInquiryApplicationService) {
        this.customerInquiryApplicationService = customerInquiryApplicationService;
    }

    // API 6 - Customer Inquiry
    // Source connectivity: Merchant site -> API Gateway -> Middleware -> App -> DB.
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerInquiryResponse>> inquire(@Valid @RequestBody CustomerInquiryRequest request) {
        return ResponseEntity.ok(ApiResponse.success(customerInquiryApplicationService.inquire(request)));
    }
}
