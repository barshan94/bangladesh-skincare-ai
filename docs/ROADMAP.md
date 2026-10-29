# Roadmap and Readiness Gates

- Last updated: 2026-10-29
- Overall state: Documentation baseline assembled; feasibility, qualified review and repository-visibility checks remain open.
- Implementation: Not started and not authorized.
- Progress estimates are planning estimates only; they are not claims of working software or validated skincare features.

## Phase 0 — Product baseline and documentation

**Status: Documentation baseline assembled; one project-control check remains**

- [x] Create dedicated GitHub repository and README.
- [x] Record product specification, scope proposal, roadmap and decision log.
- [x] Record user-approved product decisions: broad scope with feature gates, adults-only initial audience, validated photo tracking first, individually reviewed DIY formulas only, five-area navigation.
- [x] Draft screen-by-screen UX and visual design direction.
- [x] Define testable requirements and logical data model.
- [x] Define architecture defaults, engineering standards and implementation backlog.
- [x] Define safety case, privacy/security threat model, content governance and knowledge-update protocol.
- [x] Define test strategy, localization/accessibility, consent-copy draft and release runbook.
- [x] Establish durable project-state/session-handoff and documentation-update routine.
- [x] Refresh README documentation index and changelog.
- [ ] Confirm repository visibility and access expectations; earlier check reported it public.
- [x] Finish final consistency pass after documentation expansion; README links and document inventory verified against GitHub.

**Exit gate:** documentation is coherent and linked; repository visibility is known; the user separately authorizes implementation. Documentation completion is not a substitute for professional reviews.

## Phase 1 — AI model feasibility

**Status: In progress; no model selected and no feature validated**

- [x] Identify face-detection, segmentation and custom-model candidate paths.
- [x] Document skin-tone fairness, data licensing and false-confidence risks.
- [x] Draft feature-specific measurement validation plan.
- [ ] Verify candidate models, official documentation, exact licences and model/data provenance.
- [ ] Design representative evaluation data and reference methods with qualified review.
- [ ] Approve numeric acceptance thresholds before locked evaluation.
- [ ] Benchmark latency, memory, battery and package-size impact on target devices.
- [ ] Record feature-specific go/no-go decisions.

**Exit gate:** evidence, licences, protocol, pre-approved thresholds and per-feature decisions documented. Capture checks and skin measurements are not enabled merely because a library exists.

## Phase 2 — Skincare knowledge and evidence

**Status: Governance documented; reviewed content catalogue not complete**

- [x] Define knowledge-entry fields, source hierarchy, approval lifecycle and expiry concepts.
- [x] Define deterministic recommendation pipeline and hard safety filters.
- [x] Define individual DIY formula dossier and review requirements.
- [x] Define local-price provenance and safe replacement workflow.
- [ ] Finalize concern-by-concern content boundaries with qualified skincare review.
- [ ] Build evidence-reviewed ingredient/product entries with sources and review dates.
- [ ] Establish Bangladeshi price/availability sources and freshness rules.
- [ ] Approve any home-preparation option individually after formulation validation.
- [ ] Assign reviewer roles, review cadence and adverse-report ownership.

**Exit gate:** every actionable recommendation has source provenance, safety/eligibility rules and required review. An empty/disabled catalogue is acceptable until content is approved.

## Phase 3 — UX, privacy, compliance and architecture

**Status: Documentation drafted; qualified reviews and user validation remain**

- [x] Map onboarding, returning-user, scan, routine, progress, catalogue, privacy and error-state flows.
- [x] Document consent/permission timing and local-first storage intentions.
- [x] Document logical data model, deletion/migration and backup questions.
- [x] Define provisional architecture and engineering defaults.
- [x] Define accessibility/localization and visual design direction.
- [ ] Confirm repository visibility.
- [ ] Confirm supported Android API/device matrix at first build.
- [ ] Review adult-eligibility method with qualified Bangladesh legal/privacy counsel.
- [ ] Complete qualified review of applicable Bangladesh privacy, data protection, cosmetics, consumer claims and relevant obligations.
- [ ] Review actual data flows, backup/deletion semantics and any optional cloud/cross-border processing before those features are enabled.
- [ ] Test Bangla terminology and UX with adult Bangladeshi users.
- [ ] Obtain qualified skincare/formulation review for actionable content.

**Exit gate:** user-facing product flows and architecture are ready to implement; legal/clinical/formula/model reviews remain mandatory before their affected content/features ship.

## Phase 4 — Implementation authorization and first-build setup

**Status: Backlog ready; waiting for explicit user authorization**

- [x] Break future implementation into small milestones.
- [x] Define acceptance criteria and test layers.
- [x] Define release signing, privacy/security and failure-mode checks.
- [ ] User explicitly authorizes coding.
- [ ] Confirm local build environment and exact stable toolchain versions.
- [ ] Verify package identifier, repository access and protected signing-key process.

**Exit gate:** explicit user authorization plus a reproducible first-build environment. Do not treat “finish documentation” as authorization to code.

## Phase 5 — Incremental implementation

**Status: Not started**

Recommended sequence in IMPLEMENTATION_BACKLOG.md:
1. Repository/toolchain and app shell.
2. Navigation, localization and accessible components.
3. Local persistence, photo storage, deletion and privacy.
4. Questionnaire and deterministic safety/domain engine.
5. Private photo tracking and only validated capture checks.
6. Progress and routine workflows.
7. Reviewed knowledge catalogue and alternatives.
8. Integrity-checked knowledge updates and rollback.
9. Optional constrained AI explanation only if justified and approved.
10. Feature-specific image analysis only after validation.
11. Hardening, pilot and private APK.

Every milestone needs tests, documentation update, a usable deliverable and review. Do not enable unvalidated features to complete a milestone.

## Phase 6 — Independent evaluation and private pilot

**Status: Not started**

- [ ] Test measurement repeatability/reference agreement.
- [ ] Evaluate relevant skin-tone, device, lighting and supported-area subgroups.
- [ ] Measure false positives/negatives, uncertainty and inconclusive rates.
- [ ] Test Bangla/English safety flows.
- [ ] Test privacy, deletion, backup, offline behavior, update integrity and rollback.
- [ ] Run a consented adult pilot only after appropriate review and consent.
- [ ] Establish and exercise adverse-event reporting.
- [ ] Resolve all critical safety defects.

**Exit gate:** predefined criteria pass and limitations/incident response are documented.

## Phase 7 — Private APK release

**Status: Not started**

- [ ] Build signed/versioned APK from traceable source commit.
- [ ] Verify signing-key protection and artifact hash.
- [ ] Test clean install, upgrade, rollback and migration.
- [ ] Publish known limitations, supported devices and support/reporting route.
- [ ] Obtain release approval.

## Release blockers

No critical safety defect may be waived. A feature that fails validation stays disabled. If privacy, licensing, evidence, performance, fairness or regulatory compliance cannot be established, reduce scope rather than weaken acceptance criteria. No unreviewed DIY formula or fabricated local price is allowed.

## Project update convention

At each meaningful project session, update PROJECT_STATE.md, ROADMAP.md and CHANGELOG.md, plus all affected documents. Fetch the latest GitHub versions first and verify updates afterward. Report phase, actual evidence, blockers, next action and whether the scope is sufficient for the day. Never inflate progress or claim background synchronization.