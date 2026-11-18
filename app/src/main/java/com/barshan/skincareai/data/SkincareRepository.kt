package com.barshan.skincareai.data

import androidx.room.withTransaction
import com.barshan.skincareai.ProgressNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Local persistence boundary for routine completions and progress notes.
 *
 * This repository deliberately does not perform migration or access Android preferences.
 * The application must complete LegacyPreferencesImporter before exposing these streams.
 */
class SkincareRepository(
    private val database: SkincareDatabase
) {
    val completedRoutineIds: Flow<Set<String>> =
        database.routineDao().observeCompletedIds().map { it.toSet() }

    val progressNotes: Flow<List<ProgressNote>> =
        database.progressNoteDao().observeAll().map { records ->
            records.map { record ->
                ProgressNote(
                    id = record.id,
                    createdAt = record.createdAt,
                    concern = record.concern,
                    note = record.note,
                    tolerance = record.tolerance
                )
            }
        }

    suspend fun setRoutineCompleted(routineId: String, completed: Boolean) {
        if (completed) {
            database.routineDao().insertAll(listOf(RoutineCompletionEntity(routineId)))
        } else {
            database.routineDao().deleteById(routineId)
        }
    }

    suspend fun replaceCompletedRoutineIds(ids: Set<String>) {
        database.withTransaction {
            database.routineDao().clear()
            database.routineDao().insertAll(ids.map(::RoutineCompletionEntity))
        }
    }

    suspend fun addProgressNote(note: ProgressNote) {
        database.progressNoteDao().insert(
            ProgressNoteEntity(
                id = note.id,
                createdAt = note.createdAt,
                concern = note.concern,
                note = note.note,
                tolerance = note.tolerance
            )
        )
    }

    suspend fun deleteProgressNote(id: String) {
        database.progressNoteDao().deleteById(id)
    }

    /** Clears personal routine/progress records but keeps migration metadata intact. */
    suspend fun clearPersonalRecords() {
        database.withTransaction {
            database.routineDao().clear()
            database.progressNoteDao().clear()
        }
    }
}
