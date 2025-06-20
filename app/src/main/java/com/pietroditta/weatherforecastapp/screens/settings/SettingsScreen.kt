package com.pietroditta.weatherforecastapp.screens.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.widget.WeatherAppBar

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            isMainScreen = false,
            navController = navController,
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onButtonClicked = {
                navController.popBackStack() // Navigate back to the previous screen
            })
    }) { innerPadding ->
        SettingsContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun SettingsContent(modifier: Modifier) {
    Text(
        text = "Settings Screen", modifier = modifier
    )
}
