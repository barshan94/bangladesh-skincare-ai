# Test Strategy and Quality Gates

- Version: 1.0
- Date: 2026-10-29
- Status: Plan only; test implementation has not started.

## Quality philosophy
A passing build proves compilation, not safety, clinical validity, fairness, privacy compliance or measurement accuracy. Each claim needs its own evidence. Safety rules must be testable without a live AI service.

## Test layers

### Unit tests
Questionnaire validation; missing/unknown answers; eligibility and contraindication filters; ranking after safety filtering; cost/unit calculations and stale-price labels; knowledge approval/expiry; consent-purpose isolation; comparability and inconclusive logic; formula gating; update compatibility/integrity decisions; localization keys.

### Domain/property tests
- Unsafe/ineligible entries are never returned regardless of score, price or user preference.
- Adding an ineligible candidate cannot make it eligible.
- Draft/expired/withdrawn content is never actionable.
- A photo alone cannot change a routine.
- Missing safety input never defaults to safe.
- Consent for one purpose never implies another.
- Invalid knowledge release never replaces active release.
- Non-comparable photo pairs never yield improvement claims.

### Integration tests
Local database/file lifecycle; photo save/delete and orphan cleanup; routine versioning/confirmation; knowledge staging/verification/atomic activation/rollback; schema migrations; offline behavior; optional sync only if approved.

### UI/accessibility tests
All documented screens/navigation; Bangla/English switching; large fonts and screen readers; touch targets/contrast; permission granted/denied/permanently denied; empty/loading/offline/error/retry/cancel states; back navigation and process death.

### Device tests
Clean install/upgrade; low storage/memory; camera unavailable; background/resume; image orientation/EXIF/compression/front/rear camera and realistic lighting; model latency/memory/battery on target matrix.

### Model validation
Separate from ordinary software tests. Use locked evaluation data and pre-approved thresholds. Report subgroup results by relevant skin tone/device/lighting, uncertainty, repeatability, abstention, false acceptance/rejection and reference agreement. Unit tests do not approve a model.

### Security/privacy tests
No unintended network transfer; no raw photos in logs; consent isolation; deletion/backup behavior; tampered package rejection; secret scanning and release artifact inspection.

## Required scenario matrix
- Photo capture skipped; routine/manual progress still usable.
- Camera permission denied.
- Blurry/dark/overexposed/filtered/rotated/poorly framed photo.
- Two photos differ in lighting/device/pose and are not comparable.
- Unsupported concern or no safe recommendation.
- Safety answer unknown/declined/contradictory.
- Stale price/unknown availability.
- No safe replacement.
- DIY formula draft/expired/withdrawn/missing review.
- User reports irritation.
- Knowledge update offline/corrupt/unapproved/incompatible/interrupted.
- Process stops during migration/deletion.
- Consent withdrawn after prior sync, if cloud is ever implemented.
- Long Bangla copy on small screen.
- Model fails/times out; output is error/inconclusive, never fabricated.

## Acceptance criteria
Every backlog item defines requirement IDs, positive and negative/safety cases, automated tests/manual expert review, device/localization coverage, data/consent impact, offline/error behavior, evidence owner and feature activation gate.

Do not invent final model thresholds. Set them before locked evaluation and obtain expert approval.

## CI and release gates
Before PR acceptance: formatting/lint/static analysis, unit/domain tests, relevant integration/UI tests, current dependency/licence review, no secrets/photos in changes, updated requirement/doc links and risk review.

Before pilot: critical/high-risk safety cases resolved; privacy/deletion tests pass; content/legal gates signed; each enabled AI feature passes its own gate; incident/rollback procedures exercised.

Before APK release: reproducible tagged build, verified signature, clean install/upgrade/migration/rollback tests, version/changelog/known limitations published, no unresolved release blocker.

## Defect severity
- S0 Critical: plausible serious harm, sensitive-data leakage, unsafe formula/feature activated, integrity bypass. Stop release/disable feature.
- S1 High: safety/privacy behavior broken or misleading actionable output. Block affected release.
- S2 Medium: important workflow broken with safe workaround.
- S3 Low: cosmetic/low-impact usability issue.
Safety incidents require qualified review, not developer/AI judgment alone.