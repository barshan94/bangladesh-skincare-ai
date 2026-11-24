package com.barshan.skincareai.domain

data class LabelIngredientMatch(
    val matchedAlias: String,
    val risk: LabelIngredientRisk
)

enum class LabelIngredientRisk {
    DO_NOT_USE,
    CLINICIAN_GUIDANCE_ONLY
}

/**
 * A deliberately narrow keyword screen for pasted cosmetic labels.
 * A match is a reason to avoid/seek advice; no match is never a safety clearance.
 * This cannot detect undeclared contaminants, counterfeit labels or unknown synonyms.
 */
object IngredientLabelChecker {
    private data class Rule(val aliases: List<String>, val risk: LabelIngredientRisk)

    private val rules = listOf(
        Rule(
            aliases = listOf(
                "mercury", "mercuric", "mercurous", "mercurous chloride",
                "mercuric chloride", "mercurio", "calomel", "mercury chloride",
                "পারদ", "মারকিউরি", "মারকিউরিক", "মারকিউরাস", "ক্যালোমেল"
            ),
            risk = LabelIngredientRisk.DO_NOT_USE
        ),
        Rule(
            aliases = listOf(
                "hydroquinone", "1 4 benzenediol", "1 4 dihydroxybenzene",
                "quinol", "হাইড্রোকুইনোন"
            ),
            risk = LabelIngredientRisk.CLINICIAN_GUIDANCE_ONLY
        ),
        Rule(
            aliases = listOf(
                "clobetasol", "betamethasone", "mometasone", "fluocinolone",
                "fluocinonide", "clobetasone", "hydrocortisone", "triamcinolone",
                "dexamethasone", "prednisolone", "beclomethasone",
                "halobetasol", "desonide", "স্টেরয়েড", "স্টেরয়েড"
            ),
            risk = LabelIngredientRisk.CLINICIAN_GUIDANCE_ONLY
        )
    )

    fun check(labelText: String): List<LabelIngredientMatch> {
        if (labelText.isBlank()) return emptyList()
        val normalized = normalize(labelText)
        if (normalized.isBlank()) return emptyList()
        val tokens = normalized.split(Regex("\\s+")).filter(String::isNotBlank)
        return rules.flatMap { rule ->
            rule.aliases.map { alias -> alias to normalize(alias) }
                .filter { (_, normalizedAlias) ->
                    val aliasTokens = normalizedAlias.split(Regex("\\s+")).filter(String::isNotBlank)
                    aliasTokens.isNotEmpty() && tokens.windowed(aliasTokens.size)
                        .any { window -> window == aliasTokens }
                }
                .map { (alias, _) -> LabelIngredientMatch(alias, rule.risk) }
        }.distinctBy { it.matchedAlias.lowercase() }
    }

    private fun normalize(value: String): String =
        value.lowercase()
            .replace(Regex("[^\\p{L}\\p{N}]+"), " ")
            .trim()
            .replace(Regex("\\s+"), " ")
}
