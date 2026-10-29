# Safety Case and Hazard Controls

- Version: 1.0
- Date: 2026-10-29
- Status: Preliminary; requires qualified expert review.

## Top-level safety claim
SC-0: The released app does not knowingly present unreviewed actionable skincare instructions, unvalidated skin measurements or misleading certainty as established fact. This claim requires evidence from reviews, implementation and tests; documentation alone does not prove it.

## Hazard register

| ID | Hazard | Possible harm | Required control | Release evidence |
|---|---|---|---|---|
| H-01 | Unsafe/contraindicated routine | Irritation/injury | Hard rules, reviewed content, no AI override | Rule tests and qualified review |
| H-02 | Unvalidated DIY formula | Contamination/irritation/injury | Individual dossier and validation; block incomplete records | Signed formula dossier |
| H-03 | False skin-analysis result | Distress, inappropriate treatment, delayed care | Feature-specific validation, uncertainty/abstention, no diagnosis | Locked evaluation and approval |
| H-04 | Lighting/device shift mistaken for improvement | Misleading decisions | Capture standardization and comparability gate | Repeatability/robustness results |
| H-05 | Concerning lesion treated as cosmetic | Delayed professional care | Non-diagnostic limits and reviewed escalation | Content review and scenario tests |
| H-06 | Dark skin framed as defect | Psychological/social harm | No fairness scoring/whitening promises; inclusive copy | UI/content audit |
| H-07 | Photo leakage | Privacy harm | Local-first storage, no raw-photo telemetry, tested deletion/backup | Data-flow/security tests |
| H-08 | Hidden cloud processing | Loss of informed control | Separate purpose-specific opt-in | Consent/network tests |
| H-09 | Corrupt/malicious knowledge update | Unsafe recommendations | Integrity/approval/schema checks, atomic activation and rollback | Tamper tests |
| H-10 | Wrong price/availability | Financial inconvenience | Source/date/uncertainty | Catalogue audit |
| H-11 | Model/licence misuse | Legal exposure | Provenance/licence review | Inventory and legal review |
| H-12 | Adult eligibility error | Inappropriate use/legal risk | Adults-only gate and reviewed age handling | Legal review and tests |
| H-13 | Overconfident generated advice | Unsupported action | Constrained approved-content explanation | Adversarial tests |
| H-14 | Partial deletion/migration failure | Data remains/inaccessible | Recovery behavior and fault injection | Deletion/migration tests |

## Control principles
- Controls must be deterministic and testable.
- Confidence scores do not replace validation.
- If evidence is insufficient, abstain or disable.
- Safety filtering precedes cost/benefit ranking.
- User preference cannot override contraindications.
- An unsafe recommendation can be disabled immediately with explanation.
- Never claim data was deleted/synced before confirming the result.
- Reduce scope rather than weakening safety criteria.

## Adverse report handling
Provide a way to record concerns without diagnosis. Show reviewed stop-use/care-seeking guidance. Never automatically recommend a stronger treatment. Suspected systemic content issue triggers human review and possible withdrawal. Do not upload reports/photos by default. Assign incident owner, severity, response target and escalation before pilot. Keep only necessary incident evidence.

## Required release evidence
Approved intended use per feature; reviewed knowledge and sources; formula dossiers if any; feature validation and subgroup results; capture comparability/abstention tests; risk-based tests; privacy data-flow/consent/deletion evidence; Bangladesh legal/privacy and claims review; incident/withdrawal/rollback procedure; named safety reviewers.

## Release-stop conditions
Block release/feature if a critical hazard lacks a tested control, actionable content lacks review, formula dossier is incomplete, model misses thresholds or has unresolved subgroup gap, unapproved update can activate, photo/consent/deletion behavior is unclear, licence/legal status is unknown, or prohibited claims are present.

## Review ownership
Product owner coordinates but does not replace qualified reviewers for skincare/clinical content, formulation, AI validation, privacy/security and Bangladesh legal/regulatory interpretation. Record scope, qualification, date, decision and limitations.