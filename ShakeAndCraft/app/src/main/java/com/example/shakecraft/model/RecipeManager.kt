package com.example.shakecraft.model
import com.example.shakecraft.model.ItemManager.Companion.ITEMS

class RecipeManager {



    companion object {

        val recipeListObjects : List<Recipe> = listOf(
            Recipe(
                Item(type = ITEMS.WOODEN_STICK.itemtype, stack = 1),listOf(
                    Item(type = ITEMS.WOODEN_PLANK.itemtype, stack = 2)
                ), "Objects"),
            Recipe(
                Item(type = ITEMS.WOODEN_PLANK.itemtype, stack = 3),listOf(
                    Item(type = ITEMS.BEECH_LOG.itemtype, stack = 1)
                ), "Objects"),
            Recipe(
                Item(type = ITEMS.WOODEN_BALL.itemtype, stack = 1),listOf(
                    Item(type = ITEMS.WOODEN_STICK.itemtype, stack = 2),
                    Item(type = ITEMS.WOODEN_PLANK.itemtype, stack = 2)
                ), "Objects"),
            Recipe(
                Item(type = ITEMS.OPEN_TREASURE.itemtype, stack = 1),listOf(
                    Item(type = ITEMS.TREASURE.itemtype, stack = 1),
                    Item(type = ITEMS.TREASURE_KEY.itemtype, stack = 1)
                ), "Objects"),
        )
        val recipeListTools : List<Recipe> = listOf(

            Recipe(
                Item(type = ITEMS.BRONZE_SWORD.itemtype, stack = 1),listOf(
                    Item(type = ITEMS.WOODEN_STICK.itemtype, stack = 5),
                    Item(type = ITEMS.BRONZE_INGOT.itemtype, stack = 10)

                ), "Tools"),
            Recipe(
                Item(type = ITEMS.WIZARD_STAFF.itemtype, stack = 1),listOf(
                    Item(type = ITEMS.WOODEN_STICK.itemtype, stack = 10),
                    Item(type = ITEMS.MONSTER_EYE.itemtype, stack = 20),

                ), "Tools"),
            Recipe(
                Item(type = ITEMS.DIAMOND_AXE.itemtype, stack = 1),listOf(
                    Item(type = ITEMS.WOODEN_STICK.itemtype, stack = 5),
                    Item(type = ITEMS.DIAMOND.itemtype, stack = 10),

                    ), "Tools"),
        )
        val recipeListBlacksmithing : List<Recipe> = listOf(

            Recipe(
                Item(type = ITEMS.BRONZE_INGOT.itemtype, stack = 1),listOf(
                    Item(type = ITEMS.BRONZE_ORE.itemtype, stack = 5)

                ), "Blacksmithing"),
            Recipe(
                Item(type = ITEMS.IRON_INGOT.itemtype, stack = 1),listOf(
                    Item(type = ITEMS.IRON_ORE.itemtype, stack = 5)

                ), "Blacksmithing"),

        )






        fun isCraftable(recipe: Recipe, inventory: Inventory): Boolean{
            for (ingredient in recipe.ingredients) {
                if (!inventory.hasItem(ingredient)) {
                    return false
                }
            }
            return true
        }

        fun HowManyCraftable(recipe: Recipe, inventory: Inventory): Int{
            val divisedList = mutableListOf<Int>()
            if(isCraftable(recipe,inventory)==false)
                return 0
            else{
                for(element in recipe.ingredients){
                    println("cc")
                    val itemSearch = inventory.items.find { it.type.name == element.type.name }
                    if(itemSearch!= null)
                        divisedList.add(itemSearch.stack / element.stack)
                }
                return divisedList.min()
            }
        }



    }
}