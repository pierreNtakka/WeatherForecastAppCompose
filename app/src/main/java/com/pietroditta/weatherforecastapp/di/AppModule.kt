package com.pietroditta.weatherforecastapp.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pietroditta.weatherforecastapp.BuildConfig
import com.pietroditta.weatherforecastapp.data.db.FavoriteDao
import com.pietroditta.weatherforecastapp.data.db.MeasurementUnitDao
import com.pietroditta.weatherforecastapp.data.db.WeatherDatabase
import com.pietroditta.weatherforecastapp.data.network.ApiKeyInterceptor
import com.pietroditta.weatherforecastapp.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideFavoriteDao(
        db: WeatherDatabase
    ): FavoriteDao {
        return db.favoriteDao()
    }

    @Singleton
    @Provides
    fun measurementUnitDao(
        db: WeatherDatabase
    ): MeasurementUnitDao {
        return db.measurementUnitDao()
    }

    @Singleton
    @Provides
    fun provideWeatherDatabase(
        @ApplicationContext appContext: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            appContext, WeatherDatabase::class.java, WeatherDatabase.DATABASE_NAME
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                super.onCreate(db)
                db.execSQL("INSERT INTO measurement_units (name) VALUES ('metric')")
            }
        }).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor { message ->
            Log.d("OkHttpClient", message)
        }
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor()).addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(
        okHttpClient: OkHttpClient
    ): WeatherApi {
        return Retrofit.Builder().addConverterFactory(
            Json.asConverterFactory(
                "application/json; charset=UTF8".toMediaType()
            )
        ).baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient).build()
            .create(WeatherApi::class.java)
    }

}