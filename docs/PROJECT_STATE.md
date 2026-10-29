# Project State and Session Handoff

- Last updated: 2026-10-29 (M0 implementation started)
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Default branch: main
- Active branch: implementation/m0-android-baseline
- Stage: M0 Android scaffold committed; first CI build queued/pending verification.
- Source of truth: this repository, not chat history alone.

## Current milestone — M0 Android baseline
Committed on the implementation branch:
- Native Kotlin/Jetpack Compose app module and pinned Gradle/plugin/dependency versions.
- Android manifest with no permissions, no network access and Android automatic backup disabled.
- Five-area bottom navigation and first home-screen cards.
- English and Bangla string resources.
- GitHub Actions workflow to compile a debug APK, run unit tests and upload the APK artifact.
- Git ignore rules for local files and signing secrets.

**Verification status:** GitHub Actions run was created and observed as queued. No successful build is claimed until the workflow reports success. The app is only a scaffold; navigation destinations beyond the first screen show honest placeholder copy. No skincare analysis, advice, formulas, image capture/import, persistence, cloud, analytics or backend are active.

**Latest scaffold commit:** 74b92cb64eee2b9f518b39e5390587fbc23c4794 (CI workflow; earlier commits on the same branch contain project files).

## Approved decisions
1. Broader product ambition with individual feature gates.
2. Adults only initially.
3. Photo tracking and validated capture-quality checks first; skin-condition analysis only after feature-specific validation.
4. DIY formulas only when individually reviewed and validated.
5. Five areas: Skin Scan; My Routine; Progress; Ingredients and Alternatives; Profile and Privacy.
6. Android-first, private APK initial distribution, Bangla/English, free at launch.
7. Photos/progress on-device by default; cloud storage, cloud analysis and model training require separate affirmative consent.
8. Safety rules are deterministic and cannot be overridden by generative AI.
9. No diagnosis from selfies, beauty/fairness scores, guaranteed whitening, unreviewed recipes or photo-triggered automatic treatment changes.

## Key documentation
See README.md for the full linked index. Execution sources: docs/DECISIONS.md, docs/ROADMAP.md, docs/IMPLEMENTATION_BACKLOG.md, docs/CHANGELOG.md, docs/TECHNICAL_ARCHITECTURE.md, docs/ENGINEERING_STANDARDS.md, docs/TEST_STRATEGY.md and docs/RELEASE_RUNBOOK.md.

## External gates not to fake as completed
- Confirm repository visibility/access expectations; GitHub metadata reports public visibility.
- Qualified Bangladesh legal/privacy review of applicable data protection, adult eligibility, consent, retention/deletion, backups, cloud/cross-border processing, cosmetics/consumer claims and other applicable obligations.
- Qualified skincare/dermatology review of actionable guidance and escalation copy.
- Individual qualified formulation review for each DIY formula.
- Model/data licences, reference methods, pre-approved thresholds, independent validation and subgroup results for each image feature.
- Confirm supported Android API/device matrix based on feasibility.
- Decide whether any backend/cloud/account feature is justified; local core does not require a backend.
- Choose knowledge-package integrity/signature design before remote updates.
- Bangla terminology, accessibility and usability review.
- Final privacy backup/deletion/export behavior based on actual implementation.

These gates can run alongside engineering, but affected high-risk features/content cannot ship until resolved.

## Session handoff/update routine
Whenever project work is performed:
1. Fetch current PROJECT_STATE.md, DECISIONS.md, ROADMAP.md and CHANGELOG.md before relying on memory.
2. Treat GitHub as authoritative; memory is only a pointer.
3. Record user decisions with date, rationale, status and impact.
4. Update affected specs/requirements/UX/architecture/test docs in the same session.
5. Update ROADMAP.md with evidence-backed status only.
6. Append CHANGELOG.md.
7. Update this file with date, current milestone, commits, evidence and next action.
8. Re-fetch changed files and verify actual GitHub contents.
9. Report exactly what changed and any failure. Never claim background sync.

This routine does not run automatically in the background.

## Next action
Wait for the M0 CI result, fix any build failure, add appropriate tests, and open a pull request for review. Do not start persistence or image features until the app baseline is verified. Do not claim legal, clinical, formula or model gates are complete.
