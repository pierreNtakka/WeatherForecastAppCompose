package com.pietroditta.weatherforecastapp.screens.main.use_case

import com.pietroditta.weatherforecastapp.repository.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteByNameLatLonUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {

    suspend operator fun invoke(params: Params) {
        favoriteRepository.deleteByNameLatLon(params.name, params.lat, params.lon)
    }

    class Params(
        val name: String,
        val lat: Double,
        val lon: Double
    )
}