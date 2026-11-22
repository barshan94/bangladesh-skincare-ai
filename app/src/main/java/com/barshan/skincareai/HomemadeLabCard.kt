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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.barshan.skincareai.domain.FormulaCalculator
import com.barshan.skincareai.domain.FormulaError
import com.barshan.skincareai.domain.FormulaLine
import com.barshan.skincareai.domain.HomeProductType

private data class FormulaInput(val name: String, val percentage: String)

@Composable
internal fun HomemadeLabCard() {
    var productType by rememberSaveable { mutableStateOf(HomeProductType.ANHYDROUS_BALM.name) }
    var batchText by rememberSaveable { mutableStateOf("30") }
    var inputs by remember { mutableStateOf(listOf(FormulaInput("", ""), FormulaInput("", ""), FormulaInput("", ""))) }
    var showResult by rememberSaveable { mutableStateOf(false) }
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
                    }
                }
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
                    }
                }
            }
            Text(stringResource(R.string.lab_disclaimer), color = Color(0xFF8B4B31), style = MaterialTheme.typography.bodySmall)
        }
    }
}
