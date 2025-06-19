package com.pietroditta.weatherforecastapp.screens.main

import androidx.lifecycle.ViewModel
import com.pietroditta.weatherforecastapp.model.DataOrException
import com.pietroditta.weatherforecastapp.model.GeocoderResult
import com.pietroditta.weatherforecastapp.model.Weather
import com.pietroditta.weatherforecastapp.screens.main.use_case.DirectGeocoderUseCase
import com.pietroditta.weatherforecastapp.screens.main.use_case.GetDaysForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val geocodingUseCase: DirectGeocoderUseCase,
    private val daySummaryUseCase: GetDaysForecastUseCase
) : ViewModel() {

    suspend fun getGeocodingData(geocoderResult: GeocoderResult?): DataOrException<Weather, Boolean, Exception> {


        if (geocoderResult == null) {

            val cityName = "Rome"

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
        } else {
            return daySummaryUseCase(
                GetDaysForecastUseCase.Params(geocoderResult.lat, geocoderResult.lon)
            )
        }

    }

}