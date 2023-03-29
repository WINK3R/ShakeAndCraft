package com.example.shakecraft.data

import com.example.shakecraft.model.ItemManager.Companion.ITEMS
import com.example.shakecraft.model.Item
import com.example.shakecraft.model.Player


class Stub {

    fun load() : Player{
        val currentPlayer = Player()
        val items : MutableList<Item> = mutableListOf()
        items.add(Item(type = ITEMS.BEECH_LOG.itemtype, stack = 30))
        items.add(Item(type = ITEMS.BRONZE_INGOT.itemtype, stack = 30))
        items.add(Item(type = ITEMS.TREASURE.itemtype, stack = 2))
        items.add(Item(type = ITEMS.TREASURE_KEY.itemtype, stack = 1))

        currentPlayer.items = items
        return currentPlayer
    }




}