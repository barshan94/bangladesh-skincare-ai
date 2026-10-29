# AI Model Feasibility Assessment

**Status:** Initial desk research; not a model selection or validation result  
**Reviewed:** 2026-10-29  
**Project:** Bangladesh Skincare AI  
**Implementation status:** No app code or model integration started

## Executive conclusion

An Android-only, on-device-first app is technically feasible for guided capture, photo-quality checks, face/region alignment, and private progress-photo storage. Those foundations are not the same as validated skin analysis.

**No skin-condition model is approved for launch yet.** Google ML Kit Face Detection and MediaPipe Face Landmarker can help locate a face and its geometry; they do not, by themselves, measure acne, pigmentation, hydration, oiliness, or skin health. LiteRT can run a separately selected or custom-trained model on-device, but the runtime does not supply the missing clinical/cosmetic validation.

The safe strategy is to build the capture and measurement-validation plan first, then enable each analysis feature only after it passes a pre-registered evaluation. If a feature cannot be validated for the intended users, lighting, phones and skin tones, leave it disabled rather than presenting a simulated score.

## 1. Candidate Android technologies

| Candidate | What it can do | What it cannot establish | Feasibility decision |
|---|---|---|---|
| Google ML Kit Face Detection | Face box, pose, landmarks, contours and capture alignment; can run on still images | Skin condition, pigmentation severity, acne diagnosis, hydration or treatment effectiveness | **Candidate for capture assistance**, not skin analysis |
| MediaPipe Face Landmarker | Dense facial landmarks (documented output includes 478 3D landmarks) and facial geometry | Skin health, blemish meaning or reliable cosmetic measurements | **Alternative for alignment** if its added complexity is justified |
| LiteRT (formerly TensorFlow Lite runtime) | Runs compatible custom or converted ML models on Android, with CPU/GPU/NPU options depending on setup | Does not provide a validated skincare model simply by being installed | **Candidate runtime**, pending model and device benchmarks |
| Hand-coded image-quality checks | Blur, clipping/exposure, framing and consistency warnings can be prototyped with image-processing methods | Cannot fully correct mixed lighting, camera processing, filters or color shifts | **Candidate first milestone**, with user retake when uncertain |
| Custom pigmentation/texture/acne-like model | Potentially estimates a narrowly defined visual feature if suitable labeled data and reference methods exist | No dependable performance can be assumed from generic models, public demos or marketing claims | **Research only; not approved** |

### Packaging and compatibility notes

- ML Kit's official Android guide describes bundled and Google Play services-delivered face-detection models. Bundling increases APK size but avoids waiting for a first-use model download; an unbundled model can be smaller but may not be ready offline until downloaded.
- The ML Kit guide gives practical input-size guidance (images at least 480 × 360, with a face ideally at least 100 × 100 pixels; higher resolution is needed for contours). These are capture guidance, not a guarantee of accuracy for skin measurements.
- Current LiteRT documentation lists Android API 24+ for its current Android setup. This is a candidate-runtime constraint, not yet the app's approved minimum Android version.
- Hardware acceleration and performance vary by device. Measure latency, peak memory, battery impact, package size and output consistency on a defined Android device matrix before choosing a runtime.
- Dependency and model licenses must be checked individually, including the model weights, training data terms, redistribution rights and any commercial-use restrictions. A permissive runtime license does not automatically grant rights to every model or dataset.

## 2. Feature-by-feature launch recommendation

| Feature | Proposed status | Rationale / required gate |
|---|---|---|
| Capture quality: blur, exposure, framing, obvious lighting inconsistency | **Priority candidate** | Compare automated warnings with human review; test indoor daylight, warm/cool bulbs, shadows, camera processing and common Android devices |
| Face detection, alignment and repeatable region selection | **Priority candidate** | Use ML Kit or MediaPipe only for geometry; measure detection failures and alignment repeatability across skin tones and devices |
| Face progress-photo timeline and side-by-side comparison | **Include as tracking, not diagnosis** | Preserve originals, dates and capture conditions; disclose when sessions are not comparable |
| Body progress-photo tracking | **Include as private tracking only initially** | Guided framing and user-controlled comparison; no automatic body-condition claims without separate validation |
| Visible uneven pigmentation / selected dark marks | **High-priority research candidate; gated** | Needs a narrow operational definition, appropriate expert/reference labels, diverse Bangladeshi skin tones, repeatability and cross-device testing |
| Texture changes | **Exploratory / gated** | Fine texture is highly sensitive to focus, distance, sharpening and lighting; require repeatable measurements before release |
| Acne-like visible features | **Exploratory / gated; never diagnosis** | Requires suitable annotations and explicit handling of false positives/negatives; concerning lesions must not be triaged by this model |
| Redness-related change | **Defer unless independently validated** | Color balance, lighting, irritation and skin tone can confound apparent redness |
| Pores, oiliness, hydration, elasticity and fine lines from ordinary selfies | **Exclude from initial automated analysis** | A normal phone image does not directly measure physiological hydration/oil production; some attributes may require specialized imaging or instruments |
| Skin-tone/beauty score, fairness ranking or “whitening score” | **Exclude** | Darker natural skin is not a defect or treatment target |
| Disease diagnosis or clinical severity grading | **Exclude from MVP** | Outside the intended non-diagnostic product scope and requires separate clinical validation and regulatory review |

**Scope clarification:** Face and body can both be supported for questionnaire-guided routines and user-controlled progress tracking. Automated image analysis is enabled only for the specific region and feature that passes its own validation gate. A broad launch promise must not be interpreted as permission to infer every concern from a photo.

## 3. Evidence and risk assessment

