package com.lypaka.titanpokemon.Titans;

import com.google.common.reflect.TypeToken;
import com.lypaka.titanpokemon.ConfigGetters;
import com.lypaka.titanpokemon.TitanPokemon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitanHandler {

    public static List<Titan> titans = new ArrayList<>();

    public static boolean isPossibleTitan (Pokemon pokemon) {

        String species = pokemon.getSpecies().getName();
        boolean isTitan = false;
        for (Titan titan : titans) {

            for (String s : titan.getSpecies()) {

                if (s.replace(" ", "").equalsIgnoreCase(species.replace(" ", ""))) {

                    isTitan = true;
                    break;

                }

            }

        }

        return isTitan;

    }

    public static void setTitanPokemon (PixelmonEntity pixelmon, int level) {

        pixelmon.setColor(ConfigGetters.titanColor);
        pixelmon.setPixelmonScale(ConfigGetters.titanScale);
        Pokemon pokemon = pixelmon.getPokemon();
        pokemon.getPersistentData().putBoolean("TitanPokemon", true);
        pokemon.getPersistentData().putInt("TitanLevel", level);

    }

    public static void lockTitan (Pokemon pokemon, ServerPlayerEntity player) {

        pokemon.getPersistentData().putString("SpawnedPlayer", player.getUniqueID().toString());

    }

    public static String getPlayerSpawnedFor (Pokemon pokemon) {

        return pokemon.getPersistentData().getString("SpawnedPlayer");

    }

    public static boolean isTitanPokemon (Pokemon pokemon) {

        return pokemon.getPersistentData().getBoolean("TitanPokemon");

    }

    public static int getTitanLevel (Pokemon pokemon) {

        int level = 0;
        if (pokemon.getPersistentData().contains("TitanLevel")) {

            level = pokemon.getPersistentData().getInt("TitanLevel");

        }

        return level;

    }

    public static int getTitanDefeatCount (ServerPlayerEntity player, String titanID) {

        int level = 1;
        if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

            Map<String, Integer> titans = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());
            if (titans.containsKey(titanID)) level = titans.get(titanID);

        }

        return level;

    }

    public static Titan getFromPokemon (Pokemon pokemon) {

        if (!isPossibleTitan(pokemon)) return null;
        Titan titan = null;
        for (Titan t : titans) {

            for (String s : t.getSpecies()) {

                if (s.replace(" ", "").equalsIgnoreCase(pokemon.getSpecies().getName().replace(" ", ""))) {

                    titan = t;
                    break;

                }

            }

        }

        return titan;

    }

    public static Titan getFromName (String name) {

        Titan titan = null;
        for (Titan t : titans) {

            if (t.getID().equalsIgnoreCase(name)) {

                titan = t;
                break;

            }

        }

        return titan;

    }

    public static String getPrettyName (Titan titan) {

        String name = "";
        if (titan instanceof StonyCliffTitan) {

            name = "Stony Cliff Titan";

        } else if (titan instanceof QuakingEarthTitan) {

            name = "Quaking Earth Titan";

        } else if (titan instanceof OpenSkyTitan) {

            name = "Open Sky Titan";

        } else if (titan instanceof LurkingSteelTitan) {

            name = "Lurking Steel Titan";

        } else if (titan instanceof FalseDragonTitan) {

            name = "False Dragon Titan";

        }

        return name;

    }

    public static void increaseTitanLevel (ServerPlayerEntity player, Titan titan) {

        Map<String, Integer> map = new HashMap<>();
        if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

            map = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());

        }
        int value = 0;
        if (map.containsKey(titan.getID())) {

            value = map.get(titan.getID());

        }

        int updated = value + 1;
        map.put(titan.getID(), updated);
        ConfigGetters.playerTitanMap.put(player.getUniqueID().toString(), map);

    }

    public static void loadTitans() throws ObjectMappingException {

        titans = new ArrayList<>();
        Map<String, Map<String, String>> titanMap = TitanPokemon.configManager.getConfigNode(0, "Titans").getValue(new TypeToken<Map<String, Map<String, String>>>() {});
        for (Map.Entry<String, Map<String, String>> entry : titanMap.entrySet()) {

            String titanID = entry.getKey();
            String ability = entry.getValue().get("Ability");
            List<String> species = TitanPokemon.configManager.getConfigNode(0, "Titans", titanID, "Species").getList(TypeToken.of(String.class));

            if (titanID.equalsIgnoreCase("Stony-Cliff-Titan")) {

                StonyCliffTitan stonyCliffTitan = new StonyCliffTitan("StonyCliffTitan", species, ability);
                titans.add(stonyCliffTitan);

            } else if (titanID.equalsIgnoreCase("Open-Sky-Titan")) {

                OpenSkyTitan openSkyTitan = new OpenSkyTitan("OpenSkyTitan", species, ability);
                titans.add(openSkyTitan);

            } else if (titanID.equalsIgnoreCase("Lurking-Steel-Titan")) {

                LurkingSteelTitan lurkingSteelTitan = new LurkingSteelTitan("LurkingSteelTitan", species, ability);
                titans.add(lurkingSteelTitan);

            } else if (titanID.equalsIgnoreCase("Quaking-Earth-Titan")) {

                QuakingEarthTitan quakingEarthTitan = new QuakingEarthTitan("QuakingEarthTitan", species, ability);
                titans.add(quakingEarthTitan);

            } else if (titanID.equalsIgnoreCase("False-Dragon-Titan")) {

                FalseDragonTitan falseDragonTitan = new FalseDragonTitan("FalseDragonTitan", species, ability);
                titans.add(falseDragonTitan);

            }

        }

    }

}
