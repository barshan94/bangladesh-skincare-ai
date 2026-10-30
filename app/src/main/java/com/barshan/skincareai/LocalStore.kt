package com.barshan.skincareai

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class ProgressNote(
    val id: String,
    val createdAt: Long,
    val concern: String,
    val note: String,
    val tolerance: String
)

object LocalStore {
    private const val PREFS = "private_skincare_local"
    private const val ROUTINE_KEY = "routine_done"
    private const val PROGRESS_KEY = "progress_notes"
    private const val LANGUAGE_KEY = "language"
    private const val BUDGET_KEY = "budget"
    private const val SKIN_FEEL_KEY = "skin_feel"

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    fun language(context: Context): String = prefs(context).getString(LANGUAGE_KEY, "system") ?: "system"
    fun setLanguage(context: Context, value: String) { prefs(context).edit().putString(LANGUAGE_KEY, value).apply() }

    fun budget(context: Context): String = prefs(context).getString(BUDGET_KEY, "Not set") ?: "Not set"
    fun setBudget(context: Context, value: String) { prefs(context).edit().putString(BUDGET_KEY, value).apply() }

    fun skinFeel(context: Context): String = prefs(context).getString(SKIN_FEEL_KEY, "Not sure") ?: "Not sure"
    fun setSkinFeel(context: Context, value: String) { prefs(context).edit().putString(SKIN_FEEL_KEY, value).apply() }

    fun routineDone(context: Context): Set<String> {
        val raw = prefs(context).getString(ROUTINE_KEY, "[]") ?: "[]"
        return runCatching {
            val array = JSONArray(raw)
            buildSet { for (i in 0 until array.length()) add(array.getString(i)) }
        }.getOrDefault(emptySet())
    }

    fun setRoutineDone(context: Context, ids: Set<String>) {
        val array = JSONArray()
        ids.sorted().forEach(array::put)
        prefs(context).edit().putString(ROUTINE_KEY, array.toString()).apply()
    }

    fun progressNotes(context: Context): List<ProgressNote> {
        val raw = prefs(context).getString(PROGRESS_KEY, "[]") ?: "[]"
        return runCatching {
            val array = JSONArray(raw)
            (0 until array.length()).map { i ->
                val item = array.getJSONObject(i)
                ProgressNote(
                    id = item.optString("id"),
                    createdAt = item.optLong("createdAt"),
                    concern = item.optString("concern"),
                    note = item.optString("note"),
                    tolerance = item.optString("tolerance", "Not recorded")
                )
            }.sortedByDescending { it.createdAt }
        }.getOrDefault(emptyList())
    }

    fun addProgressNote(context: Context, concern: String, note: String, tolerance: String): ProgressNote {
        val created = ProgressNote(UUID.randomUUID().toString(), System.currentTimeMillis(), concern, note, tolerance)
        val existing = progressNotes(context)
        val array = JSONArray()
        (listOf(created) + existing).forEach { item ->
            array.put(JSONObject().apply {
                put("id", item.id)
                put("createdAt", item.createdAt)
                put("concern", item.concern)
                put("note", item.note)
                put("tolerance", item.tolerance)
            })
        }
        prefs(context).edit().putString(PROGRESS_KEY, array.toString()).apply()
        return created
    }

    fun deleteProgressNote(context: Context, id: String) {
        val array = JSONArray()
        progressNotes(context).filterNot { it.id == id }.forEach { item ->
            array.put(JSONObject().apply {
                put("id", item.id)
                put("createdAt", item.createdAt)
                put("concern", item.concern)
                put("note", item.note)
                put("tolerance", item.tolerance)
            })
        }
        prefs(context).edit().putString(PROGRESS_KEY, array.toString()).apply()
    }

    fun formatDate(timestamp: Long): String =
        SimpleDateFormat("dd MMM yyyy, h:mm a", Locale.getDefault()).format(Date(timestamp))

    fun clearUserData(context: Context) {
        val committed = prefs(context).edit()
            .remove(ROUTINE_KEY)
            .remove(PROGRESS_KEY)
            .remove(BUDGET_KEY)
            .remove(SKIN_FEEL_KEY)
            .commit()
        check(committed) { "Could not confirm local preference deletion" }
    }
}
