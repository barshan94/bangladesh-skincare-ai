# Approved Knowledge Update Protocol

- Version: 1.0
- Date: 2026-10-29
- Status: Protocol proposal; transport/signing implementation not selected.

## Scope
Applies to reviewed skincare knowledge, products/ingredients, sources, formula dossiers and safety notices. It does not authorize executable-code updates, photo uploads or privacy-setting changes.

## Release manifest
Each release should contain unique ID/version, schema version, compatible app versions, publication timestamp, publisher/approval identity, included content IDs/versions and statuses, provenance/review metadata, integrity digest/signature metadata, compatible rollback target, change summary and critical notices.

Choose signing/key management before remote updates. Do not invent cryptography.

## Client update sequence
1. Check on app open and at an appropriate scheduled opportunity.
2. Download only package/manifest; send no personal data unless a separately approved feature requires it.
3. Verify publisher/integrity metadata.
4. Check schema/app compatibility.
5. Reject draft/unapproved/expired/withdrawn entries from actionable status.
6. Stage separately from active package.
7. Run structural and safety invariants.
8. Atomically activate only after all checks pass.
9. Record release ID and last successful update.
10. Keep compatible known-good release only while safe.

If a step fails, keep the last safe compatible state and show status. If a critical withdrawal affects active content, disable it rather than continuing unsafe advice for offline convenience.

## Material changes
General reviewed information may update automatically under policy. Material changes to an active routine require explanation and confirmation. Critical safety withdrawal may immediately disable affected content with notice. Updates cannot enable unvalidated image features, add formula without dossier, change privacy consent or upload photos. AI-generated rewrites cannot become reviewed content without the full review workflow.

## Android background constraints
Use Android scheduling as best effort. OS battery/network/app-standby policy may defer work. Check when app opens and show last successful update. Do not promise immediate Wi-Fi-triggered background sync.

## Rollback and revocation
Reject malformed, incompatible, unapproved or integrity-failing releases. Never reactivate a withdrawn safety item through generic rollback. Track affected versions. Disable affected feature/entry without deleting unrelated photos/history. Test interrupted downloads, storage exhaustion, activation failure, corrupt packages, expired entries and emergency withdrawal.

## Privacy boundary
Updates cannot change consent, start model training, upload photos, collect new sensitive fields or enable cloud analysis. New data flow requires separate product/privacy/legal and consent review.