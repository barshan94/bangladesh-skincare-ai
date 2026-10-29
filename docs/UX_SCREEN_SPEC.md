# Screen-by-Screen UX Specification — Draft 0.1

- Date: 2026-10-29
- Status: Planning draft based on user-approved product decisions; not final visual design or implementation approval.
- Audience: Adults only initially; Bangla and English.
- Navigation: Skin Scan; My Routine; Progress; Ingredients and Alternatives; Profile and Privacy.

## Global UX rules
- Use plain Bangla/English, inclusive wording and large touch targets.
- No beauty/fairness score, guaranteed whitening, diagnosis from selfies or unsupported improvement percentages.
- Skin-condition analysis stays unavailable until that exact feature passes validation and expert review.
- Photos and progress stay on-device by default. Cloud storage, cloud analysis and model training require separate affirmative consent.
- Ask permissions only when needed and explain why. Prefer Android's photo picker where possible.
- Never silently change an active routine. Safety rules cannot be overridden by budget, preferences or generative AI.
- Show BDT estimates with source/date and uncertainty; never invent local availability or prices.
- Every screen needs relevant loading, empty, error, offline and accessibility states.

## First-run journey

### 1. Welcome and language
Choose Bangla or English. Explain evidence-informed guidance and private progress tracking, with no diagnosis or guaranteed results. Continue. No permissions requested.

### 2. Safety and expectations
Explain supported guidance, uncertainty, natural skin colour not being a defect, and when professional care may be appropriate. Acknowledgement is not consent to cloud processing or model training.

### 3. Adult eligibility
State the app is intended for adults only initially. Age confirmation method, edge cases and data handling require qualified Bangladesh legal/privacy review before this screen is finalized. Avoid collecting full date of birth unless necessary.

### 4. Choose a goal
Offer dryness/roughness, simpler routine, ingredient education, visible-change tracking, or “not sure.” Clearly label unsupported concerns and avoid forcing a false category.

### 5. Adaptive safety questionnaire
Collect only relevant answers: concern/area; user-reported skin experience; current products/actives and frequency; prior reactions/allergies; budget in BDT; time and routine complexity; home-preparation preference; and necessary safety exclusions. Use short steps, progress indicator and Back. Explain sensitive questions. If a necessary safety fact is unknown, withhold the affected recommendation instead of guessing.

### 6. Privacy choices
Explain local storage, OS backup caveats, deletion behavior and any optional account/sync. Keep cloud storage, cloud analysis and model-training consent separate and off by default. Do not bundle photo upload or account creation into Continue.

### 7. Routine recommendation
Show the smallest suitable evidence-reviewed routine, use order, amount/frequency, precautions, realistic timeline, stop conditions, rationale, evidence status and cost with source/date. Show a DIY formula only if that exact formula is reviewed and validated; otherwise offer a reviewed ready-made alternative or say no DIY option is recommended.

### 8. Routine ready
Show accepted routine and first action. Offer optional baseline photo, ingredients browsing and progress tracking. Do not require a photo, account, notification or cloud use.

## Area 1 — Skin Scan

### 9. Scan landing
Explain currently available capabilities. Offer guided capture, quality checks and tracking when supported. Mark skin-condition analysis unavailable/research-only until independently validated.

### 10. Guided capture
Guide lighting, distance, framing, pose and avoiding filters. Ask camera permission only after capture starts. Offer photo picker/manual tracking if supported when camera permission is denied. No location permission.

### 11. Capture-quality result
Show pass, retake or inconclusive with actionable reasons. Do not infer skin health from photo quality. Enable only checks tested on intended devices and conditions.

### 12. Review and save
Show photo, date, area and optional note. Explain local storage. Actions: Save to private progress, Retake, Cancel. Future cloud storage requires separate consent before upload.

### 13. Scan history/comparison
Show dated originals, area and comparability status; show validated measurements only when enabled. Avoid directional claims when sessions are not comparable.

## Area 2 — My Routine

### 14. Today's routine
Show reviewed steps, amount/frequency, precautions and cost. Allow optional adherence marking, irritation report, unavailable-item report and details. Missing a day is not a failure.

### 15. Routine step detail
Explain intended use, evidence strength, order, compatibility, exclusions, realistic timeline, stop conditions, source and review date. Suppress unsupported instructions.

