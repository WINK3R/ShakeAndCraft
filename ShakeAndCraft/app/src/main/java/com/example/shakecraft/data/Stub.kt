package com.example.shakecraft.data

import com.example.shakecraft.model.ItemManager.Companion.ITEMS
import com.example.shakecraft.model.Item
import com.example.shakecraft.model.Player


class Stub {

    fun load() : Player{
        val currentPlayer = Player("Winker",0)
        val items : MutableList<Item> = mutableListOf()
        items.add(Item(type = ITEMS.BEECH_LOG.itemtype, stack = 30))

        currentPlayer.items = items
        return currentPlayer
    }




}