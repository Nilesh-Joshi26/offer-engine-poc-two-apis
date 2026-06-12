package com.hdfcbank.offerengine.domain.service;

import com.hdfcbank.offerengine.config.OfferEngineProperties;
import com.hdfcbank.offerengine.domain.model.CustomerInquiryCriteria;
import com.hdfcbank.offerengine.domain.model.OfferRecord;
import com.hdfcbank.offerengine.domain.port.in.CustomerInquiryUseCase;
import com.hdfcbank.offerengine.domain.port.out.OfferRecordRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CustomerInquiryDomainService implements CustomerInquiryUseCase {

    private final OfferRecordRepositoryPort offerRecordRepository;
    private final OfferEngineProperties properties;

    public CustomerInquiryDomainService(OfferRecordRepositoryPort offerRecordRepository, OfferEngineProperties properties) {
        this.offerRecordRepository = offerRecordRepository;
        this.properties = properties;
    }

    @Override
    public List<OfferRecord> findEligibleOffers(CustomerInquiryCriteria criteria) {
        if (isBlank(criteria.campaignName())) {
            throw new DomainValidationException("campaignName is mandatory");
        }
        boolean noSearchKey = Stream.of(criteria.aan(), criteria.mobileNumber(), criteria.emailId(), criteria.merchantId())
                .allMatch(this::isBlank);
        if (noSearchKey) {
            throw new DomainValidationException("At least one search key must be supplied");
        }
        return offerRecordRepository.findLiveMatches(criteria, properties.customerInquiry().maxResults());
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
