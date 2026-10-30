# Project State and Session Handoff

- Last updated: 2026-10-30
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active branch: implementation/m0-android-baseline
- Current stage: offline MVP screens implemented and baseline CI verified; Room legacy-import foundation has now been added.
- Source of truth: this repository. No background sync is assumed.

## Implemented
- Native Kotlin/Jetpack Compose app with five tabs: Skin Scan, My Routine, Progress, Ingredients, Profile.
- Deterministic, non-diagnostic self-reported check-in engine with safety-first paths for concerning changes and broken skin.
- Routine checklist with locally saved completion ticks.
- Text-only progress timeline with save/delete, tolerance notes and timestamps.
- Searchable ingredient education catalogue; formal expert review remains pending.
- English/Bangla resources, language preference, optional budget preference and local-data clearing.
- No account, analytics, cloud sync, photo capture/import, photo storage or image diagnosis.
- Unit tests for guidance rules and instrumentation tests for navigation and existing local-store behavior.

## Room migration work in progress
- Room entities/DAOs already exist for routine completions, progress notes and migration metadata.
- Added a one-time importer that parses the existing SharedPreferences routine/progress JSON and inserts both datasets in one Room transaction.
- An idempotency marker is committed in the same transaction to prevent duplicate imports after process restarts.
- Original SharedPreferences values are deliberately retained as rollback copies; malformed legacy data fails without setting the marker or deleting source values.
- Added instrumentation tests for successful import, idempotency and malformed-data preservation.
- **Not complete:** the importer is not yet called during app startup, and Routine/Progress UI still reads and writes SharedPreferences through the old LocalStore. Do not call the app Room-migrated until startup wiring, UI cutover and upgrade tests pass.
- Profile language/budget preferences remain in SharedPreferences by design.

## Verification
- Baseline CI runs [#102](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015483767) and [#103](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41015490522) passed on commit cf8048453634e014d6a90f7875abcf314ca9b879.
- Room importer/test commits have been pushed; verify the latest workflow before treating this milestone as green.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. Do not merge until storage, privacy, content and release gates are addressed.

## Safety and external release gates
The app is educational and non-diagnostic. It does not provide selfie-based diagnosis, whitening promises, individualized medication advice, or homemade formula recipes. The guidance catalogue has not completed qualified expert review. A release still requires qualified skincare/dermatology review, Bangladesh legal/privacy review, Bangla usability review, storage/backup/deletion verification, and validation gates for any future image feature.

## Next actions
1. Wire the importer into app startup before Room-backed screens read data.
2. Replace Routine and Progress SharedPreferences reads/writes with Room repository/Flow.
3. Test actual upgrade with populated legacy values, process death/restart, delete and reset.
4. Review Android backup/data-extraction behavior and confirm target-device behavior.
5. Only then implement private photo progress. Keep image analysis and DIY formulas disabled until their individual review/validation gates pass.
