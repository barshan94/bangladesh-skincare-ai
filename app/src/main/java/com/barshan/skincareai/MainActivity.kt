package com.barshan.skincareai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Spa
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
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

private val destinations = listOf(
    AppDestination(R.string.nav_scan, Icons.Outlined.Face),
    AppDestination(R.string.nav_routine, Icons.Outlined.FavoriteBorder),
    AppDestination(R.string.nav_progress, Icons.Outlined.Insights),
    AppDestination(R.string.nav_ingredients, Icons.Outlined.Spa),
    AppDestination(R.string.nav_profile, Icons.Outlined.PersonOutline)
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

    Scaffold(
        containerColor = Canvas,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Sage,
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                destinations.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(destination.icon, contentDescription = null) },
                        label = {
                            Text(
                                text = stringResource(destination.labelRes),
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
                Column {
                    Text(
                        text = "SKINCARE, WITH CARE",
                        color = Sage,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = stringResource(destinations[selectedTab].labelRes),
                        color = Ink,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = SoftSage
                ) {
                    Icon(
                        imageVector = destinations[selectedTab].icon,
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
                    icon = "●"
                )
                InfoCard(
                    title = stringResource(R.string.safety_title),
                    body = stringResource(R.string.safety_body),
                    icon = "✓"
                )
                Text(
                    text = stringResource(R.string.status_scaffold),
                    color = Sage,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(Modifier.padding(22.dp)) {
                        Text(
                            text = stringResource(destinations[selectedTab].labelRes),
                            color = Ink,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.coming_soon),
                            color = Color(0xFF5D6962),
                            fontSize = 14.sp,
                            lineHeight = 21.sp
                        )
                    }
                }
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
                .background(Sage)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "01  /  YOUR SKIN JOURNEY",
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
private fun InfoCard(title: String, body: String, icon: String) {
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
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = SoftSage
            ) {
                Text(
                    text = icon,
                    color = Sage,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(title, color = Ink, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(body, color = Color(0xFF5D6962), fontSize = 13.sp, lineHeight = 20.sp)
            }
        }
    }
}
