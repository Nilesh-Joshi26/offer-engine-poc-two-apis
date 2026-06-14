package com.hdfcbank.offerengine.domain.port.in;

import com.hdfcbank.offerengine.domain.model.CustomerInquiryCriteria;
import com.hdfcbank.offerengine.domain.model.EligibleOfferRecord;

import java.util.List;

public interface CustomerInquiryUseCase {

    List<EligibleOfferRecord> findEligibleOffers(CustomerInquiryCriteria criteria);
}
