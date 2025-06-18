package com.pietroditta.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pietroditta.weatherforecastapp.screens.main.MainScreen
import com.pietroditta.weatherforecastapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreen.SplashScreen.name) {
        composable(WeatherScreen.SplashScreen.name) {
            WeatherSplashScreen(navController)
        }

        composable(WeatherScreen.MainScreen.name) {
            MainScreen(navController)
        }


        /*composable(
            WeatherScreen.DetailsScreen.name + "/{$MOVIE_ARGUMENT_KEY}",
            arguments = listOf(navArgument(name = MOVIE_ARGUMENT_KEY) {
                type = NavType.StringType
                nullable = false
            })
        ) {
            val movieTile = it.arguments?.getString(MOVIE_ARGUMENT_KEY)
            DetailsScreen(navController, movieTile ?: "")
        }*/
    }
}