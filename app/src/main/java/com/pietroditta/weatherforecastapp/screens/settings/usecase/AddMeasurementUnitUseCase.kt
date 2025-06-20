package com.pietroditta.weatherforecastapp.screens.settings.usecase

import com.pietroditta.weatherforecastapp.repository.DatabaseRepository
import javax.inject.Inject

class AddMeasurementUnitUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(parameter: Params) =
        databaseRepository.insertMeasurementUnit(parameter.measurementUnit)

    class Params(
        val measurementUnit: String
    )
}