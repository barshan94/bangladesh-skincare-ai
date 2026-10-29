# Bangladesh Skincare AI

An Android-first, local-first skincare companion for adult women in Bangladesh. The product combines evidence-reviewed skincare guidance, affordable locally relevant options, private progress tracking and carefully gated AI-assisted observations.

> **Current state:** Documentation and feasibility planning. No application code/scaffolding has been created or authorized.
>
> **Source of truth:** This repository. See [Project State and Session Handoff](docs/PROJECT_STATE.md) at the start of future work.

## Approved product decisions

- Broader product ambition with individual feature gates.
- Adults only for the initial audience.
- Photo tracking and validated capture checks first; skin-condition analysis only after feature-specific validation.
- DIY formulas only if individually reviewed and validated.
- Five main areas: Skin Scan, My Routine, Progress, Ingredients and Alternatives, Profile and Privacy.
- Android-first, private installable APK, Bangla and English, free at launch.
- Photos/history stored on-device by default. Cloud storage, cloud analysis and model training require separate informed consent.
- No diagnosis from selfies, no beauty/fairness scores, no guaranteed whitening, no AI-generated formulas, and no photo-triggered automatic treatment changes.

## Product principles

- Prioritize safe, evidence-supported and realistic improvement for the user's chosen concern.
- Natural darker skin is not a defect.
- “Natural” does not automatically mean safe; synthetic does not automatically mean harmful.
- Safety decisions are deterministic and grounded in reviewed knowledge. Generative AI cannot override safety rules.
- New, changing, painful, bleeding or otherwise concerning findings require appropriate professional-care guidance, not AI diagnosis.
- Enable every image feature only after independent, feature-specific validation.
- Inconclusive/poor-quality photos must not produce confident claims.
- Do not invent Bangladeshi prices, availability, evidence or formula details.
- No critical safety defect may be waived to meet a release date.

## Documentation index

### Product and UX
- [Product specification](docs/PRODUCT_SPEC.md)
- [First-release scope and feature gates](docs/FIRST_RELEASE_SCOPE.md)
- [Testable product requirements](docs/REQUIREMENTS.md)
- [Screen-by-screen UX specification](docs/UX_SCREEN_SPEC.md)
- [Screen-level acceptance criteria](docs/SCREEN_ACCEPTANCE_CRITERIA.md)
- [Visual design system](docs/DESIGN_SYSTEM.md)
- [Bangla/English localization and accessibility](docs/LOCALIZATION_ACCESSIBILITY.md)
- [Consent/privacy copy draft — not legal-approved](docs/CONSENT_COPY_DRAFT.md)

### Architecture and engineering
- [Technical architecture](docs/TECHNICAL_ARCHITECTURE.md)
- [Engineering standards and proposed defaults](docs/ENGINEERING_STANDARDS.md)
- [Logical data model and retention](docs/DATA_MODEL.md)
- [Implementation backlog](docs/IMPLEMENTATION_BACKLOG.md)
- [Test strategy and quality gates](docs/TEST_STRATEGY.md)
- [Private APK release runbook](docs/RELEASE_RUNBOOK.md)

### Safety, privacy and evidence
- [Safety and privacy requirements](docs/SAFETY_AND_PRIVACY.md)
- [Safety case and hazard controls](docs/SAFETY_CASE.md)
- [Privacy/security threat model](docs/THREAT_MODEL.md)
- [Bangladesh compliance review checklist — not legal advice](docs/BANGLADESH_COMPLIANCE_REVIEW.md)
- [Skincare knowledge base governance](docs/KNOWLEDGE_BASE_SPEC.md)
- [Knowledge update and rollback protocol](docs/KNOWLEDGE_UPDATE_PROTOCOL.md)
- [AI feasibility](docs/AI_FEASIBILITY.md)
- [AI model feasibility assessment](docs/AI_MODEL_FEASIBILITY.md)
- [Measurement validation plan](docs/MEASUREMENT_VALIDATION_PLAN.md)

### Project control
- [Project state and session handoff](docs/PROJECT_STATE.md)
- [Roadmap and gates](docs/ROADMAP.md)
- [Decision log](docs/DECISIONS.md)
- [Change log](docs/CHANGELOG.md)

## Technical direction

Recommended baseline: native Android with Kotlin and Jetpack Compose, a local-first app, structured local data, app-private photo storage, deterministic safety/routine logic and reviewed versioned knowledge. No backend, account provider, analytics SDK, cloud inference or skin-condition model is selected by default. See the architecture and engineering standards for provisional defaults and remaining gates.

Face detection/landmarks do not validate skin-condition measurement. Capture checks must be validated for target devices/conditions. Any skin-feature measurement requires a defined task, suitable reference method, lawful/licensed data, pre-approved criteria, subgroup analysis, uncertainty handling and on-device performance evidence.

## What is ready and what is not

**Documented:** approved product direction, screen flows, requirements, data model, architecture baseline, engineering standards, knowledge/formula governance, safety hazards, threat model, tests, backlog, release process, localization/accessibility and session handoff.

**Still requires qualified external evidence/review:** applicable Bangladesh legal/privacy and cosmetic-claims interpretation; skincare-content review; individual DIY formula validation; image-model licences/data provenance and feature-specific validation; adult eligibility handling; Bangla usability review. Documentation cannot replace these reviews.

**Repository visibility:** A prior GitHub check reported this repository as public although private visibility was previously preferred. Verify and change the setting if needed before adding sensitive material.

## Working agreement

1. GitHub is the source of truth; fetch current docs before relying on chat memory.
2. Update affected docs, roadmap, changelog and project state whenever decisions or work change.
3. Record decisions with date, rationale, status and impact.
4. Do not claim a feature validated until pre-defined acceptance criteria pass.
5. Do not commit private data, identifiable skin photos, credentials, signing keys or unlicensed datasets.
6. Do not claim background synchronization: documentation updates happen when project work is performed and the GitHub update succeeds.
7. No application code or scaffolding until the user explicitly authorizes implementation.
8. A successful build is not evidence of clinical or measurement validity.

## Next action

The documentation baseline has been assembled. Next, perform a final consistency/readability pass and confirm repository visibility. Then implementation can begin after the user's explicit authorization, following the backlog. External safety/legal/model gates remain mandatory before enabling the affected content or features.

## Disclaimer

This project is under research and development; it is not medical advice and does not establish that any proposed AI feature or skincare formula is safe/effective. Actionable skincare content and every home-prepared formula require appropriate evidence and safety review before inclusion.
