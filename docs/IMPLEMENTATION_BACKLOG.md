# Implementation Backlog — Current Status

- Last reviewed: 2026-11-22
- Repository: `barshan94/bangladesh-skincare-ai`
- Active branch: `feature/brightening-system`
- Active draft PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open, draft and unmerged.
- Latest implementation commit before this documentation update: `2e6efa564b24c7798c735cffebc01d75e57fe15d`.
- Latest push CI [#44977709002](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44977709002) and PR CI [#44977714807](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44977714807) both passed: resource parity, debug APK compilation, unit tests, Room schema export, Android navigation smoke tests, reports and APK artifact upload.

## Implemented

- Kotlin + Jetpack Compose Android app with six destinations: Skin Scan, My Routine, Progress, Ingredients, Profile and Whitening & Brightening.
- Room-backed routine and progress data with legacy preference migration.
- Self-reported skin questionnaire and deterministic, non-diagnostic guidance.
- Brightening guidance for post-acne marks, tanning and unexplained patches, with safety exclusions and realistic expectations.
- Private selfie capture, ML Kit one-face/framing check, app-private storage, deletion and side-by-side visual comparison. These features do **not** analyse pigmentation, acne, melasma, skin tone or disease.
- Hardened photo storage: staged writes, metadata validation, synchronous metadata commits, checked deletion, maximum 30-photo retention and monotonic capture timestamps.
- Full local-data reset now removes selfie images and metadata as well as other local user data.
- Ingredient catalogue with educational profiles for common skincare and dark-spot ingredients. Entries remain pending expert review; no prescriptions, concentrations or recipes.
- Home-formulation risk guide for water-based, oil-based, powder and sunscreen concepts.
- Bilingual formulation-education card explaining ingredient identity/grade, concentration and compatibility, pH measurement, validated preservation, stability, packaging and microbial testing. It deliberately provides no mixing instructions, formula percentages or shelf-life claims.
- English/Bangla resource parity validation and automated unit/navigation regression coverage.

## Remaining product work — not complete

1. **Real selfie analysis:** not implemented. Needs a narrowly defined visual feature, legally usable and relevant data, qualified reference labels, representative Bangladeshi skin-tone and device/lighting evaluation, repeatability thresholds, on-device inference and privacy review. Do not substitute face detection or unvalidated pixel heuristics and call it AI skin analysis.
2. **Photo-quality assistance:** capture currently checks face count and framing only. Blur/exposure checks remain a candidate and must be validated separately before being relied upon.
3. **Personalized brightening plan:** strengthen rules using concern, sensitivity, reaction history, current products and budget; refer uncertain or concerning patches for clinical assessment. No prescription or natural-skin-colour alteration.
4. **Formulation knowledge:** expand sourced ingredient profiles and compatibility education only after qualified review. No DIY water-based product recipes without validated preservation, pH, stability and microbial testing; never offer homemade sunscreen.
5. **Owner device acceptance:** test on the owner's Android phone, including camera, storage, full reset, TalkBack, font scaling, Bangla layout, airplane mode and upgrade behavior. Emulator CI cannot replace this.
6. **Release signing:** owner-controlled signing key and signed APK only if/when the owner wants stable distribution. Do not commit signing secrets.

## Safety boundary

The product goal is safer care for uneven tone and excess pigmentation, not changing genetically determined natural skin colour. No mercury, bleach, steroid misuse, harsh kitchen remedies, unsupervised prescription lighteners, diagnosis or fairness scoring. A patch test does not make an unsafe or contaminated formula safe.

## Completion rule

Do not merge PR #2 or call the app complete until the owner has reviewed the APK on the actual phone and the requested selfie-analysis feature is either implemented with credible validation or explicitly deferred by the owner. The current app is not production-ready as a real selfie skin analyzer.
