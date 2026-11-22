package com.barshan.skincareai.domain

data class FormulaLine(val ingredient: String, val percentage: Double)

data class FormulaAmount(val ingredient: String, val percentage: Double, val grams: Double)

data class FormulaCalculation(
    val amounts: List<FormulaAmount>,
    val totalPercentage: Double,
    val error: FormulaError?
)

enum class FormulaError {
    INVALID_BATCH_SIZE,
    TOO_MANY_LINES,
    EMPTY_INGREDIENT,
    INVALID_PERCENTAGE,
    TOTAL_NOT_100
}

enum class HomeProductType(val canCalculateDraft: Boolean) {
    ANHYDROUS_BALM(true),
    WATER_BASED_SERUM(false),
    CREAM_OR_LOTION(false),
    SUNSCREEN(false)
}

/**
 * Arithmetic helper only. A mathematically complete formula is NOT a safe,
 * compatible, preserved, stable or effective cosmetic formulation.
 */
object FormulaCalculator {
    fun calculate(batchGrams: Double, lines: List<FormulaLine>): FormulaCalculation {
        fun failed(error: FormulaError) = FormulaCalculation(emptyList(), 0.0, error)
        if (!batchGrams.isFinite() || batchGrams <= 0.0 || batchGrams > 500.0) {
            return failed(FormulaError.INVALID_BATCH_SIZE)
        }
        if (lines.size !in 1..8) return failed(FormulaError.TOO_MANY_LINES)
        if (lines.any { it.ingredient.isBlank() }) return failed(FormulaError.EMPTY_INGREDIENT)
        if (lines.any { !it.percentage.isFinite() || it.percentage <= 0.0 || it.percentage > 100.0 }) {
            return failed(FormulaError.INVALID_PERCENTAGE)
        }
        val total = lines.sumOf { it.percentage }
        if (kotlin.math.abs(total - 100.0) > 0.01) {
            return FormulaCalculation(emptyList(), total, FormulaError.TOTAL_NOT_100)
        }
        return FormulaCalculation(
            amounts = lines.map {
                FormulaAmount(it.ingredient.trim(), it.percentage, (batchGrams * it.percentage / 100.0 * 100.0).toInt() / 100.0)
            },
            totalPercentage = total,
            error = null
        )
    }
}


data class IngredientPrice(val pricePer100Grams: Double)

/** Raw-material estimate only; excludes packaging, tools, labour and waste. */
object FormulaCostCalculator {
    fun calculate(amounts: List<FormulaAmount>, prices: List<IngredientPrice>): Double? {
        if (amounts.isEmpty() || amounts.size != prices.size) return null
        if (prices.any { !it.pricePer100Grams.isFinite() || it.pricePer100Grams < 0.0 }) return null
        return amounts.indices.sumOf { amounts[it].grams * prices[it].pricePer100Grams / 100.0 }
    }
}
