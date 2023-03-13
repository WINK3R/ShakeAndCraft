package com.example.shakecraft.model

import com.example.shakecraft.R

class RecipeManager {



    companion object {

        val recipeListObjects : List<Recipe> = listOf(
            Recipe(
                Item("Wooden Stick", image = R.drawable.wooden_stick, stack = 1),listOf(
                    Item("Wooden Plank", image = R.drawable.wooden_plank, stack = 2)
                ), "Objects"),
            Recipe(
                Item("Wooden Plank", image = R.drawable.wooden_plank, stack = 3),listOf(
                    Item("Beech Log", image = R.drawable.log2, stack = 1)
                ), "Objects"),
            Recipe(
                Item("Wooden Ball", image = R.drawable.wooden_ball, stack = 1),listOf(
                    Item("Wooden Stick", image = R.drawable.wooden_stick, stack = 2),
                    Item("Wooden Plank", image = R.drawable.wooden_plank, stack = 2)
                ), "Objects"),

        )
        val recipeListTools : List<Recipe> = listOf(

            Recipe(
                Item("Bronze Sword", image = R.drawable.bronze_sword, stack = 1),listOf(
                    Item("Wooden Stick", image = R.drawable.wooden_stick, stack = 5),
                    Item("Bronze Ingot", image = R.drawable.bronze_ore, stack = 10)

                ), "Tools"),
            Recipe(
                Item("Wizard Staff", image = R.drawable.wizard_staff, stack = 1),listOf(
                    Item("Wooden Stick", image = R.drawable.wooden_stick, stack = 10),
                    Item("Monster Eye", image = R.drawable.monster_eyes, stack = 20),

                ), "Tools"),
            Recipe(
                Item("Diamond Axe", image = R.drawable.diamond_axe, stack = 1),listOf(
                    Item("Wooden Stick", image = R.drawable.wooden_stick, stack = 5),
                    Item("Diamond", image = R.drawable.diamond, stack = 10),

                    ), "Tools"),
        )
        val recipeListBlacksmithing : List<Recipe> = listOf(

            Recipe(
                Item("Bronze Ingot", image = R.drawable.bronze_ingot, stack = 1),listOf(
                    Item("Bronze Ore", image = R.drawable.bronze_ore, stack = 5)

                ), "Blacksmithing"),
            Recipe(
                Item("Iron Ingot", image = R.drawable.iron_ingot, stack = 1),listOf(
                    Item("Iron Ore", image = R.drawable.iron_ore, stack = 5)

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
            var divisedList = mutableListOf<Int>()
            if(isCraftable(recipe,player)==false)
                return 0
            else{
                for(element in recipe.ingredients){
                    println("cc")
                    val itemSearch = player.items.find { it.name == element.name }
                    if(itemSearch!= null)
                        divisedList.add(itemSearch.stack / element.stack)
                }
                return divisedList.min()
            }
        }



    }
}