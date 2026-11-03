package com.barshan.skincareai.data

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LegacyPreferencesImporterTest {
    private lateinit var context: Context
    private lateinit var database: SkincareDatabase
    private lateinit var preferences: android.content.SharedPreferences

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, SkincareDatabase::class.java).build()
        preferences = context.getSharedPreferences("legacy_import_test", Context.MODE_PRIVATE)
        preferences.edit().clear().commit()
    }

    @After
    fun tearDown() {
        database.close()
        preferences.edit().clear().commit()
    }

    @Test
    fun importsRoutineAndProgressAndRemovesLegacyKeysAfterCommit() = runBlocking {
        preferences.edit()
            .putString("routine_done", """["morning_cleanse","evening_moisturize"]""")
            .putString(
                "progress_notes",
                """[{"id":"note-1","createdAt":1234,"concern":"Dryness","note":"Test","tolerance":"comfortable"}]"""
            )
            .commit()

        val result = LegacyPreferencesImporter(database, preferences).importIfNeeded()

        assertEquals(LegacyPreferencesImporter.ImportResult.Imported(2, 1), result)
        assertEquals(
            listOf("evening_moisturize", "morning_cleanse"),
            database.routineDao().observeCompletedIds().first()
        )
        val imported = database.progressNoteDao().observeAll().first()
        assertEquals("note-1", imported.single().id)
        assertEquals("Dryness", imported.single().concern)
        assertTrue(preferences.getString("routine_done", null) == null)
        assertTrue(preferences.getString("progress_notes", null) == null)
    }

    @Test
    fun malformedLegacyJsonIsPreservedForRecovery() = runBlocking {
        preferences.edit()
            .putString("routine_done", """["morning_cleanse"]""")
            .putString("progress_notes", """not-json""")
            .commit()

        val result = LegacyPreferencesImporter(database, preferences).importIfNeeded()

        assertEquals(
            LegacyPreferencesImporter.ImportResult.InvalidLegacyData("progress_notes"),
            result
        )
        assertEquals("""["morning_cleanse"]""", preferences.getString("routine_done", null))
        assertEquals("not-json", preferences.getString("progress_notes", null))
        assertTrue(database.routineDao().observeCompletedIds().first().isEmpty())
        assertTrue(database.progressNoteDao().observeAll().first().isEmpty())
    }

    @Test
    fun successfulImportIsIdempotent() = runBlocking {
        preferences.edit()
            .putString("routine_done", """["morning_cleanse"]""")
            .putString("progress_notes", "[]")
            .commit()
        val importer = LegacyPreferencesImporter(database, preferences)

        importer.importIfNeeded()
        val second = importer.importIfNeeded()

        assertEquals(LegacyPreferencesImporter.ImportResult.AlreadyImported, second)
        assertEquals(listOf("morning_cleanse"), database.routineDao().observeCompletedIds().first())
    }
}
