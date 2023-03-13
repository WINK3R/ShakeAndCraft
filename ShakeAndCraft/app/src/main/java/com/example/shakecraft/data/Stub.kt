package com.example.shakecraft.data

import com.example.shakecraft.R
import com.example.shakecraft.model.Item
import com.example.shakecraft.model.Player


class Stub {

    fun load() : Player{
        var currentPlayer : Player = Player("Winker",0)
        val items : MutableList<Item> = mutableListOf<Item>()
        items.add(Item(name = "Beech Log", rarity = 1, stack = 30,  R.drawable.log2, xpReward = 10 ))

        currentPlayer.items = items
        return currentPlayer
    }




}