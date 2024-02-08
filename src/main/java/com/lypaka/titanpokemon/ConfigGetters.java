package com.lypaka.titanpokemon;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigGetters {

    public static Map<String, String> disableItemIDs;
    public static boolean ignoreNonSurvival;
    public static boolean lockToUUID;
    public static String message;
    public static float titanScale;
    public static double titanSpawnChance;
    public static Color titanColor;

    public static Map<String, Map<String, Integer>> playerTitanMap;

    public static Map<String, List<String>> abilityWorldBlacklist;

    public static void load() throws ObjectMappingException {

        //disableItemID = TitanPokemon.configManager.getConfigNode(0, "Disable-Item").getString();
        disableItemIDs = new HashMap<>();
        if (TitanPokemon.configManager.getConfigNode(0, "Disable-Items").isVirtual()) {

            disableItemIDs.put("Dashing", "anvil");
            disableItemIDs.put("Swimming", "anvil");
            disableItemIDs.put("High Jumping", "anvil");
            disableItemIDs.put("Gliding", "anvil");
            disableItemIDs.put("Climing", "anvil");
            TitanPokemon.configManager.getConfigNode(0, "Disable-Item").setValue(null);
            TitanPokemon.configManager.getConfigNode(0, "Disable-Items").setValue(disableItemIDs);
            TitanPokemon.configManager.save();

        } else {

            disableItemIDs = TitanPokemon.configManager.getConfigNode(0, "Disable-Items").getValue(new TypeToken<Map<String, String>>() {});

        }
        ignoreNonSurvival = TitanPokemon.configManager.getConfigNode(0, "Ignore-Non-Survival").getBoolean();
        lockToUUID = TitanPokemon.configManager.getConfigNode(0, "Lock-To-UUID").getBoolean();
        message = TitanPokemon.configManager.getConfigNode(0, "Message").getString();
        titanScale = TitanPokemon.configManager.getConfigNode(0, "Scale").getFloat();
        titanSpawnChance = TitanPokemon.configManager.getConfigNode(0, "Spawn-Chance").getDouble();
        String[] array = TitanPokemon.configManager.getConfigNode(0, "Tint").getString().split(", ");
        titanColor = new Color(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));

        playerTitanMap = TitanPokemon.configManager.getConfigNode(1, "Defeated-Titans").getValue(new TypeToken<Map<String, Map<String, Integer>>>() {});

        abilityWorldBlacklist = TitanPokemon.configManager.getConfigNode(2, "Ability-Blacklists").getValue(new TypeToken<Map<String, List<String>>>() {});

    }

    public static String getDisableItemID (String ability) {

        return disableItemIDs.get(ability);

    }

    public static boolean isWorldBlacklisted (String ability, String worldName) {

        return abilityWorldBlacklist.get(ability).contains(worldName);

    }

}
