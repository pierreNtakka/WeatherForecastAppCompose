package com.pietroditta.weatherforecastapp.network

import com.pietroditta.weatherforecastapp.model.GeocoderResult
import com.pietroditta.weatherforecastapp.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("geo/1.0/direct")
    suspend fun directGeocoder(
        @Query("q")
        cityName: String,
        @Query("limit")
        limit: Int = 3
    ): List<GeocoderResult>


    @GET("data/2.5/forecast")
    suspend fun getDaysForecast(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("cnt")
        dayCounter: Int = 5,
        @Query("units")
        units: String = "metric",
        @Query("lang")
        lang: String = Locale.getDefault().language,
        @Query("mode")
        mode: String = "json"
    ): Weather


}
