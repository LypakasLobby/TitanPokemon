package com.lypaka.titanpokemon.Titans;

import com.google.common.reflect.TypeToken;
import com.lypaka.titanpokemon.TitanPokemon;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TitanHandler {

    public static List<Titan> titans = new ArrayList<>();

    public static void loadTitans() throws ObjectMappingException {

        titans = new ArrayList<>();
        Map<String, Map<String, String>> titanMap = TitanPokemon.configManager.getConfigNode(0, "Titans").getValue(new TypeToken<Map<String, Map<String, String>>>() {});
        for (Map.Entry<String, Map<String, String>> entry : titanMap.entrySet()) {

            String titanID = entry.getKey();
            String ability = entry.getValue().get("Ability");
            List<String> species = TitanPokemon.configManager.getConfigNode(0, "Titans", titanID, "Species").getList(TypeToken.of(String.class));

            if (titanID.equalsIgnoreCase("Stony-Cliff-Titan")) {

                StonyCliffTitan stonyCliffTitan = new StonyCliffTitan(species, ability);
                titans.add(stonyCliffTitan);

            } else if (titanID.equalsIgnoreCase("Open-Sky-Titan")) {

                OpenSkyTitan openSkyTitan = new OpenSkyTitan(species, ability);
                titans.add(openSkyTitan);

            } else if (titanID.equalsIgnoreCase("Lurking-Steel-Titan")) {

                LurkingSteelTitan lurkingSteelTitan = new LurkingSteelTitan(species, ability);
                titans.add(lurkingSteelTitan);

            } else if (titanID.equalsIgnoreCase("Quaking-Earth-Titan")) {

                QuakingEarthTitan quakingEarthTitan = new QuakingEarthTitan(species, ability);
                titans.add(quakingEarthTitan);

            } else if (titanID.equalsIgnoreCase("False-Dragon-Titan")) {

                FalseDragonTitan falseDragonTitan = new FalseDragonTitan(species, ability);
                titans.add(falseDragonTitan);

            }

        }

    }

}
