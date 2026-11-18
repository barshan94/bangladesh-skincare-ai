# Private Android APK Runbook

- Last reviewed: 2026-11-18
- Current workflow: [CI run #44221154087](https://github.com/barshan94/bangladesh-skincare-ai/actions/runs/44221154087) — success; 24 emulator instrumentation tests passed (0 failed, 0 skipped).

## Current artifact

The workflow uploaded `bangladesh-skincare-ai-debug`, `bangladesh-skincare-ai-test-reports` and Room schema. The emulator suite executed 24 tests with 0 failures and 0 skips. The debug APK can be installed for personal evaluation. It is not a signed release build. CI debug signing keys may differ between runs, so later debug APKs may not update an earlier installation in place. Back up important notes before replacing an installation.

## Phone acceptance checklist

On the owner's Android phone:

1. Install the APK from the `bangladesh-skincare-ai-debug` artifact on run #44221154087.
2. Open each section: Skin Scan, My Routine, Progress, Ingredients and Profile.
3. Create a routine tick and a Progress note; close/reopen the app and confirm they remain.
4. Edit and delete a Progress note; verify the confirmation and result.
5. Change language to Bangla and back to English; inspect text layout.
6. Use Profile reset; confirm routine/progress are removed and language remains.
7. Enable airplane mode and confirm the app still opens and local features work.
8. If available, enable TalkBack and increase system font size; check labels, focus order and clipping.

Do not clear app storage before testing migration from an older installation. If the phone already has an older version with data, test the upgrade path first.

## Stable signed private APK (optional next step)

1. Generate/select an Android keystore on an owner-controlled device.
2. Back up the keystore and recovery information securely.
3. Never share the keystore or passwords in chat or commit them to GitHub.
4. Configure signing only in a protected owner-controlled environment; local signing is acceptable.
5. Build a release variant from the reviewed source commit.
6. Verify application ID, version, signature certificate fingerprint, permissions and artifact SHA-256.
7. Install the signed APK cleanly and test upgrade from the previous signed build.
8. Record source commit, build environment, signer fingerprint, artifact hash and test evidence.

## Safety and distribution

No expert is required merely to use the current personal tracker. Ingredient entries are general education and are not clinically reviewed treatment recommendations. Do not use the app to diagnose or select treatment. If symptoms are severe, persistent, worsening or concerning, seek appropriate medical care.

Adding clinical advice, image diagnosis, high-strength active protocols, home formulations or public/commercial distribution requires a separate qualified safety/privacy/legal review. Do not represent the current app as clinically validated or legally certified.

## Current decision

**Current personal-use software scope: implemented; latest CI passed.** Phone acceptance and a stable signed APK (if wanted) remain owner-side steps. The debug artifact is for evaluation, not public distribution.
