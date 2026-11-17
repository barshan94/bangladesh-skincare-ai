# Private Android APK Release Runbook

- Last reviewed: 2026-11-17
- Status: Procedure only. **Not yet cleared for release.**

## Current build status

Latest full CI [#295](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44090947039) passed and uploaded a debug APK. The debug artifact is for controlled testing only; it is not a signed private release.

## Release prerequisites

Do not start signing/distribution until all conditions are true:

- Physical upgrade from an earlier installed build with populated legacy SharedPreferences data passes; import is one-time and restart persistence is confirmed.
- Physical reset clears Routine/Progress and legacy snapshots while retaining language preference.
- Android backup/data-extraction behavior has been checked on supported devices.
- TalkBack traversal, font scaling, contrast, touch targets and English/Bangla layouts have been reviewed on real devices.
- At least one lower-memory and one current Android device pass smoke tests.
- All 11 ingredient entries and sources receive qualified skincare/dermatology review; rejected or uncertain content is corrected or removed.
- Product owner approves release scope and release notes.
- No critical/high unresolved defects; dependency/secret/permission review is complete.

## Owner-controlled signing

1. Generate or select an Android upload/release keystore on an owner-controlled secure device.
2. Back up the keystore and recovery information securely. Never send the keystore or passwords in chat.
3. Never commit keystore files, signing passwords or private keys to GitHub.
4. Configure signing secrets only in the owner's protected build environment; prefer local release signing if CI secret handling has not been reviewed.
5. Build the release variant from a clean, reviewed commit using the pinned toolchain.
6. Verify application ID, version code/name, signature certificate fingerprint, permissions, network behavior and artifact SHA-256.
7. Install the signed APK cleanly and upgrade from the prior supported signed version; test local migration, reset and offline behavior.
8. Record source commit, build environment, signer fingerprint, artifact hash, test evidence and approval date. Do not publish the keystore or its passwords.

## Release record

Record: app version, source commit, CI run, artifact hash, signing certificate fingerprint, supported Android versions, review approvals, known limitations, migration behavior, backup caveats, support/incident contact and rollback plan.

## Rollback and incident response

Retain the prior signed APK and its hash. Do not roll back to content known to contain a safety issue. If a content defect is found, withdraw/correct it with qualified review. For suspected privacy exposure, stop affected distribution, preserve only necessary evidence and follow the approved legal/privacy incident process.

## Current decision

**Not ready for signed private release.** CI success is necessary but does not replace physical-device testing, professional content review or signing verification.
