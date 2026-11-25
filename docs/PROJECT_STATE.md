# Project State — 2026-11-25

## Repository and verified build

- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active branch: `feature/brightening-system`
- PR [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2) remains open, draft and unmerged.
- Latest app-code commit: `d46aea6f409aa602b9bfacf0f3443ea4296450ec`.
- Latest full CI [#45566453626](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45566453626) passed every step: localization parity, toolchain/SDK setup, debug APK compilation, unit tests, Room schema export, emulator navigation smoke tests and artifact uploads.
- Debug APK, test-report and Room schema artifacts are available until 2026-12-09. They are temporary CI artifacts, not signed release assets.

## Current capabilities

- Six Compose destinations: Skin Scan, My Routine, Progress, Ingredients, Profile and Whitening & Brightening.
- Rule-based non-diagnostic guidance; brightening safety prompts cover skin feel, prior reaction, broken/irritated skin, concerning changes, sunscreen habits, multiple strong actives and current clinician-prescribed skin medicine.
- Local Room-backed routine and progress records.
- Private selfie capture with ML Kit one-face/framing check, app-private storage, local timeline, delete and side-by-side visual comparison. No pigmentation/acne/melasma/dark-spot/skin-tone/disease analysis.
- Selfie storage includes staged writes, metadata validation, synchronous commits, checked deletion, 30-photo retention and monotonic timestamps.
- Homemade Lab: anhydrous-balm arithmetic only, percentage-to-gram calculations, estimated BDT raw-material cost and local save/load/delete of one draft. Not a recipe or safety validator. Water-based products, creams/lotions and sunscreen are blocked.
- Ingredient catalogue, source links, bilingual education and limited English/Bangla red-flag label keyword checker. No-match is not proof of safety; entries remain pending expert review.
- Full local reset includes selfie files/metadata and formula draft. No account, analytics, cloud sync or photo upload.

## Deferred by owner

Selfie-based skin analysis is explicitly deferred for now. Do not replace it with face detection, skin-tone scoring or unvalidated pixel heuristics. Reconsider only after legal data rights, qualified reference labels, representative Bangladeshi skin tones, device/lighting validation, repeatability/accuracy testing, on-device inference and privacy review.

## Release gates

1. Owner-side physical-device acceptance on the target Android phone: camera, photo timeline/deletion, reset, offline mode, TalkBack, font scaling, Bangla layout and upgrade behavior.
2. Optional signed private APK using an owner-controlled key. The key/password must never be shared in chat or committed.
3. Qualified review before adding actionable ingredient/formulation claims or recipe recommendations.

The code and emulator CI are green, but this is not a signed release and physical-device acceptance has not been performed. Keep PR #2 draft/unmerged until owner review.
