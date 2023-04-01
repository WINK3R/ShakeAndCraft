package com.example.shakecraft.model

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

        )
        val recipeListTools : List<Recipe> = listOf(

            Recipe(
                Tool(type = ITEMS.BRONZE_SWORD.itemtype, stack = 1, damage = 4),listOf(
                    Item(type = ITEMS.WOODEN_STICK.itemtype, stack = 5),
                    Item(type = ITEMS.BRONZE_INGOT.itemtype, stack = 10)

                ), "Tools"),
            Recipe(
                Tool(type = ITEMS.WIZARD_STAFF.itemtype, stack = 1, damage = 6),listOf(
                    Item(type = ITEMS.WOODEN_STICK.itemtype, stack = 10),
                    Item(type = ITEMS.MONSTER_EYE.itemtype, stack = 20),

                ), "Tools"),
            Recipe(
                Tool(type = ITEMS.DIAMOND_AXE.itemtype, stack = 1, damage = 8),listOf(
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






        fun isCraftable(recipe: Recipe, player: Player): Boolean{
            for (ingredient in recipe.ingredients) {
                if (!player.hasItem(ingredient)) {
                    return false
                }
            }
            return true
        }

        fun HowManyCraftable(recipe: Recipe, player: Player): Int{
            val divisedList = mutableListOf<Int>()
            return if (!isCraftable(recipe,player)) 0
            else{
                for(element in recipe.ingredients){
                    val itemSearch = player.items.find { it.type.name == element.type.name }
                    if(itemSearch!= null)
                        divisedList.add(itemSearch.stack / element.stack)
                }
                divisedList.min()
            }
        }



    }
}