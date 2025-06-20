package com.pietroditta.weatherforecastapp.screens.favourite.usecase

import com.pietroditta.weatherforecastapp.repository.DatabaseRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    operator fun invoke() = databaseRepository.getAll()
}