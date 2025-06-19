package com.pietroditta.weatherforecastapp.repository

import com.pietroditta.weatherforecastapp.model.Geocoder
import com.pietroditta.weatherforecastapp.model.Weather
import com.pietroditta.weatherforecastapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    suspend fun getGeocodingData(cityName: String): List<Geocoder> {
        return weatherApi.directGeocoder(cityName)
    }

    suspend fun getDaySummary(lat: Double, lon: Double): Weather {
        return weatherApi.getDaysForecast(lat, lon)
    }
}