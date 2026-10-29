# Bangladesh Skincare AI

An Android-first, local-first skincare companion for adults in Bangladesh.

> **Current state:** M0 Android project baseline has been committed on the implementation/m0-android-baseline branch. CI has started; the build is not yet verified.
> **Source of truth:** This repository. Read [Project State and Session Handoff](docs/PROJECT_STATE.md) before continuing.

## Approved product decisions
- Adults only for the initial audience.
- Photo tracking first; skin-condition analysis only after feature-specific validation.
- DIY formulas only if individually reviewed and validated.
- Five areas: Skin Scan, My Routine, Progress, Ingredients and Alternatives, Profile and Privacy.
- Android-first, private installable APK, Bangla and English, free at launch.
- Photos/history on-device by default. Cloud storage, cloud analysis and model training require separate informed consent.
- No selfie diagnosis, beauty/fairness scores, guaranteed whitening, AI-generated formulas, or photo-triggered automatic treatment changes.

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

## Technical baseline
Native Android with Kotlin and Jetpack Compose. Initial scaffold uses JDK 17, Gradle 8.11.1, Android Gradle Plugin 8.9.2, Kotlin 2.0.21, compile/target SDK 35 and minimum SDK 26. GitHub Actions compiles a debug APK, runs unit tests and stores the APK as a short-lived artifact.

The initial UI has five navigable areas and English/Bangla string resources. Non-home areas are placeholder states. No skincare analysis, routine recommendation, DIY formula, photo capture/import, cloud service, analytics or backend is active.

## Privacy and safety baseline
The manifest disables Android automatic backup and the initial app requests no permissions and performs no network requests. This is a starting configuration, not a complete privacy audit. Photos, local persistence, consent controls and deletion workflows have not been implemented yet.

Every image feature needs its own task definition, reference method, lawful/licensed data, pre-approved criteria, subgroup analysis, uncertainty handling and on-device performance evidence.

## What is ready and what is not
**Documented:** product direction, UX, requirements, data model, architecture, safety hazards, threat model, knowledge governance, tests, backlog, release process and localization/accessibility.

**Implemented in M0 (pending CI verification):** native project configuration, Compose app shell, five-area navigation, initial Bangla/English resources, no-permission/no-network manifest and CI workflow.

**Still requires qualified review/evidence:** Bangladesh legal/privacy and cosmetic-claims interpretation; skincare-content review; individual DIY formula validation; model/data licensing and feature-specific validation; adult eligibility handling; Bangla usability review. A successful build cannot replace these reviews.

**Repository visibility:** GitHub currently reports this repository as public. Change it in repository Settings if private visibility is desired; do not add sensitive data.

## Working agreement
1. GitHub is the source of truth; fetch current docs before relying on chat memory.
2. Update affected docs, roadmap, changelog and project state during each meaningful work session.
3. Record decisions with date, rationale, status and impact.
4. Do not claim a feature is validated until acceptance criteria pass.
5. Never commit private data, identifiable skin photos, credentials, signing keys or unlicensed datasets.
6. Updates happen when project work is performed and GitHub writes succeed; there is no background sync.
7. A successful build is not evidence of clinical or measurement validity.

## Next action
Verify the M0 CI run and fix build errors. Then finish the app-shell milestone with tests, open a pull request, and proceed to localization/navigation polish before local persistence. Keep unreviewed treatment and image-analysis features disabled.

## Disclaimer
This project is under research and development; it is not medical advice and does not establish that any proposed AI feature or skincare formula is safe/effective. Actionable skincare content and every home-prepared formula require appropriate evidence and safety review before inclusion.
