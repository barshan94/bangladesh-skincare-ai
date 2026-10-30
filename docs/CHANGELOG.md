# Project Change Log

## 2026-10-30 — Room legacy migration foundation
- **Type:** Storage / Migration / Data safety
- Added a one-time importer for the existing SharedPreferences routine-completion and progress-note JSON.
- Imports both datasets into Room in one transaction and commits an idempotency marker in that same transaction.
- Retains the original preference values as rollback copies; malformed legacy data fails without marking migration complete or deleting the source.
- Added instrumentation coverage for successful import, idempotency and malformed-data preservation.
- **Status:** Migration foundation only. Startup wiring and Routine/Progress UI cutover remain.
- **Next:** connect migration to app startup, switch both screens to Room-backed state and test a real legacy upgrade.

## 2026-10-30 — Local persistence instrumentation coverage
- **Type:** Testing / Persistence / Privacy
- Added LocalStoreTest instrumentation tests for routine completion round-trip/clear, progress note create/read/delete, and clearing user data while retaining language preference.
- Baseline CI runs [#102](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015483767) and [#103](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015490522) passed on commit cf8048453634e014d6a90f7875abcf314ca9b879.
- No production storage behavior changed in that test-only commit.

## 2026-10-30 — Compose navigation smoke test corrected and verified
- **Type:** Testing / CI
- Corrected navigation assertions to inspect actual rendered Routine, Progress and Ingredients content.
- CI runs [#100](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41011174011) and [#101](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41011179610) passed.

## 2026-10-29 — Functional offline MVP screens started
- **Type:** Feature / Domain / Localization / Privacy
- Added deterministic non-diagnostic guidance rules with safety-first handling for concerning changes and broken skin.
- Added interactive self-reported check-in, routine checklist, text-only progress timeline with add/delete, ingredient education search, and profile language/budget/local-data controls.
- Added interim app-private SharedPreferences/JSON persistence. Room migration and lifecycle hardening remain required before release.
- Added English/Bangla resources. No photos, AI image analysis, account, network service, cloud sync, or DIY formula recommendations were added.

## 2026-10-29 — Compose navigation smoke tests
- Added Android Compose instrumentation tests covering all five destinations and home privacy/safety messaging.
- Updated GitHub Actions to run emulator instrumentation tests.

## 2026-10-29 — Localization consistency guard
- Added scripts/check_localization.py to detect duplicate resource names and enforce English/Bangla resource-key parity.

## 2026-10-29 — M0 Android implementation started and verified
- Created the native Kotlin/Jetpack Compose project, pinned Android toolchain and permission-free/no-network manifest.
- Added five-area navigation, English/Bangla resources and GitHub Actions build/test/APK artifact workflow.
- Baseline workflow run 40871345852 passed. Debug APK is not a signed release.
- Repository visibility is currently public; verify settings before adding sensitive material.
