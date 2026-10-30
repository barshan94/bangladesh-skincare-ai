package com.barshan.skincareai.domain

import com.barshan.skincareai.R

enum class IngredientRisk {
    GENERAL_CAUTION,
    AVOID_DIRECT_APPLICATION,
    FINISHED_SUNSCREEN_ONLY
}

enum class IngredientReviewStatus {
    PENDING_EXPERT_REVIEW
}

data class IngredientEntry(
    val id: String,
    val titleRes: Int,
    val categoryRes: Int,
    val descriptionRes: Int,
    val cautionRes: Int,
    val risk: IngredientRisk,
    val reviewStatus: IngredientReviewStatus = IngredientReviewStatus.PENDING_EXPERT_REVIEW
)

/**
 * Educational catalogue only. Entries are intentionally marked pending review;
 * the catalogue must not be treated as a formulation approval or personal advice.
 */
object IngredientCatalog {
    val entries = listOf(
        IngredientEntry("glycerin", R.string.ingredient_glycerin, R.string.ingredient_category_humectant, R.string.ingredient_glycerin_body, R.string.ingredient_glycerin_caution, IngredientRisk.GENERAL_CAUTION),
        IngredientEntry("hyaluronic_acid", R.string.ingredient_hyaluronic_acid, R.string.ingredient_category_humectant, R.string.ingredient_hyaluronic_acid_body, R.string.ingredient_hyaluronic_acid_caution, IngredientRisk.GENERAL_CAUTION),
        IngredientEntry("ceramides", R.string.ingredient_ceramides, R.string.ingredient_category_barrier, R.string.ingredient_ceramides_body, R.string.ingredient_ceramides_caution, IngredientRisk.GENERAL_CAUTION),
        IngredientEntry("petrolatum", R.string.ingredient_petrolatum, R.string.ingredient_category_occlusive, R.string.ingredient_petrolatum_body, R.string.ingredient_petrolatum_caution, IngredientRisk.GENERAL_CAUTION),
        IngredientEntry("panthenol", R.string.ingredient_panthenol, R.string.ingredient_category_skin_conditioning, R.string.ingredient_panthenol_body, R.string.ingredient_panthenol_caution, IngredientRisk.GENERAL_CAUTION),
        IngredientEntry("colloidal_oatmeal", R.string.ingredient_colloidal_oatmeal, R.string.ingredient_category_skin_protectant, R.string.ingredient_colloidal_oatmeal_body, R.string.ingredient_colloidal_oatmeal_caution, IngredientRisk.GENERAL_CAUTION),
        IngredientEntry("niacinamide", R.string.ingredient_niacinamide, R.string.ingredient_category_vitamin, R.string.ingredient_niacinamide_body, R.string.ingredient_niacinamide_caution, IngredientRisk.GENERAL_CAUTION),
        IngredientEntry("fragrance", R.string.ingredient_fragrance, R.string.ingredient_category_fragrance, R.string.ingredient_fragrance_body, R.string.ingredient_fragrance_caution, IngredientRisk.GENERAL_CAUTION),
        IngredientEntry("lemon_juice", R.string.ingredient_lemon, R.string.ingredient_category_household, R.string.ingredient_lemon_body, R.string.ingredient_lemon_caution, IngredientRisk.AVOID_DIRECT_APPLICATION),
        IngredientEntry("zinc_oxide", R.string.ingredient_zinc_oxide, R.string.ingredient_category_uv_filter, R.string.ingredient_zinc_oxide_body, R.string.ingredient_zinc_oxide_caution, IngredientRisk.FINISHED_SUNSCREEN_ONLY),
        IngredientEntry("sunscreen", R.string.ingredient_sunscreen, R.string.ingredient_category_sun_protection, R.string.ingredient_sunscreen_body, R.string.ingredient_sunscreen_caution, IngredientRisk.FINISHED_SUNSCREEN_ONLY)
    )
}
