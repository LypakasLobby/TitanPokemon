package com.lypaka.titanpokemon.Listeners;

import com.lypaka.titanpokemon.ConfigGetters;
import com.lypaka.titanpokemon.TitanPokemon;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

@Mod.EventBusSubscriber(modid = TitanPokemon.MOD_ID)
public class ServerStoppingListener {

    @SubscribeEvent
    public static void onServerStopping (FMLServerStoppingEvent event) {

        TitanPokemon.configManager.getConfigNode(1, "Defeated-Titans").setValue(ConfigGetters.playerTitanMap);
        TitanPokemon.configManager.save();

    }

}
