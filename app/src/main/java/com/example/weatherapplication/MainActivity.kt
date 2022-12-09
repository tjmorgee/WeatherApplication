package com.example.weatherapplication

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapplication.models.LatitudeLongitude
import com.example.weatherapplication.ui.CurrentConditions
import com.example.weatherapplication.ui.ForecastScreen
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.setValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "CurrentConditions") {
                composable("CurrentConditions") {
                    var latitudeLongitude: LatitudeLongitude? by remember { mutableStateOf(null) }
                    val onResult = { value: Boolean ->
                        if (ActivityCompat.checkSelfPermission(this@MainActivity, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            fusedLocationProviderClient
                                .getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
                                .addOnSuccessListener { location ->
                                    latitudeLongitude = LatitudeLongitude(
                                        latitude = location.latitude.toFloat(),
                                        longitude = location.longitude.toFloat(),
                                    )
                                }
                        }
                    }
                    val requestPermissionLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = onResult
                    )
                    CurrentConditions(
                        latitudeLongitude = latitudeLongitude,
                        onGetWeatherForMyLocationClick = {
                            requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
                        }
                    ) {
                        navController.navigate("Forecast")
                    }
                }

                composable("Forecast") {
                    var latitudeLongitude: LatitudeLongitude? by remember { mutableStateOf(null) }
                    fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                        latitudeLongitude = LatitudeLongitude(
                            latitude = it.latitude.toFloat(),
                            longitude = it.longitude.toFloat(),
                        )
                    }
                    ForecastScreen(latitudeLongitude)
                }
            }
        }
    }
}