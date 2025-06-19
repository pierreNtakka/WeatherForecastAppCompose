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
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "About",
            isMainScreen = false,
            navController = navController,
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onButtonClicked = {
                navController.popBackStack() // Navigate back to the previous screen
            })
    }) { innerPadding ->
        // Content of the Settings Screen
        AboutContent(modifier = Modifier.padding(innerPadding))

    }
}

@Composable
fun AboutContent(modifier: Modifier) {
    Text(
        text = "About Screen", modifier = modifier
    )
}
