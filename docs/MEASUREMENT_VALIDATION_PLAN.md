# Measurement Validation Plan — Proposal

**Status:** Proposed for review; not approved and not a clinical protocol  
**Date:** 2026-10-29  
**Implementation:** Not started

## Purpose

Define how each photo-analysis feature will earn permission to appear in the app. The app must distinguish image capture support from measurement validity. A feature does not pass merely because the model runs, looks plausible in a demo, or returns a confidence score.

## 1. Proposed first-release feature shortlist

### Candidate A — Capture-quality gate
Assess whether a photo is suitable for comparison: blur, exposure/clipping, face/region framing, pose, and obvious capture-condition mismatch.

**Proposed status:** First validation candidate.  
**User-facing output:** “Ready to compare” or a specific retake instruction. Never a skin-health score.  
**Key risks:** False rejection frustrates users; false acceptance creates misleading progress comparisons.

### Candidate B — Face alignment / region consistency
Use a face detector or landmark tool to guide framing and select consistent regions for comparison.

**Proposed status:** First validation candidate.  
**User-facing output:** Alignment guidance and a region overlay.  
**Key risks:** Missed or shifted landmarks, pose differences, camera orientation and subgroup variation.

### Candidate C — One narrowly defined pigmentation / selected dark-mark measurement
Explore whether standardized photos can support repeatable tracking of a clearly defined visible feature.

**Proposed status:** Research-only until all gates pass.  
**User-facing output if eventually approved:** A cautious description of observed change, uncertainty and session comparability—not diagnosis or a promise of treatment efficacy.  
**Required before evaluation:** An expert-reviewed operational definition, suitable reference method, qualified annotation protocol, diverse test set and documented model/data rights.

### Tracking-only scope
Face and body may be included for private photo timelines and user-controlled comparisons. Do not infer an unvalidated body or facial condition just because a photo was captured. Automated body analysis is a separate feature gate.

### Excluded from the first automated analysis
- Disease diagnosis or clinical severity grading.
- Beauty, attractiveness, fairness or whitening scores.
- Claims that ordinary selfies directly measure hydration, oil production or elasticity.
- Any feature whose test data, license, reference method or subgroup performance cannot be established.

## 2. Evaluation dataset and consent plan

Before collecting or using images:

1. Define intended users, age range, body/face regions and supported Android device range.
2. Obtain appropriate ethics, privacy and expert review for the proposed evaluation. Determine applicable Bangladesh requirements before recruiting participants.
3. Use consented images or datasets whose licenses explicitly allow the intended research use.
4. Document consent scope, access, retention, deletion and whether images can be reviewed by external experts.
5. Keep model-development data separate from a locked evaluation set. Prevent images of the same participant from leaking across development and test splits.
6. Include a planned range of skin tones, devices, camera processing, indoor/outdoor lighting, shadows, and common pose/framing conditions. Do not rely on aggregate results alone.
7. Include deliberately poor-quality and ambiguous examples to test abstention and retake behavior.
8. Do not use private app users' photos for training by default. Any future training use requires separate optional informed consent.

## 3. Reference methods

| Feature | Required reference / comparison |
|---|---|
| Blur, exposure, framing, pose | Predefined technical image-quality rules plus blinded human review of a stratified sample |
| Face alignment / region consistency | Landmark/region annotations and repeated-capture alignment error |
| Pigmentation / dark-mark candidate | Expert-defined annotation protocol and, where feasible, an appropriate standardized reference method; document uncertainty and inter-rater agreement |
| Longitudinal change | Repeated captures under controlled and deliberately varied conditions, plus a reference assessment that is independent of model output |

A dermatologist or appropriately qualified skin expert must review the operational definition and reference method for any skin-feature claim. If no defensible reference is available, do not claim that the model measures that feature accurately.

## 4. Metrics to report

For every feature, report:
- Coverage: proportion of intended captures receiving a usable result.
- Abstention: proportion correctly marked inconclusive or requiring retake.
- Error rates and confidence intervals.
- Repeatability across repeated captures.
- Sensitivity to lighting, pose, distance, device and image processing.
- Agreement with the reference method, including disagreement patterns.
- Subgroup metrics by relevant skin-tone groups and device/lighting groups.
- Performance on low-quality, ambiguous and out-of-scope inputs.
- Runtime, peak memory, battery impact, package/model size and crash rate on the target Android matrix.

Do not report only overall accuracy. Include per-group sample counts and uncertainty; if sample sizes are too small for reliable subgroup conclusions, label the result inconclusive rather than “fair.”

