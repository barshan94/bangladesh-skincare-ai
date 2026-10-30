package com.barshan.skincareai

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.TipsAndUpdates
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.barshan.skincareai.data.LegacyPreferencesMigration
import com.barshan.skincareai.data.RoomSkincareRepository
import com.barshan.skincareai.data.SkincareDatabase
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barshan.skincareai.domain.CheckIn
import com.barshan.skincareai.domain.SkinFeel
import com.barshan.skincareai.domain.SkinGoal
import com.barshan.skincareai.domain.SkincareGuidance
import com.barshan.skincareai.domain.GuidanceResult
import java.util.Locale

private val Sage = Color(0xFF486B5A)
private val Ink = Color(0xFF1E2924)
private val Canvas = Color(0xFFF7F8F4)
private val SoftSage = Color(0xFFE8EFE8)

private data class AppDestination(
    val labelRes: Int,
    val icon: ImageVector,
    val headlineRes: Int,
    val descriptionRes: Int,
    val statusRes: Int
)

private val destinations = listOf(
    AppDestination(R.string.nav_scan, Icons.Outlined.Face, R.string.scan_title, R.string.scan_body, R.string.scan_status),
    AppDestination(R.string.nav_routine, Icons.Outlined.FavoriteBorder, R.string.routine_title, R.string.routine_body, R.string.routine_status),
    AppDestination(R.string.nav_progress, Icons.Outlined.Insights, R.string.progress_title, R.string.progress_body, R.string.progress_status),
    AppDestination(R.string.nav_ingredients, Icons.Outlined.Spa, R.string.ingredients_title, R.string.ingredients_body, R.string.ingredients_status),
    AppDestination(R.string.nav_profile, Icons.Outlined.PersonOutline, R.string.profile_title, R.string.profile_body, R.string.profile_status)
)

class MainActivity : ComponentActivity() {
    private val startupRepository = mutableStateOf<RoomSkincareRepository?>(null)
    private val startupError = mutableStateOf(false)

    override fun attachBaseContext(newBase: Context) {
        val language = LocalStore.language(newBase)
        if (language == "system") {
            super.attachBaseContext(newBase)
        } else {
            val configuration = Configuration(newBase.resources.configuration)
            configuration.setLocale(Locale(language))
            super.attachBaseContext(newBase.createConfigurationContext(configuration))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Sage,
                    onPrimary = Color.White,
                    secondary = Color(0xFFB7785D),
                    background = Canvas,
                    surface = Color.White,
                    onSurface = Ink
                )
            ) {
                Surface(Modifier.fillMaxSize(), color = Canvas) {
                    val repository = startupRepository.value
                    when {
                        repository != null -> SkincareApp(repository)
                        startupError.value -> Text(
                            "Your existing local data could not be safely opened. No legacy data was deleted. Please restart the app; do not clear app storage.",
                            modifier = Modifier.padding(24.dp),
                            color = Ink
                        )
                        else -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            runCatching {
                withContext(Dispatchers.IO) {
                    val database = SkincareDatabase.getInstance(applicationContext)
                    LegacyPreferencesMigration.migrateIfNeeded(applicationContext, database)
                    RoomSkincareRepository(database)
                }
            }.onSuccess { repository ->
                startupRepository.value = repository
            }.onFailure {
                startupError.value = true
            }
        }
    }
}

@Composable
private fun SkincareApp(repository: RoomSkincareRepository) {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val destination = destinations[selectedTab]
    Scaffold(
        containerColor = Canvas,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Sage,
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                destinations.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(item.icon, contentDescription = stringResource(item.labelRes)) },
                        label = { Text(stringResource(item.labelRes), fontSize = 10.sp, maxLines = 1) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
                .verticalScroll(rememberScrollState()).padding(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(stringResource(R.string.header_eyebrow), color = Sage, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
                    Text(stringResource(destination.labelRes), color = Ink, fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                }
                Surface(shape = RoundedCornerShape(16.dp), color = SoftSage) {
                    Icon(destination.icon, contentDescription = null, tint = Sage, modifier = Modifier.padding(12.dp))
                }
            }

            when (selectedTab) {
                0 -> SkinCheckInScreen(repository)
                1 -> RoutineScreen(repository)
                2 -> ProgressScreen(repository)
                3 -> IngredientsScreen()
                4 -> ProfileScreen(repository)
            }
        }
    }
}

@Composable
private fun SkinCheckInScreen(repository: RoomSkincareRepository) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var savedToProgress by rememberSaveable { mutableStateOf(false) }
    var skinFeel by rememberSaveable { mutableStateOf(SkinFeel.UNSURE) }
    var goal by rememberSaveable { mutableStateOf(SkinGoal.BASIC_CARE) }
    var reaction by rememberSaveable { mutableStateOf(false) }
    var brokenSkin by rememberSaveable { mutableStateOf(false) }
    var concerningChange by rememberSaveable { mutableStateOf(false) }
    var result by remember { mutableStateOf<GuidanceResult?>(null) }

