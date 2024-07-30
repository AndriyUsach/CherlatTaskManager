package com.cherlat.taskmanager.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cherlat.taskmanager.presentation.home_page.HomeScreen
import com.cherlat.taskmanager.presentation.settings_page.SettingsScreen

enum class Screens {
    Home,
    Settings
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    paddings: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screens.Home.name
    ) {

        composable(Screens.Home.name) {
            HomeScreen(paddings)
        }

        composable(Screens.Settings.name) {
            SettingsScreen()
        }

    }

}