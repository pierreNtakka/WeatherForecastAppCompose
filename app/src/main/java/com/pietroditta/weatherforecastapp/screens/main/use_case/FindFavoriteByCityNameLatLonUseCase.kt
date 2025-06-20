package com.pietroditta.weatherforecastapp.screens.main.use_case

import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.repository.FavoriteRepository
import javax.inject.Inject

class FindFavoriteByCityNameLatLonUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    suspend operator fun invoke(params: Params): Favorite? {
        return favoriteRepository.findByCityNameLatLon(params.name, params.lat, params.lon)
    }

    class Params(
        val name: String,
        val lat: Double,
        val lon: Double
    )
}