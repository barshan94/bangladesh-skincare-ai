# Implementation Backlog — Current Status

- Last reviewed: 2026-11-17
- Active branch: `implementation/m0-android-baseline`
- Latest full CI: [run #295](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44090947039), passed on `375fd5dcea7ceac5bdc94bdd1404b7150d0ec965`.

## Product boundary

Personal-use, offline-first Android tracker. Do not implement commercial/client workflows, accounts, cloud sync, analytics, photo capture/analysis, AI prescriptions or DIY whitening recipes under the current authorization.

## Completed and CI-verified

- **M0 — Android baseline:** Kotlin, Jetpack Compose, Gradle toolchain, debug APK workflow and localization parity check.
- **M1 — App shell/localization:** five sections (Skin Scan, My Routine, Progress, Ingredients, Profile), English and Bangla resources.
- **M2 — Local persistence:** Room-backed Routine and Progress, one-time/idempotent legacy SharedPreferences import, schema v1 export, database lifecycle and reset tests.
- **M3 — Routine and Progress workflows:** daily completion keys, create/edit/delete, confirmation, localized failure feedback and regression coverage.
- **M4 — Self-reported Skin Scan:** non-diagnostic questions, explicit save to Progress and safety-first deterministic guidance; no image processing.
- **M5 — Educational ingredient catalogue:** 11 entries, search, risk filters and source links. All entries remain pending qualified review.
- **M6 — Accessibility semantics baseline:** checkbox labels are exposed as toggleable controls; automated semantics test passes.
- **Reliability:** reset clears legacy snapshot before Room transaction; forced Room reset failure regression verifies primary records survive and reset can be retried.

Full CI #295 passed localization validation, debug compilation, unit tests, Room schema export, emulator instrumentation and artifact uploads.

## Remaining release gates — not implementable from GitHub alone

1. **Legacy installation upgrade test:** install a prior version with populated legacy preferences, upgrade without clearing app data, verify import once, restart persistence and no duplicates.
2. **Physical reset test:** clear Routine/Progress, verify legacy snapshot removal and retention of language preference.
3. **Backup/data-extraction test:** verify device/OS behavior; manifest currently sets `android:allowBackup="false"`.
4. **Physical accessibility/layout review:** TalkBack focus and announcements, font scaling, contrast, touch targets and Bangla/English overflow.
5. **Device compatibility:** at least one lower-memory device and one current Android device.
6. **Professional content review:** qualified skincare/dermatology reviewer must approve or request corrections to the 11 educational entries and sources.
7. **Signed private release:** only after preceding gates, use owner-controlled signing key, verify signature/package/version/permissions/hash and test clean install plus upgrade.

These checks cannot honestly be marked complete using emulator CI alone. Physical device access, a qualified reviewer and owner-controlled signing key are required.

## Deferred / explicitly out of scope

- Photo capture, gallery import, photo timeline and image analysis.
- AI model integration or automated diagnosis/scoring.
- Cloud/backend/account/analytics features.
- Product marketplace, pricing/availability and commercial workflows.
- DIY formulation recipes, concentrations, mixing steps or personalized treatment claims.
- Knowledge package updater and remote content rollback.

Reconsider only through a separately approved scope and appropriate safety, privacy, licensing and validation evidence.

## Definition of done

A task is complete only when implementation, automated tests, failure/offline behavior, localization/accessibility impact, documentation and source-control evidence are recorded. A release is complete only after all owner/device/professional gates and signed-artifact verification pass.
