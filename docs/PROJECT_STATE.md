# Project State and Session Handoff

- Last updated: 2026-10-29
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active branch: implementation/m0-android-baseline
- Current stage: first usable MVP implementation in progress; latest CI is queued and the emulator test issue remains unresolved.
- Source of truth: this repository. No background sync is assumed.

## Implemented in the current branch

- Native Kotlin/Jetpack Compose app with five tabs: Skin Scan, My Routine, Progress, Ingredients, Profile.
- A deterministic, non-diagnostic self-reported check-in engine with safety-first paths for concerning changes and broken skin.
- General education for basic care, dryness and roughness; dark-mark and acne requests remain explicitly limited rather than diagnosed or promised to improve.
- A basic routine checklist with locally saved completion ticks.
- A text-only progress timeline with save/delete, tolerance notes and timestamps.
- A small ingredient education catalogue with search; it explicitly states formal expert review is pending and makes no local price or brand claims.
- English/Bangla resources for the functional screens and a language preference.
- Optional budget preference, local-data clearing, no account, no analytics and no cloud sync.
- Unit tests for the deterministic guidance rules.
- Existing Compose navigation smoke tests are still not verified green.

## Storage limitation that must remain visible

The prototype currently stores routine ticks, text notes and simple preferences in app-private SharedPreferences using JSON for the progress list. This is an interim implementation, not the final structured storage design. Before release, migrate to Room with tested schema migrations, review backup/restore behavior, and test deletion. No photos are captured, imported or stored. No internet permission, AI model, backend or photo upload is implemented.

## Verification

- Earlier verified baseline build/artifact runs: [40871345852](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40871345852), [40874506742](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40874506742), and localization guard [40875940316](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40875940316).
- Emulator run [40902300999](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40902300999) failed because the instrumentation process crashed before starting tests (reported 0 tests). The root cause is not yet confirmed.
- Latest code/localization workflow: [40912123852](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40912123852), queued at the last check. Do not claim the new code compiles until this run completes successfully.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1. Do not merge while verification is red or pending.

## Safety and external release gates

The app is educational and non-diagnostic. It does not provide selfie-based diagnosis, whitening promises, individualized medication advice, or homemade formula recipes. The guidance catalogue has not completed qualified expert review. A release still requires qualified skincare/dermatology review, Bangladesh legal/privacy review, Bangla usability review, storage/backup/deletion verification, and validation gates for any future image feature.

## Next implementation actions

1. Inspect the newest build/unit-test result and fix any compile/test failures.
2. Diagnose emulator instrumentation process crashes with captured logcat/report artifacts.
3. Continue local persistence hardening and tests, then implement photo progress only with a safe private-storage and deletion flow.
4. Keep image analysis and DIY formulas disabled unless their individual review/validation gates are met.
5. Update this file, ROADMAP and CHANGELOG with verified facts after each meaningful milestone.
