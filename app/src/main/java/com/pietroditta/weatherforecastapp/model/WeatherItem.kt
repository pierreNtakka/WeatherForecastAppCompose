package com.pietroditta.weatherforecastapp.model

import com.pietroditta.weatherforecastapp.utils.DateTimeUtil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WeatherItem(
    val clouds: Clouds,
    val dt: Int,
    @SerialName("dt_txt")
    val dtTxt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain? = null,
    val sys: Sys,
    val visibility: Int,
    val weather: List<WeatherObject>,
    val wind: Wind
) {
    val dayOfWeek: String
        get() = DateTimeUtil.retrieveDayOfWeekFromUnixTimestamp(dt.toLong())
}