package com.lypaka.titanpokemon.Commands;

import com.lypaka.titanpokemon.TitanPokemon;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = TitanPokemon.MOD_ID)
public class TitanPokemonCommand {

    public static final List<String> ALIASES = Arrays.asList("titanpokemon", "tpokemon", "tpoke", "titans");

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new ReloadCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());

    }

}
