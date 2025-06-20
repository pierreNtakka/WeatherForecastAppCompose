package com.pietroditta.weatherforecastapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pietroditta.weatherforecastapp.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(vararg notes: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Favorite)

    @Delete
    suspend fun delete(note: Favorite)

    @Query("DELETE FROM favorites")
    suspend fun deleteAll()

    @Query("DELETE FROM favorites WHERE name = :cityName AND lat = :lat AND lon = :lon")
    suspend fun deleteByNameLatLon(cityName: String, lat: Double, lon: Double)

    @Query("SELECT * FROM favorites WHERE name = :cityName AND lat = :lat AND lon = :lon")
    suspend fun findByCityNameLatLon(cityName: String, lat: Double, lon: Double): Favorite?
}