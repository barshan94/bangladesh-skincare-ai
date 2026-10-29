# Roadmap and Readiness Gates

- Last updated: 2026-10-29
- Overall state: Documentation baseline assembled; implementation underway on implementation/m0-android-baseline.
- M0 Android baseline: **Verified green** on run 40871345852.
- App shell/localization: **Verified green** on run 40874506742 for compilation, unit-test task and artifact upload.
- Current milestone: localization-key CI guard added; its CI run is pending verification.
- Progress measures implementation only, not clinical safety or model validity.

## Phase 0 — Product baseline and documentation
**Status: Documentation baseline assembled**
- [x] Product direction, requirements, UX, acceptance criteria and design system.
- [x] Architecture, data model, engineering standards and implementation backlog.
- [x] Safety case, privacy/security threat model, knowledge governance and update protocol.
- [x] Test strategy, localization/accessibility, consent draft and release runbook.
- [x] Project state, changelog and handoff/update routine.
- [x] Verify README document links against docs directory.
- [ ] Owner to confirm/change repository visibility; GitHub currently reports public visibility.

## Phase 1 — M0 Android project/build baseline
**Status: Build and unit-test task verified green**
- [x] Create branch implementation/m0-android-baseline.
- [x] Add native Kotlin/Jetpack Compose Android app module.
- [x] Pin JDK 17, Gradle 8.11.1, Android Gradle Plugin 8.9.2, Kotlin 2.0.21 and SDK baseline.
- [x] Add manifest with no permissions, no network capability and automatic backup disabled.
- [x] Add five-area navigation and safe placeholder UI.
- [x] Add English and Bangla string resources.
- [x] Add GitHub Actions workflow for debug APK build, unit-test task and temporary artifact upload.
- [x] Verify CI run 40871345852 succeeded and APK artifact was uploaded.
- [x] Verify app-shell/localization commit via run 40874506742.
- [ ] Verify localization guard's own CI run.
- [ ] Add meaningful UI/navigation tests and open PR review.
- [ ] Confirm build reproducibility on a target Android device.

**Exit gate:** reproducible debug build, passing tests, no secrets, documented toolchain. This is not a releasable skincare app.

## Phase 2 — App shell, localization and accessibility
**Status: In progress**
- [x] Give all five areas distinct, honest descriptions and implementation status.
- [x] Use localized string resources for section content in English and Bangla.
- [x] Add navigation icon content descriptions.
- [x] Correct photo privacy wording to distinguish future intent from current capability.
- [x] Add a CI check for duplicate/mismatched English and Bangla resource keys (new check pending its first run).
- [ ] Verify English and Bangla layout on a target device.
- [ ] Review Bangla translations with native users.
- [ ] Add accessibility labels, scalable text and contrast checks.
- [ ] Add UI smoke tests for all five areas.
- [ ] Implement loading/empty/error/offline/unavailable states consistently.

## Phase 3 — Local-first persistence and privacy
- [ ] Implement structured local data and migrations.
- [ ] Implement app-private photo storage only when photo workflow is ready.
- [ ] Implement deletion and partial-failure recovery.
- [ ] Verify Android backup/restore behavior and privacy claims.
- [ ] Test save/read/delete, migration and offline behavior.

## Phase 4 — Onboarding and deterministic domain engine
- [ ] Build adult-eligibility and consent flows after qualified policy review.
- [ ] Implement safety/eligibility rules as deterministic pure functions.
- [ ] Ensure UI can only render approved structured knowledge.
- [ ] Add property tests proving missing/unknown safety facts block affected advice.

## Phase 5 — Private photo tracking and progress
- [ ] Add photo picker/capture only with clear consent and private storage.
- [ ] Build dated photo timeline and manual progress notes.
- [ ] Ensure photos alone never produce improvement claims or automatic routine changes.
- [ ] Add capture-quality automation only after its validation gate passes.

## Phase 6 — Reviewed knowledge catalogue and alternatives
- [ ] Add structured entries with source/review/approval metadata.
- [ ] Add expiry/withdrawal and price provenance/freshness behavior.
- [ ] Keep catalogue empty/disabled until qualified review is complete.
- [ ] Individually validate any home-prepared formula before inclusion.

## Phase 7 — Knowledge updates and rollback
- [ ] Approve package integrity/signature/key management design.
- [ ] Validate schema, integrity, compatibility and approval before activation.
- [ ] Implement atomic activation, last-known-good and critical withdrawal.
- [ ] Test tampering/interruption/rollback.

## Phase 8 — Optional AI and image-analysis research
- [ ] Select an AI explanation path only if it adds value beyond deterministic copy.
- [ ] Keep generated text constrained to approved structured facts.
- [ ] Resolve model/data licensing, lawful provenance and independent validation per feature.
- [ ] Pre-register thresholds and evaluate relevant subgroups and target devices.
- [ ] Keep every feature disabled until its go/no-go evidence is approved.

## Phase 9 — Hardening, pilot and private APK release
- [ ] Complete security/privacy/accessibility/localization/performance review.
- [ ] Complete qualified Bangladesh legal/privacy and skincare/formulation reviews.
- [ ] Run a consented adult pilot only after appropriate approval.
- [ ] Resolve critical defects; document incident response.
- [ ] Build signed/versioned APK from traceable source; protect signing key and record artifact hash.

## Release blockers
No critical safety defect may be waived. A feature that fails validation stays disabled. If privacy, licensing, evidence, performance, fairness or regulatory compliance cannot be established, reduce scope rather than weaken acceptance criteria. No unreviewed DIY formula or fabricated local price is allowed.

## Project update convention
At each meaningful project session, update PROJECT_STATE.md, ROADMAP.md and CHANGELOG.md plus affected documents. Fetch latest GitHub versions first and verify updates afterward. Report actual evidence, blockers, next action and whether the scope is sufficient for the day. Never inflate progress or claim background synchronization.
