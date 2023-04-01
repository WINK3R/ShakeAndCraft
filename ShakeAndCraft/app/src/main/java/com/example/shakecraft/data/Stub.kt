package com.example.shakecraft.data

import com.example.shakecraft.model.*


class Stub {

    fun load() : Player{
        val currentPlayer = Player("Winker",0)

        currentPlayer.addItem(Item(type = ITEMS.BEECH_LOG.itemtype, stack = 30))
        currentPlayer.addItem(Item(type = ITEMS.BRONZE_INGOT.itemtype, stack = 30))
        currentPlayer.addItem(Tool(type = ITEMS.DIAMOND_AXE.itemtype, stack = 1, damage = 8))
        return currentPlayer
    }




}