package com.pietroditta.weatherforecastapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GeocoderResult(
    val country: String,
    val lat: Double,
    @SerialName("local_names")
    val localNames: Map<String, String>? = null,
    val lon: Double,
    val name: String,
    val state: String? = null
) : Parcelable