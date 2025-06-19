package com.pietroditta.weatherforecastapp.model

import com.pietroditta.weatherforecastapp.BuildConfig
import kotlinx.serialization.Serializable


@Serializable
class WeatherObject(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) {
    val iconUrl: String
        get() = "${BuildConfig.OPEN_WEATHER_BASE_URL}img/wn/$icon.png"
}