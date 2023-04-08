package com.example.shakecraft

import android.app.Application
import com.example.shakecraft.data.DataBase

class PlayerApplication: Application(){
    val database: DataBase by lazy { DataBase.getInstance(this) }
}