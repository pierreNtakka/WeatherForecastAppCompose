package com.pietroditta.weatherforecastapp.screens.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.model.GeocoderResult
import com.pietroditta.weatherforecastapp.screens.favourite.usecase.DeleteFavoriteUseCase
import com.pietroditta.weatherforecastapp.screens.favourite.usecase.GetFavoritesUseCase
import com.pietroditta.weatherforecastapp.screens.search.SEARCH_SCREEN_RESULT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {


    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites: StateFlow<List<Favorite>> = _favorites.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesUseCase().distinctUntilChanged().collect { favoriteList ->
                _favorites.value = favoriteList
            }
        }
    }

    fun delete(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase(DeleteFavoriteUseCase.Params(favorite))
        }
    }

    fun setFavoriteIntoSavedStateHandle(
        favorite: Favorite,
        navController: NavController
    ) {
        val navBackStackEntry = navController.previousBackStackEntry

        val savedStateHandle = navBackStackEntry?.savedStateHandle

        val geocoderResult = GeocoderResult(
            name = favorite.name,
            country = favorite.country,
            lat = favorite.lat,
            lon = favorite.lon,
            state = favorite.state,
        )

        savedStateHandle?.set(SEARCH_SCREEN_RESULT_KEY, geocoderResult)
    }
}
