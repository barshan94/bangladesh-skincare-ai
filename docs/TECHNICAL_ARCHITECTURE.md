# Proposed Technical Architecture

**Status:** Candidate architecture only; not approved for implementation.
**Last updated:** 2026-10-29

## 1. Platform

Leading candidate: native Android using Kotlin and Jetpack Compose. The app is Android-only initially and targets a private installable APK. Confirm minimum Android version and target device matrix before implementation.

## 2. High-level components

1. **Presentation layer:** accessible Bangla/English Compose UI, guided capture, routine, progress and privacy screens.
2. **Domain layer:** questionnaire interpretation, deterministic eligibility/safety rules, routine ranking and material-change confirmation.
3. **Knowledge layer:** versioned reviewed ingredients, products, evidence, contraindications, costs, alternatives and formula metadata.
4. **Image pipeline:** capture guidance, quality gate, region alignment, optional validated model inference and uncertainty reporting.
5. **Local data:** Room or equivalent for structured records; app-private storage for photos.
6. **Sync (optional):** integrity-checked, versioned knowledge releases; WorkManager for eligible background work subject to Android scheduling.
7. **Optional backend (unresolved):** only if justified for account sync, knowledge delivery or separately consented cloud analysis.
8. **AI explanation layer (optional):** constrained to explain approved knowledge; cannot change safety rules or invent treatment formulas.

## 3. Local-first design

- Store original images and progress history on-device by default.
- Do not upload photos automatically.
- Keep image processing on-device where a suitable model meets requirements.
- Separate optional cloud storage consent from optional cloud inference consent.
- Use Android's supported camera/photo-picker mechanisms and minimum permissions.
- Document OS backup/device transfer behavior; “device-only” does not automatically mean excluded from all platform backups.
- Basic features should not require account creation where feasible.

## 4. Image inference

A pipeline should reject unsuitable captures before attempting measurements. Use consistent preprocessing and avoid enhancement/white-balance transformations that could be mistaken for actual skin change.

Candidate components:
- Face detection/landmarks for framing and alignment.
- Segmentation only if the chosen model provides suitable labels and validates on the target use.
- Custom on-device model only if a narrow task needs it and data/licensing/validation are adequate.

No model/runtime is selected. Evaluate model size, APK size, memory, CPU/GPU/NPU support, warm/cold latency, battery use, Android compatibility, licence and offline behavior. Inference failures must result in a safe error or inconclusive output, never a fabricated result.

## 5. Deterministic recommendation engine

Recommended flow:
1. Validate user input.
2. Apply hard safety/eligibility/contraindication filters.
3. Rank eligible choices using reviewed evidence, relevance, affordability, availability and simplicity.
4. Render recommendations from structured, approved content.
5. Require confirmation for material changes.
6. Store routine/version history and follow-up feedback.

Generative AI may help explain approved content but must not make independent safety decisions or create new actionable formulas.

## 6. Knowledge versioning and updates

Each release should include a version, schema compatibility, approval status, integrity hash/signature strategy, publication date, source/review metadata and rollback compatibility. Verify before activation. Invalid or incompatible updates must be rejected.

Use Android background scheduling only as best effort; OS battery/network policies can defer work. Check updates when the app opens and at suitable opportunities. Do not promise instant background sync. Preserve safe offline behavior and show last successful update/version.

## 7. Potential local data model

Design remains open, but likely entities include:
- UserPreferences (minimal, local-first).
- QuestionnaireResponse (versioned; sensitive fields minimized).
- PhotoSession (timestamp, capture metadata, quality status, storage location).
- MeasurementResult (feature/model/version, method, value, uncertainty, comparability).
- Routine and RoutineVersion (structured approved actions, rationale, source references).
- RoutineEvent (user confirmation, adherence, tolerance, change history).
- KnowledgeRelease and KnowledgeEntry (version, approval, source/review metadata).
- ConsentRecord (purpose, version, timestamp, withdrawal status).
- Optional AccountSyncMetadata if cloud functionality is approved.

Do not store raw photos in cloud records by default. Define retention and deletion behavior before implementation.

## 8. Security and release engineering

- Keep API keys, credentials, keystores and signing keys out of Git.
- Restrict sensitive data in logs and crash reports.
- Verify data deletion, update integrity, offline failure handling and migration behavior.
- Build signed, versioned APKs with traceable source commit and documented release process.
- Test clean install, upgrade, rollback and data migration.
- Use dependency/version tracking and review licences for all shipped code and model weights.

## 9. Unresolved architecture decisions

- Minimum Android version and device matrix.
- Final inference runtime and models.
- Whether any backend is justified; backend vendor remains undecided.
- Account and sync design.
- Photo encryption/storage details and backup policy.
- Knowledge-release signing/integrity scheme.
- Model update delivery and rollback.
- Price/availability data source.
- Test automation and release pipeline.
- Current regulatory/privacy requirements.

## 10. Approval gate

This is a proposal, not an implementation instruction. Final architecture must be reviewed against the approved product scope, model feasibility, privacy, safety and UX. No coding until explicit user approval.
