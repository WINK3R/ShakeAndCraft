package com.example.shakecraft.services
import com.example.shakecraft.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): WeatherResponse
}