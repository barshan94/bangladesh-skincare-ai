# Localization and Accessibility Specification

- Version: 1.0
- Date: 2026-10-29
- Status: Requirements; user testing and expert review pending.

## Languages and terminology
- Required: Bangla and English.
- Language can be changed from Profile and Privacy.
- User-facing strings live in localization resources, not scattered hard-coded literals.
- Preserve risk warnings and uncertainty across translations.
- Native-language users and skincare experts review Bangla terminology; avoid stigmatizing or diagnostic-sounding translations.
- Keep canonical ingredient/product identity with reviewed local-language explanation where useful.
- Format dates, numbers, units and BDT according to locale.
- Never infer ethnicity, skin tone or safety eligibility from language.

## Plain-language principles
Use short sentences and one primary action where practical. Explain sensitive questions. Distinguish unknown, not checked, inconclusive, unsupported and unsafe to recommend. Use neutral retake/skipped-routine language. Never blame users for irritation, adherence, budget or availability. Do not imply certainty through charts or colour.

## Accessibility requirements
- Support Android font scaling; avoid fixed-height text that clips translations.
- Provide semantic labels, roles, states and action descriptions.
- Maintain logical focus order.
- Use readable contrast, spacing and touch targets.
- Do not convey status by colour alone.
- Provide screen-reader descriptions for charts/photo comparisons/sliders.
- Provide text alternatives for visual guidance.
- Avoid flashing and unnecessary animation/time limits.
- Review dark mode without reducing safety-message visibility.
- Announce errors and move focus appropriately when feasible.

## Camera/photo accessibility
Instructions must not depend only on overlays or colour. Provide text explanations. Allow cancellation and non-camera alternatives where supported. Explain retake/inconclusive states. Photos are never required for routine/manual progress.

## Testing
Test critical flows in both languages, small screens, large fonts, screen readers, contrast, long names/warnings, and adult Bangladeshi users with varied literacy/accessibility needs. Track translation issues and safety-copy sign-off by version.