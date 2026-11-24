package com.barshan.skincareai.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class IngredientLabelCheckerTest {
    @Test
    fun detectsMercuryAliasesAndMarksThemDoNotUse() {
        val matches = IngredientLabelChecker.check("Water, Glycerin, Mercurous Chloride, Fragrance")
        assertTrue(matches.any {
            it.matchedAlias == "mercurous chloride" &&
                it.risk == LabelIngredientRisk.DO_NOT_USE
        })
    }

    @Test
    fun detectsHydroquinoneAndCommonTopicalSteroidNames() {
        val matches = IngredientLabelChecker.check(
            "Aqua, Hydroquinone, Clobetasol Propionate, Mometasone Furoate"
        )
        assertTrue(matches.any { it.matchedAlias == "hydroquinone" })
        assertTrue(matches.any { it.matchedAlias == "clobetasol" })
        assertTrue(matches.any { it.matchedAlias == "mometasone" })
        assertTrue(matches.all { it.risk == LabelIngredientRisk.CLINICIAN_GUIDANCE_ONLY })
    }

    @Test
    fun detectsBengaliNames() {
        val matches = IngredientLabelChecker.check("পানি, গ্লিসারিন, পারদ, হাইড্রোকুইনোন")
        assertTrue(matches.any { it.matchedAlias == "পারদ" })
        assertTrue(matches.any { it.matchedAlias == "হাইড্রোকুইনোন" })
    }

    @Test
    fun doesNotMatchPartialWordsOrUnrelatedIngredients() {
        assertTrue(IngredientLabelChecker.check("Water, Glycerin, Stearate, Fragrance").isEmpty())
    }

    @Test
    fun blankOrPunctuationOnlyInputReturnsNoMatches() {
        assertTrue(IngredientLabelChecker.check("   ").isEmpty())
        assertTrue(IngredientLabelChecker.check("!@#$%^&*").isEmpty())
    }
}
