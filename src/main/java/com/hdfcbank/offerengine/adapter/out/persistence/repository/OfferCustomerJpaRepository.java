package com.hdfcbank.offerengine.adapter.out.persistence.repository;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.OfferCustomerEntity;
import com.hdfcbank.offerengine.domain.model.OfferStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferCustomerJpaRepository extends JpaRepository<OfferCustomerEntity, Long> {

    @Query("""
            select distinct c
            from OfferCustomerEntity c
            where c.campaignName = :campaignName
              and c.status = :status
              and (:labelCode is null or c.labelCode = :labelCode)
              and (:aan is null or c.aan = :aan)
              and (:mobileNumber is null or c.mobileNumber = :mobileNumber)
              and (:emailId is null or lower(c.emailId) = lower(:emailId))
              and (:keyField3 is null or c.keyField3 = :keyField3)
              and (:keyField4 is null or c.keyField4 = :keyField4)
              and (:keyField5 is null or c.keyField5 = :keyField5)
              and (:merchantId is null or exists (
                    select m.id from OfferMerchantKeyEntity m
                    where m.customerRecordId = c.id and m.merchantId = :merchantId))
              and (:merchantName is null or exists (
                    select m.id from OfferMerchantKeyEntity m
                    where m.customerRecordId = c.id and lower(m.merchantName) = lower(:merchantName)))
            order by c.sourceSequenceNumber asc
            """)
    List<OfferCustomerEntity> findLiveCustomerMatches(
            @Param("campaignName") String campaignName,
            @Param("status") OfferStatus status,
            @Param("labelCode") String labelCode,
            @Param("aan") String aan,
            @Param("mobileNumber") String mobileNumber,
            @Param("emailId") String emailId,
            @Param("merchantId") String merchantId,
            @Param("merchantName") String merchantName,
            @Param("keyField3") String keyField3,
            @Param("keyField4") String keyField4,
            @Param("keyField5") String keyField5,
            Pageable pageable);
}
