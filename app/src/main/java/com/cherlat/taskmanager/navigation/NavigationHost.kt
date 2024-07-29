package com.cherlat.taskmanager.navigation

import androidx.compose.runtime.Composable
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
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screens.Home.name
    ) {

        composable(Screens.Home.name) {
            HomeScreen()
        }

        composable(Screens.Settings.name) {
            SettingsScreen()
        }

    }

}