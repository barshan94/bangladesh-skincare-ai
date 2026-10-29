# Technical Architecture and Implementation Baseline

- Version: 1.0
- Last updated: 2026-10-29
- Status: Recommended engineering baseline for implementation planning. No application code has been started; implementation still requires explicit user authorization.
- Companion documents: ENGINEERING_STANDARDS.md, DATA_MODEL.md, REQUIREMENTS.md, KNOWLEDGE_UPDATE_PROTOCOL.md, THREAT_MODEL.md.

## 1. Platform baseline

- Native Android, Kotlin and Jetpack Compose.
- Single activity with feature-based UI packages and ViewModel/state-driven UI.
- Local-first; no backend, account provider, analytics SDK, LLM service or cloud photo processing by default.
- Room/SQLite candidate for structured records; app-private files for originals; DataStore only for small preferences.
- Android Photo Picker for import; CameraX only if guided in-app capture is needed.
- WorkManager only for deferrable background work; Android may defer it.
- Provisional minimum API 26 (Android 8.0), subject to a short device/dependency compatibility check before first build.
- Exact stable toolchain/dependency versions are recorded when implementation is authorized and the repository is created.

## 2. Component boundaries

1. Presentation: accessible Bangla/English Compose screens and navigation.
2. Domain: questionnaire interpretation, eligibility/safety rules, ranking and change confirmation as deterministic/testable logic.
3. Knowledge: versioned approved entries, evidence, exclusions, price provenance, alternatives and formula metadata.
4. Local data: repositories for structured records and app-private photo files.
5. Photo pipeline: capture guidance, orientation/quality checks and comparability; each automated check separately gated.
6. Optional validated inference: task-specific model/runtime only after licence, validation, subgroup and performance gates.
7. Knowledge updater: staged package, integrity/approval/schema checks, atomic activation and rollback.
8. Optional explanation layer: not required for the initial core; if added, may explain approved content only.
9. Optional backend: not selected and not required for core local-first experience.

## 3. Local-first privacy boundary

- Original images/progress history stay on-device by default.
- No automatic photo upload, cloud inference or model training.
- No raw photos in analytics/crash reports.
- Ask camera/photo permissions only when needed; no location permission.
- Cloud storage, cloud analysis and model training require separate affirmative consent if ever added.
- Document and test OS backup/device transfer behavior before claiming “device-only.”
- Basic functionality should not require account creation.
- Optional network services require a data-flow map, security review, legal/privacy review and consent design.

## 4. Image pipeline

1. User sees purpose, limitations and privacy disclosure.
2. Guided capture or supported photo picker.
3. Validate orientation/format and reject malformed inputs.
4. Provide capture guidance; automated quality checks remain disabled until their validation gate passes.
5. Save a user-selected original locally after review.
6. Compare sessions only if capture conditions are sufficiently comparable.
7. Run a validated feature only when enabled by approved feature metadata.
8. Return available, inconclusive or failed state with uncertainty; never fabricate a result.
9. Never change a routine automatically from a photo alone.

Face detection/landmarks do not validate skin-condition measurement. Avoid image enhancement/white-balance changes that could be mistaken for biological change.

## 5. Deterministic recommendation engine

1. Validate questionnaire/schema.
2. Resolve supported concern and user eligibility.
3. Apply hard safety/contraindication filters.
4. Exclude draft, expired, withdrawn, incompatible or unsupported knowledge.
5. Rank eligible options by documented relevance, evidence, realistic benefit, affordability, verified availability and simplicity.
6. Render only structured approved instructions.
7. Explain rationale, uncertainty, costs and source dates.
8. Require confirmation for material changes.
9. Record routine version and acceptance/decline.

Generative AI cannot make independent safety decisions or create actionable formulas.

## 6. Knowledge versioning

- Every knowledge package has a release ID, schema version, compatibility, approval/provenance metadata and integrity/signature metadata.
- Until the signing scheme and key management are approved, ship bundled reviewed content or disable remote updates. Do not ship an insecure remote JSON feed.
- Stage and verify package before activation.
- Activate atomically; reject malformed, incompatible, unapproved or integrity-failing packages.
- Keep last-known-good content only while it remains safe; safety withdrawal overrides generic rollback.
- Show current version and last successful update.
- General reviewed content may update under policy; material active-routine changes require confirmation.
- Never promise immediate background synchronization.

## 7. Local data model

See DATA_MODEL.md. Logical entities include LocalProfile, QuestionnaireResponse, PhotoSession, ImageFeatureResult, ProgressEntry, Routine/RoutineVersion, RoutineEvent, KnowledgeRelease, KnowledgeEntry, FormulaRecord, ConsentRecord and optional SyncState. No raw photos in cloud records by default.

## 8. Security and release engineering

- Keep API keys, credentials, keystores and signing keys out of Git.
- Review dependency/model/data licences and provenance.
- Restrict sensitive logs and inspect network behavior.
- Test deletion, migration, offline errors, update integrity and rollback.
- Produce signed, versioned APKs traceable to source commit and toolchain.
- Use maintained platform cryptography; do not implement custom cryptography.
- See THREAT_MODEL.md, TEST_STRATEGY.md and RELEASE_RUNBOOK.md.

## 9. Proposed first implementation sequence

Use IMPLEMENTATION_BACKLOG.md. The first milestone is repository/toolchain baseline and app shell, not skin analysis. Follow with localization/navigation, local persistence/privacy, deterministic questionnaire/safety domain, photo tracking, progress, reviewed knowledge, update protocol and release hardening. Any AI feature is a later independently gated milestone.

## 10. Decisions intentionally provisional

- Minimum API 26 is a provisional default, not a verified market-wide compatibility result.
- Final camera approach and supported device matrix.
- Final model/runtime; no model selected.
- Remote knowledge signing/key-management scheme.
- Whether a backend/cloud/account is justified.
- Encryption/backup policy and age-eligibility method.
- Price/availability sources.
- Legal/privacy requirements and qualified content approvals.

These should be resolved by the appropriate feasibility or professional review before the affected capability ships. They do not justify inventing data or enabling unvalidated features.

## 11. Approval rule

This document and ENGINEERING_STANDARDS.md provide a proposed baseline to reduce planning overhead. No code, scaffolding or implementation-file changes may begin until the user separately gives explicit implementation approval. External safety/legal/model gates remain binding for the relevant features and release.
