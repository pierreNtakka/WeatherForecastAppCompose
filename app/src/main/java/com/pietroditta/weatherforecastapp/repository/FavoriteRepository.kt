package com.pietroditta.weatherforecastapp.repository

import com.pietroditta.weatherforecastapp.data.db.FavoriteDao
import com.pietroditta.weatherforecastapp.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
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

}