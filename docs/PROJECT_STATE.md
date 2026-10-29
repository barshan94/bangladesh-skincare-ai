# Project State and Session Handoff

- Last updated: 2026-10-29
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Default branch: main
- Active branch: implementation/m0-android-baseline
- Stage: M0 build verified; Phase 2 app-shell/localization work underway.
- Source of truth: this repository.

## Verified milestone — M0 Android baseline
GitHub Actions run [40871345852](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/40871345852) completed successfully on commit 634902a6203d0f0041a822b89ea7965cd2060dae. The workflow steps for Android command-line tools/SDK setup, debug APK compilation, unit-test task, and APK artifact upload all reported success. The debug artifact was uploaded; this is a development APK, not a signed release.

Implemented:
- Native Kotlin/Jetpack Compose project and pinned Gradle/plugin/dependency versions.
- Manifest with no requested permissions, no network permission and Android automatic backup disabled.
- Five-area bottom navigation and first home screen.
- English and Bangla resources.
- GitHub Actions workflow to compile a debug APK, run unit tests and upload a 14-day artifact.
- Draft PR #1: https://github.com/barshan94/bangladesh-skincare-ai/pull/1

## Current work — app shell and localization
Updated the five sections to have distinct, accurate explanatory content and explicit status labels. Improved navigation icon content descriptions and replaced icon-like text symbols with Material icons. Expanded English and Bangla strings. Corrected the privacy copy: this version does not capture/import/save photos; local-first photo storage is a design intention for a future feature, not an implemented capability.

CI runs for these latest UI/string commits are pending at the time of this update. Do not claim those commits build until the latest run succeeds.

No skincare analysis, treatment recommendation, DIY formula, photo capture/import, persistence, backend, analytics or cloud processing is active.

## Approved decisions
1. Adults only initially.
2. Photo tracking and validated capture-quality checks first; skin-condition analysis only after feature-specific validation.
3. DIY formulas only when individually reviewed and validated.
4. Five areas: Skin Scan; My Routine; Progress; Ingredients and Alternatives; Profile and Privacy.
5. Android-first, private APK initial distribution, Bangla/English, free at launch.
6. Photos/progress on-device by default; cloud storage, cloud analysis and model training require separate affirmative consent.
7. Safety rules are deterministic and cannot be overridden by generative AI.
8. No diagnosis from selfies, beauty/fairness scores, guaranteed whitening, unreviewed recipes or photo-triggered automatic treatment changes.

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
Verify CI for the latest UI/localization commits. If green, add meaningful automated coverage for navigation/accessible labels, then continue local-first persistence only after the shell is reviewed. Keep high-risk features disabled.