    WelcomeCard()
    InfoCard(stringResource(R.string.local_first_title), stringResource(R.string.local_first_body), Icons.Outlined.Lock)
    SectionHeading(stringResource(R.string.scan_title), stringResource(R.string.scan_body))
    Text(stringResource(R.string.scan_feel_prompt), fontWeight = FontWeight.SemiBold)
    ChipChoices(
        options = listOf(
            SkinFeel.DRY to R.string.feel_dry,
            SkinFeel.OILY to R.string.feel_oily,
            SkinFeel.COMBINATION to R.string.feel_combination,
            SkinFeel.SENSITIVE to R.string.feel_sensitive,
            SkinFeel.UNSURE to R.string.feel_unsure
        ),
        selected = skinFeel,
        label = { stringResource(it) },
        onSelect = { skinFeel = it; result = null; savedToProgress = false }
    )
    Text(stringResource(R.string.scan_goal_prompt), fontWeight = FontWeight.SemiBold)
    ChipChoices(
        options = listOf(
            SkinGoal.BASIC_CARE to R.string.goal_basic,
            SkinGoal.DRYNESS to R.string.goal_dryness,
            SkinGoal.ROUGHNESS to R.string.goal_roughness,
            SkinGoal.DARK_MARKS to R.string.goal_dark_marks,
            SkinGoal.ACNE to R.string.goal_acne,
            SkinGoal.OTHER to R.string.goal_other
        ),
        selected = goal,
        label = { stringResource(it) },
        onSelect = { goal = it; result = null; savedToProgress = false }
    )
    CheckRow(stringResource(R.string.scan_reaction_question), reaction) { reaction = it; result = null; savedToProgress = false }
    CheckRow(stringResource(R.string.scan_broken_skin_question), brokenSkin) { brokenSkin = it; result = null; savedToProgress = false }
    CheckRow(stringResource(R.string.scan_concerning_change_question), concerningChange) { concerningChange = it; result = null; savedToProgress = false }
    Button(onClick = { result = SkincareGuidance.create(CheckIn(skinFeel, goal, reaction, brokenSkin, concerningChange)); savedToProgress = false }, modifier = Modifier.fillMaxWidth()) {
        Text(stringResource(R.string.scan_get_guidance))
    }
    result?.let { guidance ->
        GuidanceResultCard(guidance)
        Button(
            onClick = {
                val details = context.getString(
                    R.string.scan_saved_details,
                    context.getString(when (skinFeel) {
                        SkinFeel.DRY -> R.string.feel_dry
                        SkinFeel.OILY -> R.string.feel_oily
                        SkinFeel.COMBINATION -> R.string.feel_combination
                        SkinFeel.SENSITIVE -> R.string.feel_sensitive
                        SkinFeel.UNSURE -> R.string.feel_unsure
                    }),
                    context.getString(when (goal) {
                        SkinGoal.BASIC_CARE -> R.string.goal_basic
                        SkinGoal.DRYNESS -> R.string.goal_dryness
                        SkinGoal.ROUGHNESS -> R.string.goal_roughness
                        SkinGoal.DARK_MARKS -> R.string.goal_dark_marks
                        SkinGoal.ACNE -> R.string.goal_acne
                        SkinGoal.OTHER -> R.string.goal_other
                    }),
                    context.getString(if (reaction) R.string.scan_yes else R.string.scan_no),
                    context.getString(if (brokenSkin) R.string.scan_yes else R.string.scan_no),
                    context.getString(if (concerningChange) R.string.scan_yes else R.string.scan_no)
                )
                scope.launch {
                    repository.addProgressNote(
                        context.getString(R.string.scan_saved_concern),
                        details,
                        "unsure"
                    )
                    savedToProgress = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !savedToProgress
        ) {
            Text(stringResource(R.string.scan_save_to_progress))
        }
        if (savedToProgress) {
            Text(stringResource(R.string.scan_saved_to_progress), color = Sage, fontWeight = FontWeight.Medium)
        }
    }
    InfoCard(stringResource(R.string.safety_title), stringResource(R.string.safety_body), Icons.Outlined.TipsAndUpdates)
    Text(stringResource(R.string.status_scaffold), color = Sage, fontSize = 12.sp, fontWeight = FontWeight.Medium, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
}

@Composable
private fun GuidanceResultCard(result: GuidanceResult) {
    val title = when (result.status) {
        GuidanceResult.Status.GENERAL_EDUCATION -> stringResource(R.string.guidance_general)
        GuidanceResult.Status.SAFETY_FIRST -> stringResource(R.string.guidance_safety)
        GuidanceResult.Status.OUT_OF_SCOPE -> stringResource(R.string.guidance_limited)
    }
    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(22.dp)) {
        Column(Modifier.fillMaxWidth().padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(title, color = Sage, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(result.summary, color = Ink)
            result.cards.forEach { card ->
                Text(card.title, fontWeight = FontWeight.SemiBold)
                Text(card.explanation, color = Color(0xFF5D6962))
                card.caution?.let { Text(it, color = Color(0xFF8B4B31), fontWeight = FontWeight.Medium) }
            }
            Text(stringResource(R.string.guidance_disclaimer), color = Color(0xFF5D6962), fontSize = 12.sp)
        }
    }
}

@Composable
private fun RoutineScreen(repository: RoomSkincareRepository) {
    val scope = rememberCoroutineScope()
    val done by repository.observeRoutineDone().collectAsState(initial = emptySet())
    val items = listOf(
        RoutineItem("morning_cleanse", R.string.routine_am_cleanse, R.string.routine_am_cleanse_body),
        RoutineItem("morning_moisturize", R.string.routine_am_moisturize, R.string.routine_am_moisturize_body),
        RoutineItem("morning_sun", R.string.routine_am_sun, R.string.routine_am_sun_body),
        RoutineItem("evening_cleanse", R.string.routine_pm_cleanse, R.string.routine_pm_cleanse_body),
        RoutineItem("evening_moisturize", R.string.routine_pm_moisturize, R.string.routine_pm_moisturize_body)
    )
    Text(stringResource(R.string.routine_intro), color = Color(0xFF5D6962))
    Text(stringResource(R.string.routine_completion, done.intersect(items.map { it.id }.toSet()).size, items.size), color = Sage, fontWeight = FontWeight.SemiBold)
    items.forEach { item ->
        Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(18.dp)) {
            Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = item.id in done, onCheckedChange = { checked ->
                    val updated = if (checked) done + item.id else done - item.id
                    scope.launch { repository.setRoutineDone(updated) }
                })
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(stringResource(item.title), fontWeight = FontWeight.SemiBold)
                    Text(stringResource(item.body), color = Color(0xFF5D6962), fontSize = 13.sp)
                }
            }
        }
    }
    Text(stringResource(R.string.routine_caution), color = Color(0xFF5D6962), fontSize = 12.sp)
}

