package com.barshan.skincareai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.TipsAndUpdates
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    AppDestination(
        R.string.nav_scan, Icons.Outlined.Face, R.string.scan_title,
        R.string.scan_body, R.string.scan_status
    ),
    AppDestination(
        R.string.nav_routine, Icons.Outlined.FavoriteBorder, R.string.routine_title,
        R.string.routine_body, R.string.routine_status
    ),
    AppDestination(
        R.string.nav_progress, Icons.Outlined.Insights, R.string.progress_title,
        R.string.progress_body, R.string.progress_status
    ),
    AppDestination(
        R.string.nav_ingredients, Icons.Outlined.Spa, R.string.ingredients_title,
        R.string.ingredients_body, R.string.ingredients_status
    ),
    AppDestination(
        R.string.nav_profile, Icons.Outlined.PersonOutline, R.string.profile_title,
        R.string.profile_body, R.string.profile_status
    )
)

class MainActivity : ComponentActivity() {
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SkincareApp()
                }
            }
        }
    }
}

@Composable
private fun SkincareApp() {
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
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = stringResource(item.labelRes)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(item.labelRes),
                                fontSize = 10.sp,
                                maxLines = 1
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = stringResource(R.string.header_eyebrow),
                        color = Sage,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )
                    Text(
                        text = stringResource(destination.labelRes),
                        color = Ink,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Surface(shape = RoundedCornerShape(18.dp), color = SoftSage) {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null,
                        tint = Sage,
                        modifier = Modifier.padding(13.dp)
                    )
                }
            }

            if (selectedTab == 0) {
                WelcomeCard()
                InfoCard(
                    title = stringResource(R.string.local_first_title),
                    body = stringResource(R.string.local_first_body),
                    icon = Icons.Outlined.Lock
                )
                InfoCard(
                    title = stringResource(R.string.safety_title),
                    body = stringResource(R.string.safety_body),
                    icon = Icons.Outlined.TipsAndUpdates
                )
                Text(
                    text = stringResource(R.string.status_scaffold),
                    color = Sage,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                SectionCard(
                    title = stringResource(destination.headlineRes),
                    body = stringResource(destination.descriptionRes),
                    status = stringResource(destination.statusRes),
                    icon = destination.icon
                )
            }
        }
    }
}

@Composable
private fun WelcomeCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Sage),
        shape = RoundedCornerShape(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(R.string.hero_eyebrow),
                color = Color(0xFFDDE9DD),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.3.sp
            )
            Text(
                text = stringResource(R.string.welcome_title),
                color = Color.White,
                fontSize = 28.sp,
                lineHeight = 34.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = stringResource(R.string.welcome_body),
                color = Color(0xFFF0F4EF),
                fontSize = 14.sp,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
private fun InfoCard(title: String, body: String, icon: ImageVector) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(22.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(shape = RoundedCornerShape(12.dp), color = SoftSage) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Sage,
                    modifier = Modifier.padding(11.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(title, color = Ink, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(body, color = Color(0xFF5D6962), fontSize = 13.sp, lineHeight = 20.sp)
            }
        }
    }
}

@Composable
private fun SectionCard(title: String, body: String, status: String, icon: ImageVector) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Surface(shape = RoundedCornerShape(16.dp), color = SoftSage) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Sage,
                    modifier = Modifier.padding(14.dp)
                )
            }
            Text(title, color = Ink, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Text(body, color = Color(0xFF5D6962), fontSize = 14.sp, lineHeight = 22.sp)
            Surface(shape = RoundedCornerShape(12.dp), color = SoftSage) {
                Text(
                    text = status,
                    color = Sage,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp)
                )
            }
        }
    }
}
