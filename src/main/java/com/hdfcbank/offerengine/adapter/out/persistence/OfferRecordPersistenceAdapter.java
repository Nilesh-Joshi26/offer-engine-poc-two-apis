package com.hdfcbank.offerengine.adapter.out.persistence;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.OfferCustomerEntity;
import com.hdfcbank.offerengine.adapter.out.persistence.entity.OfferDetailEntity;
import com.hdfcbank.offerengine.adapter.out.persistence.entity.OfferMerchantKeyEntity;
import com.hdfcbank.offerengine.adapter.out.persistence.repository.OfferCustomerJpaRepository;
import com.hdfcbank.offerengine.adapter.out.persistence.repository.OfferDetailJpaRepository;
import com.hdfcbank.offerengine.adapter.out.persistence.repository.OfferMerchantKeyJpaRepository;
import com.hdfcbank.offerengine.domain.model.CustomerInquiryCriteria;
import com.hdfcbank.offerengine.domain.model.EligibleOfferRecord;
import com.hdfcbank.offerengine.domain.model.MerchantMatch;
import com.hdfcbank.offerengine.domain.model.OfferDetail;
import com.hdfcbank.offerengine.domain.model.OfferStatus;
import com.hdfcbank.offerengine.domain.port.out.OfferRecordRepositoryPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OfferRecordPersistenceAdapter implements OfferRecordRepositoryPort {

    private final OfferCustomerJpaRepository customerRepository;
    private final OfferMerchantKeyJpaRepository merchantRepository;
    private final OfferDetailJpaRepository offerDetailRepository;

    public OfferRecordPersistenceAdapter(OfferCustomerJpaRepository customerRepository,
                                         OfferMerchantKeyJpaRepository merchantRepository,
                                         OfferDetailJpaRepository offerDetailRepository) {
        this.customerRepository = customerRepository;
        this.merchantRepository = merchantRepository;
        this.offerDetailRepository = offerDetailRepository;
    }

    @Override
    public List<EligibleOfferRecord> findLiveMatches(CustomerInquiryCriteria criteria, int maxResults) {
        List<OfferCustomerEntity> customers = customerRepository.findLiveCustomerMatches(
                criteria.campaignName(),
                OfferStatus.ACTIVE,
                blankToNull(criteria.labelCode()),
                blankToNull(criteria.aan()),
                blankToNull(criteria.mobileNumber()),
                blankToNull(criteria.emailId()),
                blankToNull(criteria.merchantId()),
                blankToNull(criteria.merchantName()),
                blankToNull(criteria.keyField3()),
                blankToNull(criteria.keyField4()),
                blankToNull(criteria.keyField5()),
                PageRequest.of(0, maxResults));

        if (customers.isEmpty()) {
            return List.of();
        }

        List<Long> customerIds = customers.stream().map(OfferCustomerEntity::getId).toList();
        Map<Long, List<OfferMerchantKeyEntity>> merchantsByCustomer = merchantRepository
                .findByCustomerRecordIds(customerIds)
                .stream()
                .collect(Collectors.groupingBy(OfferMerchantKeyEntity::getCustomerRecordId));
        Map<Long, List<OfferDetailEntity>> offersByCustomer = offerDetailRepository
                .findLiveOffers(customerIds, OfferStatus.ACTIVE, LocalDate.now())
                .stream()
                .collect(Collectors.groupingBy(OfferDetailEntity::getCustomerRecordId));

        return customers.stream()
                .map(customer -> toDomain(customer,
                        merchantsByCustomer.getOrDefault(customer.getId(), Collections.emptyList()),
                        offersByCustomer.getOrDefault(customer.getId(), Collections.emptyList())))
                .filter(record -> !record.offers().isEmpty())
                .toList();
    }

    private EligibleOfferRecord toDomain(OfferCustomerEntity customer,
                                         List<OfferMerchantKeyEntity> merchants,
                                         List<OfferDetailEntity> offers) {
        return new EligibleOfferRecord(
                customer.getId(),
                customer.getSourceSequenceNumber(),
                customer.getLabelCode(),
                customer.getCampaignName(),
                customer.getAan(),
                customer.getMobileNumber(),
                customer.getEmailId(),
                customer.getKeyField3(),
                customer.getKeyField4(),
                customer.getKeyField5(),
                customer.getStatus(),
                merchants.stream().map(this::toMerchantDomain).toList(),
                offers.stream().map(this::toOfferDomain).toList());
    }

    private MerchantMatch toMerchantDomain(OfferMerchantKeyEntity entity) {
        return new MerchantMatch(entity.getMerchantSequence(), entity.getMerchantName(), entity.getMerchantId());
    }

    private OfferDetail toOfferDomain(OfferDetailEntity entity) {
        return new OfferDetail(entity.getOfferSequence(), entity.getOfferText(), entity.getOfferStartDate(),
                entity.getOfferEndDate(), entity.getOfferTncUrl(), entity.getStatus());
    }

    private String blankToNull(String value) {
        return value == null || value.isBlank() ? null : value;
    }
}
