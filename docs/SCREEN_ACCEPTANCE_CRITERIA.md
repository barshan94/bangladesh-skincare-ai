# Screen-Level Acceptance Criteria

- Version: 1.0
- Date: 2026-10-29
- Status: Testable UX criteria derived from UX_SCREEN_SPEC.md; usability and expert review remain pending.
- Rule: A screen is not accepted merely because it renders. Criteria cover safe behavior, failure states and privacy.

## First-run journey

### S01 — Welcome and language
- User can select Bangla or English before continuing.
- Selection is retained and can be changed later.
- No camera, photo, location, account or network permission is requested.
- Text is accessible at large font sizes and does not clip in either language.

### S02 — Safety and expectations
- Non-diagnostic purpose, limitations and no-guarantee wording are visible before personalization.
- Natural skin colour is not described as a defect.
- Acknowledgement does not grant cloud or training consent.
- Professional-care wording has qualified content review before release.

### S03 — Adult eligibility
- Eligibility is enforced consistently with the reviewed policy.
- The app does not collect a full birth date unless justified and approved.
- Unconfirmed/ineligible state does not expose adult-only personalization.
- Exact age-assurance behavior remains blocked pending legal/privacy review.

### S04 — Goal selection
- User can choose a supported goal or “not sure.”
- Unsupported concerns are labelled honestly and are not routed into fabricated recommendations.
- Back navigation preserves answers without duplicate records.

### S05 — Adaptive questionnaire
- Questions are driven by the approved questionnaire version.
- Sensitive questions explain why they are asked.
- “Not sure”/decline states are distinct from negative answers.
- Missing safety-critical facts block affected recommendations.
- Contradictory or invalid answers produce a clarification state, not a silent default.

### S06 — Privacy choices
- Local-first storage is explained accurately.
- Cloud storage, cloud analysis and model training are separate choices.
- Optional choices are not preselected or bundled with continue.
- Declining optional processing does not block unrelated core features.
- Copy matches actual data flow and has legal/privacy approval before release.

### S07 — Routine recommendation
- Every actionable step references an approved current knowledge entry.
- Safety filters run before ranking.
- Rationale, precautions, stop conditions, evidence/review metadata and realistic expectations are shown where applicable.
- Costs show provenance/date or are marked unknown/estimate.
- No unreviewed formula or unsupported claim can render.
- Empty-safe state appears if no eligible recommendation exists.

### S08 — Routine ready
- User can accept/leave the routine without photo, account, cloud or notifications.
- Accepted routine version and acceptance event are stored correctly.
- Declining a photo does not block the rest of the app.
- No fake reminder or success state is shown.

## Skin Scan

### S09 — Scan landing
- Only enabled/approved capabilities are offered as available.
- Skin-condition analysis remains disabled until its validation approval is present.
- The screen explains photo purpose and storage before capture/import.
- No disease-diagnosis or beauty/fairness wording appears.

### S10 — Guided capture
- Capture instructions are available as text, not only visual overlay.
- Camera permission is requested only after the user starts capture.
- Denied permission has a supported alternative or a clear explanation.
- No location permission is requested.
- Orientation and malformed image inputs are handled safely.

### S11 — Capture-quality result
- Output is pass, retake, inconclusive or error—not a skin score.
- Retake state gives a specific actionable reason when known.
- Automated checks are present only if their validation gate passes.
- Failure cannot be interpreted as a skin-condition finding.

### S12 — Review and save
- User sees the selected original and metadata before saving.
- Save/cancel/retake actions work and have truthful result states.
- Local save does not send image over network.
- Partial save failure does not leave an undiscoverable file or claim success.
- Optional cloud transfer is not present in the initial baseline.

### S13 — Scan history/comparison
- Each session shows date and area.
- Non-comparable sessions are labelled and do not yield directional improvement claims.
- Original images remain viewable without enhancement.
- Only approved validated measurements can appear.
- Delete removes file and linked result metadata or reports a recoverable failure.

## My Routine

### S14 — Today's routine
- Only current approved routine version is actionable.
- Completion logging is optional and never shames missed days.
- Cost and availability uncertainty are visible.
- Unavailable-item and reaction routes are accessible.

