package com.pietroditta.weatherforecastapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pietroditta.weatherforecastapp.model.MeasurementUnit
import kotlinx.coroutines.flow.Flow


@Dao
interface MeasurementUnitDao {

    @Query("SELECT * FROM measurement_units")
    fun getAll(): Flow<List<MeasurementUnit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeasurementUnit(vararg unit: MeasurementUnit)

    @Query("DELETE FROM measurement_units")
    suspend fun deleteAll()
}