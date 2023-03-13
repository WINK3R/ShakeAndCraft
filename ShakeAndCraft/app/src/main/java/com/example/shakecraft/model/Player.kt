package com.example.shakecraft.model

class Player(val pseudo: String, var xp: Int = 0) {
    var level: Int = 1

    var items: MutableList<Item> = mutableListOf()
    var rank: String = "Beginner"


    fun changeRank(){
        if(this.level<=2){
            this.rank = "Beginner"
        }
        else if(this.level<=5){
            this.rank = "Intermediate"
        }
        else if(this.level<=8){
            this.rank = "Proficient"
        }
        else if(this.level<=11){
            this.rank = "Expert"
        }
        else if(this.level<=14){
            this.rank = "Master"
        }
        else if(this.level<=17){
            this.rank = "Professional"
        }
        else if(this.level<=20){
            this.rank = "Champion"
        }
        else if(this.level<=23){
            this.rank = "Beginner"
        }
        else if(this.level<=26){
            this.rank = "Legend"
        }
        else if(this.level<=26){
            this.rank = "Invincible"
        }
        else if(this.level<=29){
            this.rank = "Divine"
        }
    }
    fun addItem(item: Item) {
        val findItem = items.find { it.name == item.name }

        if(findItem!= null){
            println("findItem n: "+findItem.stack+" item nb:"+item.stack)
            findItem.stack += item.stack
        }
        else{items.add( Item(item.name, item.rarity, item.stack, item.image, item.xpReward))}
    }

    fun gainXp(xp: Int) {
        this.xp += xp
        if (this.xp >= this.level *100){
            this.level +=1
            this.xp = 0
            changeRank()
        }
    }

    fun removeItem(item: Item) {
        items.remove(item)
    }

    fun hasItem(item: Item) : Boolean{
        for (playeritem in items){
            if(playeritem.name == item.name && playeritem.stack >= item.stack){
                return true
            }
        }
        return false
    }

    fun craft(recipe: Recipe) : Boolean{
        println("test")
        for (ingredient in recipe.ingredients) {
            val searchedItem = items.find { it.name == ingredient.name }
            if(searchedItem != null) {
                searchedItem.stack -= ingredient.stack
                if (searchedItem.stack == 0){
                    items.remove(searchedItem)
                }
            }
        }
        println("item:"+recipe.item.stack)
        addItem(recipe.item)
        return true
    }

}