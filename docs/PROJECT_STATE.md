# Project State — 2026-11-24

## Repository and active work

- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Baseline branch: `implementation/m0-android-baseline`
- Active feature branch: `feature/brightening-system`
- Active draft PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open, draft and unmerged.
- Latest feature work: formula worksheet now supports anhydrous-balm batch arithmetic, ingredient raw-material cost estimates, private save/load/delete of one draft, and full-reset deletion. The calculator is arithmetic only, not a safety or efficacy checker.
- Latest push CI [#44977709002](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44977709002) and PR CI [#44977714807](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44977714807) both completed successfully. The workflow passed resource parity, debug APK compilation, unit tests, Room schema export, Android navigation smoke tests, test-report upload and APK upload.
- The push workflow uploaded a debug APK artifact (36 MB) and test/schema artifacts. GitHub Actions artifacts expire on 2026-12-06.

## Current capabilities

- Six Compose destinations: Skin Scan, My Routine, Progress, Ingredients, Profile, and Whitening & Brightening.
- Skin Scan and Brightening use self-reported questionnaires and deterministic educational guidance. They are not AI, diagnosis, or camera skin analysis.
- My Routine and Progress use local Room storage.
- Private selfie capture uses on-device face detection only for one-face/framing checks. Photos remain in app-private storage; users can compare and delete them. No acne, pigmentation, melasma, dark-spot, skin-tone or disease analysis is implemented.
- Side-by-side saved-photo comparison is visual-only; lighting, camera distance, angle and exposure can mislead. Capture guidance encourages repeatable conditions but is not image analysis.
- Selfie storage now stages image writes before finalizing, validates record IDs/file names, commits metadata synchronously, limits retention to 30 photos and checks deletion failures.
- Full local-data reset now also deletes private selfie files and metadata. Capture timestamps are forced to be strictly increasing to prevent same-millisecond captures from breaking newest-first ordering or pruning the wrong photo.
- Homemade Lab currently supports anhydrous worksheet arithmetic, BDT raw-material estimates, and local draft save/load/delete. It does not recommend ingredients or concentrations. The formula is not safety-checked. Ingredients catalogue and home-formulation safety guidance are educational. The bilingual formulation card covers ingredient identity/grade, concentration and compatibility, pH, validated preservation, stability, packaging and microbial testing. No recipes, formula percentages, shelf-life claims or homemade sunscreen instructions are provided. Ingredient entries remain pending expert review.
- CI for commit `c69b4cc31f0bebd55a63c1303a24e0f17ea68be0` passed resource parity, debug APK compilation, unit tests, Room schema export, Android navigation smoke tests and APK/report uploads. The later draft-delete change is awaiting CI.
- No account, analytics, cloud sync or photo upload is implemented.

## Remaining core product work

1. **Genuine selfie skin analysis — not implemented.** Requires a legally usable and suitable dataset, expert/reference labels, a narrowly defined feature, representative Bangladeshi skin tones, device/lighting evaluation, repeatability and accuracy testing, on-device inference and privacy review. Face detection or simple pixel heuristics must not be presented as skin analysis.
2. Add conservative photo-quality assistance only after separate validation; distinguish blur/exposure/framing warnings from skin-condition interpretation.
3. Strengthen brightening guidance using concern, sensitivity, reaction history, current products and budget, while retaining clinician referral for concerning or unexplained patches.
4. Expand sourced ingredient evidence and compatibility information; qualified review is required before actionable formulation guidance. No unpreserved water-based DIY recipes and no homemade sunscreen. The current worksheet is only a calculator, not a formulation recommendation.
5. Complete physical-device acceptance on the owner's Android phone: camera, storage/reset, TalkBack, font scaling, Bangla layout, airplane mode and upgrade behavior. Emulator CI does not replace this.
6. Decide whether to provide a signed release APK after owner review; signing keys must remain owner-controlled.

## Safety and release boundary

This is a personal-use educational companion, not a diagnostic or prescription tool. The goal is to address uneven tone and excess pigmentation safely, not change genetically determined natural skin colour. Exclude mercury, bleach, steroid misuse, harsh kitchen remedies and unsupervised prescription lighteners. Patch testing cannot prove an unsafe or contaminated formula safe.

Do not merge PR #2 or call the app complete while genuine requested selfie analysis remains absent or owner-side device acceptance is outstanding.
