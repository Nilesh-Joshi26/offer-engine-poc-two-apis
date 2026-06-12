package com.hdfcbank.offerengine.application.service;

import com.hdfcbank.offerengine.application.dto.CustomerInquiryRequest;
import com.hdfcbank.offerengine.application.dto.CustomerInquiryResponse;
import com.hdfcbank.offerengine.application.dto.OfferMatchResponse;
import com.hdfcbank.offerengine.domain.model.CustomerInquiryCriteria;
import com.hdfcbank.offerengine.domain.port.in.CustomerInquiryUseCase;
import com.hdfcbank.offerengine.domain.port.out.PayloadLogPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class CustomerInquiryApplicationService {

    private static final Logger log = LoggerFactory.getLogger(CustomerInquiryApplicationService.class);

    private final CustomerInquiryUseCase customerInquiryUseCase;
    private final PayloadLogPort payloadLogPort;
    private final ObjectMapper objectMapper;

    public CustomerInquiryApplicationService(CustomerInquiryUseCase customerInquiryUseCase,
                                             PayloadLogPort payloadLogPort,
                                             ObjectMapper objectMapper) {
        this.customerInquiryUseCase = customerInquiryUseCase;
        this.payloadLogPort = payloadLogPort;
        this.objectMapper = objectMapper;
    }

    @Transactional(readOnly = true)
    public CustomerInquiryResponse inquire(CustomerInquiryRequest request) {
        CustomerInquiryCriteria criteria = new CustomerInquiryCriteria(
                request.campaignName(), request.aan(), request.mobileNumber(), request.emailId(), request.merchantId());
        var matches = customerInquiryUseCase.findEligibleOffers(criteria).stream()
                .map(OfferMatchResponse::from)
                .toList();
        CustomerInquiryResponse response = new CustomerInquiryResponse(request, matches.size(), matches);
        logPayload(request, response);
        return response;
    }

    private void logPayload(CustomerInquiryRequest request, CustomerInquiryResponse response) {
        try {
            payloadLogPort.save("API 6 - Customer Inquiry", "POST", "/api/v1/customer-inquiry",
                    objectMapper.writeValueAsString(request), objectMapper.writeValueAsString(response));
        } catch (JacksonException ex) {
            log.warn("Unable to serialize customer inquiry payload", ex);
        }
    }
}
