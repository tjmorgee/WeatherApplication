package com.example.weatherapplication.service

import com.example.weatherapplication.models.CurrentConditions
import com.example.weatherapplication.models.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "c2a337bdfc23e2f15dbd73d206138390",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String = "c2a337bdfc23e2f15dbd73d206138390",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecast(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "c2a337bdfc23e2f15dbd73d206138390",
        @Query("cnt") numDays: Int = 16,
        @Query("units") units: String = "imperial"
    ) : Forecast

    @GET("data/2.5/forecast/daily")
    suspend fun getForecast(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String = "c2a337bdfc23e2f15dbd73d206138390",
        @Query("cnt") numDays: Int = 16,
        @Query("units") units: String = "imperial"
    ) : Forecast
}