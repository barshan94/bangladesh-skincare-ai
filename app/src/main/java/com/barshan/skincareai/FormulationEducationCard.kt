package com.barshan.skincareai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun FormulationEducationCard() {
    val sections = listOf(
        R.string.formulation_quality_title to R.string.formulation_quality_body,
        R.string.formulation_concentration_title to R.string.formulation_concentration_body,
        R.string.formulation_ph_title to R.string.formulation_ph_body,
        R.string.formulation_preservation_title to R.string.formulation_preservation_body,
        R.string.formulation_stability_title to R.string.formulation_stability_body
    )
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF4F0)),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                stringResource(R.string.formulation_education_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF315744)
            )
            Text(stringResource(R.string.formulation_education_intro), color = Color(0xFF43534A))
            sections.forEach { (title, body) ->
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(stringResource(title), fontWeight = FontWeight.SemiBold, color = Color(0xFF315744))
                    Text(stringResource(body), color = Color(0xFF43534A))
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(stringResource(R.string.formulation_release_title), fontWeight = FontWeight.SemiBold, color = Color(0xFF315744))
                Text(stringResource(R.string.formulation_release_body), color = Color(0xFF43534A))
            }
        }
    }
}
