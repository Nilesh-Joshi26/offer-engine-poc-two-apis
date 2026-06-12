package com.hdfcbank.offerengine.adapter.out.persistence.repository;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.ApiPayloadLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiPayloadLogJpaRepository extends JpaRepository<ApiPayloadLogEntity, Long> {
}
