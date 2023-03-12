package com.example.shakecraft.model


data class Item(
    var name: String,
    var rarity: Int = 1,
    var stack: Int = 1,
    var image: Int,
    var xpReward: Int = 0,
)


