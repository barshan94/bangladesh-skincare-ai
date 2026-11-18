package com.barshan.skincareai

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.UUID

internal data class SelfieRecord(
    val id: String,
    val fileName: String,
    val capturedAt: Long
)

internal object SelfieProgressStore {
    private const val PREFS = "private_selfie_progress"
    private const val KEY_RECORDS = "records"
    private const val MAX_RECORDS = 30

    fun list(context: Context): List<SelfieRecord> {
        val json = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY_RECORDS, "[]") ?: "[]"
        return runCatching {
            val array = JSONArray(json)
            (0 until array.length()).mapNotNull { index ->
                val item = array.optJSONObject(index) ?: return@mapNotNull null
                SelfieRecord(
                    id = item.optString("id"),
                    fileName = item.optString("fileName"),
                    capturedAt = item.optLong("capturedAt")
                ).takeIf { it.id.isNotBlank() && it.fileName.isNotBlank() && it.capturedAt > 0L }
            }.sortedByDescending { it.capturedAt }
        }.getOrDefault(emptyList())
    }

    fun save(context: Context, source: File): SelfieRecord {
        require(source.exists() && source.length() > 0L) { "Captured image is missing or empty." }
        val directory = File(context.filesDir, "selfie-progress").apply {
            check(exists() || mkdirs()) { "Cannot create private photo directory." }
        }
        val id = UUID.randomUUID().toString()
        val target = File(directory, "$id.jpg")
        source.copyTo(target, overwrite = false)
        val record = SelfieRecord(id, target.name, System.currentTimeMillis())
        val updated = (list(context) + record).sortedByDescending { it.capturedAt }
        val retained = updated.take(MAX_RECORDS)
        val array = JSONArray()
        retained.forEach { item ->
            array.put(
                JSONObject()
                    .put("id", item.id)
                    .put("fileName", item.fileName)
                    .put("capturedAt", item.capturedAt)
            )
        }
        val saved = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit().putString(KEY_RECORDS, array.toString()).commit()
        if (!saved) {
            target.delete()
            error("Could not save photo metadata.")
        }
        updated.drop(MAX_RECORDS).forEach { old -> file(context, old).delete() }
        source.delete()
        return record
    }

    fun file(context: Context, record: SelfieRecord): File =
        File(File(context.filesDir, "selfie-progress"), record.fileName)

    fun delete(context: Context, record: SelfieRecord) {
        file(context, record).delete()
        val retained = list(context).filterNot { it.id == record.id }
        val array = JSONArray()
        retained.forEach { item ->
            array.put(
                JSONObject()
                    .put("id", item.id)
                    .put("fileName", item.fileName)
                    .put("capturedAt", item.capturedAt)
            )
        }
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit().putString(KEY_RECORDS, array.toString()).apply()
    }
}
