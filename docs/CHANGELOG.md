# Project Change Log

## 2026-10-30 — Room legacy migration foundation
- **Type:** Storage / Migration / Data safety
- Added a one-time importer for the existing SharedPreferences routine-completion JSON and progress-note JSON.
- Imports both datasets into Room in a single transaction and writes an idempotency marker in that same transaction.
- Keeps the original preference values as a rollback copy; malformed legacy JSON fails without marking the migration complete or deleting the source.
- Added instrumentation coverage for successful import, idempotency and malformed-data preservation.
- **Status:** This is migration groundwork, not a completed app migration. Startup wiring and Routine/Progress UI cutover remain.
- **Next:** connect migration to app startup, use Room-backed Flows in Routine/Progress, and test an actual upgrade with populated legacy data.

## 2026-10-30 — Local persistence instrumentation coverage
- **Type:** Testing / Persistence / Privacy
- Added `LocalStoreTest.kt` Android instrumentation tests for routine completion round-trip/clear, progress note create/read/delete, and clearing user data while retaining the language preference.
- No production storage behavior or UI functionality changed in that test-only commit.
- CI runs [#102](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015483767) and [#103](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015490522) both passed on commit `cf8048453634e014d6a90f7875abcf314ca9b879`.
- **Next:** replace interim SharedPreferences/JSON persistence with Room and add migration/lifecycle tests.

## 2026-10-30 — Compose navigation smoke test corrected and verified
- **Type:** Testing / CI
- Corrected navigation assertions to inspect the actual text rendered by Routine, Progress and Ingredients screens rather than unused legacy title/body resources.
- CI runs [#100](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41011174011) and [#101](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41011179610) passed on commit `5961b25da487b5ecf9badc92156142dfd6a096f9`.

## 2026-10-29 — Functional offline MVP screens started
- **Type:** Feature / Domain / Localization / Privacy
- Added deterministic non-diagnostic guidance rules with safety-first handling for concerning changes and broken skin; added unit tests for the core branches.
- Replaced placeholder tabs with an interactive self-reported check-in, routine checklist, text-only progress timeline with add/delete, ingredient education search, and profile language/budget/local-data controls.
- Added interim device-local persistence for routine completion, progress notes and preferences. This uses app-private SharedPreferences/JSON as a prototype; Room migration and lifecycle hardening remain required before release.
- Added English and Bangla resources for the new controls and corrected stale placeholder copy.
- No photos, AI image analysis, account, network service, cloud sync, or DIY formula recommendations were added. The ingredient summaries explicitly say expert review is pending.
- The latest full baseline CI status is recorded in the 2026-10-30 entries above.

## 2026-10-29 — Compose navigation smoke tests
- **Type:** Testing / CI / Accessibility
- Added Android instrumentation test dependencies for Jetpack Compose UI tests.
- Added `app/src/androidTest/java/com/barshan/skincareai/NavigationSmokeTest.kt` to visit all five bottom-navigation destinations and assert each has its own rendered content. It also checks home privacy/safety statements remain visible.
- Updated GitHub Actions to run the instrumentation tests on an API 35 Pixel_2 emulator using `connectedDebugAndroidTest`.
- Emulator test implementation subsequently passed in runs #100 and #101.

## 2026-10-29 — Localization consistency guard
- **Type:** Test / CI / Localization
- Added `scripts/check_localization.py`, a standard-library Python check that parses English and Bangla Android string resources, detects duplicate resource names, and requires matching resource keys.
- Added the check to the Android workflow before SDK installation/build and included the script in workflow path filters.

## 2026-10-29 — App shell and localization iteration
- **Type:** UI / Accessibility / Localization / Privacy copy
- Replaced generic non-home placeholder content with distinct descriptions/statuses for Skin Scan, My Routine, Progress, Ingredients, and Profile.
- Expanded English and Bangla resources for each section.
- Added localized header and hero labels.
- Added meaningful navigation icon content descriptions and Material icons for privacy/safety cards.
- Corrected privacy copy: no photo capture/import/storage exists in this build; local-first storage is a future design intent, not current behavior.
- GitHub Actions run [40874506742](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40874506742) passed debug compilation, unit-test task and APK artifact upload for the app-shell/localization code.

## 2026-10-29 — M0 Android baseline verified
- **Type:** Build / CI
- GitHub Actions run [40871345852](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40871345852) succeeded on commit 634902a6203d0f0041a822b89ea7965cd2060dae.
- Android SDK setup, debug APK compilation, unit-test task and artifact upload steps all passed.
- Debug APK artifact was uploaded with 14-day retention. This is a development build, not a signed release.
- Draft PR #1 remains open for review; do not merge until remaining storage, privacy and release gates pass.

## 2026-10-29 — M0 Android implementation started
- **Type:** Architecture / Implementation / CI
- Created branch implementation/m0-android-baseline and committed a native Kotlin/Jetpack Compose project scaffold.
- Added Gradle settings/build files, pinned Android Gradle Plugin 8.9.2, Kotlin 2.0.21, Gradle 8.11.1 in CI, JDK 17, compile/target SDK 35 and min SDK 26.
- Added manifest with no requested permissions, no network permission and Android automatic backup disabled.
- Added five-area bottom navigation shell, initial home screen and honest placeholder states for unimplemented areas.
- Added English and Bangla resource files.
- Added GitHub Actions workflow to compile a debug APK, run unit tests and upload the APK artifact for 14 days.
- **Verification:** Workflow run 40870169709 initially failed due to SDK setup; subsequent workflow revisions corrected setup. Final baseline verification is run 40871345852.
- **Safety/privacy:** No model, cloud service, analytics, skincare advice, DIY formula, photo capture/import or persistence implemented.
- **Approval status:** User explicitly authorized implementation on 2026-10-29.
- **Repository note:** GitHub reports repository visibility as public; owner should switch it in Settings if private visibility is desired.

## 2026-10-29 — First-release scope proposal drafted
- Added docs/FIRST_RELEASE_SCOPE.md to separate proposed MVP features from gated research and excluded features.
- Proposed core focus: reviewed routine guidance, adaptive safety questionnaire, local/private progress timeline, transparent ingredient/product knowledge, privacy-first architecture, and validated AI features only after safety gates.
- Explicitly excluded guaranteed whitening, beauty/fairness scores, diagnosis from selfies, unreviewed DIY recipes and automatic treatment changes based on photos.
