package com.hdfcbank.offerengine.adapter.out.persistence;

import com.hdfcbank.offerengine.adapter.out.persistence.entity.ApiPayloadLogEntity;
import com.hdfcbank.offerengine.adapter.out.persistence.repository.ApiPayloadLogJpaRepository;
import com.hdfcbank.offerengine.config.OfferEngineProperties;
import com.hdfcbank.offerengine.domain.port.out.PayloadLogPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class PayloadLogPersistenceAdapter implements PayloadLogPort {

    private final ApiPayloadLogJpaRepository repository;
    private final OfferEngineProperties properties;

    public PayloadLogPersistenceAdapter(ApiPayloadLogJpaRepository repository, OfferEngineProperties properties) {
        this.repository = repository;
        this.properties = properties;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(String apiName, String httpMethod, String requestPath, String requestPayload, String responsePayload) {
        LocalDateTime now = LocalDateTime.now();
        ApiPayloadLogEntity entity = new ApiPayloadLogEntity();
        entity.setApiName(apiName);
        entity.setHttpMethod(httpMethod);
        entity.setRequestPath(requestPath);
        entity.setRequestPayload(requestPayload);
        entity.setResponsePayload(responsePayload);
        entity.setCreatedAt(now);
        entity.setPurgeAfter(now.plusDays(properties.payloadLogRetentionDays()));
        repository.save(entity);
    }
}