private data class RoutineItem(val id: String, val title: Int, val body: Int)

@Composable
private fun ProgressScreen(repository: RoomSkincareRepository) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var concern by rememberSaveable { mutableStateOf("") }
    var note by rememberSaveable { mutableStateOf("") }
    var tolerance by rememberSaveable { mutableStateOf("comfortable") }
    val notes by repository.observeProgressNotes().collectAsState(initial = emptyList())

    Text(stringResource(R.string.progress_intro), color = Color(0xFF5D6962))
    OutlinedTextField(value = concern, onValueChange = { concern = it }, label = { Text(stringResource(R.string.progress_concern_label)) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
    OutlinedTextField(value = note, onValueChange = { note = it }, label = { Text(stringResource(R.string.progress_note_label)) }, modifier = Modifier.fillMaxWidth(), minLines = 3)
    Text(stringResource(R.string.progress_tolerance_label), fontWeight = FontWeight.SemiBold)
    ChipChoices(
        options = listOf("comfortable" to R.string.tolerance_comfortable, "irritated" to R.string.tolerance_irritated, "unsure" to R.string.tolerance_unsure),
        selected = tolerance,
        label = { stringResource(it) },
        onSelect = { tolerance = it }
    )
    Button(
        onClick = {
            if (note.isNotBlank() || concern.isNotBlank()) {
                val savedConcern = concern.trim().ifBlank { context.getString(R.string.progress_general_concern) }
                val savedNote = note.trim()
                scope.launch { repository.addProgressNote(savedConcern, savedNote, tolerance) }
                concern = ""
                note = ""
            }
        },
        enabled = concern.isNotBlank() || note.isNotBlank(),
        modifier = Modifier.fillMaxWidth()
    ) { Text(stringResource(R.string.progress_save)) }

    Text(stringResource(R.string.progress_history), fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
    if (notes.isEmpty()) {
        InfoCard(stringResource(R.string.progress_empty_title), stringResource(R.string.progress_empty_body), Icons.Outlined.Insights)
    } else {
        notes.forEach { item ->
            Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(18.dp)) {
                Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(item.concern, fontWeight = FontWeight.SemiBold)
                            Text(LocalStore.formatDate(item.createdAt), color = Color(0xFF5D6962), fontSize = 12.sp)
                        }
                        TextButton(onClick = { scope.launch { repository.deleteProgressNote(item.id) } }) {
                            Icon(Icons.Outlined.DeleteOutline, contentDescription = stringResource(R.string.progress_delete))
                        }
                    }
                    Text(stringResource(R.string.progress_tolerance_saved, when (item.tolerance) {
                        "comfortable" -> context.getString(R.string.tolerance_comfortable)
                        "irritated" -> context.getString(R.string.tolerance_irritated)
                        else -> context.getString(R.string.tolerance_unsure)
                    }), color = Sage, fontSize = 12.sp)
                    if (item.note.isNotBlank()) Text(item.note)
                }
            }
        }
    }
    Text(stringResource(R.string.progress_privacy_note), color = Color(0xFF5D6962), fontSize = 12.sp)
}

