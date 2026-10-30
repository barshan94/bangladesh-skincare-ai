package com.barshan.skincareai.data

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.barshan.skincareai.ProgressNote
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SkincareRepositoryTest {
    private lateinit var database: SkincareDatabase
    private lateinit var repository: SkincareRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SkincareDatabase::class.java).build()
        repository = SkincareRepository(database)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun routineCompletionCanBeAddedAndRemoved() = runBlocking {
        repository.setRoutineCompleted("morning_cleanse", true)
        assertEquals(setOf("morning_cleanse"), repository.completedRoutineIds.first())

        repository.setRoutineCompleted("morning_cleanse", false)
        assertTrue(repository.completedRoutineIds.first().isEmpty())
    }

    @Test
    fun replacingRoutineSetIsAtomicAndRemovesOldIds() = runBlocking {
        repository.replaceCompletedRoutineIds(setOf("old"))
        repository.replaceCompletedRoutineIds(setOf("morning", "evening"))

        assertEquals(setOf("evening", "morning"), repository.completedRoutineIds.first())
    }

    @Test
    fun progressNotesCanBeAddedDeletedAndCleared() = runBlocking {
        val note = ProgressNote(
            id = "note-1",
            createdAt = 100L,
            concern = "Dryness",
            note = "A short observation",
            tolerance = "comfortable"
        )
        repository.addProgressNote(note)
        assertEquals(listOf(note), repository.progressNotes.first())

        repository.deleteProgressNote(note.id)
        assertTrue(repository.progressNotes.first().isEmpty())

        repository.addProgressNote(note)
        repository.replaceCompletedRoutineIds(setOf("morning"))
        database.migrationMetadataDao().put(
            MigrationMetadataEntity("shared_preferences_v1_import", "complete")
        )
        repository.clearPersonalRecords()

        assertTrue(repository.progressNotes.first().isEmpty())
        assertTrue(repository.completedRoutineIds.first().isEmpty())
        assertEquals("complete", database.migrationMetadataDao().valueFor("shared_preferences_v1_import"))
    }
}
