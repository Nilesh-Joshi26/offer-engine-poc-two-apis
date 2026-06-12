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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "OE_MAIN_OFFER_RECORD")
public class MainOfferRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mainOfferSeq")
    @SequenceGenerator(name = "mainOfferSeq", sequenceName = "OE_MAIN_OFFER_SEQ", allocationSize = 50)
    @Column(name = "RECORD_ID")
    private Long id;

    @Column(name = "SEQUENCE_NUMBER", nullable = false, unique = true, length = 50)
    private String sequenceNumber;

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

    @Column(name = "MERCHANT_ID", length = 50)
    private String merchantId;

    @Column(name = "VALIDITY_DATE", nullable = false)
    private LocalDate validityDate;

    @Column(name = "OFFER_TEXT", length = 250)
    private String offerText;

    @Column(name = "OFFER_TNC", length = 500)
    private String offerTnc;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private OfferStatus status;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getAan() {
        return aan;
    }

    public void setAan(String aan) {
        this.aan = aan;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public String getOfferText() {
        return offerText;
    }

    public void setOfferText(String offerText) {
        this.offerText = offerText;
    }

    public String getOfferTnc() {
        return offerTnc;
    }

    public void setOfferTnc(String offerTnc) {
        this.offerTnc = offerTnc;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
