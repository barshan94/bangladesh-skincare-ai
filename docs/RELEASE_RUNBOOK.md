# Private Android APK Release Runbook

- Version: 1.0
- Date: 2026-10-29
- Status: Procedure draft; not authorization to build or distribute.

## Release channels
- Development: local/debug builds and test data.
- Internal QA: controlled testers, pre-release label, no unvalidated actionable content.
- Private pilot: consented adult participants only after safety, privacy, legal, content and enabled-feature gates pass.
- Private release: signed/versioned APK, release notes, limitations and support/incident route.

## Prerequisites
Product owner approves scope; repository visibility/branch policy verified; qualified Bangladesh legal/privacy review recorded; skincare knowledge and each DIY dossier approved; every enabled image feature has its own validation report; no critical/high release blockers; privacy/consent/deletion/backup tests pass; model/dependency/content licences recorded; source commit/build toolchain traceable; incident owner and rollback process assigned.

## Build procedure
1. Select reviewed commit and tag candidate.
2. Start from clean working tree and pinned documented toolchain.
3. Run formatting, lint, static analysis, unit/integration/UI tests.
4. Run dependency/licence/secret checks.
5. Build release artifact with approved Gradle variant.
6. Sign through protected process; never commit keystore/passwords.
7. Verify package ID, version, signature, permissions, network behavior and artifact hash.
8. Install clean and upgrade from prior supported version.
9. Test offline behavior, migration, deletion and knowledge rollback.
10. Record artifact hash, source commit, build environment, tests and approvers.

Exact commands/package IDs are deferred until the Android project/toolchain is approved and created.

## Pilot safety
Recruit eligible adults under reviewed consent. Explain experimental features, local storage and collection. External photo review requires separate consent and approved handling. Provide safety/privacy/crash reporting. Assign triage owner and response target. Pilot feedback is not clinical proof without appropriate study design.

## Rollback/withdrawal
Keep prior signed APK and known-good knowledge metadata. If a model/content/formula is unsafe, disable it where supported and issue clear notice. Never roll back to a package containing a known withdrawn hazard. For software defects, check migrations/safety before restoring. For suspected privacy exposure, stop affected transfers, preserve minimal evidence and follow approved incident/legal process. Record versions, impact, mitigation, communications and corrective action.

## Release notes
Include version/date, user-visible changes, supported Android range, enabled/disabled experimental features, storage/network behavior, limitations, knowledge version, support route and migration/backup caveats.

## Post-release
Review crash/error reports without raw photos or unnecessary sensitive data; monitor sources/prices/safety notices; review adverse reports with qualified personnel; track dependency/model changes; maintain review dates and rollback/disable capability.