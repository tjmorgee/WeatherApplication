package com.example.weatherapplication

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun Long.toMonthDay(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("MMM dd")
    val dateTime = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.of("-6"))
    return dateFormatter.format(dateTime)
}

fun Long.toHourMinute(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("h:mm a")
    val dateTime = LocalDateTime.ofEpochSecond(this, 0, ZoneOffset.of("-6"))
    return dateFormatter.format(dateTime)
}