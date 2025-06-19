package com.pietroditta.weatherforecastapp.model

import com.pietroditta.weatherforecastapp.utils.DateTimeUtil
import kotlinx.serialization.Serializable

@Serializable
data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
) {


    val sunsetHourAsText: String
        get() = DateTimeUtil.retrieveTimeFromUnixTimestamp(sunset.toLong())

    val sunriseHourAsText: String
        get() = DateTimeUtil.retrieveTimeFromUnixTimestamp(sunrise.toLong())

}