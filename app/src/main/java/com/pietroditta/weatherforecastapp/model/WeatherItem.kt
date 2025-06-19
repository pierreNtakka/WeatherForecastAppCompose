package com.pietroditta.weatherforecastapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherItem(
    val clouds: Clouds,
    val dt: Int,
    @SerialName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<WeatherObject>,
    val wind: Wind
)