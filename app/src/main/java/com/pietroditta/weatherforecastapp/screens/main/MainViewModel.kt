package com.pietroditta.weatherforecastapp.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val geocodingUseCase: DirectGeocoderUseCase,
    private val daySummaryUseCase: GetDaysForecastUseCase
) :
    ViewModel() {


    fun getGeocodingData(cityName: String) = viewModelScope.launch {

        val geocodingResponse = geocodingUseCase(DirectGeocoderUseCase.Params(cityName))

        if (geocodingResponse.data.isNullOrEmpty()) return@launch

        val dataResponse = geocodingResponse.data!![0]
        val lat = dataResponse.lat
        val lon = dataResponse.lon

        val daySummaryUseCase = daySummaryUseCase(
            GetDaysForecastUseCase.Params(lat, lon)
        )

        if (daySummaryUseCase.data != null) {
            Log.d("MainViewModel", "Day summary data: ${daySummaryUseCase.data}")
        } else if (daySummaryUseCase.e != null) {
            Log.e(
                "MainViewModel",
                "Error fetching day summary: ${daySummaryUseCase.e!!.message}"
            )
        } else {
            Log.e(
                "MainViewModel",
                "Error fetching day summary:"
            )
        }

    }

}