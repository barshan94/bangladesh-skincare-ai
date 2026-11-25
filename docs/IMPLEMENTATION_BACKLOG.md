# Implementation Backlog — Current Status

- Last reviewed: 2026-11-25
- Repository: `barshan94/bangladesh-skincare-ai`
- Active branch: `feature/brightening-system`
- Active draft PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open, draft and unmerged.
- Latest code commit verified by CI: `244eecc84a381a4dc8f8a2b3df23e4eaaed63ad2` (documentation commits follow it).
- Latest CI [#45556948012](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45556948012) passed on code commit `244eecc`, including compilation, unit tests, emulator navigation smoke tests and artifact uploads.

## Implemented

- Latest verified build: [CI run #45556948012](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45556948012) passed all steps on code commit `244eecc`; debug APK, test reports and Room schema artifacts are available until 2026-12-09.

- Kotlin + Jetpack Compose Android app with six destinations: Skin Scan, My Routine, Progress, Ingredients, Profile and Whitening & Brightening.
- Room-backed routine and progress data with legacy preference migration.
- Self-reported skin questionnaire and deterministic, non-diagnostic guidance. Brightening check-in adapts reminders to skin feel, previous reactions, broken/irritated skin, concerning changes, sunscreen use, multiple strong actives and whether a clinician-prescribed skin medicine is in use.
- Brightening guidance for post-acne marks, tanning and unexplained patches, with safety exclusions and realistic expectations.
- Private selfie capture, ML Kit one-face/framing check, app-private storage, deletion and side-by-side visual comparison. These features do **not** analyse pigmentation, acne, melasma, skin tone or disease.
- Hardened photo storage: staged writes, metadata validation, synchronous metadata commits, checked deletion, maximum 30-photo retention and monotonic capture timestamps.
- Full local-data reset removes selfie images and metadata and deletes the saved formula draft.
- Ingredient catalogue with educational profiles, safety notes, search/filtering and publisher links. Entries remain pending expert review.
- English/Bangla ingredient-label red-flag keyword checker for a limited list of mercury, hydroquinone and topical-steroid aliases. No-match is not a safety clearance.
- Home-formulation risk guide and bilingual education on ingredient identity/grade, compatibility, pH, validated preservation, stability, packaging and microbial testing.
- Homemade Lab arithmetic worksheet for anhydrous balm batch size, percentage-to-gram calculations and approximate BDT raw-material cost; one private draft can be saved, loaded and deleted. It is not a recipe recommendation or safety validator. Water-based products, creams/lotions and sunscreen are blocked.
- English/Bangla resource parity validation and automated unit/navigation regression coverage, including the prescribed-treatment warning.

## Remaining product work — not complete

1. **Real selfie analysis:** not implemented. Needs a narrowly defined visual feature, legally usable and relevant data, qualified reference labels, representative Bangladeshi skin-tone and device/lighting evaluation, repeatability thresholds, on-device inference and privacy review. Do not substitute face detection or unvalidated pixel heuristics and call it AI skin analysis.
2. **Owner device acceptance:** test on the owner's Android phone, including camera, storage, full reset, TalkBack, font scaling, Bangla layout, airplane mode and upgrade behavior. Emulator CI cannot replace this.
3. **Personalized brightening plan:** strengthen rules using concern, sensitivity, reaction history, current products and budget; refer uncertain or concerning patches for clinical assessment. No prescription or natural-skin-colour alteration.
4. **Formulation knowledge:** expand sourced ingredient profiles and compatibility education only after qualified review. No DIY water-based product recipes without validated preservation, pH, stability and microbial testing; never offer homemade sunscreen.
5. **Release signing:** owner-controlled signing key and signed APK only if/when the owner wants stable distribution. Do not commit signing secrets.

## Safety boundary

The product goal is safer care for uneven tone and excess pigmentation, not changing genetically determined natural skin colour. No mercury, bleach, steroid misuse, harsh kitchen remedies, unsupervised prescription lighteners, diagnosis or fairness scoring. A patch test does not make an unsafe or contaminated formula safe.

## Completion rule

Do not merge PR #2 or call the app complete until the owner has reviewed the APK on the actual phone and the requested selfie-analysis feature is either implemented with credible validation or explicitly deferred by the owner. The current app is not production-ready as a real selfie skin analyzer.
