# Safety and Privacy Requirements

**Status:** Baseline requirements for review; implementation not started.
**Last updated:** 2026-10-29

## 1. Safety principles

- Safety and evidence outrank cost, convenience, feature count and schedule.
- No critical safety defect may be waived to meet a milestone.
- AI must not invent formulas, override safety rules, or present unsupported claims as facts.
- Do not diagnose skin disease from selfies.
- Do not frame natural darker skin as a defect or provide fairness/beauty scores.
- Do not promise guaranteed whitening, instant results, permanent fairness or guaranteed improvement.
- “Natural” does not mean safe; synthetic does not mean toxic.
- New, changing, painful, bleeding or otherwise concerning lesions require appropriate professional evaluation.
- A single new pimple or image observation must not automatically change the whole routine.
- Unsupported or uncertain concerns must be acknowledged, not guessed at.

## 2. Excluded unsafe practices

Do not recommend mercury or undisclosed corticosteroids in whitening products, household bleach, improvised chemical peels, unsafe high-strength actives, or other unreviewed hazardous practices. Higher-risk ingredients such as hydroquinone require careful evidence, professional/regulatory handling and appropriate restrictions; never recommend unrestricted DIY use.

## 3. Home-prepared formulas

An actionable home formula requires appropriate evidence and formulation/safety review. Documentation must cover ingredient identity/specification, exact composition, preparation, packaging, storage, use-by limits and relevant stability/microbiology controls.

Do not provide free-form AI-generated formulas. Do not recommend water-based DIY creams for storage without suitable preservation and stability/microbiology support. If no validated option exists, explain that and offer an appropriate ready-made alternative or no substitute.

## 4. Recommendation controls

Every recommendation must pass explicit eligibility and contraindication rules. Rules are independent of generative AI and cannot be overridden by AI-generated explanations.

Recommendations should provide:
- Why the option is suitable and the evidence/review status.
- Exact product/formula details only where verified and appropriate.
- Use amount, frequency, order and realistic expected timeline.
- What to avoid, relevant interactions, irritation risks and stop conditions.
- Safe alternatives, if available.
- Initial and monthly cost estimates in BDT, with source, last-checked date and uncertainty.
- Knowledge-base version and review provenance.

Do not invent local availability or exact prices.

## 5. Photo privacy

- Original photos and progress history are stored on-device by default.
- Request only permissions needed for the user's chosen action; prefer Android camera/photo-picker flows and minimum access.
- On-device inference is preferred if it meets validation and performance requirements.
- Cloud photo storage and cloud analysis are separate optional choices, each requiring clear disclosure and affirmative consent.
- Use of photos for model training requires separate, informed consent and must not be bundled with core use.
- Do not send raw photos to analytics or crash reporting.
- Explain retention, access, deletion, cloud copies and Android backup behavior.
- Define “device-only” precisely: operating-system/user backup and device transfer behavior must be documented and tested.
- Basic functionality should work without an account where feasible.
- Never silently change privacy settings during knowledge updates.

## 6. Data minimization and security

- Collect only data needed for a stated feature or safety decision.
- Ask sensitive questions only when relevant; explain why they are needed.
- Protect local records using appropriate Android storage and platform security mechanisms.
- Avoid secrets in source control. Never commit credentials, signing keys, private photos, identifiable test datasets or personal health records.
- Document data flows, retention, deletion, backup and access control before any cloud feature is implemented.
- Test deletion of local data and any cloud copies, including account deletion if accounts are added.
- Avoid collecting personal data for analytics by default.

## 7. Knowledge updates and urgent safety changes

- Accept only integrity-verified, approved, compatible, versioned knowledge.
- Invalid, corrupt, unapproved or incompatible content must never activate.
- Ordinary approved general content may update automatically within the documented policy.
- Material changes to an active routine require explanation and user confirmation.
- Critical safety updates must be prominent. If a current recommendation becomes unsafe, disable or warn against the affected recommendation according to reviewed policy; do not require the user to accept a replacement before seeing the warning.
- Log version and update outcome; provide safe rollback where possible.
- Sync must not authorize photo upload or alter privacy choices.

## 8. User-facing escalation

The app is not a substitute for a clinician. It should avoid diagnostic language and provide clear professional-care guidance for concerning symptoms or findings. Exact triage wording and any emergency guidance must be reviewed by qualified professionals and aligned with current local guidance before release.

## 9. Safety acceptance criteria

Before release:
- [ ] Every recommendation passes eligibility and contraindication rules.
- [ ] Every actionable home formula has documented appropriate review.
- [ ] Unsafe ingredients/practices are blocked with explanation and safer options where possible.
- [ ] Every material recommendation is traceable to approved sources.
- [ ] Natural dark skin is not treated as a defect.
- [ ] Incomparable photos cannot generate confident progress claims.
- [ ] Image features are evaluated across relevant skin tones, devices and lighting.
- [ ] Material plan changes require confirmation; urgent warnings follow approved policy.
- [ ] Device-only default is verified; cloud storage, cloud analysis and training consent are separate.
- [ ] Corrupt/unapproved/incompatible knowledge cannot activate.
- [ ] AI/sync failure cannot bypass safety rules.
- [ ] Prices and availability show provenance/freshness or are explicitly unknown.
- [ ] Critical Bangla and English safety flows are reviewed.
- [ ] Local data deletion and any cloud deletion are tested.
- [ ] Current Bangladesh legal/regulatory requirements have been researched and reviewed by suitable expertise.
- [ ] No critical safety defect remains unresolved.

## 10. Regulatory and professional review

Applicable Bangladesh requirements for cosmetic claims, ingredients, privacy, consumer protection and distribution must be researched using current authoritative sources before release. Do not infer legal compliance from this document. Assign qualified reviewers for safety, formulation and regulatory questions.

## 11. Incident handling

Before pilot, define how users report irritation/adverse events, how reports are reviewed, how unsafe content/features can be disabled, who owns incident response, and how affected users are notified. Minimize collection of sensitive details in reports and document retention/access controls.
