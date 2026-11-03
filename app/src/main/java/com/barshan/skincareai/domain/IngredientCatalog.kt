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

data class IngredientSource(
    val title: String,
    val url: String
)

data class IngredientEntry(
    val id: String,
    val titleRes: Int,
    val categoryRes: Int,
    val descriptionRes: Int,
    val cautionRes: Int,
    val risk: IngredientRisk,
    val sources: List<IngredientSource>,
    val reviewStatus: IngredientReviewStatus = IngredientReviewStatus.PENDING_EXPERT_REVIEW
)

/**
 * Educational catalogue only. Source links support general ingredient education;
 * they do not constitute approval of a finished product, concentration or formula.
 * Every entry remains pending qualified expert review.
 */
object IngredientCatalog {
    private val aadDrySkin = IngredientSource(
        "American Academy of Dermatology — dry skin guidance",
        "https://www.aad.org/public/everyday-care/skin-care-basics/dry/dermatologists-tips-relieve-dry-skin"
    )
    private val dermnetBarrier = IngredientSource(
        "DermNet — barrier creams",
        "https://dermnetnz.org/topics/barrier-cream"
    )
    private val oatmealReview = IngredientSource(
        "Colloidal oatmeal safety and efficacy review (PMC)",
        "https://pmc.ncbi.nlm.nih.gov/articles/PMC3508548/"
    )
    private val contactDermatitis = IngredientSource(
        "MedlinePlus — contact dermatitis",
        "https://medlineplus.gov/ency/article/000869.htm"
    )
    private val lemonContact = IngredientSource(
        "ASCIA — allergic contact dermatitis",
        "https://www.allergy.org.au/patients/skin-allergy/contact-dermatitis"
    )
    private val aadSunscreen = IngredientSource(
        "American Academy of Dermatology — sunscreen labels",
        "https://www.aad.org/public/everyday-care/sun-protection/shade-clothing-sunscreen/understand-sunscreen-labels"
    )
    private val dermnetSunscreen = IngredientSource(
        "DermNet — sunscreen allergy",
        "https://dermnetnz.org/topics/sunscreen-allergy"
    )
    private val moisturizerReview = IngredientSource(
        "Journal of Drugs in Dermatology — moisturizer ingredient review",
        "https://s3.amazonaws.com/dl.jddonline.com/pdfs/23-6_eJDD.pdf"
    )

    val entries = listOf(
        IngredientEntry("glycerin", R.string.ingredient_glycerin, R.string.ingredient_category_humectant, R.string.ingredient_glycerin_body, R.string.ingredient_glycerin_caution, IngredientRisk.GENERAL_CAUTION, listOf(aadDrySkin, dermnetBarrier)),
        IngredientEntry("hyaluronic_acid", R.string.ingredient_hyaluronic_acid, R.string.ingredient_category_humectant, R.string.ingredient_hyaluronic_acid_body, R.string.ingredient_hyaluronic_acid_caution, IngredientRisk.GENERAL_CAUTION, listOf(aadDrySkin, dermnetBarrier)),
        IngredientEntry("ceramides", R.string.ingredient_ceramides, R.string.ingredient_category_barrier, R.string.ingredient_ceramides_body, R.string.ingredient_ceramides_caution, IngredientRisk.GENERAL_CAUTION, listOf(dermnetBarrier, moisturizerReview)),
        IngredientEntry("petrolatum", R.string.ingredient_petrolatum, R.string.ingredient_category_occlusive, R.string.ingredient_petrolatum_body, R.string.ingredient_petrolatum_caution, IngredientRisk.GENERAL_CAUTION, listOf(aadDrySkin, dermnetBarrier)),
        IngredientEntry("panthenol", R.string.ingredient_panthenol, R.string.ingredient_category_skin_conditioning, R.string.ingredient_panthenol_body, R.string.ingredient_panthenol_caution, IngredientRisk.GENERAL_CAUTION, listOf(moisturizerReview)),
        IngredientEntry("colloidal_oatmeal", R.string.ingredient_colloidal_oatmeal, R.string.ingredient_category_skin_protectant, R.string.ingredient_colloidal_oatmeal_body, R.string.ingredient_colloidal_oatmeal_caution, IngredientRisk.GENERAL_CAUTION, listOf(oatmealReview)),
        IngredientEntry("niacinamide", R.string.ingredient_niacinamide, R.string.ingredient_category_vitamin, R.string.ingredient_niacinamide_body, R.string.ingredient_niacinamide_caution, IngredientRisk.GENERAL_CAUTION, listOf(dermnetBarrier, moisturizerReview)),
        IngredientEntry("fragrance", R.string.ingredient_fragrance, R.string.ingredient_category_fragrance, R.string.ingredient_fragrance_body, R.string.ingredient_fragrance_caution, IngredientRisk.GENERAL_CAUTION, listOf(aadDrySkin, contactDermatitis)),
        IngredientEntry("lemon_juice", R.string.ingredient_lemon, R.string.ingredient_category_household, R.string.ingredient_lemon_body, R.string.ingredient_lemon_caution, IngredientRisk.AVOID_DIRECT_APPLICATION, listOf(lemonContact, contactDermatitis)),
        IngredientEntry("zinc_oxide", R.string.ingredient_zinc_oxide, R.string.ingredient_category_uv_filter, R.string.ingredient_zinc_oxide_body, R.string.ingredient_zinc_oxide_caution, IngredientRisk.FINISHED_SUNSCREEN_ONLY, listOf(aadSunscreen, dermnetSunscreen)),
        IngredientEntry("sunscreen", R.string.ingredient_sunscreen, R.string.ingredient_category_sun_protection, R.string.ingredient_sunscreen_body, R.string.ingredient_sunscreen_caution, IngredientRisk.FINISHED_SUNSCREEN_ONLY, listOf(aadSunscreen))
    )
}
