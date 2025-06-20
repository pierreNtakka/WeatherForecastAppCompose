package com.pietroditta.weatherforecastapp.screens.favourite.usecase

import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.repository.FavoriteRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(parameter: Params) = favoriteRepository.add(parameter.favorite)

    class Params(
        val favorite: Favorite
    )
}