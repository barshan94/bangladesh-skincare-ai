package com.barshan.skincareai.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IngredientCatalogTest {
    @Test
    fun everyEntryHasUniqueIdAndExplicitCaution() {
        val entries = IngredientCatalog.entries
        assertTrue(entries.isNotEmpty())
        assertEquals(entries.size, entries.map { it.id }.toSet().size)
        assertTrue(entries.all { it.cautionRes != 0 })
        assertTrue(entries.all { it.descriptionRes != 0 })
    }

    @Test
    fun everyEntryRemainsBlockedPendingQualifiedReview() {
        assertFalse(IngredientCatalog.entries.isEmpty())
        assertTrue(
            IngredientCatalog.entries.all {
                it.reviewStatus == IngredientReviewStatus.PENDING_EXPERT_REVIEW
            }
        )
    }

    @Test
    fun everyIngredientHasAtLeastOnePublisherSourceWithHttpsUrl() {
        assertTrue(
            IngredientCatalog.entries.all { entry ->
                entry.sources.isNotEmpty() &&
                    entry.sources.all { source ->
                        source.title.isNotBlank() && source.url.startsWith("https://")
                    }
            }
        )
    }

    @Test
    fun sourceReferencesAreUniqueWithinEachIngredient() {
        IngredientCatalog.entries.forEach { entry ->
            assertEquals(
                entry.sources.size,
                entry.sources.map { it.url }.toSet().size
            )
        }
    }

    @Test
    fun darkSpotIngredientsArePresentAndRemainUnapprovedPendingReview() {
        val expected = setOf("azelaic_acid", "glycolic_acid", "kojic_acid", "retinoids", "vitamin_c")
        val actual = IngredientCatalog.entries.filter { it.id in expected }
        assertEquals(expected, actual.map { it.id }.toSet())
        assertTrue(actual.all { it.sources.any { source -> source.url.contains("aad.org") } })
        assertTrue(actual.all { it.reviewStatus == IngredientReviewStatus.PENDING_EXPERT_REVIEW })
        assertTrue(actual.all { it.risk == IngredientRisk.GENERAL_CAUTION })
    }

    @Test
    fun householdLemonAndRawUvFilterHaveStricterSafetyClassification() {
        val lemon = IngredientCatalog.entries.single { it.id == "lemon_juice" }
        val zincOxide = IngredientCatalog.entries.single { it.id == "zinc_oxide" }

        assertEquals(IngredientRisk.AVOID_DIRECT_APPLICATION, lemon.risk)
        assertEquals(IngredientRisk.FINISHED_SUNSCREEN_ONLY, zincOxide.risk)
    }
}
