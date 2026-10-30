# Project State and Session Handoff

- Last updated: 2026-10-30
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active branch: implementation/m0-android-baseline
- Current stage: functional offline MVP screens implemented; CI build, unit-test task, Compose navigation instrumentation and local-store instrumentation have passed on the latest commit.
- Source of truth: this repository. No background sync is assumed.

## Implemented in the current branch

- Native Kotlin/Jetpack Compose app with five tabs: Skin Scan, My Routine, Progress, Ingredients, Profile.
- Deterministic, non-diagnostic self-reported check-in engine with safety-first paths for concerning changes and broken skin.
- General education for basic care, dryness and roughness; dark-mark and acne requests remain explicitly limited rather than diagnosed or promised to improve.
- Routine checklist with locally saved completion ticks.
- Text-only progress timeline with save/delete, tolerance notes and timestamps.
- Ingredient education catalogue with search; formal expert review is pending and no local price or brand claims are made.
- English/Bangla resources for functional screens and a language preference.
- Optional budget preference, local-data clearing, no account, no analytics and no cloud sync.
- Unit tests for deterministic guidance rules.
- Compose navigation instrumentation test covering all five destinations.
- LocalStore instrumentation tests covering routine save/clear, progress create/read/delete, and user-data clearing while retaining language preference.

## Storage limitation that must remain visible

The prototype stores routine ticks, text notes and simple preferences in app-private SharedPreferences, with JSON for the progress list. This is interim storage, not the final structured design. Before release, migrate to Room with tested schema migrations, review backup/restore behavior, and test deletion. No photos are captured, imported or stored. No internet permission, AI model, backend or photo upload is implemented.

## Verification

- Latest implementation commit: `cf8048453634e014d6a90f7875abcf314ca9b879`.
- CI run [#102](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015483767) passed.
- CI run [#103](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015490522) passed on the same commit.
- These workflows include localization parity, debug APK compilation, unit tests, Compose navigation instrumentation, local-store instrumentation and APK artifact upload.
- Earlier navigation assertion fixes are verified by runs [#100](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41011174011) and [#101](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41011179610).
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. Do not merge until remaining storage, privacy, content and release gates are addressed.

## Safety and external release gates

The app is educational and non-diagnostic. It does not provide selfie-based diagnosis, whitening promises, individualized medication advice, or homemade formula recipes. The guidance catalogue has not completed qualified expert review. A release still requires qualified skincare/dermatology review, Bangladesh legal/privacy review, Bangla usability review, storage/backup/deletion verification, and validation gates for any future image feature.

## Next implementation actions

1. Replace interim SharedPreferences/JSON persistence with a structured local database and tested migrations.
2. Add process-restart, malformed-data, deletion and reset tests around the new repository.
3. Review Android backup/data-extraction behavior and confirm target-device behavior.
4. Only then implement private photo progress with explicit selection, safe EXIF handling and verified deletion.
5. Keep image analysis and DIY formulas disabled unless their individual review/validation gates are met.
