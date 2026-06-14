package com.hdfcbank.offerengine.domain.port.out;

import com.hdfcbank.offerengine.domain.model.CustomerInquiryCriteria;
import com.hdfcbank.offerengine.domain.model.EligibleOfferRecord;

import java.util.List;

public interface OfferRecordRepositoryPort {

    List<EligibleOfferRecord> findLiveMatches(CustomerInquiryCriteria criteria, int maxResults);
}
