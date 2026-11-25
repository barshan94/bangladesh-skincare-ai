package com.barshan.skincareai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun HomeFormulationSafetyCard() {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    val options = listOf(
        R.string.formulation_water_based,
        R.string.formulation_oil_based,
        R.string.formulation_dry_powder,
        R.string.formulation_sunscreen,
        R.string.formulation_unsure
    )
    val advice = when (selected) {
        0 -> R.string.formulation_water_advice
        1 -> R.string.formulation_oil_advice
        2 -> R.string.formulation_powder_advice
        3 -> R.string.formulation_sunscreen_advice
        else -> R.string.formulation_unsure_advice
    }
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8EC)),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(stringResource(R.string.formulation_title), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFF765126))
            Text(stringResource(R.string.formulation_intro), color = Color(0xFF51483B))
            options.forEachIndexed { index, label ->
                FilterChip(
                    selected = selected == index,
                    onClick = { selected = index },
                    label = { Text(stringResource(label)) }
                )
            }
            Text(stringResource(advice), color = Color(0xFF51483B))
            Text(stringResource(R.string.formulation_non_recipe_note), color = Color(0xFF765126), fontWeight = FontWeight.SemiBold)
        }
    }
}
