# Project State — 2026-11-19

## Repository and active work

- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Baseline branch: `implementation/m0-android-baseline`
- Active feature branch: `feature/brightening-system`
- Active draft PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open and unmerged.
- Latest implementation: ingredient catalogue expanded with five dermatologist-listed dark-spot ingredient profiles (azelaic acid, glycolic acid, kojic acid, retinoids and vitamin C), each with English/Bangla cautions and an AAD source link. All remain pending expert review and are not prescriptions or DIY formulas.
- Latest CI for the concern-intake revision: run [#44447672282](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44447672282) passed on push, while PR run [#44447678236](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44447678236) failed in Android navigation smoke tests. APK compilation and unit tests passed in that PR run. The failure occurred around emulator/ADB shutdown with exit code 224; the exact test-level cause is not established.
- Latest CI for commit `d6ce8f4` passed on both push run [#44480719628](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44480719628) and PR run [#44480727626](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44480727626). This confirms the previous ingredient-filter smoke-test synchronization change.

## Current capabilities

- Six Compose destinations: Skin Scan, My Routine, Progress, Ingredients, Profile, and Whitening & Brightening.
- Skin Scan and Brightening use self-reported questionnaires and deterministic educational guidance. They are not AI, diagnosis, or camera skin analysis.
- My Routine and Progress use local Room storage.
- Private selfie capture uses on-device face detection only for one-face/framing checks; photos are stored in app-private storage and can be deleted. It does not analyse acne, pigmentation, melasma, dark spots, skin tone or disease.
- Side-by-side saved-photo comparison is a visual record only; lighting, camera distance, angle and exposure can mislead.
- Ingredients catalogue explains common ingredients and their cautions. The new home-formulation safety guide classifies water-based, oil-based, dry-powder and sunscreen ideas and explains why unsafe or unvalidated DIY mixtures should not be made. It does not generate recipes or certify ingredients/formulas as safe.
- English and Bangla resource-key parity was checked after adding the formulation guidance; no missing keys were found.
- No account, analytics, cloud sync or photo upload is implemented.

## Remaining core product work

1. Confirm latest CI for the formulation guide, including APK compilation, unit tests and Android navigation smoke tests. Diagnose emulator/ADB test instability separately from code assertions.
2. Add ingredient-specific compatibility and evidence-review fields, then obtain qualified review before any entry becomes actionable. Current dark-spot profiles are educational only and contain no recommended concentrations or formulas.
3. Build a practical formulation education module only where safety can be responsibly explained: ingredient grade, concentration, pH, preservation, stability, hygiene, packaging and microbial testing. Do not present unpreserved water-based mixtures as usable products; never offer homemade sunscreen.
4. Genuine selfie skin-feature/pigmentation analysis remains unimplemented. It requires a validated model/dataset, standardized capture, calibration, evaluation across varied skin tones and lighting, on-device inference/privacy design, and transparent limitations. Face detection is not skin analysis.
5. Add regression coverage for selfie capture/storage/deletion, the concern-intake flow and formulation safety decisions.
6. Complete owner-side physical-device checks and review Bangla/English accessibility, readability and overflow.

## Safety and release boundary

This is a personal-use educational companion, not a diagnostic or prescription tool. The goal is to address uneven tone and excess pigmentation safely, not change genetically determined natural skin colour. Mercury products, bleach, steroid misuse, harsh kitchen remedies and unsupervised prescription lighteners must remain excluded. Patch testing cannot prove an unsafe or contaminated formula is safe.

Do not merge the feature PR or call the app complete while CI is failing/pending or the core requested features remain unfinished.
