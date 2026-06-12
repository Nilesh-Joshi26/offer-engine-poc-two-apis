package com.hdfcbank.offerengine.adapter.out.persistence;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.LabelEntity;
import com.hdfcbank.offerengine.adapter.out.persistence.repository.LabelJpaRepository;
import com.hdfcbank.offerengine.domain.model.Label;
import com.hdfcbank.offerengine.domain.port.out.LabelRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LabelPersistenceAdapter implements LabelRepositoryPort {

    private final LabelJpaRepository repository;

    public LabelPersistenceAdapter(LabelJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Label> findByLabelCode(String labelCode) {
        return repository.findByLabelCode(labelCode).map(this::toDomain);
    }

    @Override
    public boolean existsByLabelCode(String labelCode) {
        return repository.existsByLabelCode(labelCode);
    }

    @Override
    public Label save(Label label) {
        return toDomain(repository.save(toEntity(label)));
    }

    private Label toDomain(LabelEntity entity) {
        return new Label(entity.getId(), entity.getLabelCode(), entity.getCampaignName(), entity.getDescription(),
                entity.getStatus(), entity.getExpiryDate(), entity.getCreatedAt(), entity.getCreatedBy(),
                entity.getUpdatedAt(), entity.getUpdatedBy());
    }

    private LabelEntity toEntity(Label label) {
        LabelEntity entity = new LabelEntity();
        entity.setId(label.id());
        entity.setLabelCode(label.labelCode());
        entity.setCampaignName(label.campaignName());
        entity.setDescription(label.description());
        entity.setStatus(label.status());
        entity.setExpiryDate(label.expiryDate());
        entity.setCreatedAt(label.createdAt());
        entity.setCreatedBy(label.createdBy());
        entity.setUpdatedAt(label.updatedAt());
        entity.setUpdatedBy(label.updatedBy());
        return entity;
    }
}
