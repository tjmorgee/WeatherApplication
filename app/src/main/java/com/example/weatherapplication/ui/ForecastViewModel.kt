package com.example.weatherapplication.ui

import androidx.lifecycle.ViewModel
import com.example.weatherapplication.models.Forecast
import com.example.weatherapplication.service.OpenWeatherMapApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor (private val api: OpenWeatherMapApi): ViewModel() {
    private val _forecast = Channel<Forecast>()

    public val forecast: Flow<Forecast> = _forecast.receiveAsFlow()

    fun fetchData() = runBlocking {
        val forecast = api.getForecast("55304")
        _forecast.trySend(forecast)
    }
}