package com.example.weatherapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastTemp(
    val min: Float,
    val max: Float,
) : Parcelable {
    override fun toString(): String {
        return "High: $max°\nLow: $min°"
    }
}
