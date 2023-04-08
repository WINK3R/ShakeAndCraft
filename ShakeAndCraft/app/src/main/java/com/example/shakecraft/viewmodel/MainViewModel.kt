package com.example.shakecraft.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shakecraft.data.Stub
import com.example.shakecraft.model.Boss
import com.example.shakecraft.model.Item
import com.example.shakecraft.model.Recipe

class MainViewModel : ViewModel() {

    var currentPlayer = MutableLiveData(Stub().load())


    lateinit var currentBoss : Boss
    val isBossInitialized get() = this::currentBoss.isInitialized

    fun craft(recipe : Recipe, count : Int = 1){
        currentPlayer.value?.craft(recipe, count)
        currentPlayer.value = currentPlayer.value
    }

    fun addItem(item: Item) {
        currentPlayer.value?.addItem(item)
        currentPlayer.value = currentPlayer.value

    }

    fun gainXp(xpReward: Int) {
        currentPlayer.value?.gainXp(xpReward)
        currentPlayer.value = currentPlayer.value
    }

    fun equipeItem(item: Item): Boolean {
        currentPlayer.value?.equipeItem(item)
        currentPlayer.value = currentPlayer.value
        return true
    }
}