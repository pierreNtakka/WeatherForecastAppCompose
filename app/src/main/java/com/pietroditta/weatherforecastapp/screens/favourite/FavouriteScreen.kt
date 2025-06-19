package com.pietroditta.weatherforecastapp.screens.favourite

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
fun FavoriteScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "Favorites",
            isMainScreen = false,
            navController = navController,
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onButtonClicked = {
                navController.popBackStack() // Navigate back to the previous screen
            })
    }) { innerPadding ->
        // Content of the Settings Screen
        FavoriteContent(modifier = Modifier.padding(innerPadding))

    }
}

@Composable
fun FavoriteContent(modifier: Modifier) {
    Text(
        text = "Favorites Screen", modifier = modifier
    )
}
