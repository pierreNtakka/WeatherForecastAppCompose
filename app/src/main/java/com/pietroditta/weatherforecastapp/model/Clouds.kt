package com.pietroditta.weatherforecastapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int
)