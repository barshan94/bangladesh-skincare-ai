# Project Change Log

## 2026-10-29 — Compose navigation smoke tests
- **Type:** Testing / CI / Accessibility
- Added Android instrumentation test dependencies for Jetpack Compose UI tests.
- Added `app/src/androidTest/java/com/barshan/skincareai/NavigationSmokeTest.kt` to visit all five bottom-navigation destinations and assert each has its own title/body. It also checks the home privacy/safety statements remain visible.
- Updated GitHub Actions to run the instrumentation tests on an API 35 Pixel_2 emulator using `connectedDebugAndroidTest`.
- The emulator-based CI result is pending; the test implementation is not yet claimed to have passed.
- **Next:** verify the full workflow and correct any emulator or UI assertion failures before starting persistence.

## 2026-10-29 — Localization consistency guard
- **Type:** Test / CI / Localization
- Added `scripts/check_localization.py`, a standard-library Python check that parses English and Bangla Android resource XML, detects duplicate resource names, and requires matching resource keys.
- Added the check to the Android workflow before SDK installation/build and included the script in workflow path filters.
- The guard's first CI run is not yet verified; no green status is claimed for this change until that run completes.
- **Why:** prevents an added/renamed UI string from silently falling back to a missing key in one supported locale.

## 2026-10-29 — App shell and localization iteration
- **Type:** UI / Accessibility / Localization / Privacy copy
- Replaced generic non-home placeholder content with distinct descriptions/statuses for Skin Scan, My Routine, Progress, Ingredients, and Profile.
- Expanded English and Bangla resources for each section.
- Added localized header and hero labels.
- Added meaningful navigation icon content descriptions and Material icons for privacy/safety cards.
- Corrected privacy copy: no photo capture/import/storage exists in this build; local-first storage is a future design intent, not current behavior.
- GitHub Actions run [40874506742](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40874506742) passed debug compilation, unit-test task and APK artifact upload for the app-shell/localization code.
- **Safety:** No treatment recommendation, skin analysis, DIY formula, photo, persistence or cloud feature enabled.

## 2026-10-29 — M0 Android baseline verified
- **Type:** Build / CI
- GitHub Actions run [40871345852](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40871345852) succeeded on commit 634902a6203d0f0041a822b89ea7965cd2060dae.
- Android SDK setup, debug APK compilation, unit-test task and artifact upload steps all passed.
- Debug APK artifact was uploaded with 14-day retention. This is a development build, not a signed release.
- Draft PR #1 remains open for review; do not merge until latest app-shell commits pass CI.

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
