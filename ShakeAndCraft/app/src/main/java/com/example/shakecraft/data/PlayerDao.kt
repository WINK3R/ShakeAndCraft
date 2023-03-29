package com.example.shakecraft.data

import androidx.room.Query
import com.example.shakecraft.model.Boss
import com.example.shakecraft.model.Player
import kotlinx.coroutines.flow.Flow
import java.util.List

interface PlayerDao {
    @Query("SELECT * from Player ")
    fun getBoss(): Flow<List<Player>>

    @Query("SELECT * from Player WHERE name = :name")
    fun getBoss(name : String): Flow<Player>
}