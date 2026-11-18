# Project State — 2026-11-18

## Repository and active work

- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Baseline branch: `implementation/m0-android-baseline`
- Active feature branch: `feature/brightening-system`
- Active draft PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open and unmerged.
- Latest feature commit: `3dcc5ee7095cc4600fea8b022fd0997628c68932`.
- Latest confirmed green CI before the current photo-comparison change: [#44255199222](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44255199222), commit `fe5cfe66cab0e771d030ab20873278c6bfc6291c`.
- CI for the photo-comparison change is running on the latest commit; do not claim it is verified until completion.

## Baseline app capabilities

- Six Compose destinations on the feature branch: Skin Scan, My Routine, Progress, Ingredients, Profile, and Whitening & Brightening.
- Skin Scan is a self-reported questionnaire with deterministic general education. It is not camera analysis, diagnosis, or an AI model.
- My Routine and Progress use local Room storage.
- Ingredients is an educational catalogue; entries are not professionally reviewed and are not treatment recommendations.
- Profile includes English/Bangla language preference, optional budget preference and local data reset.
- No account, analytics, cloud sync or photo upload is implemented.

## Brightening feature — implemented so far

- Dedicated bilingual Whitening & Brightening destination.
- General education for post-acne marks, tanning and persistent dark patches.
- Gentle routine foundations, sunscreen guidance, ingredient education, realistic timelines and warnings against unsafe whitening shortcuts.
- Guided selfie capture and private on-device photo timeline.
- ML Kit face detection checks that one face is visible and reasonably framed. It does **not** detect acne, pigmentation, melasma, dark spots, skin tone or skin disease. It is not skin analysis and must not be marketed as AI analysis.
- Photos are stored in app-private storage, with in-app deletion. No cloud upload.
- When at least two photos exist, the app shows the earliest and latest saved images side by side with capture dates. This is a visual record only; lighting, camera distance, angle and exposure can mislead. No automated pigmentation measurement or treatment-effect claim is made.
- A bilingual safety check flags broken/irritated skin and new, changing, painful, bleeding or spreading spots with conservative advice.

## Remaining core product work

1. Get the latest CI runs green after the localized heading fix.
2. Improve repeatable photo guidance and add controls for selecting arbitrary comparison dates; do not infer diagnosis or a skin-tone/fairness score.
3. Build a safe, transparent concern-intake and routine guidance flow with conservative rules and clear escalation advice.
4. Build a curated home-ingredient safety and formulation education library. Do not generate arbitrary recipes. Exclude unsafe lightening shortcuts, unpreserved water-based DIY products and homemade sunscreen.
5. Add regression coverage for photo capture/storage/deletion and all brightening flows.
6. Complete owner-side physical-device checks and review Bangla/English accessibility and overflow.

## Safety and release boundary

This is a personal-use educational companion, not a diagnostic or prescription tool. The goal is to address uneven tone and excess pigmentation safely, not change genetically determined natural skin colour. Mercury products, bleach, steroid misuse, harsh kitchen remedies and unsupervised prescription lighteners must remain excluded. Home formulations require validated ingredients, concentration, preservation, stability, hygiene and contamination guidance before being presented as usable recipes.

Do not merge the feature PR or call the app complete while CI is failing/pending or the core requested features remain unfinished.