### S15 — Routine step detail
- Instructions match the approved knowledge entry exactly.
- Evidence, review date, relevant cautions and stop conditions are accessible.
- Unsupported concentration, frequency or compatibility is not inferred.
- No AI output can modify structured safety rules.

### S16 — Report a reaction
- User can record a concern without uploading a photo.
- Reviewed stop/care-seeking language appears for applicable answers.
- The app does not diagnose the reaction or recommend stronger treatment automatically.
- Safety reports can trigger content review without silently changing the routine.
- Urgent guidance is reviewed by qualified personnel.

### S17 — Proposed routine change
- Current and proposed steps, reason, cost, uncertainty and cautions are compared.
- Accept/keep-current/more-explanation actions are explicit.
- Active routine changes only after user confirmation, except an unsafe entry may be disabled with an explanation.
- The accepted routine version is auditable.

## Progress

### S18 — Progress overview
- Shows timeline and available user-entered history without beauty/fairness scoring.
- Any measurement is labelled with feature status and uncertainty.
- No percentage improvement appears without validation approval.
- Empty state is useful and does not require a photo.

### S19 — Add progress entry
- User can save a manual entry without a photo.
- Fields are optional unless a documented safety purpose requires them.
- Entry can be edited and deleted.
- Sensitive notes remain local by default and are not logged.

### S20 — Compare sessions
- Both images show dates and area.
- Comparability state is clear.
- No improvement statement appears for incompatible sessions.
- Comparison itself does not alter the routine.

### S21 — History and data management
- User can identify stored entries/photos and invoke supported delete/export actions.
- Deletion status reflects actual file/database outcome.
- Backup and cloud-copy limits are explained accurately.
- Export is not shown as available until implemented and tested.

## Ingredients and Alternatives

### S22 — Browse/search
- Only reviewed catalogue entries are actionable.
- Search/filter empty states are understandable.
- Local availability is shown only with a source and freshness date.
- Paid ranking is absent unless separately assessed and disclosed.

### S23 — Ingredient/product details
- Identity and intended use are clear.
- Evidence strength/limitations, exclusions and compatibility are shown when relevant.
- Product equivalence is not inferred from shared ingredient name alone.
- Stale price/availability is labelled.

### S24 — Replacement workflow
- Missing item is identified sufficiently to avoid unsafe guessing.
- Safety/compatibility filters precede cost ranking.
- Instructions/timeline/cost changes are explained.
- No suitable alternative results in an honest no-match state.
- Routine changes require confirmation.

### S25 — DIY formula detail
- Only individually approved, current formula versions can display actionable instructions.
- Exact composition, preparation, packaging, storage, use-by limit, exclusions and review metadata are present.
- Missing/expired/withdrawn dossier blocks display and gives a reason.
- AI cannot improvise quantities or substitutions.
- No formula is released without qualified validation.

## Profile and Privacy

### S26 — Profile/preferences
- Language, goals, budget and preferences can be edited.
- Editing does not unexpectedly restart onboarding or erase history.
- Sensitive answers are not exposed unnecessarily.

### S27 — Privacy controls
- Storage and consent status are visible.
- Purpose-specific consent can be reviewed/withdrawn where supported.
- Revocation stops future optional processing where technically possible.
- Previously synced copies and backup limitations are explained.
- No action claims success before confirmed.

### S28 — Knowledge/app information
- App and knowledge versions and last successful update are visible.
- Sources/review dates and limitations are accessible.
- Unapproved/incompatible/corrupt update cannot activate.
- Rollback cannot re-enable a withdrawn safety item.
- Update failure leaves a safe state and clear message.

### S29 — Help and professional care
- Scope and limitations are easy to find.
- Reporting path is accessible.
- Local resources appear only after verification and review.
- The app does not diagnose or imply the help content replaces professional care.

## Cross-screen acceptance

- All visible strings are localized in Bangla and English.
- Font scaling, screen reader, focus order, contrast and touch targets are tested.
- Loading, empty, offline, permission-denied, malformed-input, cancellation and failure states are implemented where relevant.
- No raw photo or sensitive questionnaire answer enters logs/analytics/crash reports.
- Consent and actual network behavior match.
- No unreviewed knowledge/formula is actionable.
- No feature is marked validated without its evidence record.
- Test cases link to requirement IDs and record pass/fail evidence.
- Qualified/legal review gates remain blockers for the affected release scope.