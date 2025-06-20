package com.pietroditta.weatherforecastapp.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietroditta.weatherforecastapp.model.DataOrException
import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.model.GeocoderResult
import com.pietroditta.weatherforecastapp.model.Weather
import com.pietroditta.weatherforecastapp.screens.favourite.usecase.AddFavoriteUseCase
import com.pietroditta.weatherforecastapp.screens.main.use_case.DeleteFavoriteByNameLatLonUseCase
import com.pietroditta.weatherforecastapp.screens.main.use_case.DirectGeocoderUseCase
import com.pietroditta.weatherforecastapp.screens.main.use_case.GetDaysForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val geocodingUseCase: DirectGeocoderUseCase,
    private val daySummaryUseCase: GetDaysForecastUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteByNameLatLonUseCase: DeleteFavoriteByNameLatLonUseCase
) : ViewModel() {

    var geocoderResult: GeocoderResult? = null

    suspend fun getGeocodingData(): DataOrException<Weather, Boolean, Exception> {

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

            this.geocoderResult = geocodingResponse.data!![0]
            val lat = this.geocoderResult?.lat ?: 0.0
            val lon = this.geocoderResult?.lon ?: 0.0

            return daySummaryUseCase(
                GetDaysForecastUseCase.Params(lat, lon)
            )
        } else {
            return daySummaryUseCase(
                GetDaysForecastUseCase.Params(
                    geocoderResult?.lat ?: 0.0,
                    geocoderResult?.lon ?: 0.0
                )
            )
        }

    }


    fun addOrRemoveFavorite(isFavorite: Boolean) {
        geocoderResult?.let { geocoderResult ->
            viewModelScope.launch(Dispatchers.IO) {
                if (!isFavorite) {
                    val favorite = Favorite(
                        name = geocoderResult.name,
                        country = geocoderResult.country,
                        lat = geocoderResult.lat,
                        lon = geocoderResult.lon,
                        state = geocoderResult.state
                    )
                    addFavoriteUseCase.invoke(AddFavoriteUseCase.Params(favorite))
                } else {
                    deleteFavoriteByNameLatLonUseCase(
                        DeleteFavoriteByNameLatLonUseCase.Params(
                            geocoderResult.name,
                            geocoderResult.lat,
                            geocoderResult.lon
                        )
                    )
                }
            }
        }
    }

}