# AI Model Feasibility and Validation

**Status:** Preliminary; research incomplete; no final model selected.
**Last updated:** 2026-10-29

## 1. Objective

Determine which selfie-analysis features can be delivered privately and reliably on supported Android phones. Model availability is not proof of measurement validity. Every feature must independently pass licensing, technical, task-validity, subgroup-fairness and safety gates.

## 2. Candidate paths

### A. Mobile face detection and landmarks

Candidate: Google ML Kit Face Detection.

Potential role: detect a face and landmarks to help guide framing and align comparable facial regions.

Limit: face detection/landmarks do not measure pigmentation, acne, texture, oiliness or skin health. Confirm exact current SDK/model delivery options, licence and supported Android versions from official documentation before selection.

### B. Image segmentation

Candidate: MediaPipe Image Segmenter or another appropriately licensed Android-compatible segmentation model.

Potential role: isolate relevant image regions, if the selected model genuinely supports the needed class labels.

Limit: a segmentation mask does not establish accurate measurement of a skin concern. Confirm whether the model identifies skin pixels versus facial parts, what data it was trained on, and whether its licence permits the intended distribution and use.

### C. Custom task-specific model

Potential role: a separately trained/validated model for a narrow feature such as tracking selected visible dark-mark regions.

Prerequisites: representative, lawfully obtained and appropriately consented data; defensible reference labels; independent evaluation; subgroup analysis; documented licence and model provenance; Android performance benchmarks.

This is an option to investigate, not a commitment to train a model.

### D. Classical image analysis

Potential role: controlled image-quality metrics, alignment and carefully constrained measurements.

Limit: colour, shadows, camera processing and exposure can confound apparent changes. Do not equate pixel differences with biological improvement. Avoid automatic white balance or enhancement that changes the apparent signal.

## 3. Per-feature status

| Feature | Status | Required evidence before enablement |
|---|---|---|
| Blur/exposure/capture quality checks | Candidate for early testing | Repeatable quality gate and false-accept/reject rates |
| Face detection and framing | Candidate for early testing | Performance on supported devices, poses and skin tones |
| Facial-region alignment | Candidate for testing | Landmark stability and region repeatability |
| Skin-region segmentation | Research candidate | Correctness of relevant region labels across user groups and lighting |
| Selected visible dark-mark tracking | Experimental | Appropriate reference measurements, repeatability and subgroup performance |
| Texture tracking | Experimental | Defined measurable target and validated reference |
| Acne-like visual observations | Experimental; not diagnosis | Task-specific validity and conservative wording |
| Redness estimation | Hold pending evidence | Strong control of lighting, camera and skin-tone confounding |
| Oiliness/pores/fine lines | Hold pending evidence | Valid measurement method and demonstrated repeatability |
| Skin-disease diagnosis | Excluded from MVP | Outside product scope |
| Beauty/fairness score | Excluded | Inconsistent with product principles |

## 4. Model selection criteria

For every candidate, record:
- Official source and version/date checked.
- Licence for code, model weights, training data and derived artefacts.
- Intended task, input constraints and known limitations.
- Training/evaluation data and demographic/skin-tone coverage.
- Ground-truth/reference method and reported metrics.
- Independent evidence versus vendor-reported claims.
- Supported Android versions, CPU/GPU/NPU requirements and runtime compatibility.
- Model/app size, memory peak, warm/cold latency and battery use on representative devices.
- Offline behavior, first-run behavior and whether any data leaves the device.
- Failure modes, uncertainty handling and rollback/disable mechanism.

Do not choose by headline accuracy alone. Skin-lesion research datasets may not represent ordinary cosmetic selfies, lighting, camera processing or the target population. Review dataset-specific terms; do not assume an archive's entire collection has one licence.

## 5. Evaluation design

Before testing, define the task and threshold. Do not retrofit thresholds after seeing the results.

### Data governance

- Use lawfully obtained data with appropriate consent and purpose limitation.
- Do not upload private photos to a third-party service for evaluation without separate informed consent and review.
- Minimize collection and retention; restrict access; document deletion.
- Ensure train/test separation at the person level where applicable to prevent leakage.
- Include variation in skin tones, ages where in scope, Android devices, camera processing, lighting, poses and supported face/body regions.
- Do not assume a dataset is representative merely because it contains many images.

### Reference methods

Select a reference appropriate to each target feature. Potential visible dark-mark tracking may need standardized capture plus expert-reviewed annotations or a suitable independent measurement process. The reference method and reviewer qualifications must be specified before evaluation.

### Metrics

Choose metrics suited to each task, such as:
- Capture-quality sensitivity/specificity and false accept/reject rates.
- Landmark/region alignment error.
- Segmentation overlap and boundary error.
- Agreement with reference measurements, repeatability across sessions and measurement error.
- False-positive/false-negative rates and inconclusive-result rate.
- Calibration/uncertainty behavior.
- Subgroup performance gaps with uncertainty intervals.
- Latency, peak memory, energy/battery use and APK/model size.

Set numeric thresholds with relevant technical and skin-health expertise before testing. Sample-size planning must be sufficient for subgroup conclusions; aggregate metrics alone are inadequate.

## 6. Fairness and safe UX

- Evaluate across relevant Bangladeshi skin tones and devices.
- Report results by subgroup; do not allow aggregate performance to conceal a poorly served group.
- Never treat naturally darker skin as a defect or recommend treatment to change natural skin colour.
- If lighting or capture quality makes results unreliable, request a retake or show “unable to compare”.
- Show what was measured, dates, comparability and uncertainty.
- Do not claim a percentage improvement unless that exact measurement/use has been validated.
- Image observations alone must not trigger automatic treatment escalation.
- A concerning lesion or symptom should be handled by reviewed guidance, not a generative diagnosis.

## 7. Go/no-go gates

A feature may be enabled only when:
1. The task and intended use are precisely defined.
2. Code, weights and data licences are cleared.
3. An appropriate reference method and pre-registered thresholds exist.
4. Validation and subgroup tests meet thresholds.
5. Device performance meets target requirements.
6. Uncertainty and failure handling are implemented and tested.
7. Privacy and safety review passes.
8. Documentation describes limitations and model/version provenance.

If a gate fails, narrow the use case, collect better evidence, or disable the feature.

## 8. Research tasks

- [ ] Verify current official ML Kit documentation, terms and Android requirements.
- [ ] Verify current MediaPipe segmentation models, labels, licences and distribution terms.
- [ ] Identify and compare Android inference runtimes for custom models.
- [ ] Review candidate datasets and per-dataset licences.
- [ ] Define initial target Android device matrix.
- [ ] Choose reference methods for each candidate measurement.
- [ ] Set numeric thresholds and sample-size plan before evaluation.
- [ ] Record per-feature go/no-go decision with evidence and review date.

## 9. Current decision

No final model has been selected and no skin-analysis feature has been validated. Face detection, landmarks and capture checks are candidate foundations only. The project remains in planning; this document is a research plan, not a performance claim.
