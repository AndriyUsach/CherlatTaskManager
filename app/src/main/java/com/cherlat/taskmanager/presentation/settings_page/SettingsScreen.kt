package com.cherlat.taskmanager.presentation.settings_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cherlat.taskmanager.ui.theme.CherlatTaskManagerTheme
import com.cherlat.taskmanager.ui.theme.GreyLight

@Composable
fun SettingsScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GreyLight)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Settings screen",
            style = MaterialTheme.typography.titleMedium,
        )
    }

}

@Preview
@Composable
private fun SettingsScreenPreview() {
    CherlatTaskManagerTheme {
        SettingsScreen()
    }
}