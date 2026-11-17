# Bangladesh Skincare AI

A private, offline-first Android skincare tracker for personal use.

> **Verified status (2026-11-17):** The latest branch head before this documentation update, `4fc6e8992e50f8a1fc78a52a31bcef56d3c1aa2d`, passed full Android CI in [run #300](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44106499330). The workflow produced a debug APK and test reports. This is not a signed release APK.

## What the app actually does

- **Skin Scan:** self-reported check-in with deterministic, general educational guidance. It does not scan skin, use a camera, diagnose, or use an AI model.
- **My Routine:** local morning/evening reminder checklist.
- **Progress:** private local text check-ins with edit and confirmed delete.
- **Ingredients:** 11 educational ingredient summaries, safety notes, search, filters and publisher links. The entries are not professionally reviewed and are not treatment recommendations.
- **Profile:** English/Bangla language preference, optional budget preference and local data reset.

The word “AI” is currently the product name only; AI functionality is not implemented. Do not rely on this app for diagnosis or individualized treatment.

## Privacy and technology

Native Android, Kotlin, Jetpack Compose, Room/SQLite and SharedPreferences for small preferences and legacy migration. JDK 17, Gradle 8.11.1, Android Gradle Plugin 8.9.2, Kotlin 2.0.21, compile/target SDK 35 and minimum SDK 26.

The app works offline and currently has no account, analytics, cloud sync, photo upload, network permission or runtime permission request. Android backup is disabled in the manifest. These are code-level facts; device/OS behavior still needs a real-device check.

## Automated verification

CI run [#300](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44106499330) passed the workflow job **Compile debug APK**. The workflow includes localization parity, debug compilation, unit tests, Room schema export, emulator instrumentation tests and artifact uploads. The run contains:
- `bangladesh-skincare-ai-debug`
- `bangladesh-skincare-ai-test-reports`
- `bangladesh-skincare-ai-room-schema`

A CI debug APK is suitable for controlled installation/testing. It is not a signed release build. Debug signing keys can differ between CI runs, so a later APK may not install as an in-place update; back up anything important before replacing an installation.

## Remaining owner checks

1. Install the current debug APK on your Android phone and test each of the five sections.
2. If you have an older app installation with saved legacy data, test upgrade without clearing app storage; verify import once, restart persistence and no duplicates.
3. Test Profile reset; verify routine/progress are removed and language preference remains.
4. Check TalkBack, larger font size, Bangla/English text layout and touch targets on your phone.
5. Check offline launch and confirm no unexpected permission prompts.
6. For a stable signed private APK, create and retain your own signing key securely. Never share its password or keystore in chat or commit it to GitHub.

**No expert is required merely to install and use this as a personal tracker.** The ingredient guide remains general education, not clinically reviewed advice. Do not expand it into diagnosis, prescriptions, high-strength active recommendations or DIY formulations without appropriate qualified review. Public/commercial distribution would need a separate safety and legal review.

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

## Explicit exclusions

No photo capture/analysis, AI diagnosis, personalized prescriptions, product marketplace, cloud accounts/sync, analytics, commercial/client workflows, whitening guarantees or DIY whitening recipes.

## Disclaimer

This app is a personal record-keeping and general-education tool, not medical advice or a clinical record system. Stop using a product that causes a reaction. Seek appropriate medical care for severe, persistent, worsening or concerning symptoms.
