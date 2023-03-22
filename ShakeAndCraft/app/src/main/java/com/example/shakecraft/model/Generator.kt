package com.example.shakecraft.model
import com.example.shakecraft.R
import com.example.shakecraft.model.ItemManager.Companion.ITEMS
import kotlin.random.Random

class Generator {

    companion object {
        fun generateLootCollection(): Item {
            val possibleLoot: List<Pair<Item, Double>> = listOf(
                Pair(Item(type = ITEMS.BEECH_LOG.itemtype), 0.6),
                Pair(Item(type = ITEMS.BRONZE_ORE.itemtype), 0.25),
                Pair(Item(type = ITEMS.IRON_ORE.itemtype), 0.10),
                Pair(Item(type = ITEMS.DIAMOND.itemtype), 0.05),
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
                Pair(Boss(name = "Margit the Fell Omen", life = 150, maxlife = 150, image = R.drawable.boss, xpReward = 100, possibleLoot = listOf(
                    Pair(Item(type = ITEMS.MONSTER_BONES.itemtype), 0.7),
                    Pair(Item(type = ITEMS.MONSTER_EYE.itemtype), 0.3),
                )), 0.5),
                Pair(Boss(name = "Godrick the Grafted", life = 200, maxlife = 200, image = R.drawable.skeleton, xpReward = 130, possibleLoot = listOf(
                    Pair(Item(type = ITEMS.MONSTER_BONES.itemtype), 0.6),
                    Pair(Item(type = ITEMS.MONSTER_EYE.itemtype), 0.3),
                    Pair(Item(type = ITEMS.TREASURE_KEY.itemtype), 0.1),
                )), 0.2),
                Pair(Boss(name = "Red Wolf of Radagon", life = 250, maxlife = 250, image = R.drawable.halberdier, xpReward = 210, possibleLoot = listOf(
                    Pair(Item(type = ITEMS.MONSTER_BONES.itemtype), 0.6),
                    Pair(Item(type = ITEMS.MONSTER_EYE.itemtype), 0.3),
                    Pair(Item(type = ITEMS.TREASURE_KEY.itemtype), 0.1),
                )), 0.15),
                Pair(Boss(name = "Old Banshee", life = 300, maxlife = 300, image = R.drawable.banshee, xpReward = 300, possibleLoot = listOf(
                    Pair(Item(type = ITEMS.MONSTER_BONES.itemtype), 0.4),
                    Pair(Item(type = ITEMS.MONSTER_EYE.itemtype), 0.4),
                    Pair(Item(type = ITEMS.TREASURE_KEY.itemtype), 0.2),
                )), 0.10),
                Pair(Boss(name = "Margit the Fell Omen", life = 500, maxlife = 500, image = R.drawable.lich, xpReward = 500, possibleLoot = listOf(
                    Pair(Item(type = ITEMS.MONSTER_BONES.itemtype), 0.4),
                    Pair(Item(type = ITEMS.MONSTER_EYE.itemtype), 0.3),
                    Pair(Item(type = ITEMS.TREASURE_KEY.itemtype), 0.3),
                )), 0.05),
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

        fun generateTreasure(): Item{
            val possibleTreasure: List<Pair<Item,Double>> = listOf(
                Pair(Item(type = ITEMS.MONSTER_BONES.itemtype), 0.9),
            )
            val rand = Random.nextDouble()

            var cumulativeProb = 0.0
            for (element in possibleTreasure) {
                cumulativeProb += element.second
                if (rand < cumulativeProb) {
                    return element.first
                }
            }
            // Si aucun élément n'a été choisi, retourner le dernier élément de la liste
            return possibleTreasure.last().first
        }
    }
}