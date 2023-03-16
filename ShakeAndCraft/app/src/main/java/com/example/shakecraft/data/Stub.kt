package com.example.shakecraft.data

import com.example.shakecraft.model.ItemManager.Companion.ITEMS
import com.example.shakecraft.model.Item
import com.example.shakecraft.model.Player
import com.example.shakecraft.model.Tool


class Stub {

    fun load() : Player{
        val currentPlayer = Player("Winker",0)
        val items : MutableList<Item> = mutableListOf()
        items.add(Item(type = ITEMS.BEECH_LOG.itemtype, stack = 30))
        items.add(Item(type = ITEMS.BRONZE_INGOT.itemtype, stack = 30))
        items.add(Tool(type = ITEMS.BRONZE_SWORD.itemtype, stack = 1, damage = 4))
        items.add(Tool(type = ITEMS.DIAMOND_AXE.itemtype, stack = 1, damage = 8))

        currentPlayer.items = items
        return currentPlayer
    }




}