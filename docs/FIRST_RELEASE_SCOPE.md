# First-Release Scope — Proposal for Approval

**Status:** Proposed; not approved for implementation  
**Date:** 2026-10-29  
**Purpose:** Turn the broad product vision into a realistic, safety-first first release.

## 1. Product promise

Help adult users in Bangladesh build a simple, affordable, evidence-informed skincare routine and privately track supported changes over time. The app must be clear about what it knows, what it cannot infer from a photo, and when it cannot safely recommend an option.

The product does not promise whitening, diagnose disease, guarantee results, or replace a qualified professional.

## 2. Proposed launch features

### A. Core features (proposed for MVP)

1. **Bangla and English**
   - Language choice and reviewed translations for safety-critical content.
2. **Guided onboarding and adaptive questionnaire**
   - Main concern, face/body area, self-reported skin experience, current routine, relevant reactions/allergies, budget in BDT, routine complexity and preparation preference.
   - Ask sensitive health/safety questions only when relevant and explain why.
3. **Basic evidence-reviewed routine guidance**
   - Gentle cleansing, moisturization, and sun-protection education.
   - Dryness and roughness support within reviewed boundaries.
   - Keep the routine simple; avoid adding products without a reason.
4. **Reviewed ingredient/product knowledge**
   - Intended use, evidence strength, exclusions, compatibility, risks, review status, source and last-checked date.
   - Local price and availability only when supported by dated sources; otherwise label as unknown or an estimate.
5. **Routine plan and follow-up**
   - Clear order, amount/frequency where supported, realistic timelines, stop conditions, alternatives and cost.
   - Material routine changes require user confirmation.
6. **Private progress timeline**
   - Device-local photo history, original photo access, dated sessions, user notes, adherence and tolerance.
   - Manual side-by-side comparison is allowed; no claim of measured improvement unless the relevant measurement passes validation.
7. **Privacy controls**
   - Device-only storage by default, explain Android backup behavior, data export/deletion where feasible, optional account only if needed.
   - No photo upload or model-training use by default.
8. **Safe knowledge updates**
   - Versioned reviewed content with integrity checks and last-updated status.
   - Background update timing is best-effort, not guaranteed instant. Material routine changes are explained and confirmed.

### B. Image-analysis candidates (gated; not promised at launch)

1. Capture-quality checks for blur, exposure and framing.
2. Face alignment / repeatable region guidance.
3. One narrowly defined visible pigmentation/dark-mark change measurement.

Each candidate must pass its own pre-defined validation gate across relevant skin tones, devices, lighting and intended regions. Until it passes, show only simple capture guidance or manual comparison. A model confidence value is not evidence of accuracy.

### C. Not in the first release

- Selfie-based diagnosis or clinical severity grading.
- Beauty, attractiveness, fairness or whitening scores.
- Automated claims about hydration, oil production, elasticity or skin health inferred from ordinary selfies.
- Automatic diagnosis or routine changes triggered solely by a photo.
- Free-form AI-generated skincare formulas.
- Unreviewed home-made creams, water-based formulas without appropriate preservation/stability/microbiology support, or risky chemical whitening methods.
- Automatic upload of photos, default cloud analysis, or model training on user photos.
- Product sales, paid recommendations or advertising until separately assessed.
- Public social feed, photo sharing or other features that increase exposure of sensitive photos without a clear need.

## 3. First-release concern list (proposed)

**Candidate for initial support, after content review:**
- Dryness and roughness.
- Basic cleansing, moisturization and sun-protection guidance.
- User-reported sensitivity and prior reactions as safety filters.
- Routine simplification and product/ingredient compatibility education.

**Research-only until further review:**
- Pigmentation, tanning and dark-mark progress measurement from photos.
- Acne-related routine support, limited to reviewed non-diagnostic education and appropriate referral rules.
- Any formula prepared at home.

**Do not claim in MVP:**
- That a selfie measures skin hydration, oil production, pores, elasticity or disease.
- That a routine will whiten skin or guarantee a specific outcome.
- That visible photo differences necessarily represent treatment success.

The exact concern scope needs review by appropriately qualified skincare/dermatology and formulation experts before the content is finalized.

## 4. Proposed first-run flow

1. Welcome and choose Bangla or English.
2. Plain-language explanation of purpose and limitations.
3. Choose a goal or browse basic guidance without creating an account.
4. Ask only the questionnaire items needed for the selected concern and safety checks.
5. Show the proposed routine, why each step is included, approximate cost/source freshness, uncertainty and alternatives.
6. User reviews and accepts the routine.
7. Offer optional photo tracking with a separate explanation of local storage and backup behavior; do not force a selfie.
8. Offer a follow-up schedule chosen by the user.
9. If a concern is outside scope or a warning sign is reported, do not force a routine; explain limitations and appropriate next steps.

## 5. Proposed routine-change rules

- Never change a material part of the plan based only on one image.
- Consider adherence, user-reported tolerance, new symptoms, product availability and validated measurements together.
- Ask for confirmation before material changes.
- If a safety concern appears, clearly explain what to stop/avoid according to reviewed guidance and when to seek professional help.
- If approved knowledge becomes unsafe or is withdrawn, disable the affected recommendation and show a safety notice; do not continue recommending it merely because the user has not confirmed a replacement.

## 6. Release acceptance gates

The MVP cannot be approved until:
- actionable content has documented sources, eligibility rules, exclusions and review status;
- the privacy/data-flow design has received qualified review against applicable Bangladesh requirements;
- Bangla and English safety-critical text has been reviewed;
- photo storage, Android backup behavior, deletion and permissions are tested;
- image features that do not pass validation remain disabled;
- price and availability claims have dated provenance or are clearly labelled uncertain;
- no unresolved critical safety defect remains;
- all user-facing claims match the evidence and the app's actual capabilities.

## 7. Decisions requested

Review and decide:
1. Is this proposed core MVP acceptable, or should any core feature be removed/added?
2. Should the first release remain adult-only until requirements for minors are reviewed?
3. Should the first release omit all automated skin-condition analysis and launch first with capture checks/manual progress, pending model validation?
4. Should any DIY formula content be excluded entirely from the first release until a qualified formulation review process exists?
5. Is the five-area navigation proposal (Skin Scan, My Routine, Progress, Ingredients and Alternatives, Profile and Privacy) still the preferred structure?

No decision is assumed approved by this document. Record final choices in `docs/DECISIONS.md`. No implementation begins until the user explicitly approves the specification, safety boundaries, validation plan, UX and architecture.

## 8. References for linked project constraints

- [AI model feasibility assessment](AI_MODEL_FEASIBILITY.md)
- [Measurement validation plan](MEASUREMENT_VALIDATION_PLAN.md)
- [Safety and privacy requirements](SAFETY_AND_PRIVACY.md)
- [Bangladesh compliance review](BANGLADESH_COMPLIANCE_REVIEW.md)
- [Product specification](PRODUCT_SPEC.md)

This is a product-scope proposal, not medical, regulatory or legal advice.
