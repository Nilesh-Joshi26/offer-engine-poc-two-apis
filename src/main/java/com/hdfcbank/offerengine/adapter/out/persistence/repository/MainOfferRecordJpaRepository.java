package com.hdfcbank.offerengine.adapter.out.persistence.repository;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.MainOfferRecordEntity;
import com.hdfcbank.offerengine.domain.model.OfferStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MainOfferRecordJpaRepository extends JpaRepository<MainOfferRecordEntity, Long> {

    @Query("""
            select r
            from MainOfferRecordEntity r
            where r.campaignName = :campaignName
              and r.status = :status
              and r.validityDate >= :currentDate
              and (:aan is null or r.aan = :aan)
              and (:mobileNumber is null or r.mobileNumber = :mobileNumber)
              and (:emailId is null or lower(r.emailId) = lower(:emailId))
              and (:merchantId is null or r.merchantId = :merchantId)
            order by r.validityDate asc, r.sequenceNumber asc
            """)
    List<MainOfferRecordEntity> findLiveMatches(
            @Param("campaignName") String campaignName,
            @Param("status") OfferStatus status,
            @Param("currentDate") LocalDate currentDate,
            @Param("aan") String aan,
            @Param("mobileNumber") String mobileNumber,
            @Param("emailId") String emailId,
            @Param("merchantId") String merchantId,
            Pageable pageable);
}
