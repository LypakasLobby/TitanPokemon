package com.lypaka.titanpokemon;

import net.minecraft.util.text.Color;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class ConfigGetters {

    public static boolean ignoreNonSurvival;
    public static float titanScale;
    public static double titanSpawnChance;
    public static long titanSpawnInterval;
    public static Color titanColor;

    public static void load() throws ObjectMappingException {

        ignoreNonSurvival = TitanPokemon.configManager.getConfigNode(0, "Ignore-Non-Survival").getBoolean();
        titanScale = TitanPokemon.configManager.getConfigNode(0, "Scale").getFloat();
        titanSpawnChance = TitanPokemon.configManager.getConfigNode(0, "Spawn-Chance").getDouble();
        titanSpawnInterval = TitanPokemon.configManager.getConfigNode(0, "Spawn-Interval").getInt() * 1000L;

    }

}
