package com.hdfcbank.offerengine.domain.port.in;

import com.hdfcbank.offerengine.domain.model.CustomerInquiryCriteria;
import com.hdfcbank.offerengine.domain.model.OfferRecord;

import java.util.List;

public interface CustomerInquiryUseCase {

    List<OfferRecord> findEligibleOffers(CustomerInquiryCriteria criteria);
}
