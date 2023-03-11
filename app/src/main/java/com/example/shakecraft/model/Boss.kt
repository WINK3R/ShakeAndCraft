package com.example.shakecraft.model

import com.example.shakecraft.R

class Boss (
    var name: String,
    var life: Int,
    var maxlife: Int,
    var image: Int,
    var xpReward: Int,
){
    val possibleLoot: List<Pair<Item, Double>> = listOf(
        Pair(Item(name = "Monster Bones", rarity = 1, stack = 1, R.drawable.monster_bones, 10), 0.7),
        Pair(Item(name = "Monster Eye", rarity = 2, stack = 1, R.drawable.monster_eyes, 20), 0.25),
        Pair(Item(name = "Treasure Key", rarity = 5, stack = 1, R.drawable.treasure_key, 50), 0.05),
    )
    fun takeDamage(strength: Int) {
        this.life -= strength
    }
}