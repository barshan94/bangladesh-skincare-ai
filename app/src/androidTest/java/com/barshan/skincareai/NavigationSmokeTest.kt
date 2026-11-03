package com.barshan.skincareai

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationSmokeTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private fun waitForAppReady() {
        composeRule.waitUntil(timeoutMillis = 15_000) {
            composeRule.onAllNodesWithText(
                composeRule.activity.getString(R.string.nav_scan),
                useUnmergedTree = true
            ).fetchSemanticsNodes().isNotEmpty()
        }
    }

    private fun assertTextExists(text: String) {
        composeRule.waitForIdle()
        val nodes = composeRule.onAllNodesWithText(text, useUnmergedTree = true)
            .fetchSemanticsNodes()
        assert(nodes.isNotEmpty()) { "Expected text to exist in the UI semantics tree: $text" }
    }

    @Test
    fun everyBottomNavigationDestinationOpensItsOwnContent() {
        waitForAppReady()
        // Assert content that each destination actually renders. Some *_title
        // and *_body resources are legacy copy and are not used by these screens.
        val destinations = listOf(
            Triple(R.string.nav_scan, R.string.scan_title, R.string.scan_body),
            Triple(R.string.nav_routine, R.string.nav_routine, R.string.routine_intro),
            Triple(R.string.nav_progress, R.string.nav_progress, R.string.progress_intro),
            Triple(R.string.nav_ingredients, R.string.nav_ingredients, R.string.ingredients_intro),
            Triple(R.string.nav_profile, R.string.profile_title, R.string.profile_body)
        )

        destinations.forEach { (labelRes, titleRes, bodyRes) ->
            val label = composeRule.activity.getString(labelRes)
            composeRule.waitForIdle()
            val matchingNodes = composeRule.onAllNodesWithText(label)
                .fetchSemanticsNodes()
            assert(matchingNodes.isNotEmpty()) {
                "Expected bottom navigation label to exist: $label"
            }
            // The selected screen heading can duplicate the first tab label.
            composeRule.onAllNodesWithText(label)
                .get(matchingNodes.lastIndex)
                .performClick()
            assertTextExists(composeRule.activity.getString(titleRes))
            assertTextExists(composeRule.activity.getString(bodyRes))
        }
    }

    @Test
    fun selfReportedCheckInCanBeExplicitlySavedToRoomProgressHistory() {
        waitForAppReady()
        composeRule.onNodeWithText(
            composeRule.activity.getString(R.string.scan_get_guidance)
        ).performClick()

        // Wait for the guidance result itself before looking for its action.
        // The action is rendered only after guidance has been calculated.
        val guidanceTitle = composeRule.activity.getString(R.string.guidance_general)
        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithText(guidanceTitle, useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        val saveLabel = composeRule.activity.getString(R.string.scan_save_to_progress)
        composeRule.onNodeWithText(saveLabel, useUnmergedTree = true)
            .performScrollTo()
            .performClick()

        composeRule.waitUntil(timeoutMillis = 15_000) {
            composeRule.onAllNodesWithText(
                composeRule.activity.getString(R.string.scan_saved_to_progress),
                useUnmergedTree = true
            ).fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onAllNodesWithText(
            composeRule.activity.getString(R.string.nav_progress)
        ).get(0).performClick()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithText(
                composeRule.activity.getString(R.string.scan_saved_concern)
            ).fetchSemanticsNodes().isNotEmpty()
        }
        assertTextExists(composeRule.activity.getString(R.string.scan_saved_concern))
    }


    @Test
    fun progressDraftIsSavedAndDeletionRequiresConfirmation() {
        waitForAppReady()
        composeRule.onAllNodesWithText(
            composeRule.activity.getString(R.string.nav_progress)
        ).get(0).performClick()

        val uniqueConcern = "Progress UI test entry"
        composeRule.onNodeWithText(
            composeRule.activity.getString(R.string.progress_concern_label)
        ).performTextInput(uniqueConcern)
        composeRule.onNodeWithText(
            composeRule.activity.getString(R.string.progress_note_label)
        ).performTextInput("A temporary entry used to verify save and delete actions.")

        composeRule.onNodeWithText(
            composeRule.activity.getString(R.string.progress_save)
        ).performClick()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithText(uniqueConcern, useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onAllNodesWithContentDescription(
            composeRule.activity.getString(R.string.progress_delete),
            useUnmergedTree = true
        ).get(0).performClick()

        assertTextExists(composeRule.activity.getString(R.string.progress_delete_confirm_title))
        composeRule.onNodeWithText(
            composeRule.activity.getString(R.string.progress_delete_confirm_action),
            useUnmergedTree = true
        ).performClick()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithText(uniqueConcern, useUnmergedTree = true)
                .fetchSemanticsNodes().isEmpty()
        }
    }

    @Test
    fun homeContainsPrivacyAndSafetyGuidance() {
        waitForAppReady()
        assertTextExists(composeRule.activity.getString(R.string.local_first_title))
        assertTextExists(composeRule.activity.getString(R.string.safety_title))
        assertTextExists(composeRule.activity.getString(R.string.status_scaffold))
    }

    @Test
    fun ingredientRiskFilterShowsOnlyMatchingSafetyClass() {
        waitForAppReady()
        composeRule.onAllNodesWithText(
            composeRule.activity.getString(R.string.nav_ingredients)
        ).get(0).performClick()

        composeRule.onNodeWithText(
            composeRule.activity.getString(R.string.ingredients_filter_avoid)
        ).performClick()

        assertTextExists(composeRule.activity.getString(R.string.ingredient_lemon))
        val generalIngredient = composeRule.onAllNodesWithText(
            composeRule.activity.getString(R.string.ingredient_glycerin),
            useUnmergedTree = true
        ).fetchSemanticsNodes()
        assert(generalIngredient.isEmpty()) {
            "Avoid-direct-use filter must not show general-caution ingredients."
        }
    }

    @Test
    fun ingredientSearchShowsLocalizedEmptyStateWhenNothingMatches() {
        waitForAppReady()
        composeRule.onAllNodesWithText(
            composeRule.activity.getString(R.string.nav_ingredients)
        ).get(0).performClick()

        composeRule.onNodeWithText(
            composeRule.activity.getString(R.string.ingredients_search)
        ).performTextInput("no ingredient should match this phrase")

        assertTextExists(composeRule.activity.getString(R.string.ingredients_no_results))
    }

}
