# Project State and Session Handoff

- Last updated: 2026-10-30
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active work branch: `implementation/m2-room-legacy-migration`
- Baseline branch: `implementation/m0-android-baseline`
- Current stage: offline MVP screens are implemented and CI-verified; Room migration work has started in an isolated branch.
- Source of truth: this repository. No background sync is assumed.

## Implemented in the baseline branch

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

## Room migration work in progress

- Existing Room database schema includes routine completions, progress notes and migration metadata.
- New `LegacyPreferencesMigration` imports the old routine JSON and progress JSON into Room inside one database transaction.
- A migration marker makes the import idempotent and prevents duplicate imports after process restarts.
- The original SharedPreferences JSON is deliberately retained after import as a rollback copy; a failed/malformed import does not set the marker or delete the source.
- Instrumentation tests cover successful import, idempotency, and malformed-data preservation.
- **Not complete yet:** connect the importer to app startup, switch Routine and Progress UI/repository reads and writes to Room, and test actual on-device upgrade from the existing app version. Do not describe the app as Room-migrated until those steps pass.
- Profile language/budget preferences remain in SharedPreferences by design for this migration; only routine/progress data are being moved to Room.

## Verification

- Baseline implementation commit: `cf8048453634e014d6a90f7875abcf314ca9b879`.
- CI run [#102](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015483767) passed.
- CI run [#103](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015490522) passed on the same baseline commit.
- Room migration branch has new commits; its CI verification is pending.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. Do not merge until storage, privacy, content and release gates are addressed.

## Safety and external release gates

The app is educational and non-diagnostic. It does not provide selfie-based diagnosis, whitening promises, individualized medication advice, or homemade formula recipes. The guidance catalogue has not completed qualified expert review. A release still requires qualified skincare/dermatology review, Bangladesh legal/privacy review, Bangla usability review, storage/backup/deletion verification, and validation gates for any future image feature.

## Next implementation actions

1. Finish wiring the Room importer into application startup and switch Routine/Progress to Room-backed state.
2. Add real upgrade tests against seeded legacy SharedPreferences, process restart, deletion and reset.
3. Review Android backup/data-extraction behavior and confirm target-device behavior.
4. Only then implement private photo progress with explicit selection, safe EXIF handling and verified deletion.
5. Keep image analysis and DIY formulas disabled unless their individual review/validation gates are met.
