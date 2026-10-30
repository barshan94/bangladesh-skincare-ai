package com.barshan.skincareai.data

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.barshan.skincareai.LocalStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomRepositoryLifecycleTest {
    private val context: Context
        get() = InstrumentationRegistry.getInstrumentation().targetContext
    private val databaseName = "room-repository-lifecycle-test.db"
    private val preferencesName = "private_skincare_local"
    private lateinit var database: SkincareDatabase

    @Before
    fun setUp() {
        context.deleteDatabase(databaseName)
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
            .edit().clear().commit()
        database = openDatabase()
    }

    @After
    fun tearDown() {
        if (::database.isInitialized && database.isOpen) database.close()
        context.deleteDatabase(databaseName)
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
            .edit().clear().commit()
    }

    private fun openDatabase(): SkincareDatabase =
        Room.databaseBuilder(context, SkincareDatabase::class.java, databaseName).build()

    @Test
    fun migratedDataSurvivesDatabaseReopenAndExplicitResetPreservesLanguage() = runBlocking {
        val preferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        preferences.edit()
            .putString("language", "bn")
            .putString("budget", "৳500–৳1500")
            .putString("routine_done", """["morning_cleanse","evening_moisturize"]""")
            .putString(
                "progress_notes",
                """[{"id":"legacy-1","createdAt":1700000000000,"concern":"Dryness","note":"Legacy note","tolerance":"comfortable"}]"""
            )
            .commit()

        LegacyPreferencesMigration.migrateIfNeeded(context, database)
        var repository = RoomSkincareRepository(database)
        assertEquals(
            setOf("morning_cleanse", "evening_moisturize"),
            repository.observeRoutineDone().first()
        )
        assertEquals("legacy-1", repository.observeProgressNotes().first().single().id)

        database.close()
        database = openDatabase()
        repository = RoomSkincareRepository(database)

        // A reopened database retains the imported rows and migration marker.
        assertEquals(
            setOf("morning_cleanse", "evening_moisturize"),
            repository.observeRoutineDone().first()
        )
        assertEquals("Legacy note", repository.observeProgressNotes().first().single().note)

        // Re-running the importer after a simulated process restart is a no-op.
        preferences.edit().putString("routine_done", """["new-value-must-not-overwrite"]""").commit()
        LegacyPreferencesMigration.migrateIfNeeded(context, database)
        assertEquals(
            setOf("morning_cleanse", "evening_moisturize"),
            repository.observeRoutineDone().first()
        )

        val created = repository.addProgressNote("Oily skin", "Test note", "unsure")
        assertEquals(2, repository.observeProgressNotes().first().size)
        repository.deleteProgressNote(created.id)
        assertEquals(1, repository.observeProgressNotes().first().size)

        repository.clearUserData(context)
        assertEquals(emptySet<String>(), repository.observeRoutineDone().first())
        assertEquals(emptyList<Any>(), repository.observeProgressNotes().first())
        assertEquals("bn", LocalStore.language(context))
        assertEquals("Not set", LocalStore.budget(context))
        assertNull(preferences.getString("routine_done", null))
        assertNull(preferences.getString("progress_notes", null))
    }
}
