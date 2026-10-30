package com.barshan.skincareai

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
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

        val saveLabel = composeRule.activity.getString(R.string.scan_save_to_progress)
        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithText(saveLabel, useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText(saveLabel, useUnmergedTree = true)
            .performScrollTo()
            .performClick()

        composeRule.waitUntil(timeoutMillis = 10_000) {
            composeRule.onAllNodesWithText(
                composeRule.activity.getString(R.string.scan_saved_to_progress)
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
    fun homeContainsPrivacyAndSafetyGuidance() {
        waitForAppReady()
        assertTextExists(composeRule.activity.getString(R.string.local_first_title))
        assertTextExists(composeRule.activity.getString(R.string.safety_title))
        assertTextExists(composeRule.activity.getString(R.string.status_scaffold))
    }
}
