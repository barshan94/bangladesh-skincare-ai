# Project State — 2026-11-25

## Repository and active work

- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Baseline branch: `implementation/m0-android-baseline`
- Active feature branch: `feature/brightening-system`
- Active draft PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open, draft and unmerged.
- Latest feature commit: `023cd07445942e4e41fb45c1b4e9273f2671908c`.
- Latest push CI [#45437693538](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45437693538) passed all workflow steps: English/Bangla resource parity, debug APK compilation, unit tests, Room schema export, Android navigation smoke tests, test-report upload and debug APK upload.
- CI artifacts were uploaded: `bangladesh-skincare-ai-debug`, `bangladesh-skincare-ai-test-reports`, and `bangladesh-skincare-ai-room-schema`. They are temporary GitHub Actions artifacts, not signed releases.

## Current capabilities

- Six Compose destinations: Skin Scan, My Routine, Progress, Ingredients, Profile, and Whitening & Brightening.
- Skin Scan and Brightening use self-reported questionnaires and deterministic educational guidance. They are not AI, diagnosis, or camera skin analysis.
- My Routine and Progress use local Room storage.
- Private selfie capture uses on-device face detection only for one-face/framing checks. Photos remain in app-private storage; users can compare and delete them. No acne, pigmentation, melasma, dark-spot, skin-tone or disease analysis is implemented.
- Side-by-side saved-photo comparison is visual-only; lighting, camera distance, angle and exposure can mislead.
- Selfie storage stages image writes before finalizing, validates record IDs/file names, commits metadata synchronously, limits retention to 30 photos and checks deletion failures.
- Full local-data reset deletes private selfie files/metadata and the saved formula draft. Capture timestamps are forced to be strictly increasing.
- Homemade Lab supports anhydrous-balm worksheet arithmetic, percentage-to-gram calculations, estimated BDT raw-material costs, and local save/load/delete of one draft. It is arithmetic only, not a recipe, safety validator, compatibility checker, concentration-limit checker or efficacy assessor. Water-based products, creams/lotions and sunscreen remain blocked.
- Ingredient catalogue and home-formulation safety guidance are educational. The bilingual formulation card covers ingredient identity/grade, concentration and compatibility, pH, validated preservation, stability, packaging and microbial testing.
- English/Bangla ingredient-label red-flag checker flags a limited list of mercury, hydroquinone and topical-steroid names. A no-match is not a safety clearance; it is not lab testing or product authentication.
- No account, analytics, cloud sync or photo upload is implemented.

## Verification

Latest CI run [#45437693538](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45437693538) on commit `023cd07` passed compilation, unit tests, schema export, Android navigation smoke tests, localization parity and artifact uploads. This validates the CI environment only; it does not replace owner-side device testing.

## Remaining core product work

1. **Genuine selfie skin analysis — not implemented.** Requires a legally usable and suitable dataset, expert/reference labels, a narrowly defined feature, representative Bangladeshi skin tones, device/lighting evaluation, repeatability and accuracy testing, on-device inference and privacy review. Face detection or simple pixel heuristics must not be presented as skin analysis.
2. Complete physical-device acceptance on the owner's Android phone: camera, storage/reset, TalkBack, font scaling, Bangla layout, airplane mode and upgrade behavior.
3. Strengthen brightening guidance using concern, sensitivity, reaction history, current products and budget, while retaining clinician referral for concerning or unexplained patches.
4. Expand sourced ingredient evidence and compatibility information; qualified review is required before actionable formulation guidance. No unpreserved water-based DIY recipes and no homemade sunscreen. The current worksheet is only a calculator, not a formulation recommendation.
5. Decide whether to provide a signed release APK after owner review; signing keys must remain owner-controlled.

## Safety and release boundary

This is a personal-use educational companion, not a diagnostic or prescription tool. The goal is to address uneven tone and excess pigmentation safely, not change genetically determined natural skin colour. Exclude mercury, bleach, steroid misuse, harsh kitchen remedies and unsupervised prescription lighteners. Patch testing cannot prove an unsafe or contaminated formula safe.

Do not merge PR #2 or call the app complete while genuine requested selfie analysis remains absent or owner-side device acceptance is outstanding.
