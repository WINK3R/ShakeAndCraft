package com.example.shakecraft.model

class Player(val pseudo: String, var xp: Int = 0) {
    var level: Int = 1
        private set

    val items: MutableList<Item> = mutableListOf()

    fun addItem(item: Item) {
        var findItem = items.find { it.name == item.name }
        if(findItem!= null){
            findItem.stack += 1;
        }
        else{items.add(item)}
    }

    fun removeItem(item: Item) {
        items.remove(item)
    }

    fun increaseXp(amount: Int) {
        xp += amount
        if (xp >= 100 * level) {
            levelUp()
        }
    }

    private fun levelUp() {
        level++
        println("$pseudo a atteint le niveau $level !")
    }
}