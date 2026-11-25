package com.barshan.skincareai.domain

import com.barshan.skincareai.R

/**
 * Deterministic, non-diagnostic guidance for the offline personal-use release.
 * All user-visible text is stored in Android resources so Bangla mode is complete.
 */
enum class SkinFeel { DRY, OILY, COMBINATION, SENSITIVE, UNSURE }
enum class SkinGoal { BASIC_CARE, DRYNESS, ROUGHNESS, DARK_MARKS, ACNE, OTHER }

data class CheckIn(
    val skinFeel: SkinFeel,
    val goal: SkinGoal,
    val knownReaction: Boolean = false,
    val brokenSkin: Boolean = false,
    val concerningChange: Boolean = false,
    val regularSunProtection: Boolean = false,
    val usingMultipleActives: Boolean = false,
    val usingPrescribedSkinTreatment: Boolean = false
)

data class GuidanceCard(
    val titleRes: Int,
    val explanationRes: Int,
    val cautionRes: Int? = null
)

data class GuidanceResult(
    val status: Status,
    val summaryRes: Int,
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
                summaryRes = R.string.guidance_summary_concerning_change,
                cards = listOf(
                    GuidanceCard(R.string.guidance_pause_experimentation_title, R.string.guidance_pause_experimentation_body),
                    GuidanceCard(R.string.guidance_get_help_title, R.string.guidance_get_help_body)
                ),
                seekProfessionalCare = true
            )
        }

        if (checkIn.brokenSkin) {
            return GuidanceResult(
                status = GuidanceResult.Status.SAFETY_FIRST,
                summaryRes = R.string.guidance_summary_broken_skin,
                cards = listOf(
                    GuidanceCard(R.string.guidance_keep_care_simple_title, R.string.guidance_keep_care_simple_body),
                    GuidanceCard(R.string.guidance_watch_worsening_title, R.string.guidance_watch_worsening_body)
                ),
                seekProfessionalCare = true
            )
        }

        val cards = mutableListOf(
            GuidanceCard(R.string.guidance_cleanse_title, R.string.guidance_cleanse_body),
            GuidanceCard(
                R.string.guidance_moisturize_title,
                when (checkIn.skinFeel) {
                    SkinFeel.DRY -> R.string.guidance_moisturize_dry_body
                    SkinFeel.OILY -> R.string.guidance_moisturize_oily_body
                    SkinFeel.COMBINATION -> R.string.guidance_moisturize_combination_body
                    SkinFeel.SENSITIVE -> R.string.guidance_moisturize_sensitive_body
                    SkinFeel.UNSURE -> R.string.guidance_moisturize_unsure_body
                }
            ),
            GuidanceCard(R.string.guidance_sun_title, R.string.guidance_sun_body)
        )

        if (checkIn.knownReaction) {
            cards.add(
                0,
                GuidanceCard(
                    R.string.guidance_prior_reaction_title,
                    R.string.guidance_prior_reaction_body,
                    R.string.guidance_prior_reaction_caution
                )
            )
        }

        if (!checkIn.regularSunProtection) {
            cards.add(
                GuidanceCard(
                    R.string.guidance_sun_habit_title,
                    R.string.guidance_sun_habit_body
                )
            )
        }

        if (checkIn.usingMultipleActives) {
            cards.add(
                GuidanceCard(
                    R.string.guidance_multiple_actives_title,
                    R.string.guidance_multiple_actives_body
                )
            )
        }

        if (checkIn.usingPrescribedSkinTreatment) {
            cards.add(
                GuidanceCard(
                    R.string.guidance_prescribed_treatment_title,
                    R.string.guidance_prescribed_treatment_body
                )
            )
        }

        val (status, summary, scope, needsCare) = when (checkIn.goal) {
            SkinGoal.BASIC_CARE -> Quadruple(
                GuidanceResult.Status.GENERAL_EDUCATION,
                R.string.guidance_summary_basic,
                null,
                false
            )
            SkinGoal.DRYNESS, SkinGoal.ROUGHNESS -> Quadruple(
                GuidanceResult.Status.GENERAL_EDUCATION,
                R.string.guidance_summary_dryness_roughness,
                null,
                false
            )
            SkinGoal.DARK_MARKS -> Quadruple(
                GuidanceResult.Status.OUT_OF_SCOPE,
                R.string.guidance_summary_dark_marks,
                GuidanceCard(R.string.guidance_dark_marks_title, R.string.guidance_dark_marks_body),
                false
            )
            SkinGoal.ACNE -> Quadruple(
                GuidanceResult.Status.OUT_OF_SCOPE,
                R.string.guidance_summary_acne,
                GuidanceCard(R.string.guidance_acne_title, R.string.guidance_acne_body),
                true
            )
            SkinGoal.OTHER -> Quadruple(
                GuidanceResult.Status.OUT_OF_SCOPE,
                R.string.guidance_summary_other,
                GuidanceCard(R.string.guidance_other_title, R.string.guidance_other_body),
                true
            )
        }

        if (scope != null) cards.add(scope)
        return GuidanceResult(status, summary, cards, needsCare)
    }

    private data class Quadruple<A, B, C, D>(
        val first: A,
        val second: B,
        val third: C,
        val fourth: D
    )
}
