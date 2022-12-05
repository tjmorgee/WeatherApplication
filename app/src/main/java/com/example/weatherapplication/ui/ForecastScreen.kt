package com.example.weatherapplication.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.weatherapplication.R
import com.example.weatherapplication.models.*
import com.example.weatherapplication.toHourMinute
import com.example.weatherapplication.toMonthDay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel(),
) {
    val state by viewModel.forecast.collectAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    Scaffold(
        topBar = { AppBar(title = stringResource(id = R.string.app_name, "Forecast")) },
    )  {
        state?.let {
            LazyColumn{
                items(items = it.conditions) { item: ForecastData ->
                    ForecastRow(item = item)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun ForecastRow(item: ForecastData) {
    Row(
        modifier = Modifier.background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textStyle = TextStyle(
            fontSize = 16.sp,
        )
        val iconUrl = String.format("https://openweathermap.org/img/wn/%s@2x.png", item.weather.firstOrNull()?.iconName)
        AsyncImage(
            model = iconUrl,
            modifier = Modifier.size(72.dp),
            contentDescription = "Sunny",
        )
        Spacer(modifier = Modifier.weight(.25f, fill = true))
        Text(
            text = item.date.toMonthDay(),
            style = TextStyle(
                fontSize = 20.sp,
            )
        )
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Column {
            Text(
                text = stringResource(id = R.string.high_temp, item.temp.max.toInt()),
                style = textStyle,
            )
            Text(
                text = stringResource(id = R.string.low_temp, item.temp.min.toInt()),
                style = textStyle,
            )
        }
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = stringResource(id = R.string.sunrise, item.sunrise.toHourMinute()),
                style = textStyle,
            )
            Text(
                text = stringResource(id = R.string.sunset, item.sunset.toHourMinute()),
                style = textStyle,
            )
        }
    }
}