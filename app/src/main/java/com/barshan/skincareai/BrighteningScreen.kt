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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barshan.skincareai.domain.CheckIn
import com.barshan.skincareai.domain.GuidanceResult
import com.barshan.skincareai.domain.SkinFeel
import com.barshan.skincareai.domain.SkinGoal
import com.barshan.skincareai.domain.SkincareGuidance

private val BrighteningInk = Color(0xFF1E2924)
private val BrighteningMuted = Color(0xFF5D6962)
private val BrighteningAccent = Color(0xFF486B5A)

@Composable
internal fun BrighteningScreen() {
    var selectedGoal by rememberSaveable { mutableIntStateOf(R.string.brightening_goal_unsure) }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(stringResource(R.string.brightening_title), color = BrighteningInk, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(stringResource(R.string.brightening_body), color = BrighteningMuted, style = MaterialTheme.typography.bodyMedium)
        Text(stringResource(R.string.brightening_intro), color = BrighteningMuted, style = MaterialTheme.typography.bodyMedium)

        Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(20.dp)) {
            Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(stringResource(R.string.brightening_goal_prompt), color = BrighteningInk, fontWeight = FontWeight.SemiBold)
                listOf(R.string.brightening_goal_acne, R.string.brightening_goal_tan, R.string.brightening_goal_patches, R.string.brightening_goal_unsure).forEach { option ->
                    FilterChip(selected = selectedGoal == option, onClick = { selectedGoal = option }, label = { Text(stringResource(option)) })
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

        BrighteningPersonalGuidanceCard(selectedGoal)
        HomemadeLabCard()
        SelfieProgressCard()
        BrighteningInfoCard(R.string.brightening_morning_title, R.string.brightening_morning_body)
        BrighteningInfoCard(R.string.brightening_sunscreen_title, R.string.brightening_sunscreen_body)
        BrighteningInfoCard(R.string.brightening_evening_title, R.string.brightening_evening_body)
        BrighteningInfoCard(R.string.brightening_actives_title, R.string.brightening_actives_body)
        BrighteningInfoCard(R.string.brightening_avoid_title, R.string.brightening_avoid_body, warning = true)
        BrighteningInfoCard(R.string.brightening_timeline_title, R.string.brightening_timeline_body)
        Text(stringResource(R.string.brightening_disclaimer), color = BrighteningMuted, fontSize = 12.sp)
    }
}

@Composable
private fun BrighteningPersonalGuidanceCard(selectedGoal: Int) {
    var selectedFeel by rememberSaveable { mutableIntStateOf(4) }
    var knownReaction by rememberSaveable { mutableStateOf(false) }
    var brokenSkin by rememberSaveable { mutableStateOf(false) }
    var concerningChange by rememberSaveable { mutableStateOf(false) }
    var regularSunProtection by rememberSaveable { mutableStateOf(false) }
    var usingMultipleActives by rememberSaveable { mutableStateOf(false) }

    val feelOptions = listOf(
        SkinFeel.DRY to R.string.feel_dry,
        SkinFeel.OILY to R.string.feel_oily,
        SkinFeel.COMBINATION to R.string.feel_combination,
        SkinFeel.SENSITIVE to R.string.feel_sensitive,
        SkinFeel.UNSURE to R.string.feel_unsure
    )
    val goal = when (selectedGoal) {
        R.string.brightening_goal_acne,
        R.string.brightening_goal_tan,
        R.string.brightening_goal_patches -> SkinGoal.DARK_MARKS
        else -> SkinGoal.BASIC_CARE
    }
    val result = SkincareGuidance.create(
        CheckIn(
            skinFeel = feelOptions[selectedFeel].first,
            goal = goal,
            knownReaction = knownReaction,
            brokenSkin = brokenSkin,
            concerningChange = concerningChange,
            regularSunProtection = regularSunProtection,
            usingMultipleActives = usingMultipleActives
        )
    )
    val safetyFirst = result.status == GuidanceResult.Status.SAFETY_FIRST

    Card(
        colors = CardDefaults.cardColors(containerColor = if (safetyFirst) Color(0xFFFFF3EC) else Color.White),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
            Text(stringResource(R.string.brightening_safety_check_title), color = if (safetyFirst) Color(0xFF8B4B31) else BrighteningAccent, fontWeight = FontWeight.Bold)
            Text(stringResource(R.string.scan_feel_prompt), color = BrighteningInk, fontWeight = FontWeight.SemiBold)
            feelOptions.forEachIndexed { index, option ->
                FilterChip(selected = selectedFeel == index, onClick = { selectedFeel = index }, label = { Text(stringResource(option.second)) })
            }
            Text(stringResource(R.string.scan_reaction_question), color = BrighteningInk, fontWeight = FontWeight.SemiBold)
            FilterChip(selected = knownReaction, onClick = { knownReaction = !knownReaction }, label = { Text(stringResource(if (knownReaction) R.string.scan_yes else R.string.scan_no)) })
            Text(stringResource(R.string.scan_broken_skin_question), color = BrighteningInk, fontWeight = FontWeight.SemiBold)
            FilterChip(selected = brokenSkin, onClick = { brokenSkin = !brokenSkin }, label = { Text(stringResource(if (brokenSkin) R.string.scan_yes else R.string.scan_no)) })
            Text(stringResource(R.string.scan_concerning_change_question), color = BrighteningInk, fontWeight = FontWeight.SemiBold)
            FilterChip(selected = concerningChange, onClick = { concerningChange = !concerningChange }, label = { Text(stringResource(if (concerningChange) R.string.scan_yes else R.string.scan_no)) })

            Text(stringResource(R.string.brightening_sunscreen_habit_question), color = BrighteningInk, fontWeight = FontWeight.SemiBold)
            FilterChip(selected = regularSunProtection, onClick = { regularSunProtection = !regularSunProtection }, label = { Text(stringResource(if (regularSunProtection) R.string.scan_yes else R.string.scan_no)) })
            Text(stringResource(R.string.brightening_multiple_actives_question), color = BrighteningInk, fontWeight = FontWeight.SemiBold)
            FilterChip(selected = usingMultipleActives, onClick = { usingMultipleActives = !usingMultipleActives }, label = { Text(stringResource(if (usingMultipleActives) R.string.scan_yes else R.string.scan_no)) })

            Text(stringResource(when (result.status) {
                GuidanceResult.Status.SAFETY_FIRST -> R.string.guidance_safety
                GuidanceResult.Status.OUT_OF_SCOPE -> R.string.guidance_limited
                GuidanceResult.Status.GENERAL_EDUCATION -> R.string.guidance_general
            }), color = if (safetyFirst) Color(0xFF8B4B31) else BrighteningAccent, fontWeight = FontWeight.Bold)
            Text(stringResource(result.summaryRes), color = BrighteningInk, fontSize = 14.sp)
            result.cards.forEach { guidance ->
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(stringResource(guidance.titleRes), color = BrighteningAccent, fontWeight = FontWeight.SemiBold)
                    Text(stringResource(guidance.explanationRes), color = BrighteningInk, fontSize = 13.sp)
                    guidance.cautionRes?.let { Text(stringResource(it), color = Color(0xFF8B4B31), fontSize = 13.sp) }
                }
            }
            if (result.seekProfessionalCare) {
                Text(stringResource(R.string.guidance_get_help_body), color = Color(0xFF8B4B31), fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }
            Text(stringResource(R.string.guidance_disclaimer), color = BrighteningMuted, fontSize = 12.sp)
        }
    }
}

@Composable
private fun BrighteningInfoCard(titleRes: Int, bodyRes: Int, warning: Boolean = false) {
    Card(colors = CardDefaults.cardColors(containerColor = if (warning) Color(0xFFFFF3EC) else Color.White), shape = RoundedCornerShape(18.dp)) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
            Text(stringResource(titleRes), color = if (warning) Color(0xFF8B4B31) else BrighteningAccent, fontWeight = FontWeight.Bold)
            Text(stringResource(bodyRes), color = BrighteningInk, fontSize = 14.sp)
        }
    }
}
