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
- `GDH upload and flow OFFERS 5th March.xlsx` confirms merchant inquiry and AAN/mobile/email/key-field based eligibility lookup.

Assumption:

- API 1 delete is implemented as a soft delete (`status=DELETED`) because the RD sample mentions create/modify/delete for campaign/base maintenance, while the GDH API row explicitly names create/modify. Full publish/deactivate movement to purging tables belongs to API 4 and is not part of this two-API PoC.

Run locally with Oracle 19c:

```powershell
cd C:\Users\Nilesh\Downloads\samples\offer-engine-poc-two-apis
$env:DB_URL="jdbc:oracle:thin:@//localhost:1521/orclpdb"
$env:DB_USERNAME="OFFERENGINE"
$env:DB_PASSWORD="offerengine"
mvn spring-boot:run
```

For controlled environments, apply `src/main/resources/schema.sql` and run with:

```powershell
$env:JPA_DDL_AUTO="validate"
```

Optional sample data:

```powershell
sqlplus OFFERENGINE/offerengine@//localhost:1521/orclpdb @src/main/resources/sample-data.sql
```

Smoke requests:

```powershell
$auth = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes("admin:admin123"))

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/labels" `
  -Headers @{ Authorization = "Basic $auth" } `
  -ContentType "application/json" `
  -Body '{"labelCode":"MEJUL202601","campaignName":"ME Offer","description":"July campaign","expiryDate":"2026-12-31","createdBy":"maker01"}'

Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/v1/customer-inquiry" `
  -Headers @{ Authorization = "Basic $auth" } `
  -ContentType "application/json" `
  -Body '{"campaignName":"ME Offer","aan":"AAN1","merchantId":"A012"}'
```
