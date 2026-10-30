# Roadmap and Readiness Gates

- Last updated: 2026-10-30
- Overall state: five-screen offline MVP implemented. Routine/Progress Room cutover and startup import are coded; Skin Scan can explicitly save check-ins to Progress. CI #152 is pending.
- Percentages refer to implementation only, never clinical safety or model validity.

## Phase 0 — Product baseline and documentation
**Status: Planning baseline assembled**
- [x] Product direction, requirements, UX, acceptance criteria and design system.
- [x] Architecture, data model, engineering standards and implementation backlog.
- [x] Safety case, privacy/security threat model, knowledge governance and update protocol.
- [x] Test strategy, localization/accessibility, consent draft and release runbook.
- [ ] Confirm repository visibility/access expectations; GitHub reports the repository as public.

## Phase 1 — Android build baseline and navigation
**Status: Implemented; baseline CI passed**
- [x] Native Kotlin/Jetpack Compose app and five-area navigation.
- [x] Pinned toolchain and no-permission/no-network manifest.
- [x] English/Bangla resource parity check.
- [x] Debug APK compilation and artifact workflow.
- [x] Compose navigation smoke test for all five destinations.
- [x] Baseline full workflow passed (#102/#103).
- [ ] Physical-device verification.

## Phase 2 — Functional first-release screens
**Status: MVP implemented; content and real-device QA remain**
- [x] Interactive self-reported check-in; no photo-based diagnosis.
- [x] Deterministic safety-first guidance rules and unit tests.
- [x] Skin Scan can explicitly save selected answers to the local Progress history.
- [x] Routine checklist with Room-backed completion state.
- [x] Progress history with Room-backed add/delete and tolerance field.
- [x] Educational ingredient search catalogue with expert-review limitation.
- [x] Profile language/budget preferences and local-data clear action.
- [x] English/Bangla labels for functional controls.
- [ ] Improve Profile and Skin Scan accessibility and saved-entry affordances.
- [ ] Verify translations, accessibility and layouts on physical devices.
- [ ] Qualified expert review of actionable educational content.

## Phase 3 — Storage, privacy and lifecycle
**Status: Code wired; CI and physical upgrade verification pending**
- [x] Room entities/DAOs for routine completion and progress notes.
- [x] Atomic one-time legacy SharedPreferences importer.
- [x] Idempotency marker committed in same transaction.
- [x] Retain old preferences as rollback copy until explicit user reset.
- [x] Startup import before the app displays the main screens.
- [x] Routine and Progress use Room repository/Flow for reads and writes.
- [x] Tests for import, idempotency, malformed JSON, legacy optional-field defaults.
- [x] Persistent database close/reopen test and reset/language-retention test authored.
- [ ] Latest CI #152 passes compile, unit tests, emulator instrumentation and artifact upload.
- [ ] Test actual upgrade from a previously installed version containing legacy data.
- [ ] Confirm Android backup/restore and app-private data deletion semantics on device.
- [ ] Define export privacy/file handling before implementing export.

## Phase 4 — Guided photo progress (not AI diagnosis)
- [ ] Add Android Photo Picker and/or CameraX only with explicit user action and clear copy.
- [ ] Store selected photos privately with opaque references and safe EXIF handling.
- [ ] Add delete-photo and partial-failure recovery; no photo upload.
- [ ] Add manual dated comparison; no improvement score or automatic treatment changes.
- [ ] Validate capture-quality checks before enabling.

## Phase 5 — Reviewed ingredient knowledge and safe routine personalization
- [ ] Replace sample summaries with entries reviewed by qualified skincare/dermatology reviewers.
- [ ] For each ingredient, document evidence, role, concentration context, skin suitability, known cautions, interactions, exclusions, stop conditions, sources and review date.
- [ ] Implement structured safety warnings and tests proving unknown safety facts block affected advice.
- [ ] Add product substitutions only with safety/compatibility evidence and dated Bangladesh price provenance.
- [ ] Individually review any home-prepared formula; no free-form AI recipes.

## Phase 6 — Knowledge updates and rollback
- [ ] Approve package integrity/signature/key management.
- [ ] Validate schema, integrity, compatibility and approval before activation.
- [ ] Implement atomic activation, last-known-good and critical withdrawal.

## Phase 7 — Optional image analysis research
- [ ] Select only a narrowly defined feature with a valid reference method.
- [ ] Resolve data/model licensing and independent validation across relevant skin tones, lighting and devices.
- [ ] Pre-register thresholds and subgroup acceptance criteria.
- [ ] Keep features disabled until evidence supports the exact intended use.
- [ ] Never produce disease diagnoses, beauty/fairness scores or guaranteed whitening claims.

## Phase 8 — Release hardening and private APK
- [ ] Complete security, privacy, accessibility, localization and performance reviews.
- [ ] Complete qualified Bangladesh legal/privacy and skincare/formulation reviews.
- [ ] Conduct a consented adult pilot only after required review.
- [ ] Resolve critical defects and document incident response.
- [ ] Build a signed/versioned APK from traceable source and protect signing material.

## Release blockers
No critical safety defect may be waived. An image feature that fails validation stays disabled. No unreviewed DIY formula or fabricated local price is allowed. A working debug APK is not a signed release.
