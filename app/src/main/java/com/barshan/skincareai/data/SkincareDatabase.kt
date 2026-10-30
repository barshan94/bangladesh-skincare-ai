package com.barshan.skincareai.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "routine_completions")
data class RoutineCompletionEntity(
    @PrimaryKey val routineId: String
)

@Entity(tableName = "progress_notes")
data class ProgressNoteEntity(
    @PrimaryKey val id: String,
    val createdAt: Long,
    val concern: String,
    val note: String,
    val tolerance: String
)

@Entity(tableName = "migration_metadata")
data class MigrationMetadataEntity(
    @PrimaryKey val key: String,
    val value: String
)

@Dao
interface RoutineDao {
    @Query("SELECT routineId FROM routine_completions ORDER BY routineId")
    fun observeCompletedIds(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RoutineCompletionEntity>)

    @Query("DELETE FROM routine_completions")
    suspend fun clear()
}

@Dao
interface ProgressNoteDao {
    @Query("SELECT * FROM progress_notes ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<ProgressNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ProgressNoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ProgressNoteEntity>)

    @Query("DELETE FROM progress_notes WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM progress_notes")
    suspend fun clear()
}

@Dao
interface MigrationMetadataDao {
    @Query("SELECT value FROM migration_metadata WHERE `key` = :key")
    suspend fun valueFor(key: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun put(item: MigrationMetadataEntity)
}

@Database(
    entities = [
        RoutineCompletionEntity::class,
        ProgressNoteEntity::class,
        MigrationMetadataEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class SkincareDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao
    abstract fun progressNoteDao(): ProgressNoteDao
    abstract fun migrationMetadataDao(): MigrationMetadataDao

    companion object {
        @Volatile private var instance: SkincareDatabase? = null

        fun getInstance(context: Context): SkincareDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    SkincareDatabase::class.java,
                    "skincare-local.db"
                ).build().also { instance = it }
            }
    }
}
