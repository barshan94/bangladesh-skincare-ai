# Project Change Log

## 2026-10-29 — First-release scope proposal drafted

- Added `docs/FIRST_RELEASE_SCOPE.md` to separate proposed MVP features from gated research and excluded features.
- Proposed core focus: reviewed routine guidance, adaptive safety questionnaire, local/private progress timeline, transparent ingredient/product knowledge, privacy controls and safe knowledge updates.
- Automated skin-feature analysis remains gated by independent validation; diagnosis, fairness scores and unreviewed DIY formulas are excluded from the proposed MVP.
- Recorded five decisions requiring user review before the product scope can be finalized.
- This is a proposal only; no implementation approval is implied.

## 2026-10-29 — Bangladesh compliance checklist added

- Added `docs/BANGLADESH_COMPLIANCE_REVIEW.md` with an initial checklist based on official sources for the Personal Data Protection Act, 2026 and Drugs and Cosmetics Act, 2023.
- Recorded privacy, consent, retention/deletion, backup, third-party processing, cosmetic claims and DIY-formula review questions for qualified Bangladesh counsel and relevant experts.
- This is a preliminary checklist, not legal advice or a compliance determination.
- No app code or public release approved.

## 2026-10-29 — Measurement validation plan proposed

- Added `docs/MEASUREMENT_VALIDATION_PLAN.md` as a proposed protocol for capture-quality checks, face alignment and a narrowly defined pigmentation/dark-mark research candidate.
- Defined candidate metrics, dataset/consent requirements, reference-method expectations, test phases and release gates.
- Added provisional engineering targets for discussion only. Skin-feature agreement thresholds remain unset until expert review and a pre-registered statistical plan.
- No candidate feature is approved or validated. No model or app code has been selected or implemented.

## 2026-10-29 — Initial AI model feasibility assessment

- Added `docs/AI_MODEL_FEASIBILITY.md` comparing ML Kit, MediaPipe Face Landmarker and LiteRT as candidate foundations.
- Clarified that face geometry/capture assistance is distinct from validated skin-condition measurement.
- Proposed capture-quality checks and face alignment as first technical candidates; pigmentation/dark-mark analysis remains gated research.
- Recommended deferring automated hydration/oiliness/pores/fine-line claims and excluding beauty/fairness scores and selfie-based diagnosis from the MVP.
- No model selected, no feature declared validated, and no application implementation started.

## 2026-10-29 — Initial repository baseline

- Created the dedicated `bangladesh-skincare-ai` repository and initial planning-document structure.
- Recorded Android-first private APK direction, on-device-first analysis, device-only photo storage by default, Bangla/English, free-at-launch and optional accounts where feasible.
- Added product specification, staged roadmap, AI feasibility, safety/privacy, proposed architecture and decision log.
- No app code or implementation scaffolding was created.
- Open issue: GitHub metadata reports repository visibility as public; confirm whether to change it to private.

## Change-entry template

### YYYY-MM-DD — Short title
- **Type:** Goal / Scope / Feature / Safety / Privacy / AI / Architecture / Roadmap / Release
- **Previous state:**
- **New state:**
- **Reason/evidence:**
- **Approval status:** Proposed / Approved / Rejected
- **Affected documents:**
- **Roadmap impact:**
- **Follow-up actions:**


## 2026-10-29 — User-approved product decisions and UX draft

- Recorded user approval for broader scope with gated features, adults-only initial audience, photo tracking and validated capture checks first, skin analysis only after validation, individually reviewed/validated DIY formulas only, and five-area navigation.
- Updated docs/PRODUCT_SPEC.md to version 3.1 planning baseline.
- Added docs/UX_SCREEN_SPEC.md with first-run, five-area, returning-user, error-state and review-gate flows.
- Qualified skincare/formulation review and Bangladesh legal/privacy review remain required.
- No app code, scaffolding or implementation files created.


## 2026-10-29 — Documentation baseline expanded for implementation readiness

- Added testable functional/nonfunctional requirements in docs/REQUIREMENTS.md.
- Added local-first logical data model and deletion/migration rules in docs/DATA_MODEL.md.
- Added reviewed knowledge governance and individual DIY formula dossier requirements in docs/KNOWLEDGE_BASE_SPEC.md.
- Added hazard register/release blockers in docs/SAFETY_CASE.md and privacy/security threats in docs/THREAT_MODEL.md.
- Added layered test strategy and quality gates in docs/TEST_STRATEGY.md.
- Added milestone-by-milestone implementation backlog in docs/IMPLEMENTATION_BACKLOG.md.
- Added private APK release/rollback procedure in docs/RELEASE_RUNBOOK.md.
- Added Bangla/English localization and accessibility requirements in docs/LOCALIZATION_ACCESSIBILITY.md.
- Added knowledge package update/rollback protocol in docs/KNOWLEDGE_UPDATE_PROTOCOL.md.
- Added durable source-of-truth and session handoff routine in docs/PROJECT_STATE.md.
- Added proposed visual design system and consent copy draft in docs/DESIGN_SYSTEM.md and docs/CONSENT_COPY_DRAFT.md.
- Updated technical architecture and engineering defaults; no backend, cloud AI or skin-condition model selected by default.
- Updated README documentation index and roadmap status.
- All documentation is a planning baseline, not proof of legal compliance, clinical safety or model validity. Qualified reviews and feature-specific validation remain mandatory.
- No application code or scaffolding created.


## 2026-10-29 — Durable project handoff and documentation-first working rule

- Recorded D-010 in docs/DECISIONS.md: GitHub documentation, roadmap, changelog and project state must be updated during meaningful project work; implementation remains blocked until explicit authorization.
- Rewrote docs/ROADMAP.md to distinguish documentation coverage from unresolved professional/validation gates.
- Updated docs/PROJECT_STATE.md index and handoff instructions.
- Final documentation index is linked from README.md.
- Repository still needs visibility confirmation; a prior check reported public visibility.
- No application code or scaffolding created.


## 2026-10-29 — Final documentation consistency pass

- Verified the GitHub docs directory contains 26 documentation files.
- Verified every README.md documentation link points to an existing file.
- Corrected product-spec section numbering and clarified remaining evidence/architecture gates.
- Rewrote the roadmap to distinguish documentation coverage from professional review and model-validation completion.
- Re-fetched README, roadmap, project state, decisions and changelog from GitHub after updates.
- Repository metadata still reports public visibility; the available GitHub connection does not expose a repository-visibility setting action. Owner must change it in GitHub Settings if private visibility is desired.
- No application code or scaffolding created.
