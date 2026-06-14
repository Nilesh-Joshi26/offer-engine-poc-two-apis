# Offer Engine PoC - Two APIs

Selected PoC APIs:

1. API 1 - Label Create / Modify
   - `POST /api/v1/labels`
   - `PUT /api/v1/labels/{labelCode}`
   - `DELETE /api/v1/labels/{labelCode}`

2. API 6 - Customer Inquiry
   - `POST /api/v1/customer-inquiry`

Source alignment:

- `GDH API Details.xlsx` lists API 1 as Label Create / Modify with connectivity `Frontend -> Middleware -> App -> DB`.
- `GDH API Details.xlsx` lists API 6 as Customer Inquiry with connectivity `Merchant site -> API Gateway -> Middleware -> App -> DB`.
- `CR_12-05-2026_Offer Engine.docx` defines live lookup by campaign plus search keys, returning records where validity date is greater than or equal to current date and including the input payload in the response.
- `Sample offer file dump.xlsx` confirms that one customer row can contain multiple merchant keys and multiple offer slots.

## Branch: Updated-customer-inquiry

This branch enhances API 6 - Customer Inquiry to align with the uploaded sample offer ingestion file.

### What changed

- Customer Inquiry request now supports additional keys:
  - `labelCode`
  - `merchantName`
  - `keyField3`
  - `keyField4`
  - `keyField5`
- Customer Inquiry response now returns:
  - `isEligible`
  - `matchCount`
  - matched customer records
  - multiple merchant keys per customer record
  - multiple valid offers per customer record
- Added normalized backend tables:
  - `OE_OFFER_CUSTOMER`
  - `OE_OFFER_MERCHANT_KEY`
  - `OE_OFFER_DETAIL`
- Legacy table `OE_MAIN_OFFER_RECORD` is retained for backward compatibility with the earlier PoC scripts.

### Updated Customer Inquiry sample request

```json
{
  "campaignName": "ME Offer",
  "labelCode": "MEJUN202601",
  "aan": "AAN1",
  "merchantId": "A012"
}
```

Alternative request using merchant name:

```json
{
  "campaignName": "ME Offer",
  "merchantName": "Amazon",
  "mobileNumber": "999XXXXXXX"
}
```

Expected response shape:

```json
{
  "success": true,
  "message": "SUCCESS",
  "data": {
    "inputPayload": {
      "campaignName": "ME Offer",
      "labelCode": "MEJUN202601",
      "aan": "AAN1",
      "merchantId": "A012"
    },
    "isEligible": true,
    "matchCount": 1,
    "matches": [
      {
        "sourceSequenceNumber": "SRC-10001",
        "labelCode": "MEJUN202601",
        "campaignName": "ME Offer",
        "aan": "AAN1",
        "mobileNumber": "999XXXXXXX",
        "emailId": "def@example.com",
        "keyField3": "KF3-001",
        "keyField4": "KF4-001",
        "keyField5": "KF5-001",
        "status": "ACTIVE",
        "merchants": [
          {
            "merchantSequence": 1,
            "merchantName": "Amazon",
            "merchantId": "A012"
          }
        ],
        "offers": [
          {
            "offerSequence": 1,
            "offerText": "Spend Rs 20000 and get Rs 500 voucher on eligible HDFC Bank Credit Card transaction",
            "offerStartDate": "2026-01-01",
            "offerEndDate": "2026-12-31",
            "offerTncUrl": "https://www.smartbuy.example/tnc",
            "status": "ACTIVE"
          }
        ]
      }
    ]
  }
}
```

## Run locally with Oracle 19c

```powershell
cd C:\Projects\offer-engine-poc-two-apis
$env:DB_URL="jdbc:oracle:thin:@//localhost:1521/orclpdb"
$env:DB_USERNAME="OFFERENGINE"
$env:DB_PASSWORD="OfferEngine#123"
$env:JPA_DDL_AUTO="validate"
mvn spring-boot:run
```

For a fresh schema, apply:

```powershell
sqlplus OFFERENGINE/OfferEngine#123@//localhost:1521/orclpdb @database/02-create-offerengine-schema.sql
sqlplus OFFERENGINE/OfferEngine#123@//localhost:1521/orclpdb @database/03-seed-sample-data.sql
```

For an existing v1 PoC database, first apply the normalized customer inquiry migration, then seed sample data:

```powershell
sqlplus OFFERENGINE/OfferEngine#123@//localhost:1521/orclpdb @database/04-updated-customer-inquiry-normalized-schema.sql
sqlplus OFFERENGINE/OfferEngine#123@//localhost:1521/orclpdb @database/03-seed-sample-data.sql
```

## Smoke requests

```powershell
$auth = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("admin:admin123"))

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/customer-inquiry" `
  -Headers @{ Authorization = "Basic $auth" } `
  -ContentType "application/json" `
  -Body '{"campaignName":"ME Offer","labelCode":"MEJUN202601","aan":"AAN1","merchantId":"A012"}'

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/labels" `
  -Headers @{ Authorization = "Basic $auth" } `
  -ContentType "application/json" `
  -Body '{"labelCode":"POSTMAN_TEST_001","campaignName":"ME Offer","description":"Local test label","expiryDate":"2026-12-31","createdBy":"maker01"}'
```

## Assumption

API 1 delete is implemented as a soft delete (`status=DELETED`) because the RD sample mentions create/modify/delete for campaign/base maintenance, while the GDH API row explicitly names create/modify. Full publish/deactivate movement to purging tables belongs to API 4 and is not part of this two-API PoC.
