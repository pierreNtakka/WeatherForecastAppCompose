package com.pietroditta.weatherforecastapp.repository

import com.pietroditta.weatherforecastapp.model.Favorite
import com.pietroditta.weatherforecastapp.model.Weather
import kotlinx.serialization.json.Json

object MockedDataRepository {


    fun getMockedWeatherData(): Weather {
        val weatherJson =
            "{\"cod\":\"200\",\"message\":0,\"cnt\":5,\"list\":[{\"dt\":1750323600,\"main\":{\"temp\":23.98,\"feels_like\":24.24,\"temp_min\":23.98,\"temp_max\":24.61,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":986,\"humidity\":69,\"temp_kf\":-0.63},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":40},\"wind\":{\"speed\":2.23,\"deg\":216,\"gust\":2.97},\"visibility\":10000,\"pop\":0.57,\"rain\":{\"3h\":0.23},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2025-06-19 09:00:00\"},{\"dt\":1750334400,\"main\":{\"temp\":23.23,\"feels_like\":23.33,\"temp_min\":21.72,\"temp_max\":23.23,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":987,\"humidity\":66,\"temp_kf\":1.51},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":51},\"wind\":{\"speed\":1.44,\"deg\":239,\"gust\":1.92},\"visibility\":9800,\"pop\":0.98,\"rain\":{\"3h\":2.07},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2025-06-19 12:00:00\"},{\"dt\":1750345200,\"main\":{\"temp\":22.84,\"feels_like\":23.22,\"temp_min\":22.27,\"temp_max\":22.84,\"pressure\":1017,\"sea_level\":1017,\"grnd_level\":986,\"humidity\":78,\"temp_kf\":0.57},\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":80},\"wind\":{\"speed\":1,\"deg\":91,\"gust\":1.52},\"visibility\":9384,\"pop\":1,\"rain\":{\"3h\":4.24},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2025-06-19 15:00:00\"},{\"dt\":1750356000,\"main\":{\"temp\":21.65,\"feels_like\":21.88,\"temp_min\":21.65,\"temp_max\":21.65,\"pressure\":1017,\"sea_level\":1017,\"grnd_level\":986,\"humidity\":77,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.98,\"deg\":72,\"gust\":2.68},\"visibility\":10000,\"pop\":1,\"rain\":{\"3h\":2.01},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2025-06-19 18:00:00\"},{\"dt\":1750366800,\"main\":{\"temp\":20.78,\"feels_like\":20.98,\"temp_min\":20.78,\"temp_max\":20.78,\"pressure\":1017,\"sea_level\":1017,\"grnd_level\":987,\"humidity\":79,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.18,\"deg\":48,\"gust\":0.95},\"visibility\":10000,\"pop\":0.4,\"rain\":{\"3h\":0.33},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2025-06-19 21:00:00\"}],\"city\":{\"id\":6535661,\"name\":\"Buseto Palizzolo\",\"coord\":{\"lat\":37.9454,\"lon\":12.697},\"country\":\"IT\",\"population\":3197,\"timezone\":7200,\"sunrise\":1750304801,\"sunset\":1750358067}}\n"

        return Json.decodeFromString(Weather.serializer(), weatherJson)
    }

    fun getFavorites(): List<Favorite> {
        return listOf(
            Favorite(
                name = "Rome",
                country = "IT",
                lat = 41.9028,
                lon = 12.4964,
            ),
            Favorite(
                name = "New York",
                country = "US",
                lat = 40.7128,
                lon = -74.0060,
            ),
            Favorite(
                name = "Tokyo",
                country = "JP",
                lat = 35.6762,
                lon = 139.6503,
            ),
            Favorite(
                name = "Sydney",
                country = "AU",
                lat = -33.8688,
                lon = 151.2093,
            )
        )
    }
}