package com.barshan.skincareai.domain

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
        assertTrue(result.seekProfessionalCare)
    }

    @Test
    fun brokenSkinDoesNotReceiveRoutineExperimentationAdvice() {
        val result = SkincareGuidance.create(
            CheckIn(SkinFeel.DRY, SkinGoal.DRYNESS, brokenSkin = true)
        )
        assertEquals(GuidanceResult.Status.SAFETY_FIRST, result.status)
        assertTrue(result.cards.any { it.title == "Keep care simple" })
    }

    @Test
    fun basicCareReturnsGeneralEducationWithoutDiagnosis() {
        val result = SkincareGuidance.create(CheckIn(SkinFeel.OILY, SkinGoal.BASIC_CARE))
        assertEquals(GuidanceResult.Status.GENERAL_EDUCATION, result.status)
        assertFalse(result.seekProfessionalCare)
        assertTrue(result.cards.any { it.title == "Protect from sunlight" })
    }

    @Test
    fun darkMarksDoesNotPromiseWhiteningAndWarnsAgainstUnsafeMethods() {
        val result = SkincareGuidance.create(CheckIn(SkinFeel.UNSURE, SkinGoal.DARK_MARKS))
        assertEquals(GuidanceResult.Status.OUT_OF_SCOPE, result.status)
        assertTrue(result.summary.contains("cannot assess"))
        assertTrue(result.cards.any { it.title == "Avoid risky lightening methods" })
    }

    @Test
    fun priorReactionAddsSpecificCaution() {
        val result = SkincareGuidance.create(
            CheckIn(SkinFeel.SENSITIVE, SkinGoal.BASIC_CARE, knownReaction = true)
        )
        assertTrue(result.cards.first().title == "Prior reaction reported")
    }
}
