# Decision Log

This file records durable decisions and the rationale behind them. Proposed or preliminary decisions are not silently promoted to approved status. Add a new dated entry rather than rewriting history when direction changes.

## 2026-10-29 — D-001: Dedicated GitHub repository

- **Status:** Established
- **Decision:** Use `barshan94/bangladesh-skincare-ai` as the project documentation and eventual source-code repository.
- **Rationale:** Keep the evolving goals, requirements, roadmap, evidence, architecture and changes in one version-controlled place.
- **Impact:** Update relevant docs whenever direction, scope, risks or milestones change.
- **Note:** Repository was reported as public by GitHub metadata at initial setup. Confirm intended visibility; private is preferable during early planning if the user wants the project kept non-public.

## 2026-10-29 — D-002: Planning before implementation

- **Status:** Approved working rule
- **Decision:** Do not write application code, scaffold the app or modify implementation files until product specification, safety boundaries, model feasibility/validation plan, screen-by-screen UX and architecture have been reviewed and the user explicitly approves implementation.
- **Rationale:** Prevent expensive rework and avoid implementing unsafe or unvalidated assumptions.
- **Impact:** Roadmap phases and implementation remain gated by explicit approval.

## 2026-10-29 — D-003: Android-first, private APK pilot

- **Status:** Preliminary product direction
- **Decision:** Android-only first release, native Android preferred, private installable APK before considering public distribution.
- **Rationale:** Focus development and permit controlled testing.
- **Open items:** Minimum Android version, device matrix, signing/release process, and whether the GitHub repository itself should be private.

## 2026-10-29 — D-004: On-device-first photo analysis

- **Status:** Direction approved; feasibility pending
- **Decision:** Prefer on-device analysis, but only if candidate components meet task-validity, fairness, privacy and performance requirements.
- **Rationale:** Reduce photo exposure and recurring cloud costs.
- **Impact:** Cloud analysis is optional and needs separate informed consent if ever introduced.
- **Open items:** Final model/runtime and per-feature go/no-go results.

## 2026-10-29 — D-005: Feature-specific validation

- **Status:** Approved principle
- **Decision:** Explore a broad range of possible image observations, but enable only features that independently pass pre-defined validation criteria.
- **Rationale:** Face detection or segmentation does not prove accurate skin measurement.
- **Impact:** Unsupported features remain disabled; inconclusive images cannot produce confident claims.

## 2026-10-29 — D-006: Device-only photo storage by default

- **Status:** Approved product direction
- **Decision:** Keep original photos and progress history on-device by default. Cloud storage, cloud analysis and model-training use each require separate informed consent.
- **Rationale:** Minimize sensitive data exposure.
- **Open items:** Android backup behavior, detailed deletion semantics, and whether any cloud feature is justified.

## 2026-10-29 — D-007: Safety-first recommendations

- **Status:** Approved principle
- **Decision:** Use reviewed knowledge and deterministic eligibility/contraindication rules. Generative AI may explain approved content but must not override safety rules or invent formulas.
- **Rationale:** Avoid unsupported claims and unsafe treatment suggestions.
- **Impact:** No unvalidated DIY formulas, no diagnosis from selfies, no fairness score, no guaranteed whitening claims.

## 2026-10-29 — D-008: Initial user experience and business model

- **Status:** Preliminary product direction
- **Decision:** Bangla and English; free at launch; accounts optional where feasible; locally relevant affordability prioritized.
- **Open items:** Final concern list, knowledge review owners, local price/availability sources and visual design.

## Future entry template

### YYYY-MM-DD — D-XXX: Short decision title
- **Status:** Proposed / Approved / Superseded / Rejected
- **Decision:**
- **Context and rationale:**
- **Evidence/sources:**
- **Alternatives considered:**
- **Impact on scope, safety, privacy, architecture or roadmap:**
- **Follow-up actions:**
- **Approver:**


## 2026-10-29 — D-009: Product scope decisions approved

- **Status:** Approved by the user for planning; not implementation authorization.
- **Decision:** Broader scope with gated features; adults only initially; photo tracking and validated capture checks first, skin-condition analysis only after feature-specific validation; DIY formulas only when individually reviewed and validated; use the five proposed navigation areas.
- **Rationale:** Preserve a broad product ambition without exposing users to unvalidated analysis or unsafe formulas.
- **Approved navigation:** Skin Scan; My Routine; Progress; Ingredients and Alternatives; Profile and Privacy.
- **Scope interpretation:** Broad planning scope does not mean every feature is included in the first release. Each concern, model, formula and claim must pass applicable evidence, safety, legal/privacy, feasibility and UX gates.
- **Impact:** Update product specification and screen-by-screen UX plan. Implementation remains blocked until planning gates are reviewed and the user separately authorizes it.
- **Follow-up:** Define adult eligibility; finalize concern boundaries; obtain qualified skincare/formulation and Bangladesh legal/privacy review; validate each image feature; define formula governance.
- **Approver:** User, explicitly in conversation on 2026-10-29.
