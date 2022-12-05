package com.example.weatherapplication.models

import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "icon") val iconName: String,
)

data class CurrentConditionsData(
    @Json(name = "temp") val temperature: Float,
    @Json(name = "feels_like") val feelsLike: Float,
    @Json(name = "temp_min") val min: Float,
    @Json(name = "temp_max") val max: Float,
    @Json(name = "pressure") val pressure: Float,
    @Json(name = "humidity") val humidity: Float,
)

data class CurrentConditions(
    @Json(name = "name") val cityName: String,
    @Json(name = "weather") val weatherData: List<WeatherData>,
    @Json(name = "main") val conditions: CurrentConditionsData,
)
