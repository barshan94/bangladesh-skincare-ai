# Safety and Privacy — Personal-use Android App

**Last reviewed:** 2026-11-25  
**Scope:** Offline-first personal skincare companion; not a medical device or clinical record system.

## Safety boundary

- Skin Scan and Whitening & Brightening use self-reported inputs and deterministic general education. They do not diagnose or prescribe.
- Brightening guidance is not a guarantee of fading pigmentation or changing natural skin colour.
- Selfie capture uses face detection only for basic one-face/framing checks. The app does not analyse acne, pigmentation, melasma, dark spots, skin tone or disease. Side-by-side photo comparison is visual-only and can be misleading when lighting, distance, angle or camera exposure differs.
- Ingredient entries are educational and pending qualified expert review. Sources do not approve a finished product, concentration or formula.
- Ingredient-label keyword checking covers a limited set of names. No match does not mean a product is safe, authentic or free from undeclared ingredients.
- Homemade Lab is an arithmetic worksheet for anhydrous-balm drafts. It is not a recipe generator, compatibility checker, preservative/stability assessment, concentration-limit checker or safety approval.
- Water-based DIY products, creams/lotions and sunscreen are blocked. Do not make sunscreen at home or rely on unpreserved water-based mixtures.
- Never use mercury, household bleach, steroid misuse, harsh kitchen remedies or unsupervised prescription lighteners for skin whitening. Do not apply concentrated raw ingredients or unknown mixtures.
- Stop a product that causes a reaction. Seek qualified medical assessment for persistent, unexplained, changing, spreading, painful, bleeding or severe skin concerns.

## Privacy implementation

- Routine and Progress records use local Room storage.
- Formula drafts and preferences use app-private local storage.
- Selfies remain in app-private storage with local metadata; users can delete individual photos or use full local reset.
- No account, analytics, cloud sync or photo upload is implemented.
- The app is designed to work offline.
- OS backup/data-extraction, upgrade and reset behavior still require physical-device verification.

## User guidance

- This app is not a clinical record. Avoid entering sensitive medical details.
- Do not interpret photo comparisons as objective skin measurement.
- Use finished products according to their labels. Introduce one new cosmetic product at a time and stop if it causes irritation.
- Do not stop, replace or change clinician-prescribed treatment based on app guidance.
- A patch test cannot make an unsafe, contaminated or poorly formulated product safe.

## Deferred scope

Selfie-based skin analysis is deferred by the owner. No AI diagnosis or image-based pigmentation score is implemented. Any future analysis, personalized treatment, high-strength active protocol, actionable DIY formulation, cloud storage or public/commercial distribution requires separate validation, privacy, qualified safety and legal assessment.

## Incident handling

If guidance appears unsafe, stop following it and record the exact screen/text for review. Do not share private skin photos publicly. The current build has no remote content update or remote kill switch.
