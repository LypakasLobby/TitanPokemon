package com.lypaka.titanpokemon.Listeners;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.titanpokemon.API.TitanSpawnEvent;
import com.lypaka.titanpokemon.ConfigGetters;
import com.lypaka.titanpokemon.Titans.Titan;
import com.lypaka.titanpokemon.Titans.TitanHandler;
import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.pixelmonmod.pixelmon.spawning.PlayerTrackingSpawner;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PixelmonSpawnListener {

    @SubscribeEvent
    public void onSpawn (SpawnEvent event) {

        if (event.action.getOrCreateEntity() instanceof PixelmonEntity) {

            if (event.spawner instanceof PlayerTrackingSpawner) {

                ServerPlayerEntity player = ((PlayerTrackingSpawner) event.spawner).getTrackedPlayer();
                if (player == null) return;
                if (ConfigGetters.ignoreNonSurvival) {

                    if (player.isSpectator() || player.isCreative()) return;

                }
                PixelmonEntity pixelmon = (PixelmonEntity) event.action.getOrCreateEntity();
                Pokemon pokemon = pixelmon.getPokemon();

                if (TitanHandler.isPossibleTitan(pokemon)) {

                    if (RandomHelper.getRandomChance(ConfigGetters.titanSpawnChance)) {

                        Titan titan = TitanHandler.getFromPokemon(pokemon);
                        if (titan == null) return;
                        int titanLevel = TitanHandler.getTitanDefeatCount(player, titan.getID());
                        TitanSpawnEvent titanSpawnEvent = new TitanSpawnEvent(player, pokemon, titan, titanLevel);
                        MinecraftForge.EVENT_BUS.post(titanSpawnEvent);
                        if (!titanSpawnEvent.isCanceled()) {

                            int playersHighestLevel = StorageProxy.getParty(player).getHighestLevel();
                            int modifier = 15;
                            titanLevel = titanSpawnEvent.getTitanLevel();
                            if (titanLevel == 2) modifier = 30;
                            if (titanLevel >= 3) modifier = 50;
                            int mod = modifier + playersHighestLevel;
                            pokemon.setLevel(mod);
                            TitanHandler.setTitanPokemon(pixelmon, titanLevel);
                            if (ConfigGetters.lockToUUID) {

                                TitanHandler.lockTitan(pokemon, player);

                            }
                            player.sendMessage(FancyText.getFormattedText(ConfigGetters.message
                                    .replace("%titanID%", TitanHandler.getPrettyName(titan))
                                    .replace("%pokemonName%", pokemon.getSpecies().getName())
                            ), player.getUniqueID());

                        }

                    }

                }

            }

        }

    }

}
