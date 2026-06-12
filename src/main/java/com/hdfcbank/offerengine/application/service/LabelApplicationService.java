package com.hdfcbank.offerengine.application.service;

import com.hdfcbank.offerengine.application.dto.CreateLabelRequest;
import com.hdfcbank.offerengine.application.dto.LabelResponse;
import com.hdfcbank.offerengine.application.dto.ModifyLabelRequest;
import com.hdfcbank.offerengine.domain.port.in.LabelMaintenanceUseCase;
import com.hdfcbank.offerengine.domain.port.out.PayloadLogPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class LabelApplicationService {

    private static final Logger log = LoggerFactory.getLogger(LabelApplicationService.class);

    private final LabelMaintenanceUseCase labelMaintenanceUseCase;
    private final PayloadLogPort payloadLogPort;
    private final ObjectMapper objectMapper;

    public LabelApplicationService(LabelMaintenanceUseCase labelMaintenanceUseCase,
                                   PayloadLogPort payloadLogPort,
                                   ObjectMapper objectMapper) {
        this.labelMaintenanceUseCase = labelMaintenanceUseCase;
        this.payloadLogPort = payloadLogPort;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public LabelResponse create(CreateLabelRequest request) {
        LabelResponse response = LabelResponse.from(labelMaintenanceUseCase.createLabel(
                request.labelCode(), request.campaignName(), request.description(), request.expiryDate(), request.createdBy()));
        logPayload("API 1 - Label Create / Modify", "POST", "/api/v1/labels", request, response);
        return response;
    }

    @Transactional
    public LabelResponse modify(String labelCode, ModifyLabelRequest request) {
        LabelResponse response = LabelResponse.from(labelMaintenanceUseCase.modifyLabel(
                labelCode, request.description(), request.expiryDate(), request.updatedBy()));
        logPayload("API 1 - Label Create / Modify", "PUT", "/api/v1/labels/" + labelCode, request, response);
        return response;
    }

    @Transactional
    public LabelResponse delete(String labelCode, String deletedBy) {
        LabelResponse response = LabelResponse.from(labelMaintenanceUseCase.deleteLabel(labelCode, deletedBy));
        logPayload("API 1 - Label Create / Modify", "DELETE", "/api/v1/labels/" + labelCode,
                "deletedBy=" + deletedBy, response);
        return response;
    }

    private void logPayload(String apiName, String method, String path, Object request, Object response) {
        try {
            payloadLogPort.save(apiName, method, path, objectMapper.writeValueAsString(request), objectMapper.writeValueAsString(response));
        } catch (JacksonException ex) {
            log.warn("Unable to serialize API payload for {}", apiName, ex);
        }
    }
}
