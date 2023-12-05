package com.lypaka.titanpokemon.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.titanpokemon.ConfigGetters;
import com.lypaka.titanpokemon.Titans.TitanHandler;
import com.pixelmonmod.pixelmon.api.events.battles.BattleStartedEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.battles.controller.BattleController;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.WildPixelmonParticipant;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BattleStartListener {

    @SubscribeEvent
    public void onBattleStart (BattleStartedEvent.Pre event) {

        if (!ConfigGetters.lockToUUID) return;
        WildPixelmonParticipant wpp = null;
        PlayerParticipant pp = null;
        BattleController bc = event.getBattleController();

        if (bc.participants.get(0) instanceof WildPixelmonParticipant && bc.participants.get(1) instanceof PlayerParticipant) {

            wpp = (WildPixelmonParticipant) bc.participants.get(0);
            pp = (PlayerParticipant) bc.participants.get(1);

        } else if (bc.participants.get(0) instanceof PlayerParticipant && bc.participants.get(1) instanceof WildPixelmonParticipant) {

            wpp = (WildPixelmonParticipant) bc.participants.get(1);
            pp = (PlayerParticipant) bc.participants.get(0);

        }

        if (wpp == null || pp == null) return;

        ServerPlayerEntity player = pp.player;
        Pokemon pokemon = wpp.controlledPokemon.get(0).pokemon;

        if (TitanHandler.isTitanPokemon(pokemon)) {

            String spawnedForUUID = TitanHandler.getPlayerSpawnedFor(pokemon);
            if (!player.getUniqueID().toString().equalsIgnoreCase(spawnedForUUID)) {

                event.setCanceled(true);
                player.sendMessage(FancyText.getFormattedText("&cThis Titan was not spawned for you!"), player.getUniqueID());

            }

        }

    }

}
