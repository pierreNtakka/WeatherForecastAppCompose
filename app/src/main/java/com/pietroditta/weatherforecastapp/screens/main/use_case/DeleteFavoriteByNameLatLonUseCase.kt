package com.pietroditta.weatherforecastapp.screens.main.use_case

import com.pietroditta.weatherforecastapp.repository.DatabaseRepository
import javax.inject.Inject

class DeleteFavoriteByNameLatLonUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {

    suspend operator fun invoke(params: Params) {
        databaseRepository.deleteByNameLatLon(params.name, params.lat, params.lon)
    }

    class Params(
        val name: String,
        val lat: Double,
        val lon: Double
    )
}