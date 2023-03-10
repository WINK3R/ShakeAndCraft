package com.example.shakecraft.data

import com.example.shakecraft.model.Item


class Stub {

    fun load() : List<Item>{
        val items : MutableList<Item> = mutableListOf<Item>()
        items.add(Item(name = "Beech Log", rarity = 0, stack = 1));
        items.add(Item(name = "Bronze Ore", rarity = 0, stack = 1));
        items.add(Item(name = "Iron Ore", rarity = 0, stack = 1));
        items.add(Item(name = "Diamond", rarity = 0, stack = 1));
        return items;
    }

}