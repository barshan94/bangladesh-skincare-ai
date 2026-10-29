# Project Change Log

Record meaningful changes to project goals, feature scope, safety/privacy requirements, architecture, model choices, release plans and milestone status. This is a product change log, not a substitute for Git commit history.

## 2026-10-29 — Initial AI model feasibility assessment

- Added `docs/AI_MODEL_FEASIBILITY.md` with an initial desk assessment of ML Kit, MediaPipe Face Landmarker and LiteRT.
- Clarified that face geometry/capture assistance is distinct from validated skin-condition measurement.
- Proposed capture-quality checks and face alignment as first technical candidates; pigmentation/dark-mark analysis remains gated research.
- Recommended deferring automated hydration/oiliness/pores/fine-line claims and excluding beauty/fairness scores and selfie-based diagnosis from the MVP.
- Added requirements for reference methods, repeatability, cross-device/lighting robustness, skin-tone subgroup evaluation, model/data license review and pre-defined thresholds.
- Updated README to link the detailed report.
- No model selected, no feature declared validated, and no application code or implementation scaffolding created.
- Next milestone: define and approve the measurement-validation protocol and initial automated feature shortlist.

## 2026-10-29 — Initial repository baseline

- Created the dedicated `bangladesh-skincare-ai` repository.
- Established the initial README and planning-document structure.
- Carried forward product specification v3.0 from planning.
- Recorded Android-first private APK direction, on-device-first analysis, device-only photo storage by default, Bangla/English, free-at-launch and optional accounts where feasible.
- Added a staged roadmap with explicit approval gates before implementation.
- Recorded candidate AI foundations and the requirement to validate each skin-analysis feature independently.
- Added safety/privacy requirements, proposed architecture and decision log.
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
