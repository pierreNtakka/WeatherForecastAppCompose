package com.pietroditta.weatherforecastapp.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pietroditta.weatherforecastapp.model.MeasurementUnit
import com.pietroditta.weatherforecastapp.screens.settings.usecase.AddMeasurementUnitUseCase
import com.pietroditta.weatherforecastapp.screens.settings.usecase.DeleteMeasurementUnitUseCase
import com.pietroditta.weatherforecastapp.screens.settings.usecase.GetMeasurementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getMeasurementUseCase: GetMeasurementUseCase,
    private val addMeasurementUseCase: AddMeasurementUnitUseCase,
    private val deleteMeasurementUnitUseCase: DeleteMeasurementUnitUseCase// Assuming you have a use case for adding measurement units
) : ViewModel() {

    private val _units = MutableStateFlow<List<MeasurementUnit>>(emptyList())
    val units: StateFlow<List<MeasurementUnit>> = _units.asStateFlow()


    init {
        getMeasurementUnits()
    }

    private fun getMeasurementUnits() {
        viewModelScope.launch(Dispatchers.IO) {
            getMeasurementUseCase().distinctUntilChanged().collect { measurementUnits ->
                _units.value = measurementUnits
            }
        }
    }

    fun saveMeasurementUnit(selectedUnit: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMeasurementUnitUseCase()
            addMeasurementUseCase(AddMeasurementUnitUseCase.Params(selectedUnit))
        }
    }


}