# Bangladesh Skincare AI

An Android-first, safety-first skincare companion for Bangladeshi women. The project aims to combine evidence-based skincare guidance, affordable locally relevant options, privacy-preserving photo tracking, and carefully validated AI-assisted observations.

> **Project status: Planning and feasibility research. No application implementation has been approved or started.**
>
> This repository is the source of truth for the product specification, roadmap, research findings, technical decisions, safety requirements, and approved changes in direction.

## Product principles

- Prioritize safe, evidence-supported, realistic improvement for the user's chosen concern.
- Do not treat naturally darker skin as a defect; do not provide beauty or fairness scores.
- Do not promise guaranteed whitening, instant results, permanent fairness, or outcomes unsupported by evidence.
- "Natural" does not automatically mean safe; synthetic does not automatically mean harmful.
- Keep safety decisions deterministic and grounded in reviewed knowledge. Generative AI must never override safety rules or invent skincare formulas.
- Do not diagnose disease from selfies. Refer concerning, new, changing, painful, bleeding, or otherwise worrying findings to appropriate professional care.
- Keep original photos and progress history on-device by default. Cloud storage, cloud analysis, and use of photos for model training require separate informed consent.
- Enable each image-analysis feature only after independent validation; inconclusive images must not produce confident claims.
- Prefer affordable, locally relevant recommendations, but do not invent Bangladeshi prices or availability.
- No critical safety defect may be waived to meet a release date.

## Initial product direction

- **Platform:** Android only for the first release; native Kotlin/Jetpack Compose is the leading candidate.
- **Distribution:** Private installable APK for early testing.
- **Languages:** Bangla and English.
- **Price:** Free at launch.
- **Analysis:** On-device first, subject to feasibility and measured performance.
- **Accounts:** Optional where feasible; basic functionality should not require an account.
- **Storage:** Device-only by default, with optional cloud features only after clear consent.
- **Coverage:** Face and a limited, validated subset of body use cases.
- **Core experience:** Guided scan, adaptive questionnaire, personalized routine, progress timeline, ingredient/alternative guidance, privacy controls, and reviewed knowledge updates.
- **Scope control:** Broad candidate analysis may be explored, but only independently validated features can be enabled.

## Current feasibility position

Established mobile vision components may support face detection, landmarks, capture checks, or region segmentation. These capabilities do not, by themselves, validate pigmentation, acne, texture, oiliness, or skin-improvement measurements. The project has not selected a final skin-analysis model. Every candidate needs a licence review, task-specific evaluation, subgroup testing across skin tones, device/lighting robustness checks, and on-device performance benchmarks.

## Documentation map

- [Product specification](docs/PRODUCT_SPEC.md)
- [Roadmap and gates](docs/ROADMAP.md)
- [AI feasibility and validation](docs/AI_FEASIBILITY.md)
- [Safety and privacy requirements](docs/SAFETY_AND_PRIVACY.md)
- [Proposed technical architecture](docs/TECHNICAL_ARCHITECTURE.md)
- [Decision log](docs/DECISIONS.md)
- [Change log](docs/CHANGELOG.md)

## Working agreement

1. Planning and research come before implementation.
2. Proposed changes are labelled as proposals until explicitly approved.
3. Update the relevant documentation whenever a decision, goal, scope, constraint, or milestone changes.
4. Record important decisions with date, rationale, evidence, impact, and status.
5. Do not mark a feature validated until its pre-defined acceptance criteria pass.
6. Do not commit private data, identifiable skin photos, credentials, API keys, or unlicensed datasets.
7. A successful build is not evidence of clinical or measurement validity.

## Current next milestone

Complete the AI model feasibility assessment and design a defensible measurement-validation plan. Then finalize the first-release concern list and screen-by-screen UX. **No app code should be started until the product specification, safety boundaries, validation plan, and UX/architecture have been reviewed and explicitly approved.**

## Disclaimer

This repository describes a product under research and development; it is not medical advice and does not establish that any proposed AI feature or skincare formula is safe or effective. Any actionable skincare content and home-prepared formula must undergo appropriate evidence and safety review before inclusion.
