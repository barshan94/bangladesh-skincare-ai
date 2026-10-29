# Implementation Backlog — Execution After Explicit Authorization

- Version: 1.0
- Date: 2026-10-29
- Status: Planned; no code started and implementation not yet authorized.

## Execution rules
- Work in small independently testable milestones.
- Do not implement unapproved scope or real skincare content.
- Build UI/data plumbing with safe empty states until reviewed content exists.
- Link each task to requirements, tests and documentation.
- Update PROJECT_STATE, ROADMAP, CHANGELOG and affected technical docs after each milestone.
- Report files, tests/results, commit/PR, unresolved risks and next step.
- Never claim model validity from build/demo success.
- Coding starts only after separate explicit user authorization.

## Milestones

### M0 — Repository/toolchain baseline
Confirm visibility/branch policy; choose JDK/Gradle/Android SDK and minimum Android version; create native Kotlin/Jetpack Compose project only after authorization; add formatting/lint/unit tests/CI; define package/build variants/signing process.
**Acceptance:** reproducible debug build, clean CI, no secrets, documented toolchain.
**Dependencies:** repository visibility, target-device matrix, signing-key custody.

### M1 — App shell and localization
Five-area navigation, accessible components, Bangla/English resources, onboarding/limits, adult-eligibility flow under approved policy, standard loading/empty/error/offline states.
**Acceptance:** all areas navigable; localization/accessibility smoke tests; no fake data.

### M2 — Local-first persistence and privacy
Local database, app-private photo storage, migrations, deletion and backup configuration; local profile/progress/consent records for approved purposes.
**Acceptance:** save/read/delete, interrupted-delete recovery, migration tests, documented backup behavior.

### M3 — Onboarding and deterministic domain engine
Adaptive questionnaire, versioned answers, eligibility/safety rules independent from UI, routine rendering only from approved structured content.
**Acceptance:** missing/unknown safety facts block affected advice; safety property tests pass.

### M4 — Skin Scan: private tracking and capture checks
Guided capture/photo picker, private photo timeline and quality feedback. Begin with tracking; automated checks only after validation.
**Acceptance:** denied-permission/poor-photo states safe; no photo network upload; deletion verified.

### M5 — Progress and routine workflows
Manual progress, dated photo comparison, optional adherence/tolerance, reaction report and material-change confirmation.
**Acceptance:** non-comparable photos never produce improvement claims; photo alone never changes routine.

### M6 — Reviewed knowledge catalogue and alternatives
Versioned structured content, provenance, stale-price behavior and safety-filtered replacement. Ship only reviewed entries.
**Acceptance:** draft/expired/withdrawn entries cannot be actionable; no fabricated prices.

### M7 — Knowledge update and rollback
Package schema, integrity/signature scheme, compatibility, staging, atomic activation, safe last-known-good and withdrawal.
**Acceptance:** tamper/interruption tests pass; no partial activation.

### M8 — Optional AI explanation
Only if justified and approved; constrained to approved content. May be omitted if deterministic offline copy is sufficient.
**Acceptance:** adversarial tests cannot produce unsupported actionable instructions; privacy reviewed.

### M9 — Feature-specific image analysis
Integrate one selected/licensed model/runtime for one approved task at a time. Feature stays disabled until validation evidence and release approval.
**Acceptance:** feature thresholds pass, subgroup/uncertainty/device results reviewed, disable/rollback tested. No need to implement every candidate.

### M10 — Hardening, pilot and APK
Security/privacy, accessibility, localization, performance, migration, offline and incident response; private pilot only after expert/legal gates; signed APK and release notes.
**Acceptance:** all gates in RELEASE_RUNBOOK pass.

## External workstreams not to fabricate
Bangladesh legal/privacy review; qualified skincare review; individual DIY formula validation; reference method/protocol approval for each image feature; lawful/consented/representative evaluation data; Bangla terminology/usability review; local price/availability sources.

Use empty/disabled states while these remain unresolved.

## Definition of Done per task
Requirements linked; implementation/tests complete; no safety/privacy regression; accessibility/localization reviewed; failure/offline behavior verified; docs/state updated; diff reviewed; commit/PR linked; user receives concise completion summary.

## Definition of Done for implementation readiness
Approved spec/UX; architecture defaults accepted; repo visibility confirmed; no unresolved decision needed for first task; toolchain available; acceptance criteria defined; explicit user authorization. Expert gates remain attached to relevant feature activation and release.