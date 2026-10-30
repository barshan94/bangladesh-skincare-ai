# Roadmap and Readiness Gates

- Last updated: 2026-10-30
- Overall state: functional offline MVP screens implemented; Room migration is in progress on `implementation/m2-room-legacy-migration`.
- Progress percentages refer only to implementation, never clinical safety or model validity.
- Latest baseline CI: runs #102 and #103 passed on commit `cf8048453634e014d6a90f7875abcf314ca9b879`. Room branch CI is pending.

## Phase 0 — Product baseline and documentation
**Status: Planning baseline assembled**
- [x] Product direction, requirements, UX, acceptance criteria and design system.
- [x] Architecture, data model, engineering standards and implementation backlog.
- [x] Safety case, privacy/security threat model, knowledge governance and update protocol.
- [x] Test strategy, localization/accessibility, consent draft and release runbook.
- [x] Session handoff and changelog conventions.
- [ ] Confirm repository visibility/access expectations; GitHub currently reports public visibility.

## Phase 1 — Android build baseline and navigation
**Status: Implemented and CI-verified**
- [x] Native Kotlin/Jetpack Compose project and five-area navigation.
- [x] Pinned toolchain, Android manifest with no requested permissions or network permission.
- [x] English/Bangla resource parity check.
- [x] Debug APK compilation and artifact upload.
- [x] Compose navigation smoke test visits all five destinations.
- [x] Latest baseline full workflow passes; see runs #102 and #103.
- [ ] Confirm behavior on a physical target Android device.

## Phase 2 — Functional first-release screens
**Status: Implemented; expert/content and real-device review remain**
- [x] Interactive self-reported check-in; no photo-based diagnosis.
- [x] Deterministic safety-first guidance rules and unit tests.
- [x] Routine checklist with local completion state.
- [x] Text progress timeline with add/delete and tolerance field.
- [x] Educational ingredient search catalogue with explicit expert-review limitation.
- [x] Profile language and budget preferences plus local data clear action.
- [x] English and Bangla labels for new controls.
- [ ] Verify translations, accessibility and layouts on physical target devices.
- [ ] Review all educational content with qualified experts before actionable content expansion.

## Phase 3 — Storage, privacy and lifecycle
**Status: Room migration foundation added; UI cutover and device upgrade verification remain**
- [x] Existing Room entities/DAOs for routine completion and progress notes.
- [x] Atomic one-time importer for legacy SharedPreferences/JSON data.
- [x] Idempotency marker and preservation of original legacy values for rollback.
- [x] Instrumentation tests authored for import, idempotency and malformed-data preservation; branch CI pending.
- [ ] Wire migration into app startup before Room-backed screens read data.
- [ ] Replace Routine and Progress SharedPreferences reads/writes with Room repository/Flow.
- [ ] Test actual upgrade with populated legacy values, process death/restart, delete and reset.
- [ ] Confirm Android backup/restore and app-private data deletion semantics.
- [ ] Implement export only after its privacy and file-handling behavior is defined.

## Phase 4 — Guided photo progress (not AI diagnosis)
- [ ] Add Android Photo Picker and/or CameraX only with explicit user action and clear copy.
- [ ] Copy selected photos to app-private storage with opaque references and safe EXIF handling.
- [ ] Add delete-photo and partial-failure recovery; no photo upload.
- [ ] Add manual dated comparison; no improvement score or automatic treatment changes.
- [ ] Add capture-quality checks only after feature-specific validation.

## Phase 5 — Reviewed knowledge and safe routine personalization
- [ ] Replace sample education with entries reviewed by qualified skincare/dermatology reviewers.
- [ ] Document evidence, exclusions, stop conditions, sources, review dates and version.
- [ ] Implement deterministic eligibility rules and tests proving unknown safety facts block affected advice.
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
- [ ] Keep all features disabled until evidence supports the exact intended use.
- [ ] Never produce disease diagnoses, beauty/fairness scores or guaranteed whitening claims.

## Phase 8 — Release hardening and private APK
- [ ] Complete security, privacy, accessibility, localization and performance reviews.
- [ ] Complete qualified Bangladesh legal/privacy and skincare/formulation reviews.
- [ ] Conduct a consented adult pilot only after required review.
- [ ] Resolve critical defects and document incident response.
- [ ] Build a signed/versioned APK from traceable source and protect signing material.

## Release blockers
No critical safety defect may be waived. An image feature that fails validation stays disabled. No unreviewed DIY formula or fabricated local price is allowed. A working debug APK is not equivalent to a release-ready product.
