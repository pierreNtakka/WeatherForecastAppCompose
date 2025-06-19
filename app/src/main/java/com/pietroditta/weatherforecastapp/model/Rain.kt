package com.pietroditta.weatherforecastapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Rain(
    @SerialName("3h")
    val treeHours: Double
)