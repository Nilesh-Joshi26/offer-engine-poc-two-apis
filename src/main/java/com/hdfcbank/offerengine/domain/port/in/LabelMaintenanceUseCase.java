package com.hdfcbank.offerengine.domain.port.in;

import com.hdfcbank.offerengine.domain.model.Label;

import java.time.LocalDate;

public interface LabelMaintenanceUseCase {

    Label createLabel(String labelCode, String campaignName, String description, LocalDate expiryDate, String createdBy);

    Label modifyLabel(String labelCode, String description, LocalDate expiryDate, String updatedBy);

    Label deleteLabel(String labelCode, String deletedBy);
}
