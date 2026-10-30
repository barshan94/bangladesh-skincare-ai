package com.barshan.skincareai.data

import android.content.Context
import androidx.room.withTransaction
import org.json.JSONArray

/**
 * Imports the pre-Room SharedPreferences payload exactly once.
 *
 * Legacy values are deliberately retained after import. This gives us a rollback
 * window and ensures a successful database write never destroys the only copy
 * of a user's old data. They are removed only by the explicit user-data reset.
 */
object LegacyPreferencesMigration {
    private const val PREFS_NAME = "private_skincare_local"
    private const val ROUTINE_KEY = "routine_done"
    private const val PROGRESS_KEY = "progress_notes"
    private const val MIGRATION_KEY = "shared_preferences_v1_imported"
    private const val MIGRATION_VERSION = "1"

    suspend fun migrateIfNeeded(context: Context, database: SkincareDatabase) {
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if (database.migrationMetadataDao().valueFor(MIGRATION_KEY) == MIGRATION_VERSION) return

        // Parse before opening the transaction. Malformed legacy data must not
        // set the marker or overwrite the only existing copy.
        val routineIds = parseRoutineIds(preferences.getString(ROUTINE_KEY, "[]") ?: "[]")
        val progressNotes = parseProgressNotes(preferences.getString(PROGRESS_KEY, "[]") ?: "[]")

        database.withTransaction {
            // Recheck inside the transaction so simultaneous app starts import once.
            if (database.migrationMetadataDao().valueFor(MIGRATION_KEY) != MIGRATION_VERSION) {
                database.routineDao().insertAll(routineIds.map(::RoutineCompletionEntity))
                database.progressNoteDao().insertAll(progressNotes)
                database.migrationMetadataDao().put(
                    MigrationMetadataEntity(MIGRATION_KEY, MIGRATION_VERSION)
                )
            }
        }
    }

    private fun parseRoutineIds(raw: String): Set<String> {
        val array = JSONArray(raw)
        return buildSet {
            for (index in 0 until array.length()) {
                val id = array.getString(index)
                require(id.isNotBlank()) { "Legacy routine contains a blank ID" }
                add(id)
            }
        }
    }

    private fun parseProgressNotes(raw: String): List<ProgressNoteEntity> {
        val array = JSONArray(raw)
        return (0 until array.length()).map { index ->
            val item = array.getJSONObject(index)
            val id = item.optString("id")
            require(id.isNotBlank()) { "Legacy progress note contains a blank ID" }
            ProgressNoteEntity(
                id = id,
                createdAt = item.optLong("createdAt", 0L),
                concern = item.optString("concern"),
                note = item.optString("note"),
                tolerance = item.optString("tolerance", "Not recorded")
            )
        }
    }
}
