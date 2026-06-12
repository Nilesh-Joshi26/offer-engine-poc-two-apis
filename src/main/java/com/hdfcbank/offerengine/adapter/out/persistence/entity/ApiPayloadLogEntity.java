package com.hdfcbank.offerengine.adapter.out.persistence.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "OE_API_PAYLOAD_LOG")
public class ApiPayloadLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payloadLogSeq")
    @SequenceGenerator(name = "payloadLogSeq", sequenceName = "OE_API_PAYLOAD_LOG_SEQ", allocationSize = 50)
    @Column(name = "LOG_ID")
    private Long id;

    @Column(name = "API_NAME", nullable = false, length = 100)
    private String apiName;

    @Column(name = "HTTP_METHOD", nullable = false, length = 10)
    private String httpMethod;

    @Column(name = "REQUEST_PATH", nullable = false, length = 200)
    private String requestPath;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "REQUEST_PAYLOAD")
    private String requestPayload;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "RESPONSE_PAYLOAD")
    private String responsePayload;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "PURGE_AFTER", nullable = false)
    private LocalDateTime purgeAfter;

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }

    public void setResponsePayload(String responsePayload) {
        this.responsePayload = responsePayload;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPurgeAfter(LocalDateTime purgeAfter) {
        this.purgeAfter = purgeAfter;
    }
}
