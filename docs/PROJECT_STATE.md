# Project State — 2026-11-19

## Repository and active work

- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Baseline branch: `implementation/m0-android-baseline`
- Active feature branch: `feature/brightening-system`
- Active draft PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open and unmerged.
- Latest feature commit: `5488d2e2bc0c088fce058c35d3deadbbfa6f97a6`.
- Confirmed green CI for selectable comparison: [#44312769454](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44312769454), commit `944d76ae152beb70ac0e0a71f95129ef5eaf8de6` (debug APK compile, unit tests, navigation smoke tests and resource-parity check passed).
- A follow-up Compose state-safety fix is committed as `5488d2e`; its CI runs [#44442838290](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44442838290) and [#44442845892](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44442845892) are still running at the time of this update.

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
- When at least two photos exist, the app shows two selectable saved dates side by side. A follow-up fix removes direct Compose state writes during rendering; this fix is awaiting CI. This is a visual record only; lighting, camera distance, angle and exposure can mislead. No automated pigmentation measurement or treatment-effect claim is made.
- A bilingual safety check flags broken/irritated skin and new, changing, painful, bleeding or spreading spots with conservative advice.

## Remaining core product work

1. Confirm CI for commit `5488d2e` is green; resolve any failure before moving on.
2. Improve repeatable photo guidance and test photo capture/storage/deletion; do not infer diagnosis or a skin-tone/fairness score.
3. Build a safe, transparent concern-intake and routine guidance flow with conservative rules and clear escalation advice. Existing `SkincareGuidance` domain logic is not yet fully connected to a complete user-facing intake flow.
4. Expand the curated home-ingredient safety and formulation education library. Do not generate arbitrary recipes. Exclude unsafe lightening shortcuts, unpreserved water-based DIY products and homemade sunscreen.
5. Genuine selfie skin analysis remains unimplemented. It needs a validated model/dataset, standardized capture, calibration, performance evaluation across varied skin tones and lighting, on-device inference/privacy design, and transparent limits. Face detection alone is not a substitute.
6. Add regression coverage for photo capture/storage/deletion and all brightening flows.
7. Complete owner-side physical-device checks and review Bangla/English accessibility and overflow.

## Safety and release boundary

This is a personal-use educational companion, not a diagnostic or prescription tool. The goal is to address uneven tone and excess pigmentation safely, not change genetically determined natural skin colour. Mercury products, bleach, steroid misuse, harsh kitchen remedies and unsupervised prescription lighteners must remain excluded. Home formulations require validated ingredients, concentration, preservation, stability, hygiene and contamination guidance before being presented as usable recipes.

Do not merge the feature PR or call the app complete while CI is failing/pending or the core requested features remain unfinished.
