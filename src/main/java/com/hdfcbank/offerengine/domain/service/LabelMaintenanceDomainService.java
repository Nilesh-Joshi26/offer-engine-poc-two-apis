package com.hdfcbank.offerengine.domain.service;

import com.hdfcbank.offerengine.domain.model.Label;
import com.hdfcbank.offerengine.domain.model.LabelStatus;
import com.hdfcbank.offerengine.domain.port.in.LabelMaintenanceUseCase;
import com.hdfcbank.offerengine.domain.port.out.LabelRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class LabelMaintenanceDomainService implements LabelMaintenanceUseCase {

    private final LabelRepositoryPort labelRepository;

    public LabelMaintenanceDomainService(LabelRepositoryPort labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label createLabel(String labelCode, String campaignName, String description, LocalDate expiryDate, String createdBy) {
        requireFutureOrToday(expiryDate, "expiryDate");
        if (labelRepository.existsByLabelCode(labelCode)) {
            throw new DomainValidationException("Label code already exists: " + labelCode);
        }
        Label label = new Label(null, labelCode, campaignName, description, LabelStatus.DRAFT,
                expiryDate, LocalDateTime.now(), createdBy, null, null);
        return labelRepository.save(label);
    }

    @Override
    public Label modifyLabel(String labelCode, String description, LocalDate expiryDate, String updatedBy) {
        requireFutureOrToday(expiryDate, "expiryDate");
        Label existing = labelRepository.findByLabelCode(labelCode)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found: " + labelCode));
        if (existing.status() == LabelStatus.DELETED) {
            throw new DomainValidationException("Deleted label cannot be modified: " + labelCode);
        }
        Label modified = new Label(existing.id(), existing.labelCode(), existing.campaignName(), description,
                existing.status(), expiryDate, existing.createdAt(), existing.createdBy(), LocalDateTime.now(), updatedBy);
        return labelRepository.save(modified);
    }

    @Override
    public Label deleteLabel(String labelCode, String deletedBy) {
        Label existing = labelRepository.findByLabelCode(labelCode)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found: " + labelCode));
        Label deleted = new Label(existing.id(), existing.labelCode(), existing.campaignName(), existing.description(),
                LabelStatus.DELETED, existing.expiryDate(), existing.createdAt(), existing.createdBy(),
                LocalDateTime.now(), deletedBy);
        return labelRepository.save(deleted);
    }

    private void requireFutureOrToday(LocalDate date, String fieldName) {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new DomainValidationException(fieldName + " must be greater than or equal to current date");
        }
    }
}
