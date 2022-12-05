package com.example.weatherapplication.models

import com.squareup.moshi.Json

data class ForecastData(
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val temp: Temp,
    @Json(name = "weather") val weather: List<Weather>,
)

data class Weather(
    @Json(name = "icon") val iconName: String,
)

data class Temp(
    @Json(name = "min") val min: Float,
    @Json(name = "max") val max: Float,
)

data class Forecast(
    @Json(name = "list") val conditions: List<ForecastData>,
)