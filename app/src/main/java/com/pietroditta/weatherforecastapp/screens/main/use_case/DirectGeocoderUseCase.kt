package com.pietroditta.weatherforecastapp.screens.main.use_case

import com.pietroditta.weatherforecastapp.model.DataOrException
import com.pietroditta.weatherforecastapp.model.GeocoderResult
import com.pietroditta.weatherforecastapp.repository.WeatherRepository
import javax.inject.Inject

class DirectGeocoderUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend operator fun invoke(params: Params): DataOrException<List<GeocoderResult>, Boolean, Exception> {
        val dataOrException = DataOrException<List<GeocoderResult>, Boolean, Exception>()
        dataOrException.loading = true
        try {
            dataOrException.data = repository.getGeocodingData(params.cityName)
        } catch (e: Exception) {
            dataOrException.e = e
        }
        dataOrException.loading = false
        return dataOrException
    }


    class Params(val cityName: String)

}