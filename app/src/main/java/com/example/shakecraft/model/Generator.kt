package com.example.shakecraft.model

import com.example.shakecraft.R
import kotlin.random.Random

class Generator {

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

        fun generateLootBoss( possibleLoot : List<Pair<Item, Double>>): Item {
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


        fun generateBoss(): Boss {
            val possibleBoss: List<Pair<Boss, Double>> = listOf(
                Pair(Boss(name = "Margit the Fell Omen", life = 150, maxlife = 150, image = R.drawable.boss), 0.5),
                Pair(Boss(name = "Godrick the Grafted", life = 200, maxlife = 200, image = R.drawable.skeleton), 0.2),
                Pair(Boss(name = "Red Wolf of Radagon", life = 250, maxlife = 250, image = R.drawable.halberdier), 0.15),
                Pair(Boss(name = "Old Banshee", life = 300, maxlife = 300, image = R.drawable.banshee), 0.10),
                Pair(Boss(name = "Margit the Fell Omen", life = 500, maxlife = 500, image = R.drawable.lich), 0.05),
            )
            val rand = Random.nextDouble()

            var cumulativeProb = 0.0
            for (element in possibleBoss) {
                cumulativeProb += element.second
                if (rand < cumulativeProb) {
                    return element.first
                }
            }
            // Si aucun élément n'a été choisi, retourner le dernier élément de la liste
            return possibleBoss.last().first
        }
    }
}