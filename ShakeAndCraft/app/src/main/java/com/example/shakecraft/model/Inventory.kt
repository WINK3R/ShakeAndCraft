package com.example.shakecraft.model

class Inventory {
    var items: MutableList<Item> = mutableListOf()

    fun addItem(item: Item) {
        val findItem = items.find { it.type.name == item.type.name }

        if(findItem!= null){
            println("findItem n: "+findItem.stack+" item nb:"+item.stack)
            findItem.stack += item.stack
        }
        else{items.add(Item(type = item.type, stack = item.stack))}
    }

    fun hasItem(item: Item) : Boolean{
        for (inventoryitem in items){
            if(inventoryitem.type.name == item.type.name && inventoryitem.stack >= item.stack){
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