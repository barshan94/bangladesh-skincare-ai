# Project State and Session Handoff

- Last updated: 2026-10-29 (documentation baseline expansion)
- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Default branch: main
- Stage: Documentation and feasibility planning; application implementation has not started and is not authorized.
- Source of truth: this repository, not chat history alone.

## Approved decisions
1. Broader product scope with individual feature gates.
2. Adults only initially.
3. Photo tracking and validated capture-quality checks first; skin-condition analysis only after feature-specific validation.
4. DIY formulas only when individually reviewed and validated.
5. Five areas: Skin Scan; My Routine; Progress; Ingredients and Alternatives; Profile and Privacy.
6. Android-first, private APK initial distribution, Bangla/English, free at launch.
7. Photos/progress on-device by default; cloud storage, cloud analysis and model training require separate affirmative consent.
8. Safety rules are deterministic and cannot be overridden by generative AI.
9. No diagnosis from selfies, beauty/fairness scores, guaranteed whitening, unreviewed recipes or photo-triggered automatic treatment changes.
10. No coding until the user separately authorizes implementation.

## Source-of-truth hierarchy
1. Latest explicit user decisions.
2. docs/DECISIONS.md — dated decision history and approval status.
3. docs/PRODUCT_SPEC.md — product scope and principles.
4. docs/REQUIREMENTS.md — testable functional/nonfunctional requirements.
5. docs/UX_SCREEN_SPEC.md — screen flows and interaction behavior.
6. docs/TECHNICAL_ARCHITECTURE.md and docs/DATA_MODEL.md — technical proposals.
7. docs/SAFETY_CASE.md, docs/SAFETY_AND_PRIVACY.md, docs/THREAT_MODEL.md — risk controls.
8. docs/KNOWLEDGE_BASE_SPEC.md and docs/KNOWLEDGE_UPDATE_PROTOCOL.md — content governance.
9. docs/AI_MODEL_FEASIBILITY.md and docs/MEASUREMENT_VALIDATION_PLAN.md — image research/validation gates.
10. docs/ROADMAP.md, docs/IMPLEMENTATION_BACKLOG.md and docs/RELEASE_RUNBOOK.md — execution/release.
11. docs/CHANGELOG.md — material change history.

If documents conflict, do not silently choose. Record the conflict and resolve against the user's latest explicit decision.

## Documentation index
- README.md
- docs/PRODUCT_SPEC.md
- docs/FIRST_RELEASE_SCOPE.md
- docs/REQUIREMENTS.md
- docs/UX_SCREEN_SPEC.md
- docs/SCREEN_ACCEPTANCE_CRITERIA.md
- docs/DESIGN_SYSTEM.md
- docs/CONSENT_COPY_DRAFT.md
- docs/ENGINEERING_STANDARDS.md
- docs/TECHNICAL_ARCHITECTURE.md
- docs/DATA_MODEL.md
- docs/SAFETY_AND_PRIVACY.md
- docs/SAFETY_CASE.md
- docs/THREAT_MODEL.md
- docs/KNOWLEDGE_BASE_SPEC.md
- docs/KNOWLEDGE_UPDATE_PROTOCOL.md
- docs/AI_FEASIBILITY.md
- docs/AI_MODEL_FEASIBILITY.md
- docs/MEASUREMENT_VALIDATION_PLAN.md
- docs/BANGLADESH_COMPLIANCE_REVIEW.md
- docs/LOCALIZATION_ACCESSIBILITY.md
- docs/TEST_STRATEGY.md
- docs/IMPLEMENTATION_BACKLOG.md
- docs/RELEASE_RUNBOOK.md
- docs/DECISIONS.md
- docs/ROADMAP.md
- docs/CHANGELOG.md

## External gates not to fake as completed
- Confirm GitHub repository visibility and access expectations; previous check reported it public.
- Qualified Bangladesh legal/privacy review of applicable data protection, adult eligibility, consent, retention/deletion, backups, cloud/cross-border processing, cosmetics/consumer claims and other applicable obligations.
- Qualified skincare/dermatology review of actionable guidance, concern boundaries and escalation copy.
- Individual qualified formulation review for each DIY formula, including relevant stability/preservation/microbiology evidence.
- Model/data licences, reference methods, pre-approved thresholds, independent validation and subgroup results for each image feature.
- Choose target Android API/device matrix based on feasibility.
- Decide whether any backend/cloud/account feature is justified; local core does not require a backend.
- Select final inference runtime after technical/licence/validation evaluation.
- Choose knowledge-package integrity/signature design before remote update implementation.
- Bangla terminology, accessibility and usability review.
- Final privacy backup/deletion/export behavior based on actual implementation.

These gates can run alongside engineering where appropriate, but affected high-risk features/content cannot ship until resolved.

## Session handoff/update routine
Whenever project work is performed:
1. Fetch current PROJECT_STATE.md, DECISIONS.md, ROADMAP.md and CHANGELOG.md before relying on memory.
2. Treat GitHub as authoritative; memory is only a pointer.
3. Record user decisions with date, rationale, status and impact.
4. Update affected specification/requirements/UX/architecture/test docs in the same work session.
5. Update ROADMAP.md with evidence-backed status only.
6. Append CHANGELOG.md.
7. Update this file with date, current milestone, latest commits, evidence and next action.
8. Re-fetch changed files and verify actual GitHub contents.
9. Tell the user exactly what changed and any failure. Never claim background sync or commits that did not happen.

This routine is a working agreement for future project sessions; it does not itself run automatically in the background.

## Current milestone
Documentation coverage has been expanded to include requirements, logical data model, knowledge governance, safety case, threat model, testing, implementation backlog, release runbook, localization/accessibility and knowledge-update protocol. README, roadmap, engineering standards, design system, consent-copy draft and documentation index have been updated. The full file inventory was re-fetched from GitHub. The consistency pass is complete for the documentation index, core product/roadmap headings and screen acceptance criteria; all README document links were checked against the GitHub docs directory. Repository visibility remains public and requires the owner's setting change. Then wait for explicit implementation authorization. Do not claim external legal, clinical, formula or model gates are complete.

## Implementation readiness
The documentation should make initial engineering tasks executable without reopening product strategy. Before first code change: confirm repo visibility/branch access, target device/toolchain defaults, architecture choices needed for the first milestone, acceptance criteria/tests, and explicit user authorization. External expert gates remain attached to relevant feature activation and release.
