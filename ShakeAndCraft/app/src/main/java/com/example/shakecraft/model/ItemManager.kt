package com.example.shakecraft.model
import com.example.shakecraft.R

class ItemManager {
    companion object {
        enum class ITEMS(val itemtype: ItemType){

            // Craftable items and resources
            BEECH_LOG(ItemType(name = "Beech Log", image = R.drawable.log2, rarity = 1, xpReward = 10)),
            WOODEN_STICK(ItemType(name = "Wooden Stick", image = R.drawable.wooden_stick, rarity = 1, xpReward = 0)),
            WOODEN_PLANK(ItemType(name = "Wooden Plank", image = R.drawable.wooden_plank, rarity = 1, xpReward = 0)),
            WOODEN_BALL(ItemType(name = "Wooden Ball", image = R.drawable.wooden_ball, rarity = 1, xpReward = 0)),
            WIZARD_STAFF(ItemType(name = "Wizard Staff", image = R.drawable.wizard_staff, rarity = 3, xpReward = 0)),

            DIAMOND(ItemType(name = "Diamond", image = R.drawable.diamond, rarity = 3, xpReward = 30)),
            DIAMOND_AXE(ItemType(name = "Diamond Axe", image = R.drawable.diamond_axe, rarity = 3, xpReward = 0)),

            BRONZE_ORE(ItemType(name = "Bronze Ore", image = R.drawable.bronze_ore, rarity = 2, xpReward = 20)),
            BRONZE_INGOT(ItemType(name = "Bronze Ingot", image = R.drawable.bronze_ingot, rarity = 1, xpReward = 0)),
            BRONZE_SWORD(ItemType(name = "Bronze Sword", image = R.drawable.bronze_sword, rarity = 2, xpReward = 0)),

            IRON_ORE(ItemType(name = "Iron Ore", image = R.drawable.iron_ore, rarity = 2, xpReward = 25)),
            IRON_INGOT(ItemType(name = "Iron Ingot", image = R.drawable.iron_ingot, rarity = 1, xpReward = 0)),
            OPEN_TREASURE(ItemType(name = "Open Treasure", image = R.drawable.open_treasure, rarity = 3, xpReward = 50)),


            // Lootable items
            MONSTER_BONES(ItemType(name = "Monster Bones", image = R.drawable.monster_bones, rarity = 1, xpReward = 10)),
            MONSTER_EYE(ItemType(name = "Monster Eye", image = R.drawable.monster_eyes, rarity = 2, xpReward = 20)),
            TREASURE_KEY(ItemType(name = "Treasure Key", image = R.drawable.treasure_key, rarity = 2, xpReward = 20)),
            TREASURE(ItemType(name = "Treasure", image = R.drawable.treasure, rarity = 3, xpReward = 20)),

        }
    }
}