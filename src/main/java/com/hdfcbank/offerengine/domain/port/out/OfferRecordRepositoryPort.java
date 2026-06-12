package com.hdfcbank.offerengine.domain.port.out;

import com.hdfcbank.offerengine.domain.model.CustomerInquiryCriteria;
import com.hdfcbank.offerengine.domain.model.OfferRecord;

import java.util.List;

public interface OfferRecordRepositoryPort {

    List<OfferRecord> findLiveMatches(CustomerInquiryCriteria criteria, int maxResults);
}
