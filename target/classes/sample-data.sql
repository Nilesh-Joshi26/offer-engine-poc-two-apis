-- Optional local Oracle 19c seed data for manual API testing.
-- Apply after schema.sql if JPA_DDL_AUTO=validate.

INSERT INTO OE_LABEL (
    LABEL_ID, LABEL_CODE, CAMPAIGN_NAME, DESCRIPTION, STATUS, EXPIRY_DATE,
    CREATED_AT, CREATED_BY
) VALUES (
    OE_LABEL_SEQ.NEXTVAL, 'MEJUN202601', 'ME Offer', 'Merchant electronics offer campaign',
    'ACTIVE', DATE '2026-12-31', SYSTIMESTAMP, 'seed'
);

INSERT INTO OE_MAIN_OFFER_RECORD (
    RECORD_ID, SEQUENCE_NUMBER, LABEL_CODE, CAMPAIGN_NAME, AAN, MOBILE_NUMBER,
    EMAIL_ID, MERCHANT_ID, VALIDITY_DATE, OFFER_TEXT, OFFER_TNC, STATUS, UPDATED_AT, UPDATED_BY
) VALUES (
    OE_MAIN_OFFER_SEQ.NEXTVAL, 'SEQ-10001', 'MEJUN202601', 'ME Offer', 'AAN1',
    '999XXXXXXX', 'def@example.com', 'A012', DATE '2026-12-31',
    'Spend Rs 20000 and get a voucher on your HDFC Bank Credit Card',
    'https://www.smartbuy.example/tnc', 'ACTIVE', SYSTIMESTAMP, 'seed'
);

COMMIT;
