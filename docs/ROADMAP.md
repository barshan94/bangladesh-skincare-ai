# Personal-use roadmap and readiness gates

Last reviewed: 2026-11-17  
Active branch: `implementation/m0-android-baseline`  
Latest verified commit: `eb70ebcfc9c512dde510612498588073f610c2b1`

## Scope

This is a personal-use, offline-first Android app. Prioritize safe local record keeping and understandable educational content. Do not build commercial, multi-tenant, customer-support, monetization, cloud-sync, photo-diagnosis, or DIY formulation features unless the owner explicitly changes scope.

## Implemented and CI-verified

- [x] Native Kotlin + Jetpack Compose app with Skin Scan, My Routine, Progress, Ingredients and Profile.
- [x] English and Bangla resources with CI placeholder/key parity validation.
- [x] Self-reported, non-diagnostic skin check-in; saving to Progress requires an explicit user action.
- [x] Room-backed Routine and Progress storage with one-time legacy SharedPreferences import.
- [x] Import idempotency, malformed legacy data handling, database reopen coverage and local reset coverage.
- [x] Daily routine completion keys so yesterday's completion does not carry into today's checklist.
- [x] Progress create, edit-in-place, delete confirmation, failure handling and localized feedback.
- [x] Ingredient search, risk filters and publisher links for the 11 educational entries; all entries remain pending expert review.
- [x] Room v1 schema fixture checked into source control; no schema version change was made.
- [x] User-data reset clears the legacy snapshot first, then clears Room transactionally.
- [x] Regression test proves primary Room records survive a forced reset failure and can be cleared on retry.
- [x] Latest complete CI [run #289](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44077648148) passed, including emulator instrumentation and artifact upload.

## Remaining owner/device checks

- [ ] Install an earlier build containing legacy SharedPreferences routine/progress data, upgrade to the current build, and verify import, restart persistence and no duplicate import.
- [ ] On a physical device, verify reset removes all routine/progress records and legacy snapshots while retaining language preference.
- [ ] Review Android backup/data-extraction behavior on-device; confirm no unintended personal skin/progress data is backed up.
- [ ] Accessibility review on a physical device: TalkBack semantics and traversal, font scaling, contrast, touch targets, and English/Bangla layout overflow.
- [ ] Check app behavior on at least one lower-memory Android device and one current Android version.
- [ ] Only after these checks, produce and verify a signed private APK. Keep the signing key outside GitHub/repository history and document owner-controlled key backup.

## Content safety gates

- [ ] Keep Skin Scan clearly self-reported and non-diagnostic; no disease diagnosis, beauty/fairness score, or guaranteed whitening claim.
- [ ] Keep ingredient entries educational and marked pending review until a qualified skincare/dermatology professional reviews the wording and sources.
- [ ] Do not add concentrations, mixing instructions, home-prepared formulas or personalized treatment claims.
- [ ] Any future content change must have traceable sources, clear stop conditions and bilingual review.

## Explicitly out of scope for this personal-use release

- Cloud accounts, cloud sync, analytics, advertising, monetization and multi-user administration.
- Photo upload, remote storage, AI image diagnosis or automated skin scoring.
- Free-form AI skincare prescriptions or DIY whitening recipes.
- Product marketplace, commercial product substitutions/pricing, and client/customer workflows.

## Release decision

**Not yet ready to call a signed private release.** The current source passes the full automated Android workflow, but a green emulator run does not replace the real-device upgrade, reset, accessibility and signing checks above. The available debug APK is for testing only; do not distribute it as a signed release.
