# Implementation Backlog — Current Status

- Last reviewed: 2026-11-21
- Repository: `barshan94/bangladesh-skincare-ai`
- Active branch: `feature/brightening-system`
- Active draft PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open and unmerged.
- Latest completed CI before the formulation-education addition: [push #44541762639](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44541762639) and [PR #44541770774](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44541770774), both passed compilation, unit tests, resource parity and Android navigation smoke tests. Newer CI is running for the latest formulation-education changes; do not mark it passed until both workflows finish.

## Implemented

- Kotlin + Jetpack Compose Android app with six destinations: Skin Scan, My Routine, Progress, Ingredients, Profile and Whitening & Brightening.
- Room-backed routine and progress data with legacy preference migration.
- Self-reported skin questionnaire and deterministic, non-diagnostic guidance.
- Brightening guidance for post-acne marks, tanning and unexplained patches, with safety exclusions and realistic expectations.
- Private selfie capture, one-face/framing check using ML Kit Face Detection, local app-private storage, photo deletion and side-by-side visual comparison. These features do **not** analyse pigmentation, acne, melasma, skin tone or disease.
- Ingredient catalogue with 16 educational entries, including azelaic acid, glycolic acid, kojic acid, retinoids and vitamin C. Entries are pending expert review; no prescriptions, concentrations or recipes.
- Home-formulation risk guide for water-based, oil-based, powder and sunscreen concepts.
- New bilingual formulation-education card explaining ingredient identity/grade, concentration and compatibility, pH measurement, validated preservation, stability, packaging and microbial testing. It deliberately provides no mixing instructions, formula percentages or shelf-life claims.
- English/Bangla resource parity validation and regression coverage for catalogue safety and navigation.

## Remaining product work — not complete

1. **Real selfie analysis:** not implemented. Needs a narrowly defined visual feature, legally usable model/data, qualified reference labels, representative Bangladeshi skin-tone and device/lighting evaluation, repeatability thresholds, on-device inference and privacy review. Do not substitute pixel heuristics or face detection and call it AI analysis.
2. **Capture consistency:** improve repeatable lighting/distance/pose guidance and record capture conditions. Any automated quality gate needs its own test evidence.
3. **Personalized brightening plan:** strengthen rules using concern, sensitivity, reaction history, current products, budget and contraindications; clinician referral for uncertain or concerning patches. No prescription or natural-skin-colour alteration.
4. **Formulation knowledge:** expand sourced ingredient profiles and compatibility education only after qualified review. No DIY water-based product recipes without validated preservation, pH, stability and microbial testing; never offer homemade sunscreen.
5. **Regression and privacy:** add automated coverage for selfie capture, file storage, deletion, metadata integrity, and error/recovery paths.
6. **Owner device acceptance:** test on the owner's Android phone, including camera, storage, TalkBack, font scaling, Bangla layout, airplane mode and upgrade behavior. Emulator CI cannot replace this.
7. **Release signing:** owner-controlled signing key and signed APK only if/when the owner wants stable distribution. Do not commit signing secrets.

## Safety boundary

The product goal is safer care for uneven tone and excess pigmentation, not changing genetically determined natural skin colour. No mercury, bleach, steroid misuse, harsh kitchen remedies, unsupervised prescription lighteners, diagnosis or fairness scoring. A patch test does not make an unsafe or contaminated formula safe.

## Completion rule

Do not merge PR #2 or call the app complete until latest CI is green, the owner has reviewed the APK on the actual phone, and the remaining selfie-analysis decision is explicitly represented as either a validated feature or a clearly disclosed limitation. The requested real selfie-analysis feature is currently still missing.
