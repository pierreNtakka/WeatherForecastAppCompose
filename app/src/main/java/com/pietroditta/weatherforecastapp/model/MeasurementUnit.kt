package com.pietroditta.weatherforecastapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measurement_units")
data class MeasurementUnit(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val unitName: String
)
