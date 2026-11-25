package com.barshan.skincareai.domain

import com.barshan.skincareai.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SkincareGuidanceTest {
    @Test
    fun concerningChangeAlwaysTakesSafetyFirstPath() {
        val result = SkincareGuidance.create(
            CheckIn(SkinFeel.UNSURE, SkinGoal.BASIC_CARE, concerningChange = true)
        )
        assertEquals(GuidanceResult.Status.SAFETY_FIRST, result.status)
        assertEquals(R.string.guidance_summary_concerning_change, result.summaryRes)
        assertTrue(result.seekProfessionalCare)
    }

    @Test
    fun brokenSkinDoesNotReceiveRoutineExperimentationAdvice() {
        val result = SkincareGuidance.create(
            CheckIn(SkinFeel.DRY, SkinGoal.DRYNESS, brokenSkin = true)
        )
        assertEquals(GuidanceResult.Status.SAFETY_FIRST, result.status)
        assertTrue(result.cards.any { it.titleRes == R.string.guidance_keep_care_simple_title })
        assertTrue(result.cards.none { it.titleRes == R.string.guidance_cleanse_title })
    }

    @Test
    fun basicCareReturnsGeneralEducationWithoutDiagnosis() {
        val result = SkincareGuidance.create(CheckIn(SkinFeel.OILY, SkinGoal.BASIC_CARE))
        assertEquals(GuidanceResult.Status.GENERAL_EDUCATION, result.status)
        assertFalse(result.seekProfessionalCare)
        assertTrue(result.cards.any { it.titleRes == R.string.guidance_sun_title })
    }

    @Test
    fun darkMarksDoesNotPromiseWhiteningAndWarnsAgainstUnsafeMethods() {
        val result = SkincareGuidance.create(CheckIn(SkinFeel.UNSURE, SkinGoal.DARK_MARKS))
        assertEquals(GuidanceResult.Status.OUT_OF_SCOPE, result.status)
        assertEquals(R.string.guidance_summary_dark_marks, result.summaryRes)
        assertFalse(result.seekProfessionalCare)
        assertTrue(result.cards.any { it.titleRes == R.string.guidance_dark_marks_title })
    }

    @Test
    fun priorReactionAddsSpecificCaution() {
        val result = SkincareGuidance.create(
            CheckIn(SkinFeel.SENSITIVE, SkinGoal.BASIC_CARE, knownReaction = true)
        )
        assertEquals(R.string.guidance_prior_reaction_title, result.cards.first().titleRes)
        assertEquals(R.string.guidance_prior_reaction_caution, result.cards.first().cautionRes)
    }

    @Test
    fun missingSunProtectionAddsSunProtectionPriority() {
        val result = SkincareGuidance.create(
            CheckIn(SkinFeel.UNSURE, SkinGoal.DARK_MARKS, regularSunProtection = false)
        )
        assertTrue(result.cards.any { it.titleRes == R.string.guidance_sun_habit_title })
    }

    @Test
    fun consistentSunProtectionDoesNotRepeatTheAdditionalReminder() {
        val result = SkincareGuidance.create(
            CheckIn(SkinFeel.UNSURE, SkinGoal.DARK_MARKS, regularSunProtection = true)
        )
        assertFalse(result.cards.any { it.titleRes == R.string.guidance_sun_habit_title })
    }

    @Test
    fun multipleStrongActivesAddsSimplificationCaution() {
        val result = SkincareGuidance.create(
            CheckIn(SkinFeel.SENSITIVE, SkinGoal.DARK_MARKS, usingMultipleActives = true)
        )
        assertTrue(result.cards.any { it.titleRes == R.string.guidance_multiple_actives_title })
    }

    @Test
    fun safetyFlagsOverridePersonalizedRoutineReminders() {
        val result = SkincareGuidance.create(
            CheckIn(
                SkinFeel.SENSITIVE,
                SkinGoal.DARK_MARKS,
                concerningChange = true,
                regularSunProtection = false,
                usingMultipleActives = true
            )
        )
        assertEquals(GuidanceResult.Status.SAFETY_FIRST, result.status)
        assertTrue(result.cards.none { it.titleRes == R.string.guidance_sun_habit_title })
        assertTrue(result.cards.none { it.titleRes == R.string.guidance_multiple_actives_title })
    }

    @Test
    fun everySkinFeelHasLocalizedMoisturizerCopy() {
        SkinFeel.entries.forEach { feel ->
            val result = SkincareGuidance.create(CheckIn(feel, SkinGoal.BASIC_CARE))
            assertTrue(result.cards.any { it.titleRes == R.string.guidance_moisturize_title })
        }
    }
}
