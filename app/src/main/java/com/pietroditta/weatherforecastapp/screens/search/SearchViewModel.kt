package com.pietroditta.weatherforecastapp.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.model.GeocoderResult
import com.pietroditta.weatherforecastapp.screens.main.use_case.DirectGeocoderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val geocoderUseCase: DirectGeocoderUseCase
) :
    ViewModel() {

    private val _suggestions = MutableStateFlow<List<GeocoderResult>>(emptyList())
    val suggestions: StateFlow<List<GeocoderResult>> = _suggestions.asStateFlow()

    private var searchJob: Job? = null

    fun onQueryChanged(cityName: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500) // Debounce delay
            onSearchSuspend(cityName)
        }
    }

    fun onSearch(cityName: String) {
        viewModelScope.launch {
            onSearchSuspend(cityName)
        }
    }

    private suspend fun onSearchSuspend(cityName: String) {
        Log.d("SearchViewModel", "onSearch: $cityName")
        if (cityName.isNotEmpty()) {
            geocoderUseCase(DirectGeocoderUseCase.Params(cityName)).data?.let { geocoders ->
                Log.d("SearchViewModel", "onSearch: ${geocoders.size} results found")
                if (geocoders.isNotEmpty()) {
                    _suggestions.value = geocoders
                } else {
                    _suggestions.value = emptyList()
                }
            } ?: run {
                Log.e("SearchViewModel", "onSearch: No data found for $cityName")
                _suggestions.value = emptyList()
            }
        } else {
            _suggestions.value = emptyList()
        }
    }

    fun setGeocoderIntoSavedStateHandle(
        geocoderResult: GeocoderResult,
        navController: NavController
    ) {
        val navBackStackEntry = navController.previousBackStackEntry
        val savedStateHandle = navBackStackEntry?.savedStateHandle
        savedStateHandle?.set(SEARCH_SCREEN_RESULT_KEY, geocoderResult)
    }


}