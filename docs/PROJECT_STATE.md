# Project State and Session Handoff

- Last updated: 2026-10-30
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active implementation branch: `implementation/m0-android-baseline`
- Current stage: Routine and Progress have been switched to Room; one-time legacy import is wired into startup. Instrumentation coverage for database reopen/restart and explicit reset has been added. Latest CI run #143 is pending.
- Source of truth: this repository. No background sync is assumed.

## Implemented
- Native Kotlin/Jetpack Compose app with five tabs: Skin Scan, My Routine, Progress, Ingredients, Profile.
- Deterministic, non-diagnostic self-reported check-in engine with safety-first paths for concerning changes and broken skin.
- Routine checklist and progress timeline now read/write Room through `RoomSkincareRepository`.
- App startup opens Room and runs the legacy SharedPreferences import before showing the main app screens.
- One-time import runs in a transaction with a marker; original SharedPreferences routine/progress JSON remains as a rollback copy until explicit user reset.
- Progress note add/delete and routine completion updates use Room-backed flows.
- Profile language and budget preferences remain in SharedPreferences by design.
- Searchable ingredient education catalogue; qualified expert review remains pending.
- English/Bangla resources, optional budget preference and local-data clearing.
- No account, analytics, cloud sync, photo capture/import, photo storage or image diagnosis.

## Room migration — implementation wired; verification pending
- Room entities/DAOs exist for routine completions, progress notes and migration metadata.
- `LegacyPreferencesMigration` parses the old routine/progress JSON and inserts both datasets in one Room transaction.
- Importer preserves the old LocalStore defaults for optional progress fields (missing timestamp -> 0; missing text -> empty; missing tolerance -> "Not recorded").
- Migration marker is committed atomically to prevent duplicate imports after process restarts.
- Original preference values are retained after import for rollback. Explicit user-data reset clears Room data and legacy routine/progress copies; language preference is retained.
- `MainActivity` now performs migration on IO before showing app screens. If migration fails, it displays a safe error and does not clear legacy data.
- Routine and Progress screens use Room-backed Flows. Profile's language/budget settings stay in preferences.
- Instrumentation tests cover successful import, idempotency, malformed JSON preservation, legacy optional-field compatibility, database close/reopen, reset behavior and language retention.
- **Not yet verified:** latest Android CI run #143 is queued. Do not claim the Room migration is QA-complete until compile, unit tests, emulator instrumentation tests and APK artifact upload all pass, followed by a real-device upgrade check.

## Verification
- Baseline CI runs [#102](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015483767) and [#103](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015490522) passed on commit `cf8048453634e014d6a90f7875abcf314ca9b879`.
- Latest migration/lifecycle test commit: `0700bc2411d3139ea3b690aca6ce940a598146eb`.
- Latest CI: [run #143](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41036494722) — pending.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. It remains unmerged.

## Safety and external release gates
The app is educational and non-diagnostic. It does not provide selfie-based diagnosis, whitening promises, individualized medication advice, or homemade formula recipes. The ingredient summaries have not completed qualified expert review. Release still requires qualified skincare/dermatology review, Bangladesh legal/privacy review, Bangla usability review, storage/backup/deletion verification, and validation gates for any future image feature.

## Next actions
1. Get CI #143 green; fix compile/test failures before adding new scope.
2. Verify a real app upgrade from an installed pre-Room build with populated routine/progress data.
3. Review Android backup/data-extraction behavior and test data deletion on a physical device.
4. Improve Skin Scan and Profile experiences without implying diagnosis or collecting unnecessary data.
5. Build a versioned, sourced ingredient knowledge library and safety-warning model; expert review remains a release gate.
6. Only after storage/privacy gates, implement optional private photo progress. Keep image analysis and DIY formulas disabled until their individual validation/review gates pass.
