# Project State and Session Handoff

## 2026-11-17 — CI duplication reduction
- Restricted push-triggered Android CI to `main`; implementation branches are verified by their pull-request workflow. This prevents every development commit from launching both a push and pull-request emulator suite.
- Manual workflow dispatch remains available for branch checks without a pull request.


## 2026-11-17 — Daily routine tracking
- Routine completions are now stored with the user's local calendar date, so yesterday's checked items do not appear completed in today's checklist.
- The existing database version remains 1; this change reuses the existing routine ID column and requires no schema migration.
- Older un-dated legacy completion IDs are not treated as today's completions and are not deleted by this display change.
- Added a bilingual explanation and an emulator test that seeds an old dated completion and expects today's count to remain zero. Full CI verification is pending.


- Last updated: 2026-11-03
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

## Latest reliability work — 2026-10-30
- Ingredient cards now include per-entry publisher links; all 11 records have HTTPS source metadata and source-reference unit tests.
- Skin Scan save-to-Progress now disables repeat taps while saving, shows a localized loading state, prevents duplicate rapid submissions, and shows a localized error if persistence fails.
- Progress manual-note saving now preserves the draft until Room confirms the insert, disables duplicate submission while saving, and exposes localized saving/failure feedback.
- Progress deletion now requires explicit confirmation, disables concurrent delete actions, and exposes localized deletion/failure feedback inside the confirmation dialog.
- Progress tolerance choices are disabled during an active save so the persisted entry cannot disagree with the visible selection.
- Added an emulator UI test for Progress create → history display → confirmed delete. Latest Progress UI/test commits still need a full green Android workflow.
- Added an emulator UI test that runs a check-in, explicitly saves it, navigates to Progress and asserts the entry appears.
- The source-link and save-reliability changes are on the active PR branch; a full CI result for the latest head is still required.

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
- **Not yet verified:** Workflow #214 failed one emulator UI test after compilation and unit tests passed. The test was updated on the latest branch; workflow #218 is pending. Do not call Room migration QA-complete until a green full workflow and a real-device upgrade check pass.

## Ingredient library safety status
- Entries are educational summaries, not formulation approvals or personalized recommendations.
- Each entry carries a category, caution, risk classification and `PENDING_EXPERT_REVIEW` status.
- Raw lemon juice is classified as avoid direct application; raw zinc oxide is restricted to use in a tested, labelled finished sunscreen.
- No ingredient concentrations, mixing instructions, DIY formulas or local product-price claims are provided.
- Catalogue tests enforce unique IDs, non-empty caution references, and expert-review gating.
- Qualified skincare/dermatology review and source documentation are still required before entries can be treated as approved guidance.