## 5. Proposed engineering gates (not yet approved)

These are **starting proposals for technical discussion**, not validated clinical standards. Final thresholds must be reviewed and approved before the locked evaluation set is opened.

| Gate | Proposed initial target | Notes |
|---|---|---|
| Capture-quality gate: detect unusable captures | At least 95% sensitivity for the predefined unusable-image categories | Estimate with confidence intervals; missing a bad image can mislead progress |
| Capture-quality gate: usable-image acceptance | At least 80% specificity on the predefined usable-image set | Avoid rejecting too many acceptable photos |
| Face alignment on accepted images | At least 95% successful alignment overall and no tested subgroup below 90% | Define “successful” with an annotated pixel/region tolerance before testing |
| Repeated capture consistency | Predefine a maximum alignment error and acceptable output variation before evaluation | Must be tied to the intended feature and camera geometry |
| Skin-feature agreement | **No numeric target set yet** | Requires expert review, a defensible reference method and a pre-registered statistical plan |
| Skin-tone/device robustness | No material subgroup gap accepted without explanation and mitigation; set numerical limits before holdout testing | Exact limits require planned sample sizes and reviewer agreement |
| Safety / uncertainty behavior | 100% pass on the curated set of critical safety and abstention cases | A critical safety failure blocks release; curate and review the test set independently |
| Performance | Set device-specific latency, memory, battery and package budgets after choosing the supported device matrix | Must be tested on real target devices, not emulator-only |

Do not retrofit or relax a threshold after inspecting the locked test results. If a candidate misses its gate, investigate, revise using development data, and evaluate on a new untouched test set—or leave the feature disabled.

## 6. Test phases

### Phase 0 — Protocol approval
- Approve the shortlist, definitions, intended claims and exclusions.
- Identify expert reviewers and the reference method.
- Agree sample-size rationale, subgroup plan, metrics and numerical thresholds.
- Approve consent, data handling and deletion procedures.
- Freeze the evaluation protocol before data collection.

### Phase 1 — Technical capture pilot
- Test capture checks and face alignment on a consented, representative pilot set.
- Measure retake behavior, alignment error and device/lighting sensitivity.
- Fix capture issues using development data only.

### Phase 2 — Locked feature evaluation
- Evaluate a candidate model once on the locked set.
- Report aggregate and subgroup results with confidence intervals.
- Record failures, inconclusive outputs, device requirements and license provenance.
- Do not enable a skin feature that misses any mandatory gate.

### Phase 3 — Real-world usability and safety pilot
- Only after technical and expert review, recruit consenting testers with varied skin tones, devices and budgets.
- Measure whether users understand limitations, follow capture guidance, can delete data and respond appropriately to warnings.
- Record adverse reactions to skincare recommendations separately from image-model performance.
- No model output should autonomously diagnose, prescribe or silently change a material routine.

### Phase 4 — Release decision
Release requires documented approval of:
- feature-specific validation and subgroup analysis;
- safety and privacy review;
- dependency, model and dataset license review;
- supported device matrix and offline behavior;
- uncertainty, abstention and failure handling;
- signed APK, version traceability and rollback/update plan.

Any unresolved critical safety defect blocks release.

## 7. Open decisions required before evaluation

- Which feature candidates are approved for the first validation pilot?
- Who will review the skin-feature definitions and reference method?
- Which skin-tone grouping method is appropriate and ethically acceptable for this study?
- What target Android devices and minimum OS version will be supported?
- What sample size is required to estimate performance and subgroup gaps with useful precision?
- What numerical skin-feature agreement/repeatability thresholds are acceptable to the expert reviewers?
- Which local data-protection, research ethics and cosmetic/medical regulatory requirements apply?

## 8. Approval record

No candidate feature is approved by this document. Record any decision in `docs/DECISIONS.md` with date, rationale, evidence, owner and status. Implementation remains blocked until product specification, safety boundaries, validation plan and screen-by-screen UX/architecture are explicitly approved.

## References

- Google ML Kit face detection documentation: https://developers.google.com/ml-kit/vision/face-detection/android
- MediaPipe Face Landmarker for Android: https://developers.google.com/edge/mediapipe/solutions/vision/face_landmarker/android
- Skin Type Diversity in Skin Lesion Datasets: A Review (2024): https://doi.org/10.1007/s13671-024-00440-0
- A framework for evaluating clinical artificial intelligence systems without ground-truth annotations (2024): https://www.nature.com/articles/s41467-024-46000-9

This is a proposed engineering/research plan, not medical advice, clinical validation, ethics approval or legal advice.
