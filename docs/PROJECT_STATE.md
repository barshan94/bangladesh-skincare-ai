# Project State — 2026-11-17

## Repository and CI

- Repository: https://github.com/barshan94/bangladesh-skincare-ai
- Active branch: `implementation/m0-android-baseline`
- Draft PR: [#1](https://github.com/barshan94/bangladesh-skincare-ai/pull/1), open and unmerged.
- Latest branch head before this documentation update: `4fc6e8992e50f8a1fc78a52a31bcef56d3c1aa2d`.
- Latest full CI: [run #300](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44106499330), **success**.
- Workflow job: Compile debug APK.
- Uploaded artifacts: debug APK, test reports and Room schema.

## Implemented app

Five Compose sections:
1. Skin Scan — self-reported questionnaire and deterministic general guidance; no camera, image analysis, diagnosis or AI model.
2. My Routine — local daily checklist.
3. Progress — local text notes/check-ins with edit and confirmed delete.
4. Ingredients — 11 educational entries, search, risk filters and publisher links; not professionally reviewed and not a treatment recommendation.
5. Profile — English/Bangla preference, optional budget preference and local data reset.

Other implemented controls:
- Room-backed Routine and Progress.
- One-time/idempotent legacy SharedPreferences import.
- Daily routine completion identifiers.
- Local data reset with failure handling and regression coverage.
- English/Bangla resources and automated parity validation.
- Accessibility semantics for Routine and Skin Scan checkbox rows.
- Android backup disabled in manifest; no network permission, account, analytics, cloud sync or photo upload.

## What “finished” means for this personal-use scope

The app's current software scope is implemented and the latest full CI run passed. No expert is required just to install/use a local tracker. The ingredient catalogue is explicitly educational, unreviewed and non-prescriptive; it must not be presented as medical advice. Qualified review becomes necessary before adding diagnosis, individualized treatment plans, strong active-ingredient protocols, home formulations or public/commercial health claims.

## Owner-side checks still required

These cannot be truthfully completed from GitHub or emulator CI:
1. Install and smoke-test all five sections on the owner's Android phone.
2. If upgrading an older installation with saved legacy preferences, verify one-time import, restart persistence and no duplicate records without clearing app storage.
3. Verify Profile reset removes Routine/Progress but retains language preference.
4. Check TalkBack, larger font, contrast, touch targets and Bangla/English overflow.
5. Confirm offline launch and no unexpected permission prompts.
6. For a stable signed private APK, use an owner-controlled signing key stored outside the repository; verify signature, package/version and artifact hash.

## Release distinction

- CI debug APK: usable for controlled testing and personal evaluation, but not a signed release artifact. A later CI debug build may have a different debug signing key.
- Signed private APK: requires owner-controlled signing credentials and install/upgrade verification.
- Public/commercial distribution or clinical functionality: outside current scope and requires separate safety, privacy and legal review.

## Handoff

Continue by helping the owner install and test the CI APK on their Android phone. Never claim physical-device tests passed until the owner reports the observed results. Keep PR #1 draft/unmerged until the owner has reviewed the implementation and the intended release path is clear.