## Verification
- Baseline CI runs #102 and #103 passed on baseline commit `cf8048453634e014d6a90f7875abcf314ca9b879`.
- Full Android workflow [#192](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41038124568) passed on an earlier ingredient update.
- Workflow [#214](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41077898501) failed only in the new Skin Scan save UI test: the localized save control was not found. Compilation, unit tests, report upload and debug APK upload succeeded.
- The Skin Scan test now waits for the guidance result before finding and scrolling to its save action. The Progress deletion test now asserts the confirmation dialog before invoking its action and searches the unmerged semantics tree. Profile preference deletion requires a successful synchronous commit.
- Latest workflow #244 passed resource parity, debug compilation and unit tests, and was running emulator instrumentation when last checked. Workflow #246 was still compiling. Both predate the newest test-hardening commit `eed6932`; a fresh full workflow on that commit is required.
- The Android workflow is configured to run on pushes to `implementation/**` when app files change. The newest test-hardening commit is `eed6932ff775e47db16f85c2d217d8c307267076`. No full green workflow is claimed yet.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. It remains unmerged.

## 2026-11-03 — Ingredient catalogue usability
- Added localized risk-class filters: all, general caution, avoid direct application, and finished-sunscreen-only.
- Ingredient search now also matches publisher/source titles and shows a localized empty state when no entry matches.
- Added emulator UI tests for risk filtering and no-result search behavior. These are not verified until the latest full workflow completes.
- Product concept/formulation workspace is not implemented. Room schema export v1 is absent from the tracked repository, so no unverified database schema bump was made. CI now uploads generated Room schema JSON as a separate artifact for review; a verified v1 fixture and forward-only migration tests are still required before adding new persisted entities.

- Latest test changes specifically address both failures observed in #234. The result of these changes is pending CI.

## Next actions
1. Get the full Android compile, unit and emulator instrumentation suite green on the latest source, including Progress and ingredient filter/search UI tests.
2. Verify a real app upgrade from an installed pre-Room build with populated routine/progress data.
3. Review Android backup/data-extraction behavior and test data deletion on a physical device.
4. Improve Skin Scan and Profile accessibility and saved-history clarity; finish Progress and ingredient filtering UI QA.
5. Add evidence/source metadata and obtain qualified expert review for the ingredient catalogue.
6. Only after storage/privacy gates, implement optional private photo progress. Keep image analysis and DIY formulas disabled until their individual validation/review gates pass.

## 2026-11-17 — Personal-use scope and resumed verification
- The owner has clarified this is a personal-use project, not a commercial product or client deliverable. Prioritize useful, safe offline features and personal data reliability; remove commercial, multi-tenant, customer-support, monetization and business-readiness work from scope unless explicitly requested.
- Checked the latest previous full workflow #252 (run `41631443877`): resource parity, debug compilation and unit tests passed, but 2 of 20 emulator tests failed. Failures were in `progressDraftIsSavedAndDeletionRequiresConfirmation` (confirmation dialog was not found after tapping delete) and `selfReportedCheckInCanBeExplicitlySavedToRoomProgressHistory` (guidance result did not appear within the test wait).
- Retrieved the actual generated Room v1 schema artifact and added it to `app/schemas/com.barshan.skincareai.data.SkincareDatabase/1.json`. Database remains version 1; no migration or schema change was introduced.
- Hardened the two failing UI flows by scrolling the relevant controls into view and waiting explicitly for the guidance result and delete-confirmation dialog. Commit `f95411be95b2a11ef6f67610621ad342a2246a59` triggered workflow #255; result is pending at this update.
- No full green workflow is claimed. Do not add new persisted entities until migration tests are ready. Personal-use scope does not remove the need for safe storage, privacy, medical-content caution or physical-device checks.


- Last updated: 2026-11-03
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

## Latest reliability work — 2026-10-30
- Ingredient cards now include per-entry publisher links; all 11 records have HTTPS source metadata and source-reference unit tests.
- Skin Scan save-to-Progress now disables repeat taps while saving, shows a localized loading state, prevents duplicate rapid submissions, and shows a localized error if persistence fails.
- Progress manual-note saving now preserves the draft until Room confirms the insert, disables duplicate submission while saving, and exposes localized saving/failure feedback.
- Progress deletion now requires explicit confirmation, disables concurrent delete actions, and exposes localized deletion/failure feedback inside the confirmation dialog.
- Progress tolerance choices are disabled during an active save so the persisted entry cannot disagree with the visible selection.
- Added an emulator UI test for Progress create → history display → confirmed delete. Latest Progress UI/test commits still need a full green Android workflow.
- Added an emulator UI test that runs a check-in, explicitly saves it, navigates to Progress and asserts the entry appears.
- The source-link and save-reliability changes are on the active PR branch; a full CI result for the latest head is still required.

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
- **Not yet verified:** Workflow #214 failed one emulator UI test after compilation and unit tests passed. The test was updated on the latest branch; workflow #218 is pending. Do not call Room migration QA-complete until a green full workflow and a real-device upgrade check pass.

## Ingredient library safety status
- Entries are educational summaries, not formulation approvals or personalized recommendations.
- Each entry carries a category, caution, risk classification and `PENDING_EXPERT_REVIEW` status.
- Raw lemon juice is classified as avoid direct application; raw zinc oxide is restricted to use in a tested, labelled finished sunscreen.
- No ingredient concentrations, mixing instructions, DIY formulas or local product-price claims are provided.
- Catalogue tests enforce unique IDs, non-empty caution references, and expert-review gating.
- Qualified skincare/dermatology review and source documentation are still required before entries can be treated as approved guidance.

## Verification
- Baseline CI runs #102 and #103 passed on baseline commit `cf8048453634e014d6a90f7875abcf314ca9b879`.
- Full Android workflow [#192](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41038124568) passed on an earlier ingredient update.
- Workflow [#214](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/41077898501) failed only in the new Skin Scan save UI test: the localized save control was not found. Compilation, unit tests, report upload and debug APK upload succeeded.
- The Skin Scan test now waits for the guidance result before finding and scrolling to its save action. The Progress deletion test now asserts the confirmation dialog before invoking its action and searches the unmerged semantics tree. Profile preference deletion requires a successful synchronous commit.
- Latest workflow #244 passed resource parity, debug compilation and unit tests, and was running emulator instrumentation when last checked. Workflow #246 was still compiling. Both predate the newest test-hardening commit `eed6932`; a fresh full workflow on that commit is required.
- The Android workflow is configured to run on pushes to `implementation/**` when app files change. The newest test-hardening commit is `eed6932ff775e47db16f85c2d217d8c307267076`. No full green workflow is claimed yet.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. It remains unmerged.

## 2026-11-03 — Ingredient catalogue usability
- Added localized risk-class filters: all, general caution, avoid direct application, and finished-sunscreen-only.
- Ingredient search now also matches publisher/source titles and shows a localized empty state when no entry matches.
- Added emulator UI tests for risk filtering and no-result search behavior. These are not verified until the latest full workflow completes.
- Product concept/formulation workspace is not implemented. Room schema export v1 is absent from the tracked repository, so no unverified database schema bump was made. CI now uploads generated Room schema JSON as a separate artifact for review; a verified v1 fixture and forward-only migration tests are still required before adding new persisted entities.

- Latest test changes specifically address both failures observed in #234. The result of these changes is pending CI.

## Next actions
1. Get the full Android compile, unit and emulator instrumentation suite green on the latest source, including Progress and ingredient filter/search UI tests.
2. Verify a real app upgrade from an installed pre-Room build with populated routine/progress data.
3. Review Android backup/data-extraction behavior and test data deletion on a physical device.
4. Improve Skin Scan and Profile accessibility and saved-history clarity; finish Progress and ingredient filtering UI QA.
5. Add evidence/source metadata and obtain qualified expert review for the ingredient catalogue.
6. Only after storage/privacy gates, implement optional private photo progress. Keep image analysis and DIY formulas disabled until their individual validation/review gates pass.
