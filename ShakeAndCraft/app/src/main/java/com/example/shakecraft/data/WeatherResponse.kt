package com.example.shakecraft.data

data class WeatherResponse(
    val weather: List<Weather>,
    val info: Info
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Info(
    val temp: Double,
    val feelsLike: Double,
    val humidity: Int
)