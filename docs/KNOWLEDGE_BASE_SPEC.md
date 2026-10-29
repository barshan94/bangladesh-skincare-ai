# Skincare Knowledge Base and Recommendation Rules

- Version: 1.0
- Date: 2026-10-29
- Status: Governance specification; no formula or clinical content is approved by this document.

## Non-negotiable rules
- The knowledge base is the only source of actionable routine instructions.
- Generative AI may explain approved entries but cannot create combinations, concentrations, contraindications, formulas or treatment steps.
- Safety/eligibility filters run before cost/benefit ranking.
- Missing, stale, contradictory or unreviewed evidence means no recommendation.
- Low price, “natural,” popularity or availability never overrides safety.
- Never promise whitening, permanent fairness, instant results or guaranteed outcomes.

## Required fields
Each entry includes stable ID/version, type, canonical name and reviewed aliases/translations, intended use, supported concern/area and non-indications, evidence source/title/publisher/date/URL/access date, evidence strength and limitations, ingredient identity/formulation constraints, contraindications/exclusions/interactions/irritation risks, reviewed instructions, stop conditions, review status, reviewer role/qualification, review and next-review dates, change history, local price/availability source/date/uncertainty and release version.

## Status lifecycle
Draft → evidence review → safety review → approved → published.
Any approved item may become withdrawn, expired or superseded. Only approved/published entries are actionable. Every status change records reason and reviewer; safety withdrawal propagates to affected clients where supported.

## Evidence review hierarchy
1. Relevant clinical guidelines, regulator notices and systematic reviews.
2. Well-designed studies applicable to the specific ingredient/formulation/use.
3. Reliable safety/pharmacology references and authoritative professional sources.
4. Manufacturer material for identity/directions, not independent proof of efficacy.
5. Qualified expert consensus for narrow questions when stronger evidence is absent, labelled as such.
Anecdotes and marketing claims are insufficient. Evidence for a pure ingredient does not automatically apply to every product containing it.

## Deterministic recommendation pipeline
1. Validate inputs/questionnaire version.
2. Determine supported concern and intended user.
3. Apply hard exclusions/contraindications.
4. Remove unreviewed, expired, withdrawn, incompatible or unsupported entries.
5. Rank remaining options by documented relevance, evidence, realistic benefit, affordability, verified availability, simplicity and likely adherence.
6. Compose routine only from approved structured instructions.
7. Explain rationale, uncertainty, costs/source dates, exclusions and stop conditions.
8. Require confirmation for material changes.
9. Record routine version and user acceptance/decline.
If no eligible candidates remain, state that no safe personalized recommendation is available.

## Local costs and availability
Currency is BDT. Price records include source, pack size/quantity, date, market scope and whether observed or estimated. Convert unit prices only with known quantities. Do not infer Bangladesh availability from global catalogues. Stale data is labelled not recently checked. Compare formulation, quantity, instructions and total cost. No paid placement in the initial plan without disclosure and conflict-of-interest assessment.

## DIY formula governance
Every formula needs an individual dossier: intended use/target user, exact ingredients/specifications/amounts, batch size, validated preparation and hygiene, critical controls such as pH where relevant, packaging compatibility, storage/use-by evidence, stability/preservation/microbiology evidence appropriate to risk, contamination/misuse analysis, contraindications, stop conditions, adverse-event pathway, qualified reviewer sign-off and Bangladesh legal/regulatory assessment.

A formula lacking required evidence remains unavailable. No stored water-based cream without appropriate preservation/stability/microbiology support. No kitchen substitution merely because it is cheap or “natural.” No AI-generated improvisation. No reviewer should solely self-approve a high-risk formula they authored.

## Content review workflow
1. Author drafts intended use and evidence references.
2. Evidence reviewer checks sources and applicability.
3. Qualified safety reviewer checks exclusions, risks and instructions.
4. Formulation specialist reviews DIY dossier.
5. Bangladesh legal/privacy reviewer checks applicable claims/regulatory implications.
6. Resolve critical disagreement with independent review.
7. Record decision, reviewer, date, version and next review.
8. Publish in integrity-protected knowledge release.
9. Monitor corrections/adverse reports/source changes.
10. Exercise withdrawal/rollback process.

## Expiry and updates
Every entry has a review-by date or reason no fixed expiry applies. Prices/availability expire quickly according to source freshness policy. Safety content has a stricter reviewer-defined cadence. Never activate model-generated rewrites as reviewed content. Critical withdrawal disables affected recommendations safely.

## Initial knowledge release
Keep it small. Basic cleansing, moisturization, sun-protection education and routine-building may be included only after qualified content review. No formula, concentration, treatment claim or local price becomes real app content until sourced and reviewed. An empty catalogue is safer than fabricated content.