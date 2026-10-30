package com.barshan.skincareai

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalStoreTest {
    private val context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun routineCompletionRoundTripsAndCanBeCleared() {
        val completed = setOf("morning_cleanse", "evening_cleanse")

        LocalStore.setRoutineDone(context, completed)
        assertEquals(completed, LocalStore.routineDone(context))

        LocalStore.setRoutineDone(context, emptySet())
        assertTrue(LocalStore.routineDone(context).isEmpty())
    }

    @Test
    fun progressNoteCanBeCreatedReadAndDeleted() {
        val created = LocalStore.addProgressNote(
            context = context,
            concern = "Dryness",
            note = "Test note for local persistence",
            tolerance = "Comfortable"
        )

        try {
            val stored = LocalStore.progressNotes(context).firstOrNull { it.id == created.id }
            assertEquals(created, stored)
        } finally {
            LocalStore.deleteProgressNote(context, created.id)
        }

        assertFalse(LocalStore.progressNotes(context).any { it.id == created.id })
    }

    @Test
    fun clearingUserDataRemovesProgressRoutineAndPreferencesButKeepsLanguage() {
        LocalStore.setRoutineDone(context, setOf("morning_cleanse"))
        val note = LocalStore.addProgressNote(context, "Test", "Temporary", "Not recorded")
        LocalStore.setBudget(context, "BDT 1000")
        LocalStore.setSkinFeel(context, "Dry")
        LocalStore.setLanguage(context, "bn")

        LocalStore.clearUserData(context)

        assertTrue(LocalStore.routineDone(context).isEmpty())
        assertFalse(LocalStore.progressNotes(context).any { it.id == note.id })
        assertEquals("Not set", LocalStore.budget(context))
        assertEquals("Not sure", LocalStore.skinFeel(context))
        assertEquals("bn", LocalStore.language(context))
    }
}
