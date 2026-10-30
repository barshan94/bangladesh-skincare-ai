package com.barshan.skincareai.data

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LegacyPreferencesMigrationTest {
    private val context: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var database: SkincareDatabase

    @Before
    fun setUp() {
        context.getSharedPreferences("private_skincare_local", Context.MODE_PRIVATE)
            .edit().clear().commit()
        database = Room.inMemoryDatabaseBuilder(context, SkincareDatabase::class.java).build()
    }

    @After
    fun tearDown() {
        database.close()
        context.getSharedPreferences("private_skincare_local", Context.MODE_PRIVATE)
            .edit().clear().commit()
    }

    @Test
    fun importsRoutineAndProgressAndKeepsLegacyCopyForRollback() = runBlocking {
        val preferences = context.getSharedPreferences("private_skincare_local", Context.MODE_PRIVATE)
        val routineJson = """["morning_cleanse","evening_moisturize"]"""
        val progressJson = """[{"id":"note-1","createdAt":1700000000000,"concern":"Dryness","note":"Felt comfortable","tolerance":"comfortable"}]"""
        preferences.edit()
            .putString("routine_done", routineJson)
            .putString("progress_notes", progressJson)
            .commit()

        LegacyPreferencesMigration.migrateIfNeeded(context, database)

        assertEquals(
            listOf("evening_moisturize", "morning_cleanse"),
            database.routineDao().observeCompletedIds().first()
        )
        val notes = database.progressNoteDao().observeAll().first()
        assertEquals(1, notes.size)
        assertEquals("note-1", notes.single().id)
        assertEquals("Dryness", notes.single().concern)
        assertEquals(routineJson, preferences.getString("routine_done", null))
        assertEquals(progressJson, preferences.getString("progress_notes", null))
    }

    @Test
    fun migrationMarkerMakesImportIdempotent() = runBlocking {
        val preferences = context.getSharedPreferences("private_skincare_local", Context.MODE_PRIVATE)
        preferences.edit().putString("routine_done", """["morning_cleanse"]""").commit()

        LegacyPreferencesMigration.migrateIfNeeded(context, database)
        preferences.edit().putString("routine_done", """["evening_cleanse"]""").commit()
        LegacyPreferencesMigration.migrateIfNeeded(context, database)

        assertEquals(
            listOf("morning_cleanse"),
            database.routineDao().observeCompletedIds().first()
        )
    }

    @Test
    fun malformedLegacyJsonFailsWithoutMarkingMigrationOrDeletingSource() = runBlocking {
        val preferences = context.getSharedPreferences("private_skincare_local", Context.MODE_PRIVATE)
        val malformed = """[{"id":"missing-created-at"}]"""
        preferences.edit().putString("progress_notes", malformed).commit()

        assertThrows(Exception::class.java) {
            runBlocking { LegacyPreferencesMigration.migrateIfNeeded(context, database) }
        }

        assertNull(database.migrationMetadataDao().valueFor("shared_preferences_v1_imported"))
        assertEquals(malformed, preferences.getString("progress_notes", null))
    }
}
