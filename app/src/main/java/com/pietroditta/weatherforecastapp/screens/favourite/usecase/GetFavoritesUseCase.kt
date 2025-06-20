package com.pietroditta.weatherforecastapp.screens.favourite.usecase

import com.pietroditta.weatherforecastapp.repository.FavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke() = favoriteRepository.getAll()
}