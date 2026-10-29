# Engineering Standards and Proposed Technical Defaults

- Version: 1.0
- Date: 2026-10-29
- Status: Default engineering baseline for future implementation; use unless a concrete compatibility, safety, privacy or feasibility issue is found. No code has been started.

## 1. Platform baseline
- Native Android app, Kotlin, Jetpack Compose, single-activity architecture.
- Use a clear separation of presentation, domain/policy, data repositories and platform adapters; avoid a large abstract “clean architecture” framework for its own sake.
- Unidirectional UI state/events with ViewModels and coroutines/Flow where appropriate.
- Minimum Android API: provisional default API 26 (Android 8.0), subject to a brief market/device compatibility check before the first build. Raise it only for a documented technical requirement; do not lower it without checking dependency support.
- Target/compile SDK: current stable versions supported by the selected Android Gradle Plugin at implementation time; record exact versions in the repository.
- Distribution: signed private APK initially, not a public Play Store launch.

## 2. Initial dependency direction
Choose maintained stable versions at implementation time and record versions/licences:
- Jetpack Compose for UI.
- AndroidX ViewModel and lifecycle.
- Kotlin coroutines/Flow for async work.
- Room for structured local data.
- DataStore for small non-sensitive preferences; do not put photo/history records in preferences.
- Android Photo Picker for selecting existing images.
- CameraX only if guided in-app capture is needed and supported.
- WorkManager only for deferrable eligible background knowledge checks; no promise of immediate execution.
- Android Keystore/platform cryptography for appropriate local cryptographic material.
- Unit tests with Kotlin/JUnit and coroutine test utilities; UI/instrumentation tests as useful.
- Avoid adding a dependency for functionality available in platform/AndroidX components.

No AI model, cloud provider, analytics SDK, account provider or backend is selected. Do not add one by default.

## 3. App module/package direction
Start with one app module until build size/team complexity justifies splitting it. Organize packages by capability, for example:
- app/navigation and app/theme
- feature/onboarding
- feature/scan
- feature/routine
- feature/progress
- feature/catalog
- feature/privacy
- core/model
- core/domain and safety
- core/data/local
- core/data/knowledge
- core/platform/camera
- core/platform/updates
- core/ui
Names can change during implementation if the separation remains understandable and tested.

## 4. Domain boundaries
- Safety/eligibility rules are pure deterministic functions with unit tests.
- UI never constructs actionable skincare instructions from free text.
- The knowledge repository returns only approved, current, compatible entries.
- Ranking cannot override safety eligibility.
- AI explanation, if ever approved, consumes structured approved facts and cannot write safety rules or formulas.
- Photo capture, quality checks and skin-feature measurement are separate capabilities with separate feature gates.
- UI must represent loading, empty, error, offline, unavailable and inconclusive distinctly.

## 5. Local data and image handling
- Room for structured records; app-private files for original photos.
- Store opaque file references, not public filesystem paths.
- Do not request location permission for skincare photos.
- Inspect EXIF/orientation behavior; never assume a picker or camera normalizes metadata.
- No raw-photo logging, analytics or crash attachment.
- Define and test backup behavior before claiming device-only storage.
- Deleting a photo must remove its file and linked records, with truthful recovery on partial failure.
- No network upload of photos in the initial local-first baseline.

## 6. Knowledge content and update baseline
- Use structured, schema-versioned content with source/review/approval metadata.
- Until remote update signing and key management are approved, use bundled reviewed knowledge or a deliberately disabled update path; do not ship an insecure remote JSON feed.
- No formula or actionable treatment content is shipped until reviewed.
- Remote content must never be executable code.
- Schema/integrity/approval validation happens before activation; activate atomically.
- Critical withdrawals take precedence over ordinary rollback.

## 7. AI baseline
- No skin-condition model in the initial scaffold.
- Implement photo tracking independently from automated skin analysis.
- Capture-quality automation is disabled until validation criteria and evidence are approved.
- No cloud inference, LLM API or model-training data collection by default.
- Any later model requires licence/provenance inventory, validation report, subgroup results, resource benchmarks and feature-specific go/no-go.

## 8. Code quality
- Kotlin formatting/linting and static analysis in CI.
- Avoid global mutable state and hidden singleton business logic.
- Keep safety logic free of UI/framework dependencies where feasible.
- Use explicit sealed result types for unavailable/inconclusive/error outcomes.
- Use typed identifiers/enums for feature and knowledge statuses.
- Handle coroutine cancellation and lifecycle correctly.
- No silent catch-and-ignore for data/safety failures.
- Do not expose stack traces or sensitive data to users/logs.
- Comment why a safety rule exists and cite its governing knowledge/risk record.
- Prefer small testable functions over premature abstraction.

## 9. Git and pull-request policy
- Main branch should remain buildable.
- Small changes, one coherent task per commit/PR.
- Never commit secrets, signing keys, private photos, participant data or unlicensed model/dataset files.
- Update tests and documentation with behavior changes.
- PR description states requirement IDs, behavior, tests, data/privacy impact and unresolved risks.
- No merge that bypasses critical safety or privacy checks.
- Tag releases and retain artifact hash/source commit.

## 10. Configuration and secrets
- No production secret embedded in APK; mobile clients cannot keep a shared server secret confidential.
- Use local-only configuration for the initial no-backend app.
- If a backend is later approved, use server-side secret storage and short-lived/user-scoped credentials as appropriate.
- Keep signing key outside Git and back it up through a separately secured process.
- Document secret rotation and compromised-key response.

## 11. Performance and supported devices
Do not guess performance success. Record a representative Android device matrix and measure:
- cold/warm startup and screen responsiveness;
- image import/capture latency;
- peak memory and storage;
- battery/thermal behavior for any inference;
- model/app package size;
- crash and failure rates.
Set thresholds before feature acceptance, with target-device evidence.

## 12. Engineering defaults that remain gated
The minimum Android API is provisional. Final camera approach, model/runtime, knowledge signing scheme, cloud/backend, age-handling mechanism, encryption/backup settings and external content data sources require the reviews listed in PROJECT_STATE. These unresolved choices must not be disguised as settled facts.