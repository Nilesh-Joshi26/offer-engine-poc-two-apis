package com.hdfcbank.offerengine.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "OE_OFFER_MERCHANT_KEY")
public class OfferMerchantKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offerMerchantKeySeq")
    @SequenceGenerator(name = "offerMerchantKeySeq", sequenceName = "OE_OFFER_MERCHANT_KEY_SEQ", allocationSize = 50)
    @Column(name = "MERCHANT_KEY_ID")
    private Long id;

    @Column(name = "CUSTOMER_RECORD_ID", nullable = false)
    private Long customerRecordId;

    @Column(name = "MERCHANT_SEQUENCE", nullable = false)
    private Integer merchantSequence;

    @Column(name = "MERCHANT_NAME", length = 100)
    private String merchantName;

    @Column(name = "MERCHANT_ID", length = 50)
    private String merchantId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCustomerRecordId() { return customerRecordId; }
    public void setCustomerRecordId(Long customerRecordId) { this.customerRecordId = customerRecordId; }
    public Integer getMerchantSequence() { return merchantSequence; }
    public void setMerchantSequence(Integer merchantSequence) { this.merchantSequence = merchantSequence; }
    public String getMerchantName() { return merchantName; }
    public void setMerchantName(String merchantName) { this.merchantName = merchantName; }
    public String getMerchantId() { return merchantId; }
    public void setMerchantId(String merchantId) { this.merchantId = merchantId; }
}