Published research reviews report that skin-image datasets often lack verifiable representation across skin types, making generalization difficult to assess. This is especially important for a Bangladesh-focused product, where testing must intentionally include darker skin tones and varied real-world capture conditions.

A 2024 open-access review on skin-type diversity in skin-lesion datasets describes representation and reporting gaps. A separate 2024 Nature Communications paper discusses performance degradation when dermatology models are evaluated on a diverse external dataset. These are warnings about dataset quality and transferability; they do not prove that every non-diagnostic cosmetic measurement model fails.

A 2026 study of consumer skin-lesion apps reported poor diagnostic performance on its tested set. That study is not a direct benchmark for this app's proposed cosmetic progress tracking, but it reinforces why we must not reuse diagnostic claims or trust consumer-app marketing as validation evidence.

A 2026 study of a smartphone skin-colorimeter reported promising results under its own defined workflow and comparison devices. Its results are specific to that application, population, and protocol; they cannot be transferred to this app or treated as proof that ordinary selfies are reliable across Bangladeshi phones and lighting.

## 4. Required validation design before model selection

### A. Define the measurement, not just the label

For each candidate feature, document:
1. The exact user-facing claim and what it deliberately does not claim.
2. The anatomical region and supported capture conditions.
3. The reference method and qualified annotators/reviewers.
4. How disagreements and ambiguous cases are handled.
5. The target population, skin-tone representation, phone models and lighting conditions.
6. The primary metric, subgroup metrics, uncertainty handling and pre-set pass/fail thresholds.

### B. Test repeatability separately from accuracy

- **Repeatability:** Can repeated captures under controlled conditions produce sufficiently similar results?
- **Robustness:** How much does output change with small lighting, distance, pose, exposure or device changes?
- **Agreement:** Does the output agree with a defensible reference method or blinded expert annotation?
- **Subgroup performance:** Are error rates and inconclusive rates acceptable across relevant skin tones, devices, age groups where applicable, and face/body regions?
- **Longitudinal validity:** Does a measured change reflect a real change rather than a new phone, new room, camera processing or routine capture variation?

Do not set final thresholds after seeing test results. Define thresholds with appropriate reviewers before the holdout evaluation. Separate development data from locked evaluation data; avoid tuning against the final test set.

### C. Data governance

- Prefer appropriately licensed datasets and documented consent for any new data collection.
- Verify whether dataset licenses permit the intended research, modification, redistribution and commercial use.
- Do not upload private selfies to external services for convenience.
- If a future evaluation requires external reviewers, use explicit informed consent, a defined retention period, access controls and a deletion procedure.
- Never use app users' photos for model training by default; training consent must be separate and optional.

## 5. Runtime/model selection scorecard

No model should be selected by demo quality alone. For each candidate, record:

- Exact model name, version, source URL, model card and license.
- Intended task and documented limitations.
- Training/evaluation data provenance and skin-tone representation.
- External validation on data resembling the intended use.
- Per-feature accuracy/agreement, repeatability and subgroup results.
- Input resolution, preprocessing and output interpretation.
- APK/model size, peak RAM, latency, battery and thermal impact.
- CPU/GPU/NPU behavior and fallback consistency on low-, mid- and higher-range Android devices.
- Offline behavior, model update mechanism, rollback and integrity verification.
- Dependency maintenance, security status and redistribution obligations.

**Go/no-go rule:** If the task-specific model, rights, reference data, subgroup evaluation or repeatability evidence is missing, do not enable the feature. Continue with capture guidance and manual progress tracking instead.

## 6. Recommended next milestones

1. Approve the initial automated feature candidates: capture quality, face/region alignment, and possibly one narrowly defined pigmentation/dark-mark measurement.
2. Write a measurement protocol and data-collection/annotation plan before training or selecting a skin-analysis model.
3. Create a small Android device matrix for the intended private APK audience and benchmark the capture pipeline.
4. Compare ML Kit and MediaPipe for alignment on representative consented test images; select the simplest tool that meets requirements.
5. Evaluate suitable custom models only after their task, training data, license and reference method are documented.
6. Complete expert safety review, privacy review and applicable Bangladesh regulatory review before release claims are finalized.

No application implementation should start until the user explicitly approves the product specification and design gates.

## References

1. Google for Developers, **Detect faces with ML Kit on Android**: https://developers.google.com/ml-kit/vision/face-detection/android
2. Google for Developers, **Face detection concepts**: https://developers.google.com/ml-kit/vision/face-detection/face-detection-concepts
3. Google AI Edge, **Face Landmarker for Android**: https://developers.google.com/edge/mediapipe/solutions/vision/face_landmarker/android
4. Google AI Edge, **LiteRT overview and Android requirements**: https://developers.google.com/edge/litert/overview
5. Alipour N, Burke T, Courtney J. **Skin Type Diversity in Skin Lesion Datasets: A Review.** Current Dermatology Reports. 2024;13:198–210. https://doi.org/10.1007/s13671-024-00440-0
6. **A framework for evaluating clinical artificial intelligence systems without ground-truth annotations.** Nature Communications. 2024. https://www.nature.com/articles/s41467-024-46000-9
7. **Diagnostic Accuracy of Artificial Intelligence Applications on a Diverse Skin Image Set.** PubMed Central. 2026. https://pmc.ncbi.nlm.nih.gov/articles/PMC12936399/
8. **Bridging Accessibility and Precision: Evaluating the Reliability and Validity of a Smartphone-Based Skin Colorimeter.** Clinical, Cosmetic and Investigational Dermatology. 2026. https://pmc.ncbi.nlm.nih.gov/articles/PMC13050169/

**Research limitation:** This is an initial desk assessment, not a systematic review, clinical validation, legal opinion, or endorsement of a particular model. Re-check model versions, licenses, local requirements and evidence before implementation or release.
