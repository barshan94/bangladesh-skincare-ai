# Bangladesh Compliance Review — Initial Checklist

**Status:** Initial desk research; requires qualified legal/privacy review  
**Last checked:** 2026-10-29  
**Scope:** Planned Android skincare companion; no implementation or public release approved

## Why this document exists

The product may process identifiable face/body photos, questionnaire responses about skin and health, and recommendations about cosmetic ingredients or home-prepared products. Privacy and cosmetic claims therefore need review before the app's data model, claims, consent flow or formula catalogue is finalized.

This checklist is not legal advice and does not determine the app's precise legal classification. Applicability can depend on the product's functionality, the claims it makes, the entities operating it, data flows and later regulatory guidance.

## 1. Primary Bangladesh sources found

### Personal Data Protection Act, 2026

Official Laws of Bangladesh text:
https://bdlaws.minlaw.gov.bd/act-details-1692.html?lang=en

The official text identifies the Personal Data Protection Act, 2026 (Act No. 63 of 2026), dated 10 April 2026. It defines personal data broadly, includes health-related data in sensitive personal data, and addresses consent, purpose limitation and technical/organizational safeguards. The exact obligations, exceptions, commencement provisions, rules and applicability must be checked by qualified counsel against the authoritative Bangla text and current implementing instruments.

**Product implications to design for now:**
- Treat identifiable face/body photos and skin/health questionnaire responses as sensitive by default, pending legal advice on their exact classification.
- State purposes clearly before collection; collect only data needed for the selected feature.
- Keep cloud storage, cloud inference and model-training consent separate from basic local functionality.
- Use device-only storage by default and do not send raw images to analytics or crash-reporting services.
- Document retention and deletion for originals, thumbnails, derived measurements, exports, logs and any cloud copies.
- Use appropriate technical/organizational controls, including encryption where applicable, access controls, secure transport and incident handling.
- Explain whether Android system backup can copy local app data and design backup behavior deliberately.
- Provide clear controls to withdraw optional consent and delete data, subject to legally reviewed exceptions.
- Review any third-party SDK, AI service, cloud provider or cross-border transfer before adoption.
- Establish an owner and documented process for privacy requests, incidents and updates to the privacy notice.

### Drugs and Cosmetics Act, 2023

Official Laws of Bangladesh text:
https://bdlaws.minlaw.gov.bd/act-print-1447.html

The Act addresses manufacture, import, sale, storage, distribution and quality control of drugs and cosmetics. Section 71 includes controls on certain drug-use/treatment claims in advertising and prohibits false or untrue claims about cosmetic use or results.

**Product implications to investigate:**
- Have qualified counsel determine which app features and claims may be regulated and what approvals, licences or restrictions apply.
- Do not publish misleading promises, guaranteed outcomes or unsupported cosmetic efficacy claims.
- Distinguish general educational content from diagnosis, treatment advice, product marketing or regulated claims.
- Review the status and requirements for any DIY formula guidance, ingredient concentrations, home preparation, packaging or storage advice.
- Do not assume a product is compliant because an ingredient is sold locally or because a formula is described as “natural.”
- Keep source, evidence, reviewer, version and approval status for every actionable recommendation.
- Review any future paid placement, affiliate link, product listing or sponsored recommendation separately.
- Define an escalation and correction process for unsafe or misleading content.

Official Directorate General of Drug Administration (DGDA) overview:
https://dgda.portal.gov.bd/site/page/89391a5c-e203-49a0-9c30-39f23d85e045/www.dgda.gov.bd

## 2. Required expert questions before launch

### Privacy and data protection
- Does the Act apply to the developer/operator and each planned processing activity?
- What is the correct legal basis and consent standard for photos, skin observations and health-related questionnaire data?
- Are any separate rules, regulations, notices, registrations or designated responsibilities required?
- What are the applicable rights, response deadlines, breach obligations and retention/deletion duties?
- How should Android backups, exports, shared devices and deletion from cloud backups be handled?
- What restrictions apply to third-party processors, international hosting and cross-border transfers?
- Are minors allowed? If so, what age assurance, guardian consent and additional protections are required? Prefer an adult-only pilot until this is resolved.
- What records must be maintained to demonstrate compliance?

### Cosmetics, medical claims and product recommendations
- Does the intended feature set remain general cosmetic/wellness guidance, or could any claims be considered diagnosis, prevention or treatment?
- What approvals or restrictions apply to claims, advertising, product recommendations, DIY formulas and any future commerce?
- Who is qualified to review formulation safety and cosmetic claims?
- What local rules apply to ingredients, concentrations, manufacturing, packaging, preservation, labelling and sale if the project ever distributes a product rather than merely providing educational guidance?
- What disclaimer and referral language is appropriate, without using a disclaimer to excuse an otherwise regulated or unsafe feature?

### AI and consumer transparency
- What disclosures are needed to explain that photo observations are estimates, not diagnoses?
- What records are needed to substantiate claims about model accuracy, fairness, reliability and product benefit?
- What process is required to correct or disable a faulty model or harmful recommendation?
- What rules apply to any third-party AI provider or processing outside Bangladesh?

## 3. Conservative release position pending review

1. No public release or commercial claims until applicability is reviewed.
2. Keep original photos and history on-device by default.
3. No automatic upload of images, no model-training use by default and no unnecessary analytics.
4. Do not offer diagnosis, disease severity grading, or treatment claims based on a selfie.
5. Do not provide unreviewed DIY formulas or unsupported efficacy promises.
6. Keep cosmetic recommendations evidence-based, versioned and reviewed by an appropriate person.
7. Make consent, privacy notices, data deletion and contact/escalation flows available in Bangla and English.
8. Keep a documented process to disable affected recommendations if legal guidance or safety evidence changes.

## 4. Approval checklist

- [ ] Qualified Bangladesh legal review completed against the authoritative current texts and applicable rules.
- [ ] Product classification and allowed claims documented.
- [ ] Privacy/data-flow map and retention schedule reviewed.
- [ ] Consent, access, deletion, backup and incident processes reviewed.
- [ ] Third-party services, hosting region and cross-border data flows reviewed.
- [ ] Formula and ingredient content review responsibilities established.
- [ ] Bangla and English privacy/safety copy reviewed.
- [ ] Release blockers and remediation owner recorded.

**Release gate:** Unresolved legal applicability or a critical privacy/safety issue blocks the affected feature. Reduce scope rather than assume compliance.

## Sources

1. Laws of Bangladesh, Personal Data Protection Act, 2026: https://bdlaws.minlaw.gov.bd/act-details-1692.html?lang=en
2. Laws of Bangladesh, Drugs and Cosmetics Act, 2023: https://bdlaws.minlaw.gov.bd/act-print-1447.html
3. Directorate General of Drug Administration, legal basis overview: https://dgda.portal.gov.bd/site/page/89391a5c-e203-49a0-9c30-39f23d85e045/www.dgda.gov.bd

This document is an initial checklist, not a legal opinion, compliance certification or substitute for professional review.
