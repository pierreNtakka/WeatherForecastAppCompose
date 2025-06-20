package com.pietroditta.weatherforecastapp.screens.favourite.usecase

import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.repository.DatabaseRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(parameter: Params) = databaseRepository.remove(parameter.favorite)

    class Params(
        val favorite: Favorite
    )
}