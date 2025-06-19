package com.pietroditta.weatherforecastapp.screens.main

import androidx.lifecycle.ViewModel
import com.pietroditta.weatherforecastapp.model.DataOrException
import com.pietroditta.weatherforecastapp.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val geocodingUseCase: DirectGeocoderUseCase,
    private val daySummaryUseCase: GetDaysForecastUseCase
) : ViewModel() {

    suspend fun getGeocodingData(cityName: String): DataOrException<Weather, Boolean, Exception> {

        val geocodingResponse = geocodingUseCase(DirectGeocoderUseCase.Params(cityName))

        if (geocodingResponse.data.isNullOrEmpty()) {
            return DataOrException(
                data = null,
                loading = false,
                e = Exception("No geocoding data found for city: $cityName")
            )
        }

        val dataResponse = geocodingResponse.data!![0]
        val lat = dataResponse.lat
        val lon = dataResponse.lon


        return daySummaryUseCase(
            GetDaysForecastUseCase.Params(lat, lon)
        )

    }

}