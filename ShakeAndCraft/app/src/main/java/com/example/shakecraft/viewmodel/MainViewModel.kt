package com.example.shakecraft.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shakecraft.data.Stub
import com.example.shakecraft.model.Boss
import com.example.shakecraft.model.Recipe

class MainViewModel : ViewModel() {

    var currentPlayer = MutableLiveData(Stub().load())


    lateinit var currentBoss : Boss
    val isBossInitialized get() = this::currentBoss.isInitialized

    fun craft(recipe : Recipe){
        currentPlayer.value?.craft(recipe)
        currentPlayer.value = currentPlayer.value
    }
}