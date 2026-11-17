# Safety Case — Personal-use Educational Tracker

- Version: 1.1
- Date: 2026-11-17
- Status: Scope-limited; not a clinical validation claim.

## Top-level claim

The current app is a local record-keeping and general-education tool. It does not capture or analyze skin photos, diagnose disease, prescribe treatment, score beauty/fairness or provide DIY formulas. This claim is supported by the current feature scope and source implementation; it is not a claim that the app is clinically validated.

## Hazard controls

| Hazard | Current control | Remaining limitation |
|---|---|---|
| User mistakes guidance for diagnosis | Explicit non-diagnostic wording; deterministic general guidance | User must not rely on app for clinical assessment |
| Irritation from experimentation | Stop-use cautions; no DIY formula or mixing instructions | Ingredient summaries are not professionally reviewed |
| Unsafe whitening practice | Warnings against lemon juice, bleach, unknown creams and homemade mixtures | No product-level screening or local-market verification |
| Misleading improvement claim | Notes are user-authored; app does not infer improvement | No validated photo comparison |
| Privacy exposure | Local Room storage; no network permission/account/analytics/photo upload; backup disabled | Physical-device/OS backup behavior still needs checking |
| Data loss or incomplete reset | Reset error handling and automated regression coverage | Owner should verify on their phone |
| Accessibility barrier | Automated checkbox semantics coverage | TalkBack and large-font behavior need phone check |

## Release-stop conditions

Do not expand the current app into diagnosis, image scoring, personalized treatment, strong active-ingredient protocols, home formulations or clinical claims without appropriate qualified review and validation. Do not distribute publicly or commercially without separate privacy/legal/safety review.

## Evidence still required from owner device

Install and smoke-test the current APK, verify local persistence, edit/delete, reset, offline behavior, Bangla/English layout, TalkBack and larger font behavior. Emulator CI is not a substitute for these checks.

## Review boundary

No professional review is required merely to use the current app as a personal tracker within this limited scope. This safety case must not be reused to claim clinical efficacy, regulatory compliance or professional approval.
