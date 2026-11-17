package com.barshan.skincareai.data

import android.content.Context
import androidx.room.withTransaction
import com.barshan.skincareai.LocalStore
import com.barshan.skincareai.ProgressNote
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Room-backed source of truth for routine completion and progress history.
 * Profile preferences intentionally remain in SharedPreferences.
 */
class RoomSkincareRepository(private val database: SkincareDatabase) {
    fun observeRoutineDone(): Flow<Set<String>> =
        database.routineDao().observeCompletedIds().map { it.toSet() }

    suspend fun setRoutineDone(ids: Set<String>) {
        database.withTransaction {
            database.routineDao().clear()
            if (ids.isNotEmpty()) {
                database.routineDao().insertAll(ids.map(::RoutineCompletionEntity))
            }
        }
    }

    fun observeProgressNotes(): Flow<List<ProgressNote>> =
        database.progressNoteDao().observeAll().map { entities ->
            entities.map { entity ->
                ProgressNote(
                    id = entity.id,
                    createdAt = entity.createdAt,
                    concern = entity.concern,
                    note = entity.note,
                    tolerance = entity.tolerance
                )
            }
        }

    suspend fun addProgressNote(
        concern: String,
        note: String,
        tolerance: String
    ): ProgressNote {
        val created = ProgressNote(
            id = UUID.randomUUID().toString(),
            createdAt = System.currentTimeMillis(),
            concern = concern,
            note = note,
            tolerance = tolerance
        )
        database.progressNoteDao().insert(
            ProgressNoteEntity(
                id = created.id,
                createdAt = created.createdAt,
                concern = created.concern,
                note = created.note,
                tolerance = created.tolerance
            )
        )
        return created
    }

    suspend fun updateProgressNote(
        existing: ProgressNote,
        concern: String,
        note: String,
        tolerance: String
    ) {
        database.progressNoteDao().insert(
            ProgressNoteEntity(
                id = existing.id,
                createdAt = existing.createdAt,
                concern = concern,
                note = note,
                tolerance = tolerance
            )
        )
    }

    suspend fun deleteProgressNote(id: String) {
        database.progressNoteDao().deleteById(id)
    }

    suspend fun clearUserData(context: Context) {
        // Room is the primary source of truth after migration. Clear the legacy
        // recovery snapshot first, then clear Room atomically. If the Room
        // transaction fails, the primary records remain available for retry.
        LocalStore.clearUserData(context)
        database.withTransaction {
            database.routineDao().clear()
            database.progressNoteDao().clear()
        }
    }
}
