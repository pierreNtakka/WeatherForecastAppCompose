package com.pietroditta.weatherforecastapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pietroditta.weatherforecastapp.data.db.converter.UUIDConverter
import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.model.MeasurementUnit

@Database(entities = [Favorite::class, MeasurementUnit::class], version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "weather_database"
    }

    abstract fun favoriteDao(): FavoriteDao

    abstract fun measurementUnitDao(): MeasurementUnitDao

}