package com.example.weatherapplication.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.weatherapplication.R
import com.example.weatherapplication.models.CurrentConditions
import com.example.weatherapplication.models.LatitudeLongitude

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentConditions(
    latitudeLongitude: LatitudeLongitude?,
    viewModel: CurrentConditionsViewModel = hiltViewModel(),
    onGetWeatherForMyLocationClick: () -> Unit,
    onForecastButtonClick: () -> Unit,
) {
    val state by viewModel.currentConditions.collectAsState(null)

    if (latitudeLongitude != null) {
        LaunchedEffect(Unit) {
            viewModel.fetchCurrentLocationData(latitudeLongitude)
        }
    } else {
        LaunchedEffect(Unit) {
            viewModel.fetchData()
        }
    }

    Scaffold(
        topBar = { AppBar(title = stringResource(id = R.string.app_name, "Current Conditions")) },
    ) {
        state?.let {
            CurrentConditionsContent(it, onGetWeatherForMyLocationClick, onForecastButtonClick)
        }
    }

}

@Composable
private fun CurrentConditionsContent(
    currentConditions: CurrentConditions,
    onGetWeatherForMyLocationClick: () -> Unit,
    onForecastButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.city, currentConditions.cityName),
            style = TextStyle(
                fontWeight = FontWeight(600),
                fontSize = 28.sp
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.temperature, currentConditions.conditions.temperature.toInt()),
                    style = TextStyle(
                        fontWeight = FontWeight(400),
                        fontSize = 72.sp,
                    )
                )
                Text(
                    text = stringResource(id = R.string.feels_like_temp, currentConditions.conditions.feelsLike.toInt()),
                    style = TextStyle(
                        fontSize = 14.sp,
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f, fill = true))
            val iconUrl = String.format("https://openweathermap.org/img/wn/%s@2x.png", currentConditions.weatherData.firstOrNull()?.iconName)
            AsyncImage(
                model = iconUrl,
                modifier = Modifier.size(100.dp),
                contentDescription = "Sunny"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            val textStyle = TextStyle(
                fontSize = 18.sp,
            )
            Text(text = stringResource(id = R.string.low_temp, currentConditions.conditions.min.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.high_temp, currentConditions.conditions.max.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.humidity, currentConditions.conditions.humidity.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.pressure, currentConditions.conditions.pressure.toInt()), style = textStyle)
        }

        Spacer(modifier = Modifier.height(96.dp))
        Button(onClick = onForecastButtonClick) {
            Text(text = stringResource(id = R.string.forecast))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onGetWeatherForMyLocationClick) {
            Text(text = stringResource(id = R.string.get_weather_for_my_location))
        }
    }
}