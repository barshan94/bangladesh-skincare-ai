package com.barshan.skincareai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.barshan.skincareai.domain.FormulaCalculator
import com.barshan.skincareai.domain.FormulaError
import com.barshan.skincareai.domain.FormulaLine
import com.barshan.skincareai.domain.FormulaCostCalculator
import com.barshan.skincareai.domain.IngredientPrice
import com.barshan.skincareai.domain.HomeProductType

private data class FormulaInput(val name: String, val percentage: String, val pricePer100g: String = "")

@Composable
internal fun HomemadeLabCard() {
    var productType by rememberSaveable { mutableStateOf(HomeProductType.ANHYDROUS_BALM.name) }
    var batchText by rememberSaveable { mutableStateOf("30") }
    var inputs by remember { mutableStateOf(listOf(FormulaInput("", "", ""), FormulaInput("", "", ""), FormulaInput("", "", ""))) }
    var showResult by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    var draftSaved by rememberSaveable { mutableStateOf(FormulaDraftStore.load(context) != null) }
    var draftMessage by rememberSaveable { mutableStateOf(0) }
    val selected = HomeProductType.valueOf(productType)
    val parsedLines = inputs.filter { it.name.isNotBlank() || it.percentage.isNotBlank() }.map {
        FormulaLine(it.name, it.percentage.toDoubleOrNull() ?: Double.NaN)
    }
    val calculation = FormulaCalculator.calculate(batchText.toDoubleOrNull() ?: Double.NaN, parsedLines)

    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(18.dp)) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(stringResource(R.string.lab_title), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFF486B5A))
            Text(stringResource(R.string.lab_intro), color = Color(0xFF5D6962))
            Text(stringResource(R.string.lab_product_type), fontWeight = FontWeight.SemiBold)
            listOf(
                HomeProductType.ANHYDROUS_BALM to R.string.lab_anhydrous,
                HomeProductType.WATER_BASED_SERUM to R.string.lab_water_serum,
                HomeProductType.CREAM_OR_LOTION to R.string.lab_cream,
                HomeProductType.SUNSCREEN to R.string.lab_sunscreen
            ).forEach { (type, label) ->
                FilterChip(
                    selected = selected == type,
                    onClick = { productType = type.name; showResult = false },
                    label = { Text(stringResource(label)) }
                )
            }
            if (!selected.canCalculateDraft) {
                val gateText = if (selected == HomeProductType.SUNSCREEN) R.string.lab_sunscreen_block else R.string.lab_water_block
                Text(stringResource(gateText), color = Color(0xFF8B4B31), fontWeight = FontWeight.SemiBold)
            } else {
                Text(stringResource(R.string.lab_anhydrous_notice), color = Color(0xFF765126))
                OutlinedTextField(
                    value = batchText,
                    onValueChange = { batchText = it; showResult = false },
                    label = { Text(stringResource(R.string.lab_batch_size)) },
                    suffix = { Text("g") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Text(stringResource(R.string.lab_ingredient_rows), fontWeight = FontWeight.SemiBold)
                inputs.forEachIndexed { index, input ->
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        OutlinedTextField(
                            value = input.name,
                            onValueChange = { value -> inputs = inputs.toMutableList().also { it[index] = input.copy(name = value) }; showResult = false },
                            label = { Text(stringResource(R.string.lab_ingredient_name)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = input.percentage,
                            onValueChange = { value -> inputs = inputs.toMutableList().also { it[index] = input.copy(percentage = value) }; showResult = false },
                            label = { Text(stringResource(R.string.lab_percentage)) },
                            suffix = { Text("%") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = input.pricePer100g,
                            onValueChange = { value -> inputs = inputs.toMutableList().also { it[index] = input.copy(pricePer100g = value) }; showResult = false },
                            label = { Text(stringResource(R.string.lab_price_per_100g)) },
                            suffix = { Text("BDT") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {
                            val valid = calculation.error == null && selected == HomeProductType.ANHYDROUS_BALM
                            if (valid) {
                                val draft = FormulaDraft(
                                    productType = selected.name,
                                    batchGrams = batchText,
                                    lines = inputs.filter { it.name.isNotBlank() || it.percentage.isNotBlank() }.map {
                                        FormulaDraftLine(it.name, it.percentage, it.pricePer100g)
                                    }
                                )
                                draftMessage = if (FormulaDraftStore.save(context, draft)) 1 else 3
                                draftSaved = draftMessage == 1
                            } else draftMessage = 3
                        },
                        enabled = calculation.error == null && selected == HomeProductType.ANHYDROUS_BALM,
                        modifier = Modifier.weight(1f)
                    ) { Text(stringResource(R.string.lab_save_draft)) }
                    Button(
                        onClick = {
                            val draft = FormulaDraftStore.load(context)
                            if (draft != null) {
                                productType = draft.productType
                                batchText = draft.batchGrams
                                inputs = draft.lines.map { FormulaInput(it.name, it.percentage, it.pricePer100g) }
                                showResult = false
                                draftMessage = 2
                            } else draftMessage = 3
                        },
                        enabled = draftSaved,
                        modifier = Modifier.weight(1f)
                    ) { Text(stringResource(R.string.lab_load_draft)) }
                }
                if (draftSaved) {
                    Button(
                        onClick = {
                            draftMessage = if (FormulaDraftStore.clear(context)) 4 else 3
                            draftSaved = draftMessage != 4
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(stringResource(R.string.lab_delete_draft)) }
                }
                if (draftMessage != 0) Text(stringResource(when (draftMessage) {
                    1 -> R.string.lab_draft_saved
                    2 -> R.string.lab_draft_loaded
                    4 -> R.string.lab_draft_deleted
                    else -> R.string.lab_draft_error
                }), color = if (draftMessage == 3) Color(0xFF8B4B31) else Color(0xFF486B5A))
                if (inputs.size < 8) {
                    Button(onClick = { inputs = inputs + FormulaInput("", ""); showResult = false }, modifier = Modifier.fillMaxWidth()) {
                        Text(stringResource(R.string.lab_add_ingredient))
                    }
                }
                Text(stringResource(R.string.lab_total, parsedLines.sumOf { if (it.percentage.isFinite()) it.percentage else 0.0 }), color = Color(0xFF486B5A), fontWeight = FontWeight.SemiBold)
                Button(
                    onClick = { showResult = true },
                    enabled = parsedLines.isNotEmpty() && inputs.none { it.name.isBlank() xor it.percentage.isBlank() },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(stringResource(R.string.lab_calculate)) }
                if (showResult) {
                    if (calculation.error != null) {
                        val message = when (calculation.error) {
                            FormulaError.INVALID_BATCH_SIZE -> R.string.lab_error_batch
                            FormulaError.TOO_MANY_LINES -> R.string.lab_error_lines
                            FormulaError.EMPTY_INGREDIENT -> R.string.lab_error_name
                            FormulaError.INVALID_PERCENTAGE -> R.string.lab_error_percentage
                            FormulaError.TOTAL_NOT_100 -> R.string.lab_error_total
                        }
                        Text(stringResource(message), color = Color(0xFF8B4B31), fontWeight = FontWeight.SemiBold)
                    } else {
                        calculation.amounts.forEach { amount ->
                            Text(stringResource(R.string.lab_result_line, amount.ingredient, amount.percentage, amount.grams), color = Color(0xFF1E2924))
                        }
                        Text(stringResource(R.string.lab_result_total, calculation.totalPercentage), fontWeight = FontWeight.Bold)
                        val activeInputs = inputs.filter { it.name.isNotBlank() || it.percentage.isNotBlank() }
                        val prices = activeInputs.map { IngredientPrice(it.pricePer100g.toDoubleOrNull() ?: Double.NaN) }
                        val estimatedCost = FormulaCostCalculator.calculate(calculation.amounts, prices)
                        if (estimatedCost == null) {
                            Text(stringResource(R.string.lab_cost_enter_prices), color = Color(0xFF765126))
                        } else {
                            Text(stringResource(R.string.lab_cost_result, estimatedCost), fontWeight = FontWeight.Bold, color = Color(0xFF486B5A))
                            Text(stringResource(R.string.lab_cost_excludes), color = Color(0xFF5D6962), style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
            Text(stringResource(R.string.lab_disclaimer), color = Color(0xFF8B4B31), style = MaterialTheme.typography.bodySmall)
        }
    }
}
