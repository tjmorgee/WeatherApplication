package com.example.weatherapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ForecastAdapter(private val data: List<DayForecast>) : RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val date: TextView
    private val sunrise: TextView
    private val sunset: TextView
    private val temp: TextView
    private val pressure: TextView
    private val humidity: TextView

    init {
        date = view.findViewById(R.id.date)
        sunrise = view.findViewById(R.id.sunrise)
        sunset = view.findViewById(R.id.sunset)
        temp = view.findViewById(R.id.temp)
        pressure = view.findViewById(R.id.pressure)
        humidity = view.findViewById(R.id.humidity)
    }

    fun bind(data: DayForecast) {
        date.text= convertDate(data.date)
        sunrise.text = convertTimeSunrise(data.sunrise)
        sunset.text = convertTimeSunset(data.sunset)
        temp.text = data.temp.toString()
        pressure.text = convertPressure(data.pressure)
        humidity.text = convertHumidity(data.humidity)
    }

    // Used these convert functions to add in the specific variable tags such as (Sunset, Humidity, etc.) since it wasn't working
    // when I tried for example "Sunrise: %d" within the strings.xml file
    private fun convertDate(date: Long):String {
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val dateTime = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.of("-5")) // Has to be switched to Long to work
        return(formatter.format(dateTime))
    }

    private fun convertTimeSunrise(time: Long):String {
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
        val dateTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.of("-0"))
        val formattedTime = timeFormatter.format(dateTime)
        return("Sunrise: $formattedTime")
    }

    private fun convertTimeSunset(time: Long):String {
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")
        val dateTime = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.of("-0"))
        val formattedTime = timeFormatter.format(dateTime)
        return("Sunset: $formattedTime")
    }

    private fun convertPressure(pressure: Float):String {
        return("Pressure: $pressure hPa")
    }

    private fun convertHumidity(humidity: Int):String {
        return("Humidity: $humidity%")
    }
}