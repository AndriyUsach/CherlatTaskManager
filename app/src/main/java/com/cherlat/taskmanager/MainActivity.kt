package com.cherlat.taskmanager

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.cherlat.taskmanager.presentation.home_page.HomeScreen
import com.cherlat.taskmanager.navigation.BottomNavBar
import com.cherlat.taskmanager.navigation.NavigationHost
import com.cherlat.taskmanager.ui.theme.CherlatTaskManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        setContent {

            val navController = rememberNavController()

            CherlatTaskManagerTheme {

                Scaffold(
                    bottomBar = {
                        BottomNavBar(navController)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .background(Color.White)
                ) { innerPadding ->

                    NavigationHost(navController = navController, innerPadding)

                }
            }
        }
    }
}