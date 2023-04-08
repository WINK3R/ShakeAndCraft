package com.example.shakecraft

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shakecraft.data.PlayerDao
import com.example.shakecraft.model.Player
import kotlinx.coroutines.launch

class PlayerViewModel(private val playerDao: PlayerDao): ViewModel() {
    val player:LiveData<Player> = playerDao.getPlayer()
        .asLiveData()
    fun updatePlayer(
        Id: Int,
        pseudo: String,
        level: Int,
        xp: Int,
        image: Int,
        rank: String,
    ){
        val updatePlayer= getUpdatePlayerEntry(Id, pseudo, level, xp, image, rank)
        updatePlayer(updatePlayer)
    }

    private fun updatePlayer(player: Player) {
        viewModelScope.launch{
            playerDao.update(player)
        }
    }

    private fun getUpdatePlayerEntry(
        Id: Int,
        pseudo: String,
        level: Int,
        xp: Int,
        image: Int,
        rank: String,
    ): Player {
        return Player(
            id= Id,
            pseudo= pseudo,
            level= level,
            xp= xp,
            image= image,
            rank= rank,
        )
    }
}

class PlayerViewModelFactory(private val playerDao: PlayerDao): ViewModelProvider.Factory{
    fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(playerDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}