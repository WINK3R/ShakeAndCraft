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
    @ColumnInfo(name = "rank")
    var rank: String = "Beginner"
    //@ColumnInfo(name = "items") Je ne sais pas comment faire pour stocker la liste dans la BDD
    var items: MutableList<Item> = mutableListOf()


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

    fun addItem(item: Item) {
        val findItem = items.find { it.type.name == item.type.name }

        if(findItem!= null){
            println("findItem n: "+findItem.stack+" item nb:"+item.stack)
            findItem.stack += item.stack
        }
        else{items.add(Item(type = item.type, stack = item.stack))}
    }

    fun hasItem(item: Item) : Boolean{
        for (playerItem in items){
            if(playerItem.type.name == item.type.name && playerItem.stack >= item.stack){
                return true
            }
        }
        return false
    }


    fun craft(recipe: Recipe) : Boolean{
        println("test")
        for (ingredient in recipe.ingredients) {
            val searchedItem = items.find { it.type.name == ingredient.type.name }
            if(searchedItem != null) {
                searchedItem.stack -= ingredient.stack
                if (searchedItem.stack == 0){
                    items.remove(searchedItem)
                }
                println(ingredient.type.name)
                if (recipe.item.type.name == "Open Treasure"){
                    addItem(Generator.generateTreasure())
                    return true
                }
            }
        }
        println("item:"+recipe.item.stack)
        addItem(recipe.item)
        return true
    }
}