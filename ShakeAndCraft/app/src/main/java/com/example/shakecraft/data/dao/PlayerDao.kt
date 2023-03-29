package com.example.shakecraft.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shakecraft.model.Player

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    suspend fun getAllPlayers(): List<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)
}