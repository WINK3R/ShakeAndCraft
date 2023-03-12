package com.example.shakecraft.model


class Boss (
    var name: String,
    var life: Int,
    var maxlife: Int,
    var image: Int,
    var xpReward: Int,
    val possibleLoot: List<Pair<Item, Double>>,
){

    fun takeDamage(strength: Int) {
        this.life -= strength
    }
}