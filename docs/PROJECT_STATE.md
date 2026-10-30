# Project State and Session Handoff

- Last updated: 2026-10-30
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active implementation branch: `implementation/m0-android-baseline`
- Current stage: Routine and Progress use Room; startup runs the one-time legacy import. Skin Scan now lets users explicitly save a self-reported check-in to local Progress history. Latest full CI run #175 is compiling the debug APK; unit and emulator tests have not yet run.
- Source of truth: this repository. No background sync is assumed.

## Implemented
- Native Kotlin/Jetpack Compose app with five tabs: Skin Scan, My Routine, Progress, Ingredients, Profile.
- Deterministic, non-diagnostic self-reported check-in engine with safety-first paths for concerning changes and broken skin.
- Skin Scan can save the selected self-reported answers to local Progress only after the user taps the save action; the save action is explicit and localized in English/Bangla.
- Routine and Progress screens read/write through Room-backed repository flows.
- App startup opens Room and runs legacy SharedPreferences import before displaying the main app screens.
- Progress note add/delete and routine completion updates use Room-backed flows.
- Profile language and budget preferences remain in SharedPreferences by design.
- Searchable ingredient education catalogue; qualified expert review remains pending.
- English/Bangla resources, optional budget preference and local-data clearing.
- No account, analytics, cloud sync, photo capture/import, photo storage or image diagnosis.

## Room migration — implementation wired; verification pending
- Room entities/DAOs exist for routine completions, progress notes and migration metadata.
- Legacy importer parses old routine/progress JSON and inserts both datasets in one Room transaction.
- Importer preserves the old LocalStore defaults for optional progress fields.
- Migration marker is committed atomically to prevent duplicate imports after process restarts.
- Original preference values are retained unchanged after import as a recovery snapshot. Subsequent Room edits are not mirrored back, so this is not a downgrade guarantee. Explicit user-data reset clears Room data and legacy routine/progress snapshots; language preference is retained.
- MainActivity performs migration on IO before showing the app. If migration fails, it displays a safe error and does not clear legacy data.
- Instrumentation tests cover successful import, idempotency, malformed JSON preservation, legacy optional-field compatibility, database close/reopen, reset behavior and language retention.
- **Not yet verified:** latest Android CI run #175 is in progress. Do not call Room migration QA-complete until compile, unit tests, emulator instrumentation tests and APK artifact upload pass, followed by a real-device upgrade check.

## Verification
- Baseline CI runs #102 and #103 passed on baseline commit `cf8048453634e014d6a90f7875abcf314ca9b879`.
- Room lifecycle implementation commit: `0700bc2411d3139ea3b690aca6ce940a598146eb`.
- Skin Scan save-to-Progress commit: `327511d1d101c61dddbaa1a93a768b4cc9d4a3c5`; bilingual resource commit `19ac47326f8c389e575faceef67b08c74e464096`.
- Latest full CI: [run #175](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41037503937) — debug APK compilation in progress; unit and emulator tests pending.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. It remains unmerged.

## Safety and external release gates
The app is educational and non-diagnostic. It does not provide selfie-based diagnosis, whitening promises, individualized medication advice, or homemade formula recipes. Ingredient summaries have not completed qualified expert review. Release still requires qualified skincare/dermatology review, Bangladesh legal/privacy review, Bangla usability review, storage/backup/deletion verification, and validation gates for any future image feature.

## Next actions
1. Get CI #175 green; fix compile/test failures before expanding scope.
2. Verify a real app upgrade from an installed pre-Room build with populated routine/progress data.
3. Review Android backup/data-extraction behavior and test data deletion on a physical device.
4. Improve Profile and Skin Scan accessibility, saved-history clarity and error handling.
5. Build a versioned, sourced ingredient knowledge library and structured safety warnings; expert review remains a release gate.
6. Only after storage/privacy gates, implement optional private photo progress. Keep image analysis and DIY formulas disabled until their individual validation/review gates pass.
