package com.pietroditta.weatherforecastapp.model

import kotlinx.serialization.Serializable


@Serializable
data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItem>,
    val message: Int
)