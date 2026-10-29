# Roadmap and Readiness Gates

- Last updated: 2026-10-29
- Overall state: functional MVP coding is underway on `implementation/m0-android-baseline`.
- Progress percentages refer only to implementation, never clinical safety or model validity.
- CI status at last update: new code build queued; emulator instrumentation remains unresolved.

## Phase 0 — Product baseline and documentation
**Status: Planning baseline assembled**
- [x] Product direction, requirements, UX, acceptance criteria and design system.
- [x] Architecture, data model, engineering standards and implementation backlog.
- [x] Safety case, privacy/security threat model, knowledge governance and update protocol.
- [x] Test strategy, localization/accessibility, consent draft and release runbook.
- [x] Session handoff and changelog conventions.
- [ ] Confirm repository visibility/access expectations; GitHub has reported public visibility.

## Phase 1 — Android build baseline and navigation
**Status: Baseline build verified; current branch needs fresh green CI**
- [x] Native Kotlin/Jetpack Compose project and five-area navigation.
- [x] Pinned toolchain, Android manifest with no requested permissions or network permission.
- [x] English/Bangla resource parity check.
- [x] Initial baseline APK build and artifact upload verified in historical runs.
- [x] Compose navigation smoke tests authored.
- [ ] Verify latest full build and unit tests after functional implementation.
- [ ] Diagnose emulator instrumentation process crash (run 40902300999 reported zero tests).
- [ ] Confirm build on a target Android device.

## Phase 2 — Functional first-release screens
**Status: In progress**
- [x] Interactive self-reported check-in; no photo-based diagnosis.
- [x] Deterministic safety-first guidance rules and unit tests.
- [x] Routine checklist with local completion state.
- [x] Text progress timeline with add/delete and tolerance field.
- [x] Educational ingredient search catalogue with explicit expert-review limitation.
- [x] Profile language and budget preferences plus local data clear action.
- [x] English and Bangla labels for new controls.
- [ ] Verify full build, tests, translations and real-device layouts.
- [ ] Improve accessibility, loading/empty/error states and UI polish after functionality is stable.

## Phase 3 — Storage, privacy and lifecycle
**Status: Interim local storage exists; hardening not complete**
- [x] Local routine ticks, profile preferences and text notes saved on device.
- [ ] Replace interim SharedPreferences/JSON data with Room and tested migrations.
- [ ] Test process death, restart, corrupted data, save/delete and reset flows.
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
- [ ] Test tampering, interruption and rollback.

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
