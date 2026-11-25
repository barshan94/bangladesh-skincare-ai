package com.barshan.skincareai

import com.barshan.skincareai.domain.HomeProductType

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class FormulaDraftLine(val name: String, val percentage: String, val pricePer100g: String)
data class FormulaDraft(val productType: String, val batchGrams: String, val lines: List<FormulaDraftLine>)

/** Stores one private, user-authored worksheet draft; it does not certify a formula. */
object FormulaDraftStore {
    private const val PREFS = "private_formula_drafts"
    private const val KEY = "latest_draft"

    fun save(context: Context, draft: FormulaDraft): Boolean {
        if (draft.productType != HomeProductType.ANHYDROUS_BALM.name || draft.lines.isEmpty() || draft.lines.size > 8) return false
        val json = JSONObject().apply {
            put("productType", draft.productType)
            put("batchGrams", draft.batchGrams.take(20))
            put("lines", JSONArray().apply {
                draft.lines.forEach { line -> put(JSONObject().apply {
                    put("name", line.name.take(100))
                    put("percentage", line.percentage.take(20))
                    put("pricePer100g", line.pricePer100g.take(20))
                }) }
            })
        }
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putString(KEY, json.toString()).commit()
    }

    fun load(context: Context): FormulaDraft? {
        val raw = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, null) ?: return null
        return runCatching {
            val json = JSONObject(raw)
            val type = json.getString("productType")
            require(type == HomeProductType.ANHYDROUS_BALM.name)
            val array = json.getJSONArray("lines")
            require(array.length() in 1..8)
            FormulaDraft(
                productType = type,
                batchGrams = json.getString("batchGrams"),
                lines = (0 until array.length()).map { index ->
                    val line = array.getJSONObject(index)
                    FormulaDraftLine(line.getString("name"), line.getString("percentage"), line.optString("pricePer100g"))
                }
            )
        }.getOrNull()
    }

    fun clear(context: Context): Boolean = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().remove(KEY).commit()
}
