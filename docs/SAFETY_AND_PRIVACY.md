# Safety and Privacy — Current Personal-use App

**Last reviewed:** 2026-11-17  
**Scope:** Offline-first personal Android tracker; not a medical device or clinical record system.

## Current safety boundary

- Skin Scan is a self-reported check-in, not camera analysis, diagnosis or AI inference.
- Guidance is deterministic, general education only, not individualized treatment.
- Routine is a reminder checklist, not a prescribed plan.
- Ingredient catalogue entries are educational summaries, not endorsements or treatment instructions; they have not been professionally reviewed.
- No skin whitening guarantee, fairness score, diagnosis, prescription, home formula or mixing instruction is provided.
- Do not frame natural darker skin as a defect.
- Stop a product that causes irritation or an allergic reaction. Seek appropriate care for severe, persistent, worsening, painful, bleeding or otherwise concerning symptoms.
- Do not use household bleach, lemon juice, unknown fairness creams or unreviewed mixtures as skin treatments.

## Privacy implementation

- Routine and Progress records are stored in the local Room database.
- Language and budget preferences use app-private preferences.
- No account, analytics, cloud sync, photo upload or network permission is implemented.
- No runtime permissions are currently requested.
- Android backup is disabled in the manifest.
- Profile provides local data reset.

These are source-code properties. Verify OS backup/data-extraction behavior, reset and migration on a physical device before treating them as device-tested.

## User guidance

- Do not enter highly sensitive medical information; this is not a clinical record.
- The app does not infer skin improvement from notes.
- Use finished products as labelled; do not apply concentrated raw ingredients or mix sunscreen yourself.
- Introduce one new cosmetic product at a time and stop if it causes a reaction.
- Do not rely on this app to assess a new/changing lesion or persistent skin condition.

## Future scope restrictions

Any future photo analysis, AI, personalized treatment, high-strength active protocol, DIY formulation, cloud storage or public/commercial distribution requires a separate safety, privacy, validation and legal assessment. Qualified review is required for clinical/formulation claims; it is not a prerequisite for personal use of the current non-diagnostic tracker.

## Incident handling

If the app appears to give unsafe guidance, stop following that guidance and report the exact screen/text to the project owner. Do not share private skin records publicly. The current build has no remote content update or remote kill switch.
