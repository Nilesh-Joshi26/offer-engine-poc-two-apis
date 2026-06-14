package com.hdfcbank.offerengine.adapter.out.persistence.repository;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.OfferMerchantKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface OfferMerchantKeyJpaRepository extends JpaRepository<OfferMerchantKeyEntity, Long> {

    @Query("""
            select m
            from OfferMerchantKeyEntity m
            where m.customerRecordId in :customerRecordIds
            order by m.customerRecordId asc, m.merchantSequence asc
            """)
    List<OfferMerchantKeyEntity> findByCustomerRecordIds(@Param("customerRecordIds") Collection<Long> customerRecordIds);
}
