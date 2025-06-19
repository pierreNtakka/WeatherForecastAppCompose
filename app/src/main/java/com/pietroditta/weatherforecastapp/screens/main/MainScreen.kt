package com.pietroditta.weatherforecastapp.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.model.DataOrException
import com.pietroditta.weatherforecastapp.model.Weather
import com.pietroditta.weatherforecastapp.widget.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    val weatherData =
        produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(
                loading = true
            )
        ) {
            value = mainViewModel.getGeocodingData("Fulgatore")
        }.value

    if (weatherData.loading) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(
            weather = weatherData.data!!,
            navController = navController
        )
    } else if (weatherData.e != null) {
        Text(text = "Error: ${weatherData.e!!.message}")
    } else {
        Text(text = "No data available")
    }

}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = "${weather.city.name} ,${weather.city.country}",
            isMainScreen = true,
            navController = navController
        )
    }) { contentPadding ->
        MainContent(
            weather = weather,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Composable
fun MainContent(weather: Weather, modifier: Modifier) {

    Text(
        text = "Weather in ${weather.city.country}",
        modifier = modifier
    )

}
