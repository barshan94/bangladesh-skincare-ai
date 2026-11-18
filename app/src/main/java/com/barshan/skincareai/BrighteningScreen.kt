package com.barshan.skincareai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.sp

private val BrighteningInk = Color(0xFF1E2924)
private val BrighteningMuted = Color(0xFF5D6962)
private val BrighteningAccent = Color(0xFF486B5A)

@Composable
internal fun BrighteningScreen() {
    var selectedGoal by rememberSaveable { mutableIntStateOf(R.string.brightening_goal_unsure) }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            stringResource(R.string.brightening_intro),
            color = BrighteningMuted,
            style = MaterialTheme.typography.bodyMedium
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    stringResource(R.string.brightening_goal_prompt),
                    color = BrighteningInk,
                    fontWeight = FontWeight.SemiBold
                )
                listOf(
                    R.string.brightening_goal_acne,
                    R.string.brightening_goal_tan,
                    R.string.brightening_goal_patches,
                    R.string.brightening_goal_unsure
                ).forEach { option ->
                    FilterChip(
                        selected = selectedGoal == option,
                        onClick = { selectedGoal = option },
                        label = { Text(stringResource(option)) }
                    )
                }
                val explanation = when (selectedGoal) {
                    R.string.brightening_goal_acne -> R.string.brightening_goal_acne_body
                    R.string.brightening_goal_tan -> R.string.brightening_goal_tan_body
                    R.string.brightening_goal_patches -> R.string.brightening_goal_patches_body
                    else -> R.string.brightening_goal_unsure_body
                }
                Text(stringResource(explanation), color = BrighteningMuted, fontSize = 13.sp)
            }
        }

        SelfieProgressCard()
        BrighteningInfoCard(
            R.string.brightening_morning_title,
            R.string.brightening_morning_body
        )
        BrighteningInfoCard(
            R.string.brightening_sunscreen_title,
            R.string.brightening_sunscreen_body
        )
        BrighteningInfoCard(
            R.string.brightening_evening_title,
            R.string.brightening_evening_body
        )
        BrighteningInfoCard(
            R.string.brightening_actives_title,
            R.string.brightening_actives_body
        )
        BrighteningInfoCard(
            R.string.brightening_avoid_title,
            R.string.brightening_avoid_body,
            warning = true
        )
        BrighteningInfoCard(
            R.string.brightening_timeline_title,
            R.string.brightening_timeline_body
        )
        Text(
            stringResource(R.string.brightening_disclaimer),
            color = BrighteningMuted,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun BrighteningInfoCard(titleRes: Int, bodyRes: Int, warning: Boolean = false) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (warning) Color(0xFFFFF3EC) else Color.White
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Text(
                stringResource(titleRes),
                color = if (warning) Color(0xFF8B4B31) else BrighteningAccent,
                fontWeight = FontWeight.Bold
            )
            Text(stringResource(bodyRes), color = BrighteningInk, fontSize = 14.sp)
        }
    }
}
