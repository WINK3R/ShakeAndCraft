package com.example.shakecraft.model

import com.example.shakecraft.R
import kotlin.random.Random

class GeneratorLoot {

    companion object {
        fun generateLootCollection(): Item {
            val possibleLoot: List<Pair<Item, Double>> = listOf(
                Pair(Item(name = "Beech Log", rarity = 0, stack = 1, R.drawable.log2), 0.6),
                Pair(Item(name = "Bronze Ore", rarity = 0, stack = 1, R.drawable.bronze_ore), 0.25),
                Pair(Item(name = "Iron Ore", rarity = 0, stack = 1, R.drawable.iron_ore), 0.10),
                Pair(Item(name = "Diamond", rarity = 0, stack = 1, R.drawable.diamond), 0.05),
            )
            val rand = Random.nextDouble()

            var cumulativeProb = 0.0
            for (element in possibleLoot) {
                cumulativeProb += element.second
                if (rand < cumulativeProb) {
                    return element.first
                }
            }
            // Si aucun élément n'a été choisi, retourner le dernier élément de la liste
            return possibleLoot.last().first
        }
    }
}