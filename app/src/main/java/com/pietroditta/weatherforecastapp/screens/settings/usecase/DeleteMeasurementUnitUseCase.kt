package com.pietroditta.weatherforecastapp.screens.settings.usecase

import com.pietroditta.weatherforecastapp.repository.DatabaseRepository
import javax.inject.Inject

class DeleteMeasurementUnitUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke() =
        databaseRepository.deleteMeasurementUnits()


}