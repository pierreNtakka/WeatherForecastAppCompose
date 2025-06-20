package com.pietroditta.weatherforecastapp.repository

import com.pietroditta.weatherforecastapp.data.db.FavoriteDao
import com.pietroditta.weatherforecastapp.data.db.MeasurementUnitDao
import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.model.MeasurementUnit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val favoriteDao: FavoriteDao,
    private val measurementUnitDao: MeasurementUnitDao
) {

    fun getAll(): Flow<List<Favorite>> {
        return favoriteDao.getAll()
    }

    suspend fun add(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite)
    }

    suspend fun remove(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }

    suspend fun deleteAll() {
        favoriteDao.deleteAll()
    }

    suspend fun deleteByNameLatLon(name: String, lat: Double, lon: Double) {
        favoriteDao.deleteByNameLatLon(name, lat, lon)
    }

    suspend fun findByCityNameLatLon(name: String, lat: Double, lon: Double): Favorite? {
        return favoriteDao.findByCityNameLatLon(name, lat, lon)
    }

    fun getAllMeasurementUnits() = measurementUnitDao.getAll()

    suspend fun insertMeasurementUnit(unit: String) {
        measurementUnitDao.insertMeasurementUnit(MeasurementUnit(unit))

    }

    suspend fun deleteMeasurementUnits() {
        measurementUnitDao.deleteAll()
    }

}