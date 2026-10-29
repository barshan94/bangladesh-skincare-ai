# Product Specification

- **Version:** 3.0 baseline, carried forward from planning conversations
- **Status:** Draft baseline for review; not final implementation approval
- **Last updated:** 2026-10-29
- **Platform:** Android-first
- **Implementation status:** Not started

## 1. Product goal

Build a free-at-launch, privacy-conscious skincare companion for Bangladeshi women. It should help users understand supported concerns, follow simple evidence-based routines, track changes over time, and find affordable, locally relevant options without unsafe claims or unnecessary products.

The goal is the fastest reasonably achievable **safe and evidence-supported** improvement for the concern chosen by the user—not guaranteed whitening, instant transformation, or a beauty ranking.

## 2. Target experience

Five main areas are proposed:

1. **Skin Scan:** guided face/body capture, quality checks, baseline and follow-up comparison.
2. **My Routine:** personalized routine, clear instructions, costs, warnings, realistic timelines.
3. **Progress:** dated photos, validated measurements, symptoms, adherence and plan-change history.
4. **Ingredients and Alternatives:** reviewed ingredients/products, validated DIY options, local cost estimates and substitutions.
5. **Profile and Privacy:** questionnaire, language, budget, storage, consent, optional account and data deletion.

Navigation and visual design are not yet approved.

## 3. Platform and distribution

- Android only for the initial release.
- Native Kotlin + Jetpack Compose is the leading architecture candidate.
- Private installable APK for early testing.
- Bangla and English.
- Free at launch.
- On-device analysis first, if candidate models meet quality and performance requirements.
- Optional accounts for features that genuinely need sync; basic features should work without an account where feasible.

## 4. Supported concerns: proposed initial scope

Candidate first-release areas, subject to evidence and safety review:

- Dryness and hydration support.
- Roughness and basic barrier-friendly routine guidance.
- Selected visible pigmentation, tanning or dark-mark tracking only if image measurement validation succeeds.
- Basic cleansing, moisturization and sun-protection guidance.
- Acne-related routine support only within a clearly limited, reviewed scope; selfie observations are not a diagnosis.

Do not infer that every listed concern will be supported at launch. The exact first-release list is an open decision. If a concern is outside the supported scope, explain the limitation and suggest appropriate professional advice when warranted.

## 5. Adaptive questionnaire

Ask only what is needed to personalize guidance and apply safety rules. Prefer plain-language Bangla/English multiple-choice questions and checklists, with optional free text.

Potential fields:
- Main goal and concern; face/body area.
- User-reported skin experience: dry, oily, sensitive, combination or uncertain.
- Current routine and known products/actives, use frequency and prior reactions.
- Budget in BDT, time available, routine complexity, willingness to prepare products at home.
- Allergies and relevant safety circumstances.
- Follow-up adherence, tolerance, perceived benefit, availability and new concerns.

Ask age, pregnancy/breastfeeding, medications, diagnosed conditions or other sensitive details only where necessary for a specific safety decision. Explain why the information is needed.

## 6. Recommendation pipeline

1. Collect relevant questionnaire answers and, only where useful, validated image observations.
2. Apply hard safety and eligibility rules. Safety is never traded off for lower price or greater predicted benefit.
3. Rank eligible options by relevance, expected benefit, evidence, cost, local availability, simplicity and likely adherence.
4. Present a complete routine with instructions and uncertainty.
5. Ask for confirmation before material treatment changes.
6. Follow up on tolerance, adherence, availability and perceived results.

Every actionable recommendation should explain why it was selected, what to use, amount/frequency/order, realistic timeline, what to avoid, stop conditions, substitutions, cost and its date/source, evidence/review status, and knowledge version. Avoid unnecessary products.

## 7. Home-prepared options

Offer home preparation only when a specific formulation has appropriate evidence and formulation/safety review. A validated formula needs documented ingredient identity/specification, exact composition, preparation, packaging, storage, use-by limit and relevant stability/microbiology support.

Do not generate recipes freely with AI. Do not recommend unvalidated water-based creams stored without suitable preservation and testing. If a safe validated DIY option is unavailable, explain this and offer an appropriate ready-made alternative or no substitute.

## 8. Replacement workflow

For “Can't find this?”:
1. Ask which product, ingredient, concentration, brand, size or price is missing.
2. Search reviewed catalogue.
3. Filter for safety, intended use, formulation, contraindications and compatibility.
4. Rank by expected benefit, cost, availability and simplicity.
5. Explain differences and changes to use instructions or expected timeline.
6. Update routine cost.
7. Confirm material changes.

Never replace a validated cosmetic ingredient with a kitchen ingredient solely because it is cheap or natural. If no suitable alternative exists, say so.

## 9. Selfie analysis and progress

Selfies support tracking of validated visible changes; they do not diagnose disease or score beauty.

Pipeline:
1. Guided capture.
2. Image-quality gate: blur, exposure, shadows, framing, filters and comparability.
3. Validated feature measurement only.
4. Compare standardized sessions.
5. Explain observations, uncertainty and limitations.
6. Combine with symptoms, adherence and feedback before proposing routine changes.

Candidate features require separate validation: image quality, facial-region alignment, selected visible dark marks/pigmentation, texture changes, redness-related changes, acne-like visual features, pores/oiliness/fine lines. None is approved merely by being technically possible. Skin-tone estimation must never become a beauty score or reason to treat natural skin colour as a defect.

Poor or incomparable photos must trigger a retake or an inconclusive result. Do not claim percentage improvement unless the measure has been validated for that use. New, changing, painful, bleeding or concerning lesions should be directed to appropriate professional care.

## 10. Knowledge updates

Use versioned, reviewed knowledge releases. Check for updates when the app opens and at appropriate opportunities; eligible background updates may run when Android permits. Do not promise immediate Wi-Fi-triggered background sync.

Verify integrity, approval, compatibility and metadata before activation. General knowledge can update automatically within policy. Material changes to a user's active routine require explanation and confirmation. Critical safety notices should be prominent; affected unsafe recommendations may need to be disabled without waiting for confirmation.

## 11. Out of scope for MVP

- Diagnosis of skin disease from selfies.
- Unvalidated clinical severity scoring.
- Automatic treatment escalation based solely on an image.
- Unreviewed AI-generated chemical or DIY formulas.
- Guaranteed whitening or improvement claims.
- Automatic upload of photos.
- Any image feature that has not passed its own validation gate.

## 12. Open product decisions

- Exact initial concern list.
- Which visual features meet validation criteria.
- Measurement reference methods and numeric acceptance thresholds.
- Minimum Android version and supported device matrix.
- Whether optional cloud processing is justified.
- Expert review ownership for skincare claims and formulations.
- Backend and price/availability data sources, if needed.
- Current Bangladesh regulatory requirements and review pathway.
- Final screen-by-screen UX and visual design.

## 13. Approval rule

This document is the current planning baseline, not permission to begin implementation. Changes to goals, supported concerns, safety boundaries, privacy, or distribution must be recorded and reviewed. Implementation begins only after the user explicitly approves the relevant specification and design gates.
