package com.example.weatherapplication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(title: String) {
    TopAppBar(
        backgroundColor = Color.Blue,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight(700),
                fontSize = 24.sp,
            )
        )
    }
}