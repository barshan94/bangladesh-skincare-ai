# Project State and Session Handoff

- Last updated: 2026-10-29
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Default branch: main
- Active branch: implementation/m0-android-baseline
- Stage: Android baseline and app-shell verified; localization guard passed; emulator-based navigation tests added, awaiting CI verification.
- Source of truth: this repository.

## Verified milestone — M0 Android baseline
GitHub Actions run [40871345852](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40871345852) completed successfully on commit 634902a6203d0f0041a822b89ea7965cd2060dae. Android SDK setup, debug APK compilation, the unit-test task, and APK artifact upload all succeeded. This is a development APK, not a signed release.

## Verified milestone — app shell and English/Bangla copy
GitHub Actions run [40874506742](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40874506742) completed successfully with debug compilation, unit-test task, and APK artifact upload. The tested commit includes the five-section UI and expanded English/Bangla strings.

Implemented:
- Native Kotlin/Jetpack Compose project with pinned Gradle/plugin/dependency versions.
- Manifest with no requested permissions, no network permission, and Android automatic backup disabled.
- Five-area bottom navigation: Skin Scan, My Routine, Progress, Ingredients, Profile.
- Distinct section copy/statuses, navigation icon descriptions, and privacy/safety cards.
- English and Bangla resource files.
- GitHub Actions workflow for debug APK compilation, unit-test task, artifact upload, and now a localization-key consistency check.
- Python standard-library script `scripts/check_localization.py` detects duplicate resource names and mismatched English/Bangla keys.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1

## Latest change — localization guard
Added a CI check that requires English and Bangla Android resource keys to match and rejects duplicate resource names. The workflow now runs this check before Android setup/build. The CI run triggered by this guard has not yet been verified; do not claim that run passed until its result is checked.

Added Compose instrumentation smoke tests for all five bottom-navigation destinations and the home privacy/safety statements. CI now launches an API 35 Pixel_2 emulator and runs `:app:connectedDebugAndroidTest`. This new emulator workflow has not yet been verified green.

The current app does not analyze skin, recommend treatments, provide DIY formulas, capture/import/save photos, or implement persistence, backend, analytics, or cloud processing. Local-first photo storage remains a future design intention.

## Approved decisions
1. Adults only initially.
2. Photo tracking and validated capture-quality checks first; skin-condition analysis only after feature-specific validation.
3. DIY formulas only when individually reviewed and validated.
4. Five areas: Skin Scan; My Routine; Progress; Ingredients and Alternatives; Profile and Privacy.
5. Android-first, private APK initial distribution, Bangla/English, free at launch.
6. Photos/progress on-device by default; cloud storage, cloud analysis and model training require separate affirmative consent.
7. Safety rules are deterministic and cannot be overridden by generative AI.
8. No diagnosis from selfies, beauty/fairness scores, guaranteed whitening, unreviewed recipes, or photo-triggered automatic treatment changes.

## External gates not to fake as completed
- Confirm repository visibility/access expectations; GitHub metadata reports public visibility.
- Qualified Bangladesh legal/privacy review of applicable data protection, adult eligibility, consent, retention/deletion, backups, cloud/cross-border processing, cosmetics/consumer claims and other applicable obligations.
- Qualified skincare/dermatology review of actionable guidance and escalation copy.
- Individual qualified formulation review for each DIY formula.
- Model/data licences, reference methods, pre-approved thresholds, independent validation and subgroup results for each image feature.
- Bangla terminology, accessibility and usability review.
- Final privacy backup/deletion/export behavior based on actual implementation.

## Session handoff/update routine
At each meaningful session:
1. Fetch current PROJECT_STATE.md, DECISIONS.md, ROADMAP.md and CHANGELOG.md.
2. Treat GitHub as authoritative; memory is a pointer.
3. Record decisions with date, rationale, status and impact.
4. Update affected specs/requirements/UX/architecture/test docs in the same session.
5. Update ROADMAP.md with evidence-backed status only.
6. Append CHANGELOG.md.
7. Update this file with milestone, commits, evidence and next action.
8. Re-fetch changed files and verify actual GitHub contents.
9. Report exactly what changed and any failure. Never claim background sync.

## Next action
Verify CI for the emulator-based navigation tests. Fix any dependency, emulator, or assertion failures before moving to local-first persistence. Keep high-risk features disabled.
