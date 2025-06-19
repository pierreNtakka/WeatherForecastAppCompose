package com.pietroditta.weatherforecastapp.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.pietroditta.weatherforecastapp.R
import com.pietroditta.weatherforecastapp.model.DataOrException
import com.pietroditta.weatherforecastapp.model.Weather
import com.pietroditta.weatherforecastapp.model.WeatherItem
import com.pietroditta.weatherforecastapp.repository.MockedDataRepository
import com.pietroditta.weatherforecastapp.widget.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    val weatherData =
        produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(
                loading = true
            )
        ) {
            value = mainViewModel.getGeocodingData("Maputo")
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

@Preview(showBackground = true)
@Composable
fun MainContent(
    weather: Weather = MockedDataRepository.getMockedWeatherData(),
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.list[0].dtTxt,
            modifier = Modifier.padding(6.dp),
            style = MaterialTheme.typography.bodySmall,
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            color = Color(0xFFB2C1DF),
            shape = CircleShape
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    modifier = Modifier
                        .size(80.dp),
                    model = weather.list[0].weather[0].iconUrl,
                    contentDescription = "${weather.list[0].weather[0].main} image"
                )

                Text(
                    text = weather.list[0].main.temp.toString() + "°C",
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = weather.list[0].weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    fontStyle = FontStyle.Italic
                )
            }
        }

        HumidityWindPressureRow(weather = weather)

        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

        SunriseAndSunsetRow(weather = weather)

        Text(
            text = "This week forecast",
            modifier = Modifier.padding(8.dp),
            style = TextStyle(fontSize = 20.sp),
            fontWeight = FontWeight.Bold,
        )

        WeekForecast(list = weather.list)


    }
}


@Preview(showBackground = true)
@Composable
fun HumidityWindPressureRow(
    weather: Weather = MockedDataRepository.getMockedWeatherData(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Icon(
                painter = painterResource(R.drawable.humidity),
                contentDescription = "Humidity",
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "${weather.list[0].main.humidity}%",
                style = MaterialTheme.typography.bodyMedium
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Icon(
                painter = painterResource(R.drawable.pressure),
                contentDescription = "Pressure",
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "${weather.list[0].main.pressure} hPa",
                style = MaterialTheme.typography.bodyMedium
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Icon(
                painter = painterResource(R.drawable.wind),
                contentDescription = "Wind speed",
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "${weather.list[0].wind.speed} m/sec",
                style = MaterialTheme.typography.bodyMedium
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun SunriseAndSunsetRow(
    weather: Weather = MockedDataRepository.getMockedWeatherData(),
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Icon(
                painter = painterResource(R.drawable.sunrise),
                contentDescription = "Sunrise",
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = weather.city.sunriseHourAsText,
                modifier = Modifier.padding(start = 5.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Text(
                text = weather.city.sunsetHourAsText,
                modifier = Modifier.padding(end = 5.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            Icon(
                painter = painterResource(R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier = Modifier.size(20.dp)
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun WeekForecast(list: List<WeatherItem> = MockedDataRepository.getMockedWeatherData().list) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color.Cyan,
                shape = RoundedCornerShape(10.dp)
            ),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list.size) { index ->
            val dayWeather = list[index]
            WeekForecastItem(weatherItem = dayWeather)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WeekForecastItem(
    weatherItem: WeatherItem = MockedDataRepository.getMockedWeatherData().list[0]
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                color = Color.White,
                shape = CircleShape.copy(topEnd = CornerSize(10.dp))
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = weatherItem.dayOfWeek,
            modifier = Modifier
                .padding(8.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        AsyncImage(
            modifier = Modifier
                .size(60.dp),
            model = weatherItem.weather[0].iconUrl,
            contentDescription = "${weatherItem.weather[0].main} image"
        )

        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFB2DFDB)
        ) {
            Text(
                modifier = Modifier.padding(6.dp),
                text = weatherItem.weather[0].description,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold
                    )
                ) {
                    append("${weatherItem.main.tempMax}°")
                }
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append("${weatherItem.main.tempMin}°")
                }
            }
        )

    }
}
