# Personal-use roadmap and readiness

Last reviewed: 2026-11-17  
Active branch: `implementation/m0-android-baseline`  
Latest full CI: [run #300](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44106499330) — success.

## Scope

Offline-first Android skincare tracker for personal use. Prioritize private record keeping and general education. No cloud accounts, analytics, photo diagnosis, treatment prescriptions, DIY whitening formulas or commercial workflows.

## Implemented

- [x] Native Kotlin + Jetpack Compose app with five sections.
- [x] English/Bangla resources and CI parity validation.
- [x] Self-reported Skin Scan check-in with deterministic non-diagnostic general guidance.
- [x] Room-backed Routine and Progress with one-time legacy preference import.
- [x] Import idempotency, malformed legacy data handling and database reopen coverage.
- [x] Daily routine completion keys.
- [x] Progress create/edit/delete, confirmation and failure handling.
- [x] Ingredient catalogue with 11 general educational entries, search, filters and source links.
- [x] Room schema export and reset recovery regression test.
- [x] Accessible checkbox semantics for Skin Scan and Routine.
- [x] Latest complete CI [run #300](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44106499330) passed, including emulator instrumentation and artifacts.

## Owner phone acceptance

- [ ] Install the CI debug APK and smoke-test all five sections.
- [ ] If migrating from a prior installed version, verify import once and data persistence after restart; do not clear app storage before this test.
- [ ] Verify Profile reset clears Routine/Progress and preserves language preference.
- [ ] Check TalkBack, large font, touch targets and Bangla/English overflow.
- [ ] Check airplane-mode launch and permission prompts.
- [ ] If a stable signed APK is desired, sign with an owner-controlled key and verify install/upgrade.

## Safety boundary

The ingredient list is general education, not a clinically reviewed treatment guide. This is not a blocker to personal use as long as the app remains non-diagnostic and non-prescriptive. Do not add individualized treatment, strong active protocols, DIY formulations or clinical claims without qualified review. Public/commercial distribution needs separate review.

## Release decision

**Software implementation for the current personal-use scope is complete and CI-green.** Physical phone acceptance has not yet been reported. A signed private APK has not yet been produced. Do not describe either of those two items as complete.
