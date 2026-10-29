# Data Model and Storage Specification

- Version: 1.0
- Date: 2026-10-29
- Status: Logical model; physical schema and storage implementation not started.
- Default: local-first; no raw-photo cloud upload.

## Principles
1. Store only fields needed for a documented purpose.
2. Keep original photos and progress history on-device by default.
3. Separate profile data from optional diagnostics.
4. Retain source/version provenance for content-derived results.
5. Define retention, deletion, backup and export before release.
6. Never put raw images in logs, analytics or ordinary database text fields.
7. Avoid collecting full date of birth; adult-eligibility mechanism is pending legal/privacy review.

## Logical entities

### LocalProfile
Random local profile ID; language (bn/en); adult-eligibility state; optional BDT budget range and routine complexity; timestamps. Keep sensitive answers separate/minimized.

### QuestionnaireResponse
Response ID, questionnaire version, question key, structured answer, timestamp, purpose and whether safety-critical. Avoid generic free-form medical-history blobs.

### PhotoSession
Opaque session ID; capture time; area; guidance version; quality status (not checked/pass/retake/inconclusive); comparability status; reason codes; opaque app-private file reference; optional note. No geolocation by default.

### ImageFeatureResult
Session ID; feature/method/model versions; status (available/inconclusive/failed/disabled); structured value/unit only for an approved feature; uncertainty; comparability status; validation release ID. Unapproved feature versions must not produce results.

### ProgressEntry
Date; optional concern/area, symptoms, tolerance, adherence, availability and note; timestamps. Keep local by default and allow edit/delete.

### Routine and RoutineVersion
Stable routine ID, immutable version, knowledge-release reference, creation date, status, approved content references, use order/frequency only when supported, change reason and user-confirmation event.

### RoutineEvent
Routine/version, event type (accepted/completed/skipped/reaction/unavailable/change accepted/rejected), timestamp and minimal structured detail.

### KnowledgeRelease
Unique release/version, schema version, publication/approval status, provenance manifest, integrity metadata, supported app versions, compatibility and rollback metadata.

### KnowledgeEntry
Entry ID/type, identity, intended use, evidence references/strength/limitations, exclusions, compatibility, warning codes, review status, reviewer role, review/expiry dates, version, and price/availability source/date/uncertainty.

### FormulaRecord
Immutable formula version, intended use, exact ingredient specifications/amounts, preparation, packaging, storage, use-by limit, required safety/quality evidence, reviewer qualifications, decision/review dates and status. Incomplete, expired or withdrawn formula is never actionable.

### ConsentRecord
Purpose-specific choice (cloud storage, cloud analysis, model training, etc.), notice version, timestamp and withdrawal timestamp. One consent never implies another.

### SyncState (only if sync is approved)
Active knowledge version, last check/success, status and error code. Contains no photos/questionnaire data.

## Relationships
Profiles own responses, sessions, entries and routine versions. Sessions may have results only from enabled validated feature versions. Routine versions reference a knowledge release and approved entries. Formula records require extra validation metadata. Sync state references reviewed knowledge packages only.

## Storage mapping candidate
- Structured local data: Room/SQLite candidate.
- Original photos: app-private file storage with opaque references.
- Cryptographic material: Android Keystore where appropriate; no server secrets embedded in APK.
- Cloud database: not selected and not required for local-first core.
- Knowledge package: verified immutable/versioned payload; activate atomically after checks.

## Deletion behavior
- Deleting a photo removes file and related metadata/results; partial failure is handled and truthfully reported.
- Deleting a progress entry removes content and links.
- Resetting local app data cannot delete copies already exported or synced elsewhere.
- If cloud sync is added, define server deletion, backup expiry, processor propagation and audit behavior before launch.
- Configure and test Android backup behavior; do not promise device-only storage until verified.
- Uninstall is not the only documented deletion mechanism.

## Migration and integrity
Version all database migrations and test from each supported prior schema. Knowledge validation and database migration are separate. Never partially activate a knowledge release. Keep last-known-good content only when it remains safe; a safety withdrawal overrides rollback.

## Privacy review questions
Confirm backup exclusions, EXIF handling, encryption, screenshots/task-switcher previews, app-lock expectations, export and deletion semantics, and whether cloud features are justified.