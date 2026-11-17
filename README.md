# Bangladesh Skincare AI

A private, offline-first Android skincare tracker for personal use.

> **Current status (2026-11-17):** The five-section Android app is implemented. Full CI run [#295](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44090947039) passed on commit `375fd5dcea7ceac5bdc94bdd1404b7150d0ec965`. This is a **debug APK**, not a signed release. Physical-device checks and qualified content review remain required.

## Current app

- **Skin Scan:** self-reported check-in only; no photo capture, image analysis or diagnosis.
- **My Routine:** local daily checklist.
- **Progress:** local notes/check-ins with edit and confirmed delete.
- **Ingredients:** 11 educational entries with search, risk filters and publisher sources. Entries remain pending qualified expert review.
- **Profile:** English/Bangla preference, budget preference and local data reset.

## Technology and privacy

Native Android, Kotlin, Jetpack Compose, Room/SQLite and SharedPreferences for small preferences/legacy import. JDK 17, Gradle 8.11.1, Android Gradle Plugin 8.9.2, Kotlin 2.0.21, compile/target SDK 35 and minimum SDK 26.

The app is offline-first. It requests no runtime permissions and has no network permission, account, analytics, cloud sync or photo upload. Android backup is disabled in the manifest. These are implementation facts, not a substitute for device-level privacy verification.

## Verification

- Full Android CI [#295](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44090947039) passed: English/Bangla resource parity, debug APK compile, unit tests, Room schema export, emulator instrumentation tests, and APK/test-report artifact uploads.
- The CI APK artifact is available from the run for controlled testing; it is not signed for private release.
- CI does not verify migration from an actual earlier installation, physical-device reset/backup behavior, TalkBack traversal, real-screen layout, or a signed release.

## Remaining release gates

1. Test upgrade from a pre-Room install containing legacy routine/progress data; verify one-time import, restart persistence and no duplicates.
2. Test local reset on a physical device; confirm Routine/Progress are cleared and language preference remains.
3. Verify Android backup/data-extraction behavior on device.
4. Review TalkBack traversal, font scaling, contrast, touch targets and Bangla/English layout on real devices.
5. Check a lower-memory device and a current Android version.
6. Obtain qualified skincare/dermatology review of all 11 ingredient entries and sources.
7. Only then build and verify a signed private APK. Keep the signing key and passwords outside GitHub and repository history.

## Documentation

- [Project state and session handoff](docs/PROJECT_STATE.md)
- [Roadmap and readiness gates](docs/ROADMAP.md)
- [Implementation backlog](docs/IMPLEMENTATION_BACKLOG.md)
- [Technical architecture](docs/TECHNICAL_ARCHITECTURE.md)
- [Test strategy](docs/TEST_STRATEGY.md)
- [Private APK release runbook](docs/RELEASE_RUNBOOK.md)
- [Product specification](docs/PRODUCT_SPEC.md)
- [Safety and privacy requirements](docs/SAFETY_AND_PRIVACY.md)
- [Threat model](docs/THREAT_MODEL.md)
- [Bangladesh compliance review checklist](docs/BANGLADESH_COMPLIANCE_REVIEW.md)
- [Change log](docs/CHANGELOG.md)

## Scope exclusions

No commercial/client workflows, accounts, analytics, cloud sync, AI diagnosis, photo capture/import, personalized prescriptions, whitening guarantees, DIY recipes or ingredient-mixing instructions are part of this personal-use release.

## Disclaimer

This app is not medical advice. Educational ingredient content is not a treatment recommendation or approval of any home formulation. Do not present unreviewed content as professionally approved.
