package com.pietroditta.weatherforecastapp.screens.main.use_case

import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.repository.DatabaseRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(parameter: Params) = databaseRepository.add(parameter.favorite)

    class Params(
        val favorite: Favorite
    )
}