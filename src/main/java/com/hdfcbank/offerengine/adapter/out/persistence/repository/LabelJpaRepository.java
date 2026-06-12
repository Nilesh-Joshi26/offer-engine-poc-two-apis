package com.hdfcbank.offerengine.adapter.out.persistence.repository;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabelJpaRepository extends JpaRepository<LabelEntity, Long> {

    Optional<LabelEntity> findByLabelCode(String labelCode);

    boolean existsByLabelCode(String labelCode);
}
