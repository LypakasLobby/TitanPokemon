package com.lypaka.titanpokemon;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.awt.*;
import java.util.Map;

public class ConfigGetters {

    public static boolean ignoreNonSurvival;
    public static boolean lockToUUID;
    public static String message;
    public static float titanScale;
    public static double titanSpawnChance;
    public static Color titanColor;

    public static Map<String, Map<String, Integer>> playerTitanMap;

    public static void load() throws ObjectMappingException {

        ignoreNonSurvival = TitanPokemon.configManager.getConfigNode(0, "Ignore-Non-Survival").getBoolean();
        lockToUUID = TitanPokemon.configManager.getConfigNode(0, "Lock-To-UUID").getBoolean();
        message = TitanPokemon.configManager.getConfigNode(0, "Message").getString();
        titanScale = TitanPokemon.configManager.getConfigNode(0, "Scale").getFloat();
        titanSpawnChance = TitanPokemon.configManager.getConfigNode(0, "Spawn-Chance").getDouble();
        String[] array = TitanPokemon.configManager.getConfigNode(0, "Tint").getString().split(", ");
        titanColor = new Color(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));

        playerTitanMap = TitanPokemon.configManager.getConfigNode(1, "Defeated-Titans").getValue(new TypeToken<Map<String, Map<String, Integer>>>() {});

    }

}
