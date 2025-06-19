package com.pietroditta.weatherforecastapp.model

data class DataOrException<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: kotlin.Boolean = false,
    var e: E? = null
)