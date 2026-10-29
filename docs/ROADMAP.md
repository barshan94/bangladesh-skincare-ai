# Roadmap

**Status:** Planning and feasibility research. Implementation has not started.  
**Last updated:** 2026-10-29

Progress estimates are planning estimates, not claims of completed engineering. A phase is complete only when its deliverables and acceptance checks are reviewed.

## Phase 0 — Project baseline and documentation

**Status: In progress**

- [x] Create dedicated GitHub repository.
- [x] Establish README and initial specification/roadmap documents.
- [ ] Confirm repository visibility and access expectations.
- [ ] Review the documented baseline and resolve material ambiguities.
- [ ] Keep a dated decision and change log.

**Exit gate:** project goals, constraints and documentation workflow agreed.

## Phase 1 — AI model feasibility

**Status: In progress; no final model selected**

- [x] Identify face detection, segmentation and custom-model paths for evaluation.
- [x] Identify risks around skin-tone fairness, dataset licensing and false confidence.
- [x] Draft a feature-specific measurement validation plan.
- [ ] Verify candidate models, official documentation, exact licences and model/data provenance.
- [ ] Design representative evaluation data and appropriate reference methods with expert review.
- [ ] Approve numeric acceptance thresholds before evaluating holdout results.
- [ ] Benchmark latency, memory, battery and app-size impact on target devices.
- [ ] Make a per-feature go/no-go decision.

**Exit gate:** reviewed feasibility report, licensing, test protocol, thresholds and feature-specific decisions.

## Phase 2 — Knowledge base and evidence review

**Status: Not started**

- [ ] Finalize supported first-release concerns.
- [ ] Establish source hierarchy and evidence grading.
- [ ] Define reviewed ingredient/product records and exclusions.
- [ ] Establish review ownership for safety, cosmetic claims and formulations.
- [ ] Design provenance, last-checked date and version metadata.
- [ ] Establish Bangladeshi price/availability data collection and uncertainty rules.
- [ ] Define approved alternatives and safe substitution rules.
- [ ] Approve any home-preparation options only where formulation and safety evidence supports them.

**Exit gate:** every actionable recommendation has an evidence, eligibility, safety and review pathway.

## Phase 3 — Product UX, compliance and architecture approval

**Status: Not started**

- [ ] Map first-time, returning, inconclusive scan and safety-warning journeys.
- [ ] Design screen-by-screen UX in Bangla and English.
- [ ] Specify consent and permission timing.
- [ ] Define device-only storage, Android backup and deletion behavior.
- [ ] Decide whether optional account/cloud features are justified and map any third-party/cross-border data flow.
- [ ] Define offline behavior, knowledge sync, integrity checks and rollback.
- [ ] Confirm Android minimum version and device matrix.
- [ ] Complete qualified review of applicable Bangladesh privacy, cosmetics, claims and related requirements.
- [ ] Obtain explicit approval of UX and architecture.

**Exit gate:** signed-off product, compliance, UX and architecture baseline. No coding before this gate.

## Phase 4 — Implementation planning

**Status: Blocked pending approval**

- [ ] Break approved scope into small, testable milestones.
- [ ] Define repository structure, build pipeline, test strategy and release signing.
- [ ] Define acceptance criteria for every feature.
- [ ] Plan security, privacy and failure-mode tests.

**Exit gate:** user explicitly authorizes implementation.

## Phase 5 — Incremental implementation

**Status: Not started**

Implement in small milestones only after approval. Each milestone requires tests, review, documentation updates and a usable deliverable. Never enable an unvalidated AI feature just to complete a milestone.

## Phase 6 — Independent evaluation and private pilot

**Status: Not started**

- [ ] Test measurement repeatability and agreement with reference methods.
- [ ] Evaluate across relevant skin tones, Android devices, lighting and supported areas.
- [ ] Measure false positives/negatives, uncertainty and inconclusive-result rates.
- [ ] Test Bangla and English safety flows.
- [ ] Test privacy, data deletion, offline behavior, update integrity and rollback.
- [ ] Run a consented pilot with varied users/devices/budgets and a process for adverse-event reporting.
- [ ] Resolve all critical safety defects.

**Exit gate:** pre-defined criteria pass; limitations and incident response documented.

## Phase 7 — Private APK release

**Status: Not started**

- [ ] Build signed, versioned APK.
- [ ] Verify signing-key protection and release traceability.
- [ ] Test fresh install, upgrade, rollback and data migration.
- [ ] Publish known limitations and support/incident process.
- [ ] Obtain release approval.

## Release blockers

No critical safety defect may be waived. A feature that fails validation stays disabled. If privacy, licensing, evidence, performance, fairness or regulatory compliance cannot be established, reduce scope rather than weaken acceptance criteria.

## Progress reporting convention

For future project updates, report:
- current phase and estimated overall progress;
- completed deliverables and evidence;
- current blocker and next smallest action;
- whether the day's scope is sufficient or becoming excessive;
- any change to goals, features, risks or timeline.

Do not inflate progress for research that has not been documented or decisions that remain open.
