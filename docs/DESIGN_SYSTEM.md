# Visual Design System — Draft 1.0

- Date: 2026-10-29
- Status: Proposed implementation-ready visual direction; validate with adult Bangladeshi users before release.
- Scope: Android app UI, not marketing/brand identity.
- Principle: Calm, trustworthy, practical skincare utility. Avoid luxury/whitening tropes or beauty scoring.

## 1. Visual direction
- Light warm-neutral surfaces with muted botanical accent and high-contrast dark text.
- Use a small, semantic colour palette: primary, secondary, background, surface, text, muted text, success, warning, error and outline.
- Status must never rely on colour alone; pair colour with icon and text.
- Avoid bright “fairness/whitening” visual motifs, skin-tone ranking gradients, before/after exaggeration and medical-looking badges that imply diagnosis.
- Dark theme may be supported, but safety warnings and image comparisons must remain legible.

## 2. Typography
- Use system-supported typefaces with reliable Bangla glyph coverage.
- Respect Android font scaling and dynamic type.
- Use clear hierarchy: screen title, section heading, body, helper, metadata.
- Avoid long all-caps labels and tiny low-contrast captions.
- Do not use fixed-height containers that clip Bangla text.

## 3. Layout and navigation
- Five areas: Skin Scan, My Routine, Progress, Ingredients and Alternatives, Profile and Privacy.
- Use Android-native bottom navigation for these five top-level destinations when it remains clear at supported screen sizes; labels and icons both present.
- Primary action should be visually obvious; secondary actions should not compete.
- Prefer single-column layouts and cards with clear grouping over dense dashboards.
- Use consistent spacing scale and reusable components.
- Keep critical safety notices close to the relevant action, not buried in settings.
- Back navigation must preserve safe state without silently committing a routine change.

## 4. Components
- Buttons: primary, secondary, text and destructive variants with clear disabled/loading states.
- Inputs: visible labels, helper text, validation message and accessible error association.
- Cards: one purpose each; avoid presenting recommendation scores as medical authority.
- Chips: use for filters or user-selected values, not as unsupported diagnosis tags.
- Progress indicator: show questionnaire progress without shaming or implying clinical certainty.
- Photo comparison: show dates, original toggle/side-by-side, comparability notice and measurement uncertainty if enabled.
- Status component: icon + text + colour for ready/retake/inconclusive/disabled.
- Consent controls: clear separate choices, no preselected optional cloud/training permissions.
- Empty/error states: explain cause and next action.

## 5. Photo presentation
- Keep the original visible and unaltered for tracking.
- Do not apply beautifying filters or auto-retouching.
- Any overlays must be clearly identified as guidance/region masks and removable.
- Comparison requires date labels and comparability status.
- No progress percentage unless the exact metric has passed validation.
- Avoid framing skin colour as a score or defect.

## 6. Content and microcopy
- Plain Bangla/English, supportive and non-judgmental.
- Prefer “not enough information to compare” over “failed.”
- Prefer “not currently supported” over false certainty.
- Explain why a permission or sensitive question is requested.
- Never promise instant, permanent or guaranteed whitening/results.
- Use consistent terms for consent, local storage, cloud processing, retake and inconclusive.

## 7. Accessibility
- Touch targets and spacing suitable for varied dexterity.
- Text scaling, screen-reader semantics, focus order and sufficient contrast.
- Never encode meaning through colour alone.
- Long translations, large font, screen reader, dark mode and narrow-screen layouts must be tested.
- Animation is optional and must not be required to understand a status.

## 8. Motion and feedback
- Short purposeful transitions only.
- Respect system animation preferences where possible.
- Use progress/loading states for real work, never fake activity.
- Confirm destructive actions; explain whether data was actually deleted.
- Never show success before storage, deletion or update operation confirms success.

## 9. UX validation
Before release, test onboarding, questionnaire, scan, routine, reaction, privacy/deletion and language switching with adult Bangladeshi users. Record issues, severity, fix and retest. This draft is not a substitute for usability testing.