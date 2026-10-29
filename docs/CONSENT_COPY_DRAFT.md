# Consent and Privacy Copy — Draft for Review

- Version: 0.1
- Date: 2026-10-29
- Status: Copy draft only. Not a legal notice, not final Bangla translation, and not legal approval.
- Required review: qualified Bangladesh legal/privacy counsel, product owner and native Bangla reviewer.

## 1. General scope notice — English draft
“Bangladesh Skincare AI provides skincare education and progress-tracking tools for adults. It does not diagnose skin disease and cannot guarantee a result. Some photo features may be unavailable or inconclusive. For new, changing, painful, bleeding or otherwise concerning skin findings, seek appropriate professional care.”

## 2. Local photo storage — English draft
“If you choose to save a photo, the app stores it in its private on-device storage for your progress history. This does not automatically mean the photo is excluded from every Android backup or device-transfer mechanism. Check the final app’s storage and backup settings before relying on device-only storage. You can delete saved photos in the app; deletion behavior and backup copies will be explained in the final privacy notice.”

## 3. Camera permission — English draft
“Camera access is used only when you choose to take a photo for capture guidance or progress tracking. You can deny access and use another supported option where available. The app does not need your location to take a skincare photo.”

## 4. Optional cloud storage — separate opt-in draft
“Optional cloud storage would copy selected data to a remote service so it can be available across supported devices. Before enabling it, the app will identify what is uploaded, the provider, storage region where known, retention/deletion behavior and relevant risks. This choice does not consent to cloud image analysis or model training.”

Suggested controls: “Enable cloud storage” / “Keep data on this device”. Both should be equally clear; do not preselect optional consent.

## 5. Optional cloud image analysis — separate opt-in draft
“If a future feature offers cloud image analysis, the selected photo will be sent to the named service for the stated analysis. The app will explain the provider, data handling, retention and limits before you choose. This does not consent to storing your photos in a cloud gallery or using them to train a model.”

Suggested controls: “Allow this analysis” / “Cancel and keep processing on-device or skip” where a valid on-device path exists.

## 6. Optional model training — separate opt-in draft
“Your photos and related data will not be used to train models unless you separately choose to participate. If a future research/training programme is offered, the app will explain the data requested, who may access it, purpose, retention, withdrawal limits and whether data may be shared. Declining will not block core features.”

Suggested controls: “I choose to participate” / “No thanks”. No preselection or bundled consent.

## 7. Sensitive questionnaire items — English draft
“We ask this question because it may change which guidance is appropriate. You can choose ‘not sure’ or ‘prefer not to answer’ where available. If a necessary safety detail is unknown, the app may withhold the affected recommendation rather than guess.”

## 8. Material routine change — English draft
“This change affects your current routine. Review what changes, why it is suggested, known uncertainty, cost and precautions. Nothing changes until you confirm, except that an unsafe recommendation may be disabled with an explanation.”

## 9. Safety limitation for photo analysis — English draft
“This result is limited to the feature described on this screen. It is not a diagnosis and may be wrong or inconclusive. Lighting, camera processing, pose and skin tone can affect image-based results. Do not change treatment based only on a photo comparison.”

## 10. Consent record requirements
For every purpose-specific choice, store purpose ID, notice version, decision, timestamp and withdrawal timestamp where relevant. Do not interpret reading a notice as consent. No optional transfer or training before affirmative consent. Consent wording and implementation must match actual data flows.

## 11. Finalization checklist
- Verify each claim against implemented data flow.
- Identify controller/processor, service, region and transfer details if cloud exists.
- Define retention, deletion, backup and withdrawal limits.
- Obtain qualified Bangladesh legal/privacy review against applicable current law.
- Translate into Bangla and review with native speakers and relevant experts.
- Test that declining optional consent does not silently disable unrelated core features.
- Do not ship placeholder copy as final legal notice.