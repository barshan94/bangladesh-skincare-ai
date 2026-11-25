# Bangladesh Skincare AI

A private, offline-first Android skincare companion for personal use in Bangladesh.

> **Latest verified app-code commit:** `d46aea6f409aa602b9bfacf0f3443ea4296450ec`. Android CI run [#45566453626](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45566453626) passed all steps on this commit, including English/Bangla parity, debug APK compilation, unit tests, Room schema export, emulator navigation smoke tests and artifact uploads. The APK is a CI debug build, not a signed release or proof of physical-device acceptance.

## Features

- **Skin Scan:** self-reported check-in with deterministic, general educational guidance; no diagnosis or AI skin analysis.
- **Whitening & Brightening:** educational guidance for uneven tone, tanning and post-acne marks. A rule-based safety check adapts to skin feel, prior reactions, broken/irritated skin, concerning changes, sunscreen habits, multiple strong actives and current clinician-prescribed skin medicine. It is not a diagnosis or treatment prescription.
- **Selfie progress (not analysis):** private camera capture, on-device one-face/framing checks, local photo timeline, deletion and side-by-side comparison. It does not analyse pigmentation, acne, melasma, dark spots, skin tone or disease. Lighting and camera differences can mislead visual comparisons.
- **Homemade Lab:** personal-use worksheet for anhydrous-balm arithmetic, percentage-to-gram calculations, estimated BDT raw-material cost and one locally saved formula draft. It is a calculator, not a recipe generator or safety/compatibility/stability validator. Water-based products, creams/lotions and sunscreen are blocked from DIY formulation.
- **Ingredients:** educational profiles, safety notes, search/filtering, publisher links and a limited English/Bangla red-flag label keyword checker. A no-match result does not establish safety.
- **My Routine and Progress:** local Room-backed routine checklist and text check-ins.
- **Profile:** English/Bangla preference, optional budget preference and local data reset.

## Explicitly deferred

The owner has asked to defer genuine selfie-based skin analysis for now. No face/pigmentation score, AI diagnosis or pixel-heuristic substitute is included. Reconsider only with a legally usable dataset, qualified reference labels, representative Bangladeshi skin tones, device/lighting validation, repeatability/accuracy testing, on-device inference and privacy review.

## Privacy and technology

Native Android, Kotlin, Jetpack Compose, Room/SQLite, SharedPreferences and ML Kit face detection. Routine, progress, formula draft and selfie data are stored locally; selfies remain in app-private storage. No account, analytics, cloud sync or photo upload is implemented. The app is designed to work offline. These are code-level properties, not physical-device acceptance results.

## Automated verification

[CI run #45566453626](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45566453626) passed:
- English/Bangla resource parity
- Gradle 8.11.1 and Android SDK setup
- Debug APK compilation
- Unit tests
- Room schema export
- Android emulator navigation smoke tests
- Debug APK and test-report artifact uploads

Artifacts: `bangladesh-skincare-ai-debug`, `bangladesh-skincare-ai-test-reports` and `bangladesh-skincare-ai-room-schema`. GitHub Actions artifacts are temporary and expire on 2026-12-09. This is not a signed release APK; CI debug signing keys may differ between builds.

## Remaining before a stable private release

1. Owner-side physical-device acceptance: camera capture, photo timeline/deletion, full reset, TalkBack, font scaling, Bangla layout, airplane mode and upgrade behavior.
2. Optional owner-controlled signing key and signed private APK. Never commit the keystore or password.
3. Qualified review before expanding ingredient/formulation claims or adding actionable recipes.

Do not claim production-ready clinical analysis. Keep PR #2 draft and unmerged until the owner has reviewed the app on-device.

## Safety boundary

The goal is safer care for uneven tone and excess pigmentation, not changing genetically determined natural skin colour. Exclude mercury, bleach, steroid misuse, harsh kitchen remedies and unsupervised prescription lighteners. No homemade sunscreen and no unpreserved water-based DIY recipes. A patch test does not make an unsafe or contaminated formula safe. This is a personal-use educational companion, not medical advice.
