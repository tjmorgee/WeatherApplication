package com.example.weatherapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weatherapplication.databinding.FragmentForecastBinding

val temp1 = ForecastTemp(45f,72f)
val temp2 = ForecastTemp(52f,82f)
val temp3 = ForecastTemp(48f,77f)
val temp4 = ForecastTemp(56f,74f)
val temp5 = ForecastTemp(49f,70f)
val temp6 = ForecastTemp(55f,89f)
val temp7 = ForecastTemp(77f,93f)
val temp8 = ForecastTemp(42f,68f)
val temp9 = ForecastTemp(41f,66f)
val temp10 = ForecastTemp(40f,62f)
val temp11 = ForecastTemp(39f,64f)
val temp12 = ForecastTemp(49f,68f)
val temp13 = ForecastTemp(36f,56f)
val temp14 = ForecastTemp(41f,61f)
val temp15 = ForecastTemp(33f,52f)
val temp16 = ForecastTemp(34f,57f)

val forecastData = listOf<DayForecast>(
    DayForecast(1664423548L, 1665644431L, 1665696631L, temp1, 1023f, 99),
    DayForecast(1664484511L, 1665644551L, 1665696511L, temp2, 1087f, 82),
    DayForecast(1664570911L, 1665644611L, 1665696451L, temp3, 1028f, 77),
    DayForecast(1664657311L, 1665644791L, 1665696271L, temp4, 1011f, 89),
    DayForecast(1664743711L, 1665644911L, 1665696091L, temp5, 1049f, 37),
    DayForecast(1664830111L, 1665645031L, 1665695791L, temp6, 1065f, 50),
    DayForecast(1664916511L, 1665645211L, 1665695731L, temp7, 1012f, 46),
    DayForecast(1665002911L, 1665645331L, 1665695611L, temp8, 1039f, 91),
    DayForecast(1665089311L, 1665645391L, 1665695431L, temp9, 1033f, 36),
    DayForecast(1665175711L, 1665645511L, 1665695371L, temp10, 1063f, 72),
    DayForecast(1665262111L, 1665645631L, 1665695191L, temp11, 1094f, 94),
    DayForecast(1665348511L, 1665645691L, 1665695131L, temp12, 1061f, 16),
    DayForecast(1665434911L, 1665645811L, 1665695011L, temp13, 1048f, 88),
    DayForecast(1665521311L, 1665645931L, 1665694891L, temp14, 1004f, 57),
    DayForecast(1665607711L, 1665646051L, 1665694711L, temp15, 1061f, 13),
    DayForecast(1665694111L, 1665646231L, 1665694531L, temp16, 1082f, 80),
)

class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    private lateinit var binding: FragmentForecastBinding
    private val args: ForecastFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)
        binding.forecastList.adapter = ForecastAdapter(forecastData)
    }
}