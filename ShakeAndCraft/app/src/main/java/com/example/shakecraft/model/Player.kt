package com.example.shakecraft.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shakecraft.R

@Entity(tableName="Player")
class Player() {
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
    @ColumnInfo(name = "name")
    val pseudo: String = "Unknow"
    @ColumnInfo(name = "level")
    var level: Int = 0
    @ColumnInfo(name = "xp")
    var xp: Int = 0
    @ColumnInfo(name = "image")
    val image: Int = R.drawable.player_image
    @ColumnInfo(index = true)
    var rank: String = "Beginner"
    val inventory: Inventory= Inventory()


    fun changeRank(){
        this.rank = when(level){
            in 1..2 -> "Beginner"
            in 3..5 -> "Intermediate"
            in 6..8 -> "Proficient"
            in 9..11 -> "Expert"
            in 12..14 -> "Master"
            in 15..17 -> "Professional"
            in 18..19 -> "Champion"
            in 20..22 -> "Legend"
            in 23..25 -> "Invincible"
            else -> {"Divine"}
        }
    }


    fun gainXp(xp: Int) {
        this.xp += xp
        if (this.xp >= this.level *100){
            this.level +=1
            this.xp = 0
            changeRank()
        }
    }

}