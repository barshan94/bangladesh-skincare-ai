# Product Requirements — Bangladesh Skincare AI

- Version: 1.0
- Date: 2026-10-29
- Status: Implementation-planning baseline. Clinical, legal and validation gates remain mandatory.
- Approved product choices: broad scope with gates; adults-only initially; photo tracking and validated capture checks first; skin analysis only after validation; DIY formulas only when individually reviewed and validated; five navigation areas.

## Requirement language
MUST is release-blocking. SHOULD is expected unless a documented reason and product-owner approval justify deviation. MAY is optional. A requirement is not evidence that a feature is safe, validated or legally compliant.

## Product outcomes
- PR-001: Help adult users in Bangladesh understand supported skincare routines and track progress without promising guaranteed results.
- PR-002: Support Bangla and English.
- PR-003: Prioritize safe, evidence-supported guidance and explain uncertainty.
- PR-004: Never treat naturally darker skin as a defect or rank beauty, attractiveness, fairness or whitening.
- PR-005: Target a private Android APK, free at launch.
- PR-006: Provide core functionality without an account or network where feasible.
- PR-007: Plan broadly, but gate each feature independently.

## Functional requirements

### Onboarding and profile
- FR-001: Users can choose Bangla or English and change it later.
- FR-002: Initial product is intended for adults only. Age confirmation remains configurable until legal/privacy review approves it.
- FR-003: Explain limits, uncertainty, privacy defaults and non-diagnostic purpose.
- FR-004: Collect only information needed for personalization or a defined safety rule.
- FR-005: Allow “not sure” where safe; missing required safety facts withhold affected recommendations rather than being guessed.
- FR-006: Budget uses BDT and may be unknown.
- FR-007: Core functionality should work without an account.

### Skin Scan
- FR-010: Support private, user-controlled photo tracking before skin-condition measurement is enabled.
- FR-011: Request camera/photo access only when needed and explain why.
- FR-012: Provide an alternate supported path if camera permission is denied.
- FR-013: Show capture-quality checks only after target-device/intended-condition validation.
- FR-014: Quality checks return pass/retake/inconclusive and actionable explanation, not a skin-health score.
- FR-015: Skin-condition analysis remains disabled until its own validation and qualified review gates pass.
- FR-016: Never diagnose disease, infer medical severity or produce beauty/fairness scores from photos.
- FR-017: Do not claim improvement from sessions that are not sufficiently comparable.
- FR-018: Never change a routine automatically from a photo alone.

### My Routine
- FR-020: Apply deterministic safety/eligibility filters before ranking.
- FR-021: Actionable routine instructions come from structured approved knowledge; generative AI cannot invent formulas or override safety rules.
- FR-022: Recommendations show purpose, instructions, precautions, realistic timeline, uncertainty, stop conditions, review information and cost provenance where available.
- FR-023: Material routine changes require explanation and user confirmation. Unsafe recommendations may be disabled immediately with explanation.
- FR-024: Reaction reporting triggers conservative reviewed guidance, never diagnosis or automatic escalation to stronger treatment.
- FR-025: Do not present a routine as personalized when necessary safety data is missing.

### Progress
- FR-030: Manual progress entries work without photos.
- FR-031: Users can review dated originals and delete saved entries/photos.
- FR-032: A measurement retains feature, method/model version, timestamp, uncertainty and comparability status.
- FR-033: No percentage improvement unless that exact metric/use is validated and approved.
- FR-034: Missing adherence entries are not framed as failure.

### Ingredients and Alternatives
- FR-040: Catalogue entries include identity, intended use, source/evidence, review status/date, exclusions and relevant formulation details.
- FR-041: Prices and availability have source and last-checked date or are explicitly unknown/estimated.
- FR-042: Alternatives pass safety, intended-use, formulation and compatibility filters before ranking.
- FR-043: If no appropriate substitute is known, say so rather than forcing one.
- FR-044: DIY formulas are individually reviewed/validated; unapproved, expired, withdrawn or incomplete records are not actionable.
- FR-045: Prohibit free-form AI recipe generation and unreviewed substitutions.

### Profile, privacy and updates
- FR-050: Original photos/progress history stored on-device by default.
- FR-051: Cloud storage, cloud analysis and model training have separate affirmative consent.
- FR-052: Raw photos never go to analytics/crash reporting.
- FR-053: Users can review privacy choices and use supported deletion controls.
- FR-054: Explain OS backup/device-transfer limits accurately.
- FR-055: Consent withdrawal stops future optional processing where technically possible and explains previously synced copies.
- FR-056: Knowledge updates cannot change privacy settings or silently alter an active routine.
- FR-060: Knowledge releases are versioned and include compatibility, approval, provenance and integrity metadata.
- FR-061: Reject invalid, unapproved, incompatible or integrity-failing releases.
- FR-062: General approved content may update automatically under the approved policy.
- FR-063: Material changes to an active routine require explanation and confirmation.
- FR-064: Critical safety notices are prominent; unsafe recommendations may be disabled without waiting for confirmation.
- FR-065: Offline operation exposes last successful update and does not invent current prices/availability.

## Nonfunctional requirements
- NFR-001 Privacy: minimum necessary data; local-first default.
- NFR-002 Security: no secrets in source control; restrict logs; protect app-private files and any cloud endpoints.
- NFR-003 Reliability: errors fail safely; never fabricate results for failed analysis/sync.
- NFR-004 Performance: measure startup, responsiveness, capture and inference on the declared device matrix before final thresholds.
- NFR-005 Compatibility: record supported Android API range/device matrix before implementation.
- NFR-006 Accessibility: scalable text, screen-reader semantics, contrast, large targets and non-colour-only status cues.
- NFR-007 Localization: no hard-coded UI strings; locale-aware pluralization, dates, units and currency.
- NFR-008 Traceability: requirements map to design, tests and release decisions.
- NFR-009 Maintainability: safety policy and knowledge data are versioned/testable independently of UI.
- NFR-010 Observability: diagnostics avoid sensitive content and raw images.
- NFR-011 Offline: saved local content remains accessible offline where feasible.
- NFR-012 Auditability: material content/formula/knowledge changes retain reviewer/version history.
- NFR-013 Model safety: every image feature supports abstention and safe disablement.
- NFR-014 Reproducibility: document JDK/Gradle/Android SDK, dependency versions and release source commit.

## Cross-cutting prohibitions
No diagnosis from selfies; no beauty/fairness/whitening scores or guaranteed results; no unreviewed formula or AI-generated recipe; no photo-triggered treatment escalation; no hidden photo upload or bundled consent; no unsupported claims; no fabricated local price/availability certainty; no release with critical safety, privacy, licence or integrity blockers.

## Acceptance mapping
Every backlog item links requirement IDs and defines positive, negative/safety, accessibility/localization, offline/failure and privacy tests; evidence and reviewer requirements; and whether feature activation is blocked.

## Deliberately unresolved
Exact Android minimum version, model/runtime, backend vendor, numeric image thresholds, clinical claims, formula contents and legal interpretation require feasibility or qualified review. Engineering must not invent these to unblock a demo.