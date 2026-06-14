package com.hdfcbank.offerengine.adapter.out.persistence.repository;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.OfferDetailEntity;
import com.hdfcbank.offerengine.domain.model.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface OfferDetailJpaRepository extends JpaRepository<OfferDetailEntity, Long> {

    @Query("""
            select d
            from OfferDetailEntity d
            where d.customerRecordId in :customerRecordIds
              and d.status = :status
              and d.offerEndDate >= :currentDate
              and (d.offerStartDate is null or d.offerStartDate <= :currentDate)
            order by d.customerRecordId asc, d.offerSequence asc
            """)
    List<OfferDetailEntity> findLiveOffers(
            @Param("customerRecordIds") Collection<Long> customerRecordIds,
            @Param("status") OfferStatus status,
            @Param("currentDate") LocalDate currentDate);
}
