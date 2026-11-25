# Bangladesh Skincare AI

A private, offline-first Android skincare companion for personal use.

> **Latest verified CI (2026-11-24):** Commit `023cd07445942e4e41fb45c1b4e9273f2671908c` passed [Android workflow run #45437693538](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45437693538). Debug APK compilation, unit tests, Room schema export, Android navigation smoke tests, localization parity and artifact uploads all succeeded. This is a CI debug APK, not a signed release APK or proof of real-device acceptance.

## What the app actually does

- **Skin Scan:** self-reported check-in with deterministic, general educational guidance. It does not diagnose or use AI skin analysis.
- **Whitening & Brightening:** educational guidance for uneven tone, tanning and post-acne marks. A rule-based check-in adapts reminders to skin feel, previous reactions, broken/irritated skin, concerning changes, sunscreen habits and use of multiple strong actives. It is not a diagnosis or treatment prescription.
- **Selfie progress:** captures photos privately, uses on-device face detection only for basic one-face/framing checks, and provides a timeline and side-by-side visual comparison. It does **not** detect acne, pigmentation, melasma, dark spots, skin tone or disease. Lighting and camera differences can make comparisons misleading.
- **Homemade Lab:** an educational worksheet for anhydrous-balm batch arithmetic, percentage-to-gram calculations, estimated BDT raw-material cost and one locally saved formula draft. It is a calculator, not a recipe generator, formula safety validator, compatibility checker or efficacy assessor. Water-based products, creams/lotions and sunscreen are not enabled for DIY formulation.
- **My Routine:** local morning/evening routine checklist.
- **Progress:** private local text check-ins with edit and delete.
- **Ingredients:** educational ingredient profiles, safety notes, search/filtering, publisher links and a limited English/Bangla label red-flag keyword checker. The checker flags selected mercury, hydroquinone and topical-steroid names; a no-match result does not establish product safety.
- **Profile:** English/Bangla language preference, optional budget preference and local data reset.

The word “AI” is currently the product name only; AI functionality and validated selfie-based skin analysis are not implemented. Do not rely on this app for diagnosis or individualized treatment.

## Privacy and technology

Native Android, Kotlin, Jetpack Compose, Room/SQLite, SharedPreferences and ML Kit face detection. JDK 17, Gradle 8.11.1, Android Gradle Plugin 8.9.2, Kotlin 2.0.21, compile/target SDK 35 and minimum SDK 26.

Routine, progress, formula draft and selfie data are stored locally. Selfies remain in app-private storage; no account, cloud sync, analytics or photo upload is implemented. The app is designed to work offline. These are code-level facts; device/OS behavior still needs real-device checks.

## Automated verification

[CI run #45437693538](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45437693538) on the prior code revision passed:
- English/Bangla resource parity
- Debug APK compilation
- Unit tests
- Room schema export
- Android navigation smoke tests
- Debug APK and test-report artifact uploads

Artifacts include `bangladesh-skincare-ai-debug`, `bangladesh-skincare-ai-test-reports` and `bangladesh-skincare-ai-room-schema`. GitHub Actions artifacts are temporary and expire; this is not a signed release. Debug signing keys may differ between CI runs, so a later APK may not install as an in-place update. Back up anything important before replacing an installation.

Latest app-code revision is `5c200e0a487121f73d5a7458c2e00af44e1a3410`; CI run [#45534561002](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45534561002) is pending/running. The earlier successful run does not verify this newer code.

## Remaining work before release

1. **Genuine selfie skin analysis is not implemented.** It needs a legally usable dataset, expert/reference labels, representative Bangladeshi skin tones, device and lighting evaluation, repeatability/accuracy testing, on-device inference and privacy review. Face detection or simple pixel heuristics must not be presented as skin analysis.
2. Complete physical-device acceptance on the owner's Android phone: camera capture, photo timeline/deletion, full reset, TalkBack, font scaling, Bangla layout, airplane mode and upgrade behavior.
3. Strengthen brightening guidance using concern, sensitivity, reaction history, current products and budget, with clinical referral for concerning or unexplained patches.
4. Expand ingredient evidence and formulation knowledge only with qualified review. The current worksheet checks arithmetic only. Do not use it as proof a formula is safe.
5. Decide whether to create a signed private APK. The owner must create and retain the signing key securely. Never share its password or keystore in chat or commit it to GitHub.

## Documentation

- [Project state](docs/PROJECT_STATE.md)
- [Roadmap](docs/ROADMAP.md)
- [Implementation backlog](docs/IMPLEMENTATION_BACKLOG.md)
- [Technical architecture](docs/TECHNICAL_ARCHITECTURE.md)
- [Test strategy](docs/TEST_STRATEGY.md)
- [Private APK release runbook](docs/RELEASE_RUNBOOK.md)
- [Product specification](docs/PRODUCT_SPEC.md)
- [Safety and privacy](docs/SAFETY_AND_PRIVACY.md)
- [Safety case](docs/SAFETY_CASE.md)

## Safety boundary

The goal is to address uneven tone and excess pigmentation safely, not change genetically determined natural skin colour. Exclude mercury, bleach, steroid misuse, harsh kitchen remedies and unsupervised prescription lighteners. No homemade sunscreen and no unpreserved water-based DIY recipes. A patch test does not make an unsafe or contaminated formula safe.

This is a personal-use educational companion, not medical advice or a clinical record system. Stop using a product that causes a reaction. Seek appropriate medical care for severe, persistent, worsening or concerning symptoms.
