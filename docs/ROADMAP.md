# Roadmap and Readiness Gates

- Last updated: 2026-10-30
- Overall state: five-screen offline MVP implemented. Routine/Progress Room cutover and startup import are coded; Skin Scan can explicitly save check-ins; Ingredients uses a structured 11-entry safety catalogue. CI #175 is in progress; newer catalogue runs #192/#193 are queued.
- Percentages refer to implementation only, never clinical safety or model validity.

## Latest ingredient evidence update — 2026-10-30
- [x] Attach publisher/source references to each ingredient entry.
- [x] Show source links in ingredient cards; open links through the external browser.
- [x] Localize source-link label in English and Bangla.
- [x] Test non-empty HTTPS source metadata for all entries.
- [ ] Verify the latest head with full Android CI; runs #175 and #192 passed, while run #193 has no job result yet.
- [ ] Qualified expert review remains mandatory; source links alone do not approve ingredient advice or formulations.

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
- [x] English/Bangla resource parity check, including format-placeholder parity.
- [x] Debug APK compilation and artifact workflow.
- [x] Compose navigation smoke test for all five destinations.
- [x] Baseline full workflow passed (#102/#103).
- [ ] Physical-device verification.

## Latest reliability work — 2026-10-30
- [x] Add per-ingredient publisher references and external source links.
- [x] Add repeat-tap protection and saving/error UI states to Skin Scan check-in persistence.
- [x] Add UI instrumentation test for explicit Skin Scan → Progress persistence.
- [ ] Verify these latest commits with a complete Android workflow before marking them QA-passed.

## Phase 2 — Functional first-release screens
**Status: MVP implemented; content and real-device QA remain**
- [x] Interactive self-reported check-in; no photo-based diagnosis.
- [x] Deterministic safety-first guidance rules and unit tests.
- [x] Skin Scan can explicitly save selected answers to the local Progress history.
- [x] Routine checklist with Room-backed completion state.
- [x] Progress history with Room-backed add/delete and tolerance field.
- [x] Profile language/budget preferences and local-data clear success/error states.
- [x] Structured 11-entry ingredient catalogue with categories, warnings and risk classifications.
- [x] Every ingredient entry explicitly remains pending qualified expert review.
- [ ] Add evidence/source metadata and qualified expert review for every ingredient.
- [ ] Improve Profile and Skin Scan accessibility and saved-entry affordances.
- [ ] Verify translations, accessibility and layouts on physical devices.

## Phase 3 — Storage, privacy and lifecycle
**Status: Code wired; CI and physical upgrade verification pending**
- [x] Room entities/DAOs for routine completion and progress notes.
- [x] Atomic one-time legacy SharedPreferences importer.
- [x] Idempotency marker committed in same transaction.
- [x] Retain old preferences unchanged as a recovery snapshot until explicit user reset; later Room edits are not dual-written, so this is not a downgrade guarantee.
- [x] Startup import before the app displays the main screens.
- [x] Routine and Progress use Room repository/Flow for reads and writes.
- [x] Tests for import, idempotency, malformed JSON, legacy optional-field defaults.
- [x] Persistent database close/reopen test and reset/language-retention test authored.
- [ ] Latest full Android CI passes compile, unit tests, emulator instrumentation and artifact upload.
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
- [x] Create structured ingredient metadata with categories, caution text, risk classifications and review status.
- [ ] Add primary-source references, evidence summaries, exclusions, stop conditions and review dates.
- [ ] Obtain qualified skincare/dermatology review before treating entries as approved guidance.
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
