package com.pietroditta.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pietroditta.weatherforecastapp.screens.favourite.FavoriteScreen
import com.pietroditta.weatherforecastapp.screens.main.MainScreen
import com.pietroditta.weatherforecastapp.screens.main.MainViewModel
import com.pietroditta.weatherforecastapp.screens.search.SearchScreen
import com.pietroditta.weatherforecastapp.screens.search.SearchViewModel
import com.pietroditta.weatherforecastapp.screens.settings.AboutScreen
import com.pietroditta.weatherforecastapp.screens.settings.SettingsScreen
import com.pietroditta.weatherforecastapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreen.SplashScreen.name) {
        composable(WeatherScreen.SplashScreen.name) {
            WeatherSplashScreen(navController)
        }

        composable(WeatherScreen.MainScreen.name) {
            val mainViewModel: MainViewModel = hiltViewModel()
            MainScreen(navController, mainViewModel)
        }

        composable(WeatherScreen.SearchScreen.name) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(navController, searchViewModel)
        }

        composable(WeatherScreen.SettingsScreen.name) {
            SettingsScreen(navController)
        }

        composable(WeatherScreen.AboutScreen.name) {
            AboutScreen(navController)
        }

        composable(WeatherScreen.FavoritesScreen.name) {
            FavoriteScreen(navController)
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