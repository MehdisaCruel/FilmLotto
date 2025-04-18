package com.example.filmlotto

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        val navController = rememberNavController()
        val currentDestination = navController.currentDestinationAsState().value
        val destinations = BottomBarDestination.values()

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("فیلم لاتتو 🎬") })
            },
            bottomBar = {
                NavigationBar {
                    destinations.forEach { item ->
                        NavigationBarItem(
                            selected = currentDestination == item.direction,
                            onClick = {
                                if (item == BottomBarDestination.ThemeToggle) {
                                    onToggleTheme()
                                } else {
                                    item.direction?.let { direction ->
                                        navController.navigate(direction) {
                                            popUpTo(NavGraphs.root.startRoute.route) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = when (item) {
                                        BottomBarDestination.Home -> Icons.Default.Home
                                        BottomBarDestination.Dice -> Icons.Default.RemoveRedEye
                                        BottomBarDestination.Favorites -> Icons.Default.Favorite
                                        BottomBarDestination.ThemeToggle ->
                                            if (isDarkTheme) Icons.Default.Nightlight
                                            else Icons.Default.WbSunny
                                    },
                                    contentDescription = item.label
                                )
                            },
                            label = {
                                Text(
                                    when (item) {
                                        BottomBarDestination.ThemeToggle ->
                                            if (isDarkTheme) "روشن" else "تاریک"

                                        else -> item.label
                                    }
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            DestinationsNavHost(
                navGraph = NavGraphs.root,
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}