package com.pietroditta.weatherforecastapp.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    Text(text = "Main Screen")
    mainViewModel.getGeocodingData("Ballata")
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        navController = rememberNavController(),
        mainViewModel = hiltViewModel<MainViewModel>()
    )
}