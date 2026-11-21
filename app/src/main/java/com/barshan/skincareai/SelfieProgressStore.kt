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

    private fun directory(context: Context): File = File(context.filesDir, "selfie-progress")

    fun list(context: Context): List<SelfieRecord> {
        val json = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY_RECORDS, "[]") ?: "[]"
        return runCatching {
            val array = JSONArray(json)
            (0 until array.length()).mapNotNull { index ->
                val item = array.optJSONObject(index) ?: return@mapNotNull null
                val id = item.optString("id")
                val fileName = item.optString("fileName")
                val capturedAt = item.optLong("capturedAt")
                // Records may only address files created by this store. Reject malformed
                // metadata rather than allowing arbitrary paths inside the app sandbox.
                SelfieRecord(id, fileName, capturedAt).takeIf {
                    runCatching { UUID.fromString(id) }.isSuccess &&
                        fileName == "$id.jpg" && capturedAt > 0L
                }
            }.filter { file(context, it).isFile }
                .sortedByDescending { it.capturedAt }
        }.getOrDefault(emptyList())
    }

    fun save(context: Context, source: File): SelfieRecord {
        require(source.exists() && source.isFile && source.length() > 0L) {
            "Captured image is missing, not a file, or empty."
        }
        val directory = directory(context).apply {
            check(exists() || mkdirs()) { "Cannot create private photo directory." }
            check(isDirectory) { "Private photo path is not a directory." }
        }
        val id = UUID.randomUUID().toString()
        val target = File(directory, "$id.jpg")
        val staging = File(directory, ".$id.tmp")
        try {
            source.copyTo(staging, overwrite = false)
            check(staging.renameTo(target)) { "Could not finalize private photo file." }
        } catch (failure: Exception) {
            staging.delete()
            target.delete()
            throw failure
        }

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

    fun file(context: Context, record: SelfieRecord): File {
        require(runCatching { UUID.fromString(record.id) }.isSuccess && record.fileName == "${record.id}.jpg") {
            "Invalid private photo record."
        }
        return File(directory(context), record.fileName)
    }

    fun delete(context: Context, record: SelfieRecord) {
        val photo = file(context, record)
        check(!photo.exists() || photo.delete()) { "Could not delete private photo file." }

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
        check(
            context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putString(KEY_RECORDS, array.toString()).commit()
        ) { "Could not update private photo metadata." }
    }
}
