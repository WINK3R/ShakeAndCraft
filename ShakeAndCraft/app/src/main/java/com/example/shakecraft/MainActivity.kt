package com.example.shakecraft

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.shakecraft.services.OpenWeatherMapService
import com.example.shakecraft.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity: AppCompatActivity() {

    var isRaining = false
    private val model: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,
            window.decorView.findViewById(android.R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())

            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

    }

    private lateinit var bottomNav : BottomNavigationView
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        hideSystemUI()
        setContentView(R.layout.activity_main)
        val apiKey = "85a2724ad38b3994c2b7ebe1d239bbff"
        val cityName = "Clermont-Ferrand"

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val openWeatherMapService = retrofit.create(OpenWeatherMapService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val weatherResponse = openWeatherMapService.getCurrentWeather(cityName, apiKey)

            isRaining =
                weatherResponse.weather.any { it.main.contains("rain", ignoreCase = true) }

            println(isRaining)
        }

        bottomNav = findViewById(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        navController.popBackStack(R.id.fragment, false)
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemReselectedListener { item ->
            // Pop everything up to the reselected item
            val reselectedDestinationId = item.itemId
            navController.popBackStack(reselectedDestinationId, inclusive = false)
        }
        bottomNav.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            true
        }



    }




    override fun onResume() {
        super.onResume()

        // Masquer la barre de navigation et la barre d'état
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }





}