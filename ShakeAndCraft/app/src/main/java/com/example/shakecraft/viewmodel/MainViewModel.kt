package com.example.shakecraft.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shakecraft.data.Stub
import com.example.shakecraft.model.Boss
import com.example.shakecraft.model.Player

class MainViewModel : ViewModel() {

    var currentPlayer : Player = Stub().load()
    var currentBoss : Boss? = null
}