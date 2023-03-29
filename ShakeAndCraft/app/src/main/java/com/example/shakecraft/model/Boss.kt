package com.example.shakecraft.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Boss (
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