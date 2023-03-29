package com.example.shakecraft.data
import androidx.room.Database
import com.example.shakecraft.model.Player

@Database(entities = [Player::class],version = 1)
abstract class DataBase {

}