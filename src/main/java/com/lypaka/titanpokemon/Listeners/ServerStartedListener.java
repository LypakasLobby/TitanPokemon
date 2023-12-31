package com.lypaka.titanpokemon.Listeners;

import com.lypaka.titanpokemon.TitanPokemon;
import com.lypaka.titanpokemon.Titans.TitanHandler;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Mod.EventBusSubscriber(modid = TitanPokemon.MOD_ID)
public class ServerStartedListener {

    @SubscribeEvent
    public static void onServerStarted (FMLServerStartedEvent event) throws ObjectMappingException {

        TitanHandler.loadTitans();

        MinecraftForge.EVENT_BUS.register(new BlockInteractListeners());
        MinecraftForge.EVENT_BUS.register(new DamageListener());
        MinecraftForge.EVENT_BUS.register(new ServerTickListener());

        Pixelmon.EVENT_BUS.register(new BattleStartListener());
        Pixelmon.EVENT_BUS.register(new CatchListener());
        Pixelmon.EVENT_BUS.register(new DefeatListener());
        Pixelmon.EVENT_BUS.register(new PixelmonSpawnListener());

    }

}
