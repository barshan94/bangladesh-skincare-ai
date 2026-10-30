package com.barshan.skincareai.domain

/**
 * Deterministic, non-diagnostic guidance for the first offline release.
 * This layer deliberately does not infer a condition from photographs.
 */
enum class SkinFeel { DRY, OILY, COMBINATION, SENSITIVE, UNSURE }
enum class SkinGoal { BASIC_CARE, DRYNESS, ROUGHNESS, DARK_MARKS, ACNE, OTHER }
enum class SafetyFlag { NONE, REACTION_HISTORY, OPEN_OR_BROKEN_SKIN, SUDDEN_OR_CONCERNING_CHANGE }

data class CheckIn(
    val skinFeel: SkinFeel,
    val goal: SkinGoal,
    val knownReaction: Boolean = false,
    val brokenSkin: Boolean = false,
    val concerningChange: Boolean = false
)

data class GuidanceCard(
    val title: String,
    val explanation: String,
    val caution: String? = null
)

data class GuidanceResult(
    val status: Status,
    val summary: String,
    val cards: List<GuidanceCard>,
    val seekProfessionalCare: Boolean
) {
    enum class Status { GENERAL_EDUCATION, SAFETY_FIRST, OUT_OF_SCOPE }
}

object SkincareGuidance {
    fun create(checkIn: CheckIn): GuidanceResult {
        if (checkIn.concerningChange) {
            return GuidanceResult(
                status = GuidanceResult.Status.SAFETY_FIRST,
                summary = "A new, changing, painful, bleeding, or otherwise concerning skin change needs an in-person assessment.",
                cards = listOf(
                    GuidanceCard(
                        title = "Pause experimentation",
                        explanation = "Do not try to treat an unexplained change with homemade mixtures or new active ingredients."
                    ),
                    GuidanceCard(
                        title = "Get appropriate help",
                        explanation = "Arrange an assessment with a qualified healthcare professional. Seek urgent care for severe or rapidly worsening symptoms."
                    )
                ),
                seekProfessionalCare = true
            )
        }

        if (checkIn.brokenSkin) {
            return GuidanceResult(
                status = GuidanceResult.Status.SAFETY_FIRST,
                summary = "Broken or open skin needs gentle care rather than cosmetic experimentation.",
                cards = listOf(
                    GuidanceCard(
                        title = "Keep care simple",
                        explanation = "Avoid scrubs, lemon juice, bleaching products, and active ingredients on broken skin."
                    ),
                    GuidanceCard(
                        title = "Watch for worsening",
                        explanation = "Seek medical advice if the area worsens, becomes increasingly painful, warm, swollen, or shows signs of infection."
                    )
                ),
                seekProfessionalCare = true
            )
        }

        val cards = mutableListOf(
            GuidanceCard(
                title = "Cleanse gently",
                explanation = "Use a gentle cleanser when needed; avoid harsh scrubbing and stop products that sting or irritate."
            ),
            GuidanceCard(
                title = "Moisturize",
                explanation = when (checkIn.skinFeel) {
                    SkinFeel.DRY -> "A fragrance-free cream or ointment may help reduce dryness; apply to slightly damp skin."
                    SkinFeel.OILY -> "A light, non-comedogenic moisturizer can still be useful; oily skin can also become dehydrated or irritated."
                    SkinFeel.COMBINATION -> "Use a comfortable lightweight moisturizer and adjust the amount by area."
                    SkinFeel.SENSITIVE -> "Prefer a short ingredient list and fragrance-free products; introduce one new product at a time."
                    SkinFeel.UNSURE -> "Choose a simple moisturizer that feels comfortable and does not cause irritation."
                }
            ),
            GuidanceCard(
                title = "Protect from sunlight",
                explanation = "When outdoors, use shade, clothing, and a broad-spectrum sunscreen labelled SPF 30 or higher if available and suitable."
            )
        )

        if (checkIn.knownReaction) {
            cards.add(
                0,
                GuidanceCard(
                    title = "Prior reaction reported",
                    explanation = "Avoid the product or ingredient linked to the reaction. Do not deliberately re-test it on your face.",
                    caution = "If the reaction was severe or recurrent, ask a qualified clinician or pharmacist for advice."
                )
            )
        }

        val (status, summary, scope) = when (checkIn.goal) {
            SkinGoal.BASIC_CARE -> Triple(
                GuidanceResult.Status.GENERAL_EDUCATION,
                "Start with a simple routine and change only one thing at a time.",
                null
            )
            SkinGoal.DRYNESS, SkinGoal.ROUGHNESS -> Triple(
                GuidanceResult.Status.GENERAL_EDUCATION,
                "These are general comfort-focused steps, not a diagnosis. Give a simple routine time and stop anything that irritates.",
                null
            )
            SkinGoal.DARK_MARKS -> Triple(
                GuidanceResult.Status.OUT_OF_SCOPE,
                "This release cannot assess the cause of dark marks or promise skin-lightening results.",
                GuidanceCard(
                    title = "Avoid risky lightening methods",
                    explanation = "Do not apply lemon juice, household bleach, unknown fairness creams, or unreviewed homemade mixtures. Sun protection can help reduce further darkening."
                )
            )
            SkinGoal.ACNE -> Triple(
                GuidanceResult.Status.OUT_OF_SCOPE,
                "This release does not diagnose or grade acne and does not provide an individualized acne treatment plan.",
                GuidanceCard(
                    title = "Keep irritation low",
                    explanation = "Avoid squeezing spots and harsh scrubs. Seek professional advice for painful, scarring, persistent, or worsening acne."
                )
            )
            SkinGoal.OTHER -> Triple(
                GuidanceResult.Status.OUT_OF_SCOPE,
                "This concern is not covered by the current guidance set. Avoid experimenting with strong actives or homemade treatments.",
                GuidanceCard(
                    title = "Get tailored advice",
                    explanation = "A qualified skincare professional can help assess the concern and suitable options."
                )
            )
        }

        if (scope != null) cards.add(scope)
        return GuidanceResult(status, summary, cards, checkIn.goal == SkinGoal.ACNE || checkIn.goal == SkinGoal.OTHER)
    }
}
