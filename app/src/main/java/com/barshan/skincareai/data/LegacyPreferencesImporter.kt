package com.barshan.skincareai.data

import android.content.Context
import android.content.SharedPreferences
import androidx.room.withTransaction
import org.json.JSONArray

/**
 * Imports the prototype SharedPreferences records exactly once.
 *
 * The legacy keys are removed only after the Room transaction commits. If parsing or
 * insertion fails, the original preference values remain available for a safe retry.
 */
class LegacyPreferencesImporter(
    private val database: SkincareDatabase,
    private val preferences: SharedPreferences
) {
    suspend fun importIfNeeded(): ImportResult {
        val alreadyImported = database.migrationMetadataDao().valueFor(IMPORT_MARKER) == "complete"
        if (alreadyImported) {
            removeLegacyKeys()
            return ImportResult.AlreadyImported
        }

        val routineIds = parseRoutineIds(preferences.getString(ROUTINE_KEY, null))
            ?: return ImportResult.InvalidLegacyData(ROUTINE_KEY)
        val notes = parseProgressNotes(preferences.getString(PROGRESS_KEY, null))
            ?: return ImportResult.InvalidLegacyData(PROGRESS_KEY)

        return try {
            database.withTransaction {
                val marker = database.migrationMetadataDao().valueFor(IMPORT_MARKER)
                if (marker != "complete") {
                    database.routineDao().insertAll(routineIds.map(::RoutineCompletionEntity))
                    database.progressNoteDao().insertAll(notes)
                    database.migrationMetadataDao().put(
                        MigrationMetadataEntity(IMPORT_MARKER, "complete")
                    )
                }
            }
            removeLegacyKeys()
            ImportResult.Imported(routineIds.size, notes.size)
        } catch (error: Exception) {
            ImportResult.Failed(error)
        }
    }

    private fun parseRoutineIds(raw: String?): Set<String>? {
        if (raw == null) return emptySet()
        return try {
            val array = JSONArray(raw)
            buildSet {
                for (index in 0 until array.length()) {
                    val id = array.getString(index)
                    if (id.isBlank()) return null
                    add(id)
                }
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun parseProgressNotes(raw: String?): List<ProgressNoteEntity>? {
        if (raw == null) return emptyList()
        return try {
            val array = JSONArray(raw)
            buildList {
                for (index in 0 until array.length()) {
                    val item = array.getJSONObject(index)
                    val id = item.optString("id")
                    if (id.isBlank()) return null
                    add(
                        ProgressNoteEntity(
                            id = id,
                            createdAt = item.optLong("createdAt"),
                            concern = item.optString("concern"),
                            note = item.optString("note"),
                            tolerance = item.optString("tolerance", "Not recorded")
                        )
                    )
                }
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun removeLegacyKeys() {
        preferences.edit()
            .remove(ROUTINE_KEY)
            .remove(PROGRESS_KEY)
            .commit()
    }

    sealed interface ImportResult {
        data class Imported(val routineCount: Int, val progressCount: Int) : ImportResult
        data object AlreadyImported : ImportResult
        data class InvalidLegacyData(val key: String) : ImportResult
        data class Failed(val cause: Exception) : ImportResult
    }

    private companion object {
        const val IMPORT_MARKER = "shared_preferences_v1_import"
        const val ROUTINE_KEY = "routine_done"
        const val PROGRESS_KEY = "progress_notes"
    }
}
