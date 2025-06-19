package com.pietroditta.weatherforecastapp.screens.main

import com.pietroditta.weatherforecastapp.model.DataOrException
import com.pietroditta.weatherforecastapp.model.Weather
import com.pietroditta.weatherforecastapp.repository.WeatherRepository
import javax.inject.Inject

class GetDaysForecastUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend operator fun invoke(params: Params): DataOrException<Weather, Boolean, Exception> {
        val dataOrException = DataOrException<Weather, Boolean, Exception>()
        dataOrException.loading = true
        try {
            dataOrException.data = repository.getDaySummary(params.lat, params.lon)
        } catch (e: Exception) {
            dataOrException.e = e
        }
        dataOrException.loading = false
        return dataOrException
    }


    data class Params(val lat: Double, val lon: Double)

}