# Project State and Session Handoff

- Last updated: 2026-10-30
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active implementation branch: `implementation/m0-android-baseline`
- Current stage: Routine and Progress use Room; startup runs the one-time legacy import. Skin Scan can explicitly save a self-reported check-in to Progress. Ingredients now use a structured 11-entry catalogue with safety classifications; all entries remain pending expert review.
- Latest full CI #175 is still in progress on the previous source snapshot. Newer ingredient commits have queued runs #192/#193; neither has completed.
- Source of truth: this repository. No background sync is assumed.

## Latest ingredient evidence update — 2026-10-30
- Added publisher/source links to every ingredient entry (AAD, DermNet, MedlinePlus, ASCIA and peer-reviewed/open-access review sources).
- Ingredient cards now expose those links and open the selected publisher page in the external browser; English and Bangla labels are included.
- Unit tests validate that every entry has at least one uniquely listed HTTPS source.
- Sources support general education only. They do not validate a particular concentration, product, DIY recipe or personalized recommendation. All entries remain pending qualified expert review.
- Full Android CI runs #175 and #192 passed compilation, unit tests, emulator navigation tests and artifact upload. Run #193 has not yet returned a job result; verify the latest head before treating the source-link change as green.

## Implemented
- Native Kotlin/Jetpack Compose app with five tabs: Skin Scan, My Routine, Progress, Ingredients, Profile.
- Deterministic, non-diagnostic self-reported check-in engine with safety-first paths for concerning changes and broken skin.
- Skin Scan can save selected answers to local Progress only after the user taps the explicit save action.
- Routine and Progress screens read/write through Room-backed repository flows.
- App startup opens Room and runs legacy SharedPreferences import before displaying the main app screens.
- Profile language and budget preferences remain in SharedPreferences; data clearing has loading, success and failure states.
- Structured ingredient catalogue with 11 entries, categories, caution text, risk classifications and explicit pending-review status.
- English/Bangla resources and localization placeholder-parity validation.
- No account, analytics, cloud sync, photo capture/import, photo storage or image diagnosis.

## Room migration — implementation wired; verification pending
- Room entities/DAOs exist for routine completions, progress notes and migration metadata.
- Legacy importer parses old routine/progress JSON and inserts both datasets in one Room transaction.
- Importer preserves the old LocalStore defaults for optional progress fields.
- Migration marker is committed atomically to prevent duplicate imports after process restarts.
- Original preference values are retained unchanged after import as a recovery snapshot. Subsequent Room edits are not mirrored back, so this is not a downgrade guarantee.
- Explicit user-data reset clears Room data and legacy routine/progress snapshots; language preference is retained.
- MainActivity performs migration on IO before showing the app. If migration fails, it displays a safe error and does not clear legacy data.
- Instrumentation tests cover import, idempotency, malformed JSON, legacy optional-field compatibility, database close/reopen, reset and language retention.
- **Not yet verified:** CI #175 remains in progress; runs #192/#193 are queued for the later ingredient commits. Do not call Room migration QA-complete until full Android CI and a real-device upgrade check pass.

## Ingredient library safety status
- Entries are educational summaries, not formulation approvals or personalized recommendations.
- Each entry carries a category, caution, risk classification and `PENDING_EXPERT_REVIEW` status.
- Raw lemon juice is classified as avoid direct application; raw zinc oxide is restricted to use in a tested, labelled finished sunscreen.
- No ingredient concentrations, mixing instructions, DIY formulas or local product-price claims are provided.
- Catalogue tests enforce unique IDs, non-empty caution references, and expert-review gating.
- Qualified skincare/dermatology review and source documentation are still required before entries can be treated as approved guidance.

## Verification
- Baseline CI runs #102 and #103 passed on baseline commit `cf8048453634e014d6a90f7875abcf314ca9b879`.
- Latest full run [#175](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41037503937) is compiling; unit and emulator tests are pending.
- Ingredient changes are queued in [runs #192](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41038124568) and [#193](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41038134621).
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. It remains unmerged.

## Next actions
1. Get the full Android compile, unit and emulator instrumentation suite green on the latest source.
2. Verify a real app upgrade from an installed pre-Room build with populated routine/progress data.
3. Review Android backup/data-extraction behavior and test data deletion on a physical device.
4. Improve Skin Scan and Profile accessibility and saved-history clarity.
5. Add evidence/source metadata and obtain qualified expert review for the ingredient catalogue.
6. Only after storage/privacy gates, implement optional private photo progress. Keep image analysis and DIY formulas disabled until their individual validation/review gates pass.
