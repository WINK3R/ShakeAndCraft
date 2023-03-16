package com.example.shakecraft.model


class Boss (
    var name: String,
    var life: Double,
    var maxlife: Int,
    var image: Int,
    var xpReward: Int,
    val possibleLoot: List<Pair<Item, Double>>,
){

    fun takeDamage(strength: Double) {
        this.life -= strength
    }
}