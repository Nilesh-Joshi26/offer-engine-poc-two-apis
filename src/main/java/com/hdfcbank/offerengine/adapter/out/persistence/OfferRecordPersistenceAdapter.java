package com.hdfcbank.offerengine.adapter.out.persistence;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.MainOfferRecordEntity;
import com.hdfcbank.offerengine.adapter.out.persistence.repository.MainOfferRecordJpaRepository;
import com.hdfcbank.offerengine.domain.model.CustomerInquiryCriteria;
import com.hdfcbank.offerengine.domain.model.OfferRecord;
import com.hdfcbank.offerengine.domain.model.OfferStatus;
import com.hdfcbank.offerengine.domain.port.out.OfferRecordRepositoryPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class OfferRecordPersistenceAdapter implements OfferRecordRepositoryPort {

    private final MainOfferRecordJpaRepository repository;

    public OfferRecordPersistenceAdapter(MainOfferRecordJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OfferRecord> findLiveMatches(CustomerInquiryCriteria criteria, int maxResults) {
        return repository.findLiveMatches(
                        criteria.campaignName(),
                        OfferStatus.ACTIVE,
                        LocalDate.now(),
                        blankToNull(criteria.aan()),
                        blankToNull(criteria.mobileNumber()),
                        blankToNull(criteria.emailId()),
                        blankToNull(criteria.merchantId()),
                        PageRequest.of(0, maxResults))
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private OfferRecord toDomain(MainOfferRecordEntity entity) {
        return new OfferRecord(entity.getId(), entity.getSequenceNumber(), entity.getLabelCode(), entity.getCampaignName(),
                entity.getAan(), entity.getMobileNumber(), entity.getEmailId(), entity.getMerchantId(),
                entity.getValidityDate(), entity.getOfferText(), entity.getOfferTnc(), entity.getStatus());
    }

    private String blankToNull(String value) {
        return value == null || value.isBlank() ? null : value;
    }
}
