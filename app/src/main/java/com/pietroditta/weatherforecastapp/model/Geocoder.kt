package com.pietroditta.weatherforecastapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geocoder(
    val country: String,
    val lat: Double,
    @SerialName("local_names")
    val localNames: Map<String, String>? = null,
    val lon: Double,
    val name: String,
    val state: String? = null
)