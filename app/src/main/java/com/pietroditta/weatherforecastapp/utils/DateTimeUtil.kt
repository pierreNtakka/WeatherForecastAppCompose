package com.pietroditta.weatherforecastapp.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DateTimeUtil {

    fun retrieveTimeFromUnixTimestamp(unixTimestamp: Long): String {
        val dateTime = retrieveLocalDateTimeFromUnixTimestamp(unixTimestamp)

        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return dateTime.format(formatter)
    }

    fun retrieveDayOfWeekFromUnixTimestamp(unixTimestamp: Long): String {

        val dateTime = retrieveLocalDateTimeFromUnixTimestamp(unixTimestamp)

        return dateTime.dayOfWeek
            .getDisplayName(TextStyle.SHORT, Locale.getDefault())
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    private fun retrieveLocalDateTimeFromUnixTimestamp(unixTimestamp: Long): LocalDateTime {
        return LocalDateTime.ofInstant(
            Instant.ofEpochSecond(unixTimestamp),
            ZoneId.systemDefault()
        )
    }

    fun retrieveHourOfTheDayFromUnixTimestamp(unixTimestamp: Long): String {
        val dateTime = retrieveLocalDateTimeFromUnixTimestamp(unixTimestamp)
        return dateTime.hour.toString().padStart(2, '0')
    }
}