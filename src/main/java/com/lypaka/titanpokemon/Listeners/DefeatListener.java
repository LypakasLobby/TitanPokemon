package com.lypaka.titanpokemon.Listeners;

import com.lypaka.titanpokemon.API.TitanDefeatEvent;
import com.lypaka.titanpokemon.Titans.Titan;
import com.lypaka.titanpokemon.Titans.TitanHandler;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DefeatListener {

    @SubscribeEvent
    public void onWildBeat (BeatWildPixelmonEvent event) {

        ServerPlayerEntity player = event.player;
        Pokemon pokemon = event.wpp.controlledPokemon.get(0).pokemon;

        System.out.println("hi");
        if (TitanHandler.isTitanPokemon(pokemon)) {

            System.out.println("hi 2");
            Titan titan = TitanHandler.getFromPokemon(pokemon);
            // Safety check
            if (titan == null) return;

            System.out.println("hi 3");
            int titanLevel = TitanHandler.getTitanLevel(pokemon);
            TitanDefeatEvent defeatEvent = new TitanDefeatEvent(player, titan, titanLevel);
            MinecraftForge.EVENT_BUS.post(defeatEvent);
            TitanHandler.increaseTitanLevel(player, titan);

        }

    }

}