### 16. Report a reaction
Collect simple symptoms, onset/context and optional note. Do not diagnose. Use reviewed stop/seek-care rules; urgent symptoms need appropriate urgent-care guidance. Never automatically escalate to stronger treatment.

### 17. Proposed routine change
Compare current and proposed steps, reason, evidence/uncertainty, cost and risks. Actions: Accept, Keep current, Explain more. Material changes require confirmation; unsafe recommendations may be disabled with explanation.

## Area 3 — Progress

### 18. Progress overview
Show timeline, notes, adherence/tolerance trends, photo dates and validated measurements only. No beauty/fairness scores.

### 19. Add progress entry
Allow date, area/concern, optional symptoms, adherence, tolerance, product availability and note. Local by default; editable/deletable.

### 20. Compare sessions
Show two dated originals and comparability. Measurements only if validated. Do not change a routine from visual differences alone.

### 21. History and data management
List photos/entries and explain storage. Offer export/delete only when implemented and tested. Deletion, backup and export semantics require architecture/privacy review.

## Area 4 — Ingredients and Alternatives

### 22. Browse/search
Search reviewed ingredients/products. Filters may include intended use, budget and format. Show local availability only with a dated source. No undisclosed paid ranking.

### 23. Ingredient/product detail
Show identity, intended use, evidence, formulation/concentration where supported, cautions, compatibility, exclusions, cost estimate and source/review date. Do not assume products are equivalent just because they share an ingredient.

### 24. Replacement workflow
Identify missing item → search reviewed catalogue → filter by safety/compatibility → compare eligible options → explain changed instructions/cost/timeline → user confirms. Never force a substitute; say when none is appropriate.

### 25. DIY formula detail — approved formulas only
Show exact ingredient specifications and quantities, preparation, hygiene/equipment, packaging, storage, use-by limit, exclusions, stop conditions, evidence, reviewer, validation record and version. If required review is missing, expired or withdrawn, hide the recipe and explain why. No AI-generated improvisation or unreviewed substitutions.

## Area 5 — Profile and Privacy

### 26. Profile/preferences
Language, goals/areas, budget, routine complexity and preferences. Let users correct answers without restarting. Avoid unnecessary display of sensitive information.

### 27. Privacy controls
Show local/cloud status, consent history, permissions, data access/export/deletion and backup caveats. Explain what happens to already-synced copies if consent is revoked.

### 28. Knowledge/app information
Show app and knowledge versions, last successful update, sources/review dates, limitations and update status. Verify integrity/approval before activation; support rollback. Updates cannot change privacy choices or silently change routines.

### 29. Help and professional care
Explain supported scope, limitations, safety-report path and when to seek professional care. Add local resources only after verification and review.

## Returning-user journey
1. Open to a simple Today/Home view with access to all five areas.
2. Show accepted routine and a small progress summary.
3. Surface relevant safety notices or knowledge updates with explanation.
4. Offer optional scan or progress entry.
5. Offline: show saved content and last update time; do not invent current prices/availability.
6. If a recommendation is withdrawn or unsafe, disable it and explain why.

## Essential empty/error states
No safe routine; unsupported concern; missing safety answer; photo too dark/blurry/filtered/incomparable; camera denied/unavailable; no progress history; no suitable product/formula; price/availability unverified; offline/update/integrity failure; save/delete failure; consent revoked; formula/feature withdrawn.
Each state explains what happened, whether data was retained and the next reasonable action. Never claim save, sync, deletion or analysis succeeded unless confirmed.

## Review gates before UX sign-off
- Qualified Bangladesh legal/privacy review of adult eligibility, data protection, consent, retention/deletion, backups, cloud and cross-border processing.
- Qualified skincare/dermatology review of concerns, claims, routine guidance and escalation language.
- Individual qualified formulation/safety review for each DIY formula, including relevant stability, preservation and microbiological evidence.
- Independent validation of each photo feature with pre-defined criteria, reference methods, uncertainty and device/skin-tone/lighting robustness.
- Bangla terminology, accessibility and usability review with adult Bangladeshi users.
- Final data-flow, offline/update/rollback and deletion behavior review.
- User approval of detailed flows and visual design.
- No application implementation until the user separately authorizes it.
