package com.example.shakecraft.model

import com.example.shakecraft.R

class Player(var pseudo: String, var xp: Int = 0) {
    var level: Int = 1
        private set
    val image: Int = R.drawable.player_image

    var items: MutableList<Item> = mutableListOf()
        private set
    var rank: String = "Beginner"
        private set
    var equipedItem : Tool? = null
        private set


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
    fun addItem(item: Item) {
        val findItem = items.find { it.type.name == item.type.name }

        if(findItem!= null){
            findItem.stack += item.stack
        }
        else{
            if(item is Tool){
                items.add(Tool(type = item.type, stack = item.stack, 4))
            }
            else{
                items.add(Item(type = item.type, stack = item.stack))
            }
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

    fun attack() : Int{
        if(equipedItem == null)
            return 0
        return equipedItem!!.damage
    }


    fun hasItem(item: Item) : Boolean{
        for (playeritem in items){
            if(playeritem.type.name == item.type.name && playeritem.stack >= item.stack){
                return true
            }
        }
        return false
    }

    fun craft(recipe: Recipe, count: Int = 1) : Boolean{
        println("test")
        for (i in 1..count) {
            for (ingredient in recipe.ingredients) {
                val searchedItem = items.find { it.type == ingredient.type }
                if (searchedItem != null) {
                    searchedItem.stack -= ingredient.stack
                    if (searchedItem.stack == 0) {
                        items.remove(searchedItem)
                    }
                }
            }
            println("item:" + recipe.item.stack)
            addItem(recipe.item)
        }
        return true
    }

    fun equipeItem(item : Item) : Boolean{
        if(equipedItem == item) {
            println("ca jarte")
            equipedItem = null
            return false
        }
        equipedItem = item as Tool
        return true
    }


}