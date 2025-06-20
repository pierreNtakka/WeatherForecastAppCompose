package com.pietroditta.weatherforecastapp.screens.settings.usecase

import com.pietroditta.weatherforecastapp.repository.DatabaseRepository
import javax.inject.Inject

class GetMeasurementUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    operator fun invoke() = databaseRepository.getAllMeasurementUnits()
}