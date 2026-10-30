# Project Change Log

## 2026-10-30 — Room startup migration and screen cutover
- **Type:** Storage / Architecture / UI
- Wired the one-time SharedPreferences-to-Room importer into app startup; the main app is displayed only after the import finishes.
- Replaced Routine and Progress screen persistence with Room-backed repository flows.
- Kept profile language/budget settings in SharedPreferences.
- On migration failure, app shows a safe error state and does not clear the legacy data.
- **Verification:** CI #143 is pending; no claim of QA completion until compile and emulator tests pass.

## 2026-10-30 — Room migration compatibility and lifecycle tests
- **Type:** Data migration / QA / Privacy
- Updated importer parsing to match the previous LocalStore defaults for optional legacy progress fields.
- Added test coverage for optional fields and malformed JSON preserving the source and leaving migration marker unset.
- Added a persistent database close/reopen test covering imported routine/progress data, marker idempotency, note create/delete, explicit reset and language preference retention.
- Latest commit: `0700bc2411d3139ea3b690aca6ce940a598146eb`; CI [run #143](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41036494722) pending.

## 2026-10-30 — Room legacy migration foundation
- Added Room entities/DAOs and a one-time importer for routine completion and progress-note JSON.
- Imports both datasets in a single transaction and commits an idempotency marker atomically.
- Retains legacy preferences as rollback copies until explicit reset.
- Added successful import, idempotency and malformed-data tests.

## 2026-10-30 — Local persistence instrumentation coverage
- Added LocalStore instrumentation tests for routine completion round-trip/clear, progress create/read/delete, and clearing user data while retaining language preference.
- Baseline CI runs [#102](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015483767) and [#103](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015490522) passed on baseline commit `cf8048453634e014d6a90f7875abcf314ca9b879`.

## 2026-10-30 — Compose navigation smoke test corrected and verified
- Corrected navigation assertions to inspect actual rendered Routine, Progress and Ingredients content.
- CI runs #100 and #101 passed.

## 2026-10-29 — Functional offline MVP screens started
- Added deterministic non-diagnostic safety-first guidance rules and tests.
- Added interactive self-reported check-in, routine checklist, text progress timeline with add/delete, ingredient education search and profile language/budget/local-data controls.
- Added interim SharedPreferences/JSON persistence, now superseded for Routine/Progress by Room.
- Added English/Bangla resources. No photo capture, image diagnosis, account, network, cloud sync or DIY formulas.

## 2026-10-29 — Compose navigation and localization checks
- Added five-destination navigation instrumentation tests and English/Bangla resource parity validation.

## 2026-10-29 — M0 Android baseline
- Created native Kotlin/Jetpack Compose project, pinned Android toolchain, offline/no-permission manifest, five-area navigation and GitHub Actions build/test/APK artifact workflow.
- Baseline workflow passed. Debug APK is not a signed release.
- Repository visibility is public; verify settings before adding sensitive material.