@Composable
private fun IngredientsScreen() {
    var query by rememberSaveable { mutableStateOf("") }
    val items = listOf(
        IngredientItem(R.string.ingredient_glycerin, R.string.ingredient_glycerin_body, R.string.ingredient_glycerin_caution),
        IngredientItem(R.string.ingredient_ceramides, R.string.ingredient_ceramides_body, R.string.ingredient_ceramides_caution),
        IngredientItem(R.string.ingredient_niacinamide, R.string.ingredient_niacinamide_body, R.string.ingredient_niacinamide_caution),
        IngredientItem(R.string.ingredient_fragrance, R.string.ingredient_fragrance_body, R.string.ingredient_fragrance_caution),
        IngredientItem(R.string.ingredient_lemon, R.string.ingredient_lemon_body, R.string.ingredient_lemon_caution),
        IngredientItem(R.string.ingredient_sunscreen, R.string.ingredient_sunscreen_body, R.string.ingredient_sunscreen_caution)
    )
    Text(stringResource(R.string.ingredients_intro), color = Color(0xFF5D6962))
    OutlinedTextField(value = query, onValueChange = { query = it }, label = { Text(stringResource(R.string.ingredients_search)) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
    items.forEach { item ->
        val title = stringResource(item.title)
        val body = stringResource(item.body)
        val caution = stringResource(item.caution)
        if (query.isBlank() || title.contains(query, ignoreCase = true) || body.contains(query, ignoreCase = true)) {
            Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(18.dp)) {
                Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(title, fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                    Text(body, color = Color(0xFF5D6962))
                    Text(caution, color = Color(0xFF8B4B31), fontSize = 13.sp)
                }
            }
        }
    }
    Text(stringResource(R.string.ingredients_review_note), color = Color(0xFF5D6962), fontSize = 12.sp)
}

private data class IngredientItem(val title: Int, val body: Int, val caution: Int)

@Composable
private fun ProfileScreen(repository: RoomSkincareRepository) {
    val context = LocalContext.current
    val activity = context as? Activity
    var language by remember { mutableStateOf(LocalStore.language(context)) }
    var budget by remember { mutableStateOf(LocalStore.budget(context)) }
    var showClearDialog by remember { mutableStateOf(false) }
    var clearedMessage by remember { mutableStateOf(false) }
    var clearingData by remember { mutableStateOf(false) }
    var clearError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    SectionHeading(stringResource(R.string.profile_title), stringResource(R.string.profile_body))
    Text(stringResource(R.string.profile_language), fontWeight = FontWeight.SemiBold)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FilterChip(selected = language == "system", onClick = { LocalStore.setLanguage(context, "system"); language = "system"; activity?.recreate() }, label = { Text(stringResource(R.string.language_system)) })
        FilterChip(selected = language == "en", onClick = { LocalStore.setLanguage(context, "en"); language = "en"; activity?.recreate() }, label = { Text("English") })
        FilterChip(selected = language == "bn", onClick = { LocalStore.setLanguage(context, "bn"); language = "bn"; activity?.recreate() }, label = { Text("বাংলা") })
    }
    Text(stringResource(R.string.profile_budget), fontWeight = FontWeight.SemiBold)
    ChipChoices(
        options = listOf("Not set" to R.string.budget_none, "Under ৳500" to R.string.budget_500, "৳500–৳1500" to R.string.budget_1500, "৳1500+" to R.string.budget_1500_plus),
        selected = budget,
        label = { stringResource(it) },
        onSelect = { budget = it; LocalStore.setBudget(context, it) }
    )
    InfoCard(stringResource(R.string.profile_local_title), stringResource(R.string.profile_local_body), Icons.Outlined.Lock)
    InfoCard(stringResource(R.string.profile_no_account_title), stringResource(R.string.profile_no_account_body), Icons.Outlined.TipsAndUpdates)
    Button(
        onClick = { showClearDialog = true; clearError = false },
        enabled = !clearingData,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(stringResource(if (clearingData) R.string.profile_clearing_data else R.string.profile_clear_data))
    }
    if (clearedMessage) Text(stringResource(R.string.profile_data_cleared), color = Sage)
    if (clearError) Text(stringResource(R.string.profile_clear_error), color = Color(0xFF8B4B31))
    if (showClearDialog) {
        AlertDialog(
            onDismissRequest = { showClearDialog = false },
            title = { Text(stringResource(R.string.profile_clear_confirm_title)) },
            text = { Text(stringResource(R.string.profile_clear_confirm_body)) },
            confirmButton = {
                TextButton(onClick = {
                    clearingData = true
                    clearError = false
                    clearedMessage = false
                    showClearDialog = false
                    scope.launch {
                        runCatching { repository.clearUserData(context) }
                            .onSuccess {
                                budget = "Not set"
                                clearedMessage = true
                            }
                            .onFailure {
                                clearError = true
                            }
                        clearingData = false
                    }
                }) { Text(stringResource(R.string.profile_clear_confirm_action)) }
            },
            dismissButton = { TextButton(onClick = { showClearDialog = false }) { Text(stringResource(R.string.cancel)) } }
        )
    }
}

