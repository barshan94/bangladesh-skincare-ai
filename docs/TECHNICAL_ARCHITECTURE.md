# Technical Architecture — Implemented Personal-Use App

- Last reviewed: 2026-11-17
- Status: Core Android app implemented; CI #295 passed. Physical-device and signed-release gates remain.
- Scope: personal-use, offline-first tracker. This document describes the implemented app, not the earlier broader photo-analysis product concept.

## 1. Platform

- Native Android, Kotlin and Jetpack Compose.
- Single-activity app with five navigable sections.
- JDK 17, Gradle 8.11.1, Android Gradle Plugin 8.9.2, Kotlin 2.0.21.
- Compile/target SDK 35; minimum SDK 26 (device coverage still needs validation).
- Room/SQLite for Routine and Progress records; SharedPreferences used for small preferences and legacy migration source.

## 2. App boundaries

1. **Presentation:** Compose screens, English/Bangla resources and accessible controls.
2. **Skin Scan:** self-reported, non-diagnostic questionnaire with deterministic safety-first explanatory copy; saving a check-in requires explicit user action.
3. **Routine:** local checklist with date-qualified completion IDs.
4. **Progress:** local check-ins/notes, edit-in-place, confirmed deletion and localized failure states.
5. **Ingredients:** bundled educational catalogue of 11 entries, search, risk filters and publisher links; content is pending qualified review.
6. **Profile:** language/budget preferences and local data reset.
7. **Persistence:** Room repository, transactional one-time legacy SharedPreferences import and reset behavior.

## 3. Privacy and security

- No network permission, account, analytics, cloud sync, photo upload or remote inference.
- No runtime permissions requested by the current manifest.
- Android backup is disabled using `android:allowBackup="false"`.
- Routine and Progress data remain in app-private local storage.
- Never commit user records, identifiable skin images, credentials, API keys or signing keys.
- Device-level backup/data-extraction and reset behavior still require physical-device verification; do not overstate privacy guarantees based solely on manifest configuration.

## 4. Data lifecycle

- Room schema v1 is checked into `app/schemas/com.barshan.skincareai.data.SkincareDatabase/1.json`.
- Legacy SharedPreferences records are imported once and idempotently.
- Migration and repository lifecycle behavior have instrumentation coverage.
- User reset clears the legacy snapshot before deleting Room data transactionally. A forced Room failure test verifies primary records survive and reset can be retried.
- Real upgrade from an installed legacy build and physical-device reset remain release gates.

## 5. Safety/content boundary

- No skin-condition diagnosis, image-based assessment, whitening score/guarantee or automated treatment recommendation.
- No DIY formulas, concentrations or mixing instructions.
- Ingredient entries are educational references, not individualized medical advice; all 11 need qualified professional review before being presented as approved guidance.
- Any future expansion into photo capture, image analysis, AI or remote content requires separate authorization and task-specific safety, privacy, licensing and validation review.

## 6. CI and release engineering

GitHub Actions validates English/Bangla resource parity, compiles a debug APK, runs unit tests, exports the Room schema, runs Android emulator instrumentation and uploads APK/test artifacts. Latest complete run: [#295](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44090947039), passed.

CI does not replace physical-device compatibility/accessibility testing, qualified content review or signed-release verification. The current artifact is a debug APK. The signing key must remain outside the repository and be controlled by the owner.

## 7. Deliberately deferred

Photo capture/import/storage, image-quality checks, skin measurement/model inference, AI explanation, backend/cloud sync, knowledge-package updater, product marketplace and commercial/customer workflows are not implemented and are out of scope for the current personal-use release.
