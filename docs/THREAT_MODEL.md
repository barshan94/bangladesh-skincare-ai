# Privacy and Security Threat Model

- Version: 1.0
- Date: 2026-10-29
- Status: Preliminary; reconcile with final architecture and qualified privacy/legal review.

## Assets
Original face/body photos and EXIF; questionnaire/safety answers; progress notes and routine history; consent/adult eligibility; approved knowledge packages/integrity keys; model weights/validation metadata; optional account tokens if approved; APK signing key/release credentials.

## Trust boundaries
1. User/device input to app validation.
2. Android camera/photo picker to app-private storage.
3. UI/domain rules to local database/files.
4. External knowledge source to verified staging and active store.
5. Optional network/API boundary if approved.
6. Build machine/CI to signed APK distribution.
7. User-exported data to external storage/apps.

## Threats and controls

### T-01 Accidental photo upload
Risk: dependency/analytics silently sends images. Controls: no upload code in local-first core; no raw-photo telemetry; dependency audit and network inspection; separate consent if cloud analysis is later approved.

### T-02 EXIF/location leakage
Risk: image metadata exposes location/device details. Controls: no location permission; test metadata handling; strip metadata from exports when appropriate; explain retained original metadata.

### T-03 Local data exposure
Risk: backups, logs, screenshots, shared device or device compromise expose records. Controls: app-private storage, minimize fields, review backup rules/task-switcher previews, avoid logs, document device-compromise limits; review encryption-at-rest requirements.

### T-04 Consent confusion
Risk: one acceptance grants cloud storage, analysis and training. Controls: separate purpose choices default off, versioned consent, visible withdrawal, network tests.

### T-05 Malicious knowledge update
Risk: forged/corrupt content changes formula or safety rules. Controls: approved publisher, integrity/signature design, schema/compatibility checks, staging, atomic swap, rollback and revocation.

### T-06 Unsafe model provenance
Risk: unlicensed or unsuitable model shipped. Controls: inventory code/weights/datasets; verify licences; hash/version artefacts; independent validation; no activation without approval.

### T-07 Prompt injection/unsafe generated advice
Risk: untrusted text leads AI to override safety. Controls: deterministic structured rendering preferred; constrain any LLM to approved content; safety logic outside model control; adversarial tests; no formula generation.

### T-08 Broken deletion
Risk: database record disappears but photo file/cloud copy remains. Controls: transactional or compensating deletion, orphan audit, cloud deletion semantics if added, fault injection and truthful UI.

### T-09 Secret/signing-key leakage
Risk: credentials exposed in Git/CI/APK. Controls: no secrets in repo/APK; protected signing process; least privilege; rotate exposed secrets; scanning and recovery procedure.

### T-10 Dependency supply-chain risk
Risk: vulnerable/unlicensed dependency. Controls: pin versions, review licences/maintenance, vulnerability scanning, update review, dependency inventory and SBOM where feasible.

### T-11 Unnecessary backend collection
Risk: cloud collects data not needed for core. Controls: no backend until justified and approved; minimize fields; review retention/deletion/processors/cross-border flow first.

### T-12 Shared-device exposure
Risk: family/other users see history. Controls: assess discreet notifications, optional app lock, task-switcher preview and shared-device warning; do not promise protection from an unlocked compromised device.

## Security requirements
- No credentials, keys, signing keystore or private data in Git.
- No raw photos/sensitive answers in logs, analytics or crash reports.
- Fail closed for integrity, approval and compatibility checks.
- Never accept remote executable-code updates.
- Minimize network features/permissions.
- Validate imported files; handle malformed inputs safely.
- Use platform cryptography; do not invent cryptographic primitives.
- Define backup, screenshot, export and deletion policy before release.
- Document release signing and provenance.

## Data-flow review
For every feature record data, purpose, location, encryption/backup, network destination/country, consent/legal basis, retention/deletion, access controls and tests.

## Security tests
Static/dependency/licence review; network inspection; consent bypass tests; malformed image/package tests; tampered/unapproved/incompatible release tests; migration/deletion fault injection; secret scanning; release artifact inspection; shared-device review; threat-model refresh after adding backend, model provider, analytics or cloud functionality.