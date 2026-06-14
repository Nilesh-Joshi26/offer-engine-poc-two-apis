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

@Entity
@Table(name = "OE_OFFER_DETAIL")
public class OfferDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offerDetailSeq")
    @SequenceGenerator(name = "offerDetailSeq", sequenceName = "OE_OFFER_DETAIL_SEQ", allocationSize = 50)
    @Column(name = "OFFER_DETAIL_ID")
    private Long id;

    @Column(name = "CUSTOMER_RECORD_ID", nullable = false)
    private Long customerRecordId;

    @Column(name = "OFFER_SEQUENCE", nullable = false)
    private Integer offerSequence;

    @Column(name = "OFFER_TEXT", length = 1000)
    private String offerText;

    @Column(name = "OFFER_START_DATE")
    private LocalDate offerStartDate;

    @Column(name = "OFFER_END_DATE", nullable = false)
    private LocalDate offerEndDate;

    @Column(name = "OFFER_TNC_URL", length = 500)
    private String offerTncUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 20)
    private OfferStatus status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCustomerRecordId() { return customerRecordId; }
    public void setCustomerRecordId(Long customerRecordId) { this.customerRecordId = customerRecordId; }
    public Integer getOfferSequence() { return offerSequence; }
    public void setOfferSequence(Integer offerSequence) { this.offerSequence = offerSequence; }
    public String getOfferText() { return offerText; }
    public void setOfferText(String offerText) { this.offerText = offerText; }
    public LocalDate getOfferStartDate() { return offerStartDate; }
    public void setOfferStartDate(LocalDate offerStartDate) { this.offerStartDate = offerStartDate; }
    public LocalDate getOfferEndDate() { return offerEndDate; }
    public void setOfferEndDate(LocalDate offerEndDate) { this.offerEndDate = offerEndDate; }
    public String getOfferTncUrl() { return offerTncUrl; }
    public void setOfferTncUrl(String offerTncUrl) { this.offerTncUrl = offerTncUrl; }
    public OfferStatus getStatus() { return status; }
    public void setStatus(OfferStatus status) { this.status = status; }
}
