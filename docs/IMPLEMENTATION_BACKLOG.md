# Implementation Backlog — Current Status

- Last reviewed: 2026-11-18
- Active branch: `implementation/m0-android-baseline`
- Latest full CI: [run #44221154087](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44221154087), success; emulator instrumentation: 24 tests, 0 failed, 0 skipped.

## Product boundary

Personal-use, offline-first Android skincare tracker. No commercial/client workflows, accounts, cloud sync, analytics, photo capture/analysis, AI diagnosis, prescriptions or DIY whitening recipes.

## Implemented and CI-verified

- **M0 — Android baseline:** Kotlin, Jetpack Compose, pinned Gradle toolchain and debug APK workflow.
- **M1 — App shell/localization:** five sections and English/Bangla resources with parity validation.
- **M2 — Local persistence:** Room-backed Routine and Progress, one-time/idempotent legacy SharedPreferences import and schema export.
- **M3 — Routine and Progress:** daily completion keys, create/edit/delete, confirmation and localized failure feedback.
- **M4 — Skin check-in:** self-reported questions, deterministic non-diagnostic general guidance and explicit save to Progress.
- **M5 — Ingredient education:** 11 general educational entries, search, safety filters and publisher source links; no treatment prescriptions.
- **M6 — Accessibility baseline:** combined labels/toggle semantics for key checkbox rows.
- **Reliability:** reset ordering and regression test for failed-reset recovery.

Latest CI #44221154087 passed the complete workflow, including 24 emulator instrumentation tests (0 failed, 0 skipped) and artifact upload.

## Owner-side acceptance tests

1. Install current debug APK on the owner's Android phone; inspect all five sections.
2. If a prior build contains legacy data, upgrade without clearing storage; verify import once, restart persistence and no duplicate import.
3. Use Profile reset; verify Routine/Progress clear while language preference remains.
4. Check TalkBack, font scaling, touch targets and English/Bangla layout.
5. Launch in airplane mode and check for unexpected permission prompts.
6. For stable long-term private distribution, create an owner-controlled signing key, build a signed APK and verify clean install/upgrade.

These checks require the owner's device and must not be marked passed based only on emulator CI.

## Content boundary

No professional review is required merely to use the current app as a personal tracker. The ingredient catalogue must remain clearly labelled as general education, with no individualized treatment advice, diagnosis, concentrations, mixing steps or claims of clinical approval. If the scope expands to treatment recommendations, home formulations, image diagnosis or public/commercial distribution, qualified safety and legal review becomes a release prerequisite.

## Explicitly deferred

Photo capture/import/timeline, AI model integration, image diagnosis/scoring, cloud/backend/accounts/analytics, product marketplace/pricing, remote knowledge updates, DIY formulations and commercial workflows.

## Definition of done

Implementation and automated tests are complete for the current personal-use feature scope. The remaining work is owner-side phone acceptance and (if a stable distributable build is wanted) owner-controlled signing and verification.
