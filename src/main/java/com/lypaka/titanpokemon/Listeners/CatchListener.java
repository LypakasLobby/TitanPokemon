package com.lypaka.titanpokemon.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.titanpokemon.Titans.Titan;
import com.lypaka.titanpokemon.Titans.TitanHandler;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CatchListener {

    @SubscribeEvent
    public void onCaptureAttempt (CaptureEvent.StartCapture event) {

        Pokemon pokemon = event.getPokemon().getPokemon();
        ServerPlayerEntity player = event.getPlayer();

        Titan titan = TitanHandler.getFromPokemon(pokemon);
        if (titan == null) return;

        if (TitanHandler.isTitanPokemon(pokemon)) {

            event.setCanceled(true);
            player.sendMessage(FancyText.getFormattedText("&cTitan Pokemon cannot be captured!"), player.getUniqueID());

        }

    }

}
