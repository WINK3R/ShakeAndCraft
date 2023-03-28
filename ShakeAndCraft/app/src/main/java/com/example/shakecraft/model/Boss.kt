package com.example.shakecraft.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Boss")
data class Boss (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "life")
    var life: Int,
    @ColumnInfo(name = "maxlife")
    var maxlife: Int,
    @ColumnInfo(name = "image")
    var image: Int,
    @ColumnInfo(name = "xpreward")
    var xpReward: Int,
    val possibleLoot: List<Pair<Item, Double>>,
){

    fun takeDamage(strength: Int) {
        this.life -= strength
    }
}