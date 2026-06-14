package com.hdfcbank.offerengine.adapter.out.persistence.entity;

import com.hdfcbank.offerengine.domain.model.OfferStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "OE_OFFER_CUSTOMER")
public class OfferCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offerCustomerSeq")
    @SequenceGenerator(name = "offerCustomerSeq", sequenceName = "OE_OFFER_CUSTOMER_SEQ", allocationSize = 50)
    @Column(name = "CUSTOMER_RECORD_ID")
    private Long id;

    @Column(name = "SOURCE_SEQUENCE_NUMBER", nullable = false, unique = true, length = 50)
    private String sourceSequenceNumber;

    @Column(name = "LABEL_CODE", nullable = false, length = 50)
    private String labelCode;

    @Column(name = "CAMPAIGN_NAME", nullable = false, length = 100)
    private String campaignName;

    @Column(name = "AAN", length = 50)
    private String aan;

    @Column(name = "MOBILE_NUMBER", length = 50)
    private String mobileNumber;

    @Column(name = "EMAIL_ID", length = 100)
    private String emailId;

    @Column(name = "KEY_FIELD_3", length = 50)
    private String keyField3;

    @Column(name = "KEY_FIELD_4", length = 50)
    private String keyField4;

    @Column(name = "KEY_FIELD_5", length = 50)
    private String keyField5;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private OfferStatus status;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSourceSequenceNumber() { return sourceSequenceNumber; }
    public void setSourceSequenceNumber(String sourceSequenceNumber) { this.sourceSequenceNumber = sourceSequenceNumber; }
    public String getLabelCode() { return labelCode; }
    public void setLabelCode(String labelCode) { this.labelCode = labelCode; }
    public String getCampaignName() { return campaignName; }
    public void setCampaignName(String campaignName) { this.campaignName = campaignName; }
    public String getAan() { return aan; }
    public void setAan(String aan) { this.aan = aan; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }
    public String getKeyField3() { return keyField3; }
    public void setKeyField3(String keyField3) { this.keyField3 = keyField3; }
    public String getKeyField4() { return keyField4; }
    public void setKeyField4(String keyField4) { this.keyField4 = keyField4; }
    public String getKeyField5() { return keyField5; }
    public void setKeyField5(String keyField5) { this.keyField5 = keyField5; }
    public OfferStatus getStatus() { return status; }
    public void setStatus(OfferStatus status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
