# Project Change Log

## 2026-10-29 — M0 Android implementation started
- **Type:** Architecture / Implementation / CI
- **Previous state:** Documentation baseline only; no application code.
- **New state:** Created branch implementation/m0-android-baseline and committed a native Kotlin/Jetpack Compose project scaffold.
- Added Gradle settings/build files, pinned Android Gradle Plugin 8.9.2, Kotlin 2.0.21, Gradle 8.11.1 in CI, JDK 17, compile/target SDK 35 and min SDK 26.
- Added manifest with no requested permissions, no network permission and Android automatic backup disabled.
- Added five-area bottom navigation shell, initial home screen and honest placeholder states for unimplemented areas.
- Added English and Bangla resource files.
- Added GitHub Actions workflow to compile a debug APK, run unit tests and upload the APK artifact for 14 days.
- **Verification:** Workflow run 40870169709 was observed as queued. Build and tests are not yet confirmed passing.
- **Safety/privacy:** No model, cloud service, analytics, skincare advice, DIY formula, photo capture/import or persistence implemented.
- **Roadmap impact:** M0 implementation started; CI verification and UI tests are next.
- **Approval status:** User explicitly authorized implementation on 2026-10-29.
- **Repository note:** GitHub reports repository visibility as public; owner should change visibility in Settings if private is desired.

## 2026-10-29 — First-release scope proposal drafted
- Added docs/FIRST_RELEASE_SCOPE.md to separate proposed MVP features from gated research and excluded features.
- Proposed core focus: reviewed routine guidance, adaptive safety questionnaire, local/private progress timeline, transparent ingredient/product knowledge, privacy controls and safe knowledge updates.
- Automated skin-feature analysis remains gated by independent validation; diagnosis, fairness scores and unreviewed DIY formulas are excluded from the proposed MVP.

## 2026-10-29 — Bangladesh compliance checklist added
- Added docs/BANGLADESH_COMPLIANCE_REVIEW.md with an initial checklist based on official sources for the Personal Data Protection Act, 2026 and Drugs and Cosmetics Act, 2023.
- Recorded privacy, consent, retention/deletion, backup, third-party processing, cosmetic claims and DIY-formula review questions for qualified Bangladesh counsel and relevant experts.
- This is a preliminary checklist, not legal advice or a compliance determination.

## 2026-10-29 — Measurement validation and AI feasibility documented
- Added docs/MEASUREMENT_VALIDATION_PLAN.md and docs/AI_MODEL_FEASIBILITY.md.
- Clarified that face geometry/capture assistance is distinct from validated skin-condition measurement.
- No candidate feature is approved or validated.

## 2026-10-29 — Documentation baseline expanded
- Added testable requirements, local-first logical data model, knowledge governance, safety case, threat model, test strategy, implementation backlog, release runbook, localization/accessibility and knowledge update protocol.
- Updated architecture/engineering defaults, design system, consent-copy draft, README index, roadmap and project handoff.
- All documentation remains a planning baseline, not proof of legal compliance, clinical safety or model validity.

## 2026-10-29 — Final documentation consistency pass
- Verified README document links against the GitHub docs directory and refreshed project-state/roadmap records.
- GitHub metadata reported repository visibility as public. Owner action is required if private visibility is desired.
- Screen acceptance criteria were added and linked from the UX specification.

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
