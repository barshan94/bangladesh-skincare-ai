package com.barshan.skincareai

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationSmokeTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private fun assertTextExists(text: String) {
        composeRule.waitForIdle()
        val nodes = composeRule.onAllNodesWithText(text, useUnmergedTree = true)
            .fetchSemanticsNodes()
        assert(nodes.isNotEmpty()) { "Expected text to exist in the UI semantics tree: $text" }
    }

    @Test
    fun everyBottomNavigationDestinationOpensItsOwnContent() {
        val destinations = listOf(
            Triple(R.string.nav_scan, R.string.scan_title, R.string.scan_body),
            Triple(R.string.nav_routine, R.string.routine_title, R.string.routine_body),
            Triple(R.string.nav_progress, R.string.progress_title, R.string.progress_body),
            Triple(R.string.nav_ingredients, R.string.ingredients_title, R.string.ingredients_body),
            Triple(R.string.nav_profile, R.string.profile_title, R.string.profile_body)
        )

        destinations.forEach { (labelRes, titleRes, bodyRes) ->
            val label = composeRule.activity.getString(labelRes)
            composeRule.onAllNodesWithText(label).get(1).performClick()
            assertTextExists(composeRule.activity.getString(titleRes))
            assertTextExists(composeRule.activity.getString(bodyRes))
        }
    }

    @Test
    fun homeContainsPrivacyAndSafetyGuidance() {
        assertTextExists(composeRule.activity.getString(R.string.local_first_title))
        assertTextExists(composeRule.activity.getString(R.string.safety_title))
        assertTextExists(composeRule.activity.getString(R.string.status_scaffold))
    }
}
