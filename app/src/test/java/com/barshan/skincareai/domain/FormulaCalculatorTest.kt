package com.barshan.skincareai.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class FormulaCalculatorTest {
    @Test fun calculatesGramsForCompleteDraft() {
        val result = FormulaCalculator.calculate(30.0, listOf(
            FormulaLine("Oil A", 60.0),
            FormulaLine("Wax B", 40.0)
        ))
        assertNull(result.error)
        assertEquals(18.0, result.amounts[0].grams, 0.001)
        assertEquals(12.0, result.amounts[1].grams, 0.001)
    }

    @Test fun rejectsIncompletePercentageTotal() {
        val result = FormulaCalculator.calculate(30.0, listOf(FormulaLine("Oil A", 50.0)))
        assertEquals(FormulaError.TOTAL_NOT_100, result.error)
    }

    @Test fun rejectsInvalidBatchAndIngredient() {
        assertEquals(FormulaError.INVALID_BATCH_SIZE, FormulaCalculator.calculate(0.0, listOf(FormulaLine("A", 100.0))).error)
        assertEquals(FormulaError.EMPTY_INGREDIENT, FormulaCalculator.calculate(30.0, listOf(FormulaLine(" ", 100.0))).error)
    }

    @Test fun waterProductsAndSunscreenAreNotEligibleForHomeDraftCalculation() {
        assertEquals(false, HomeProductType.WATER_BASED_SERUM.canCalculateDraft)
        assertEquals(false, HomeProductType.CREAM_OR_LOTION.canCalculateDraft)
        assertEquals(false, HomeProductType.SUNSCREEN.canCalculateDraft)
        assertEquals(true, HomeProductType.ANHYDROUS_BALM.canCalculateDraft)
    }
}
