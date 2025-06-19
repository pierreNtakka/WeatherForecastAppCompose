package com.pietroditta.weatherforecastapp.di

import android.util.Log
import com.pietroditta.weatherforecastapp.BuildConfig
import com.pietroditta.weatherforecastapp.network.ApiKeyInterceptor
import com.pietroditta.weatherforecastapp.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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