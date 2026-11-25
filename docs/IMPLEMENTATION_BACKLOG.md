# Implementation Backlog — Current Status

- Last reviewed: 2026-11-25
- Repository: `barshan94/bangladesh-skincare-ai`
- Active branch: `feature/brightening-system`
- Active PR: [#2](https://github.com/barshan94/bangladesh-skincare-ai/pull/2), open, draft and unmerged.
- Latest app-code commit: `d46aea6f409aa602b9bfacf0f3443ea4296450ec`.
- Latest full CI: [#45566453626](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/45566453626), success. Compile, unit tests, Room schema, emulator navigation tests and artifact uploads all passed.

## Implemented for current personal-use scope

- Six Android destinations: Skin Scan, My Routine, Progress, Ingredients, Profile and Whitening & Brightening.
- Local Room-backed routine/progress data and legacy preference migration.
- Rule-based, non-diagnostic check-ins with skin feel, prior reaction, broken skin, concerning changes, sunscreen habits, multiple strong actives and prescribed-treatment safety prompts.
- Dedicated brightening concern education for tanning, post-acne marks and unexplained/persistent patches; patchy concerns trigger professional assessment guidance.
- Private selfie capture, ML Kit one-face/framing checks, app-private storage, local timeline, deletion and visual side-by-side comparison. This is not skin analysis.
- Hardened selfie storage: staged writes, metadata validation, synchronous commits, checked deletion, maximum 30-photo retention and monotonic timestamps.
- Full local reset clears private selfie data and saved formula draft.
- Ingredient education, sources, search/filtering and bilingual limited red-flag label keyword checks for mercury, hydroquinone and topical steroids. A no-match is not a safety clearance.
- Homemade Lab arithmetic for anhydrous balm only: batch size, percentage-to-gram calculations, estimated BDT raw-material cost and local save/load/delete of one draft.
- Water-based products, creams/lotions and sunscreen are blocked from home formulation. Educational guidance explains identity/grade, compatibility, pH, validated preservation, stability, packaging and microbial testing.
- English/Bangla strings and automated localization parity, unit and navigation regression coverage.

## Explicitly deferred by owner

**Selfie-based skin analysis is deferred for now.** Do not implement face pigmentation scores, AI diagnosis or unvalidated image heuristics as a substitute. It can be reconsidered only after data rights, expert labels, representative Bangladeshi skin-tone coverage, device/lighting evaluation, repeatability/accuracy criteria, on-device privacy design and validation are satisfied.

## Remaining release gates (not code tasks that can be truthfully completed remotely)

1. **Physical-device acceptance:** owner must install and test the debug APK on the target Android phone: camera capture, timeline, delete, full reset, offline mode, TalkBack, font scaling, Bangla layout and upgrade behavior. Emulator CI is not a substitute.
2. **Signed private APK (optional):** requires an owner-controlled signing key. Never request or store the key/password in chat or commit them to GitHub.
3. **Qualified content review:** ingredient catalogue and formulation education are general education and pending expert review. No validated formula recipes, concentration recommendations, shelf-life claims or safety approvals are supplied.

## Safety boundary

The app addresses uneven tone and excess pigmentation safely, not genetically determined natural skin colour. No mercury, bleach, steroid misuse, harsh kitchen remedies, unsupervised prescription lighteners, diagnosis or fairness scoring. No homemade sunscreen or unpreserved water-based DIY products. A patch test does not make an unsafe or contaminated formula safe.

## Definition of done for this phase

The implemented personal-use software scope is CI-green. Selfie analysis is explicitly deferred. The app is **not yet owner-accepted or a signed release** until physical-device checks and, if desired, owner-controlled signing are completed. Keep PR #2 draft and unmerged pending owner review.
