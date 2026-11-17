# Project State — 2026-11-17

## Verified repository state

- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active branch: `implementation/m0-android-baseline`
- Draft PR: [#1](https://github.com/barshan94/bangladesh-skincare-ai/pull/1), open and unmerged.
- Latest source/documentation commit: `375fd5dcea7ceac5bdc94bdd1404b7150d0ec965`.
- Latest full CI: [run #295](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44090947039), **passed**.
- CI steps passed: English/Bangla resource parity, debug APK compilation, unit tests, Room schema export, Android emulator navigation/instrumentation tests, test-report upload and debug APK upload.
- Debug APK artifact: `bangladesh-skincare-ai-debug`, SHA-256 `7edbd80f915df7afa1b1e7ed9a90c39e30b3db44f6ff06eb5abab176d202992a`. This is not a signed release APK.

## Implemented

- Five Compose sections: Skin Scan, My Routine, Progress, Ingredients and Profile.
- English/Bangla localization and automated parity validation.
- Self-reported, non-diagnostic Skin Scan with explicit save to Progress.
- Room-backed Routine and Progress with one-time/idempotent legacy SharedPreferences import.
- Daily routine completion identifiers.
- Progress create/edit/delete, confirmation, localized error feedback and regression tests.
- Ingredient catalogue with 11 educational entries, search, risk filters and source links; all entries remain pending qualified review.
- Profile preferences and local data reset.
- Accessible toggle semantics and combined checkbox labels for Skin Scan and Routine.
- Android backup disabled in the manifest; app currently has no network permission, account or analytics.

## Remaining release blockers

1. Physical upgrade test from a prior installed version containing legacy preferences; verify one-time import, restart persistence and no duplicate records.
2. Physical-device reset test; verify Routine/Progress are deleted and language preference remains.
3. Verify Android backup/data-extraction behavior on device.
4. Physical accessibility/layout checks: TalkBack, text scaling, contrast, touch targets and Bangla/English overflow.
5. Test one lower-memory device and one current Android device.
6. Qualified skincare/dermatology review of the 11 ingredient entries and their cited sources.
7. After all above, create and verify signed private APK. Signing key/passphrase remain owner-controlled and outside GitHub.

## Not implemented / intentionally deferred

Photo capture/import, photo storage/timeline, image analysis, AI diagnosis or prescriptions, cloud/backend/accounts, analytics, product marketplace, DIY whitening recipes and commercial/client workflows. These are not hidden incomplete tasks for the current release; they are out of scope.

## Important distinction

Automated CI is green, but physical-device behavior, professional content approval and signed release are not verified. Do not call this release-ready or distribute the debug APK as a private release.

## Session handoff

Continue with code/documentation review that can be completed without user secrets or external approvals. Ask the owner to perform device-only checks when needed; do not fabricate results. Keep PR #1 draft until the release blockers are actually cleared.