@Composable
private fun WelcomeCard() {
    Card(colors = CardDefaults.cardColors(containerColor = Sage), shape = RoundedCornerShape(26.dp)) {
        Column(Modifier.fillMaxWidth().padding(22.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(stringResource(R.string.hero_eyebrow), color = Color(0xFFDDE9DD), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
            Text(stringResource(R.string.welcome_title), color = Color.White, fontSize = 26.sp, lineHeight = 32.sp, fontWeight = FontWeight.SemiBold)
            Text(stringResource(R.string.welcome_body), color = Color(0xFFF0F4EF), fontSize = 14.sp, lineHeight = 21.sp)
        }
    }
}

@Composable
private fun SectionHeading(title: String, body: String) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, fontSize = 21.sp, fontWeight = FontWeight.SemiBold, color = Ink)
        Text(body, color = Color(0xFF5D6962), fontSize = 14.sp, lineHeight = 21.sp)
    }
}

@Composable
private fun InfoCard(title: String, body: String, icon: ImageVector) {
    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(20.dp)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Top) {
            Surface(shape = RoundedCornerShape(12.dp), color = SoftSage) {
                Icon(icon, contentDescription = null, tint = Sage, modifier = Modifier.padding(10.dp))
            }
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(title, color = Ink, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(body, color = Color(0xFF5D6962), fontSize = 13.sp, lineHeight = 19.sp)
            }
        }
    }
}

@Composable
private fun CheckRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(label, modifier = Modifier.weight(1f), color = Ink)
    }
}

@Composable
private fun <T> ChipChoices(
    options: List<Pair<T, Int>>,
    selected: T,
    label: @Composable (Int) -> String,
    onSelect: (T) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        options.chunked(2).forEach { pair ->
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                pair.forEach { (value, textRes) ->
                    FilterChip(selected = selected == value, onClick = { onSelect(value) }, label = { Text(label(textRes)) })
                }
            }
        }
    }
}
