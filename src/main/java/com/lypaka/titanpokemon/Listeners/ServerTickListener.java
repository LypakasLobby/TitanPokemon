package com.lypaka.titanpokemon.Listeners;

import com.lypaka.lypakautils.Listeners.JoinListener;
import com.lypaka.titanpokemon.ConfigGetters;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.UUID;

public class ServerTickListener {

    @SubscribeEvent
    public void onServerTick (TickEvent.ServerTickEvent event) {

        if (event.phase == TickEvent.Phase.START) {

            for (Map.Entry<UUID, ServerPlayerEntity> entry : JoinListener.playerMap.entrySet()) {

                ServerPlayerEntity player = entry.getValue();
                if (player.isInWater()) {

                    if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

                        Map<String, Integer> map = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());
                        if (map.containsKey("OpenSkyTitan")) {

                            if (player.inventory.mainInventory.toString().contains(ConfigGetters.disableItemID)) return;
                            int defeatAmount = map.get("OpenSkyTitan");
                            int amplifier = Math.min(15, (defeatAmount * 5));
                            EffectInstance dolphinsGrace = new EffectInstance(Effects.DOLPHINS_GRACE, 30, amplifier);
                            if (!player.getActivePotionEffects().contains(dolphinsGrace)) {

                                player.addPotionEffect(dolphinsGrace);

                            }

                        }

                    }

                } else if (player.isOnGround()) {

                    if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

                        Map<String, Integer> map = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());
                        if (map.containsKey("StonyCliffTitan")) {

                            if (player.inventory.mainInventory.toString().contains(ConfigGetters.disableItemID)) return;
                            int defeatAmount = map.get("StonyCliffTitan");
                            int amplifier = Math.min(3, defeatAmount);
                            EffectInstance speed = new EffectInstance(Effects.SPEED, 30, amplifier);
                            if (!player.getActivePotionEffects().contains(speed)) {

                                player.addPotionEffect(speed);

                            }

                        }

                    }

                } else if (!player.isOnGround() && !player.isElytraFlying() && !player.isInWater() && !player.isPotionActive(Effects.LEVITATION)) {

                    if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

                        Map<String, Integer> map = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());
                        if (map.containsKey("QuakingEarthTitan")) {

                            if (player.inventory.mainInventory.toString().contains(ConfigGetters.disableItemID)) return;
                            int defeatAmount = map.get("QuakingEarthTitan");
                            int amplifier = Math.min(15, (defeatAmount * 5));
                            EffectInstance falling = new EffectInstance(Effects.SLOW_FALLING, 30, amplifier);
                            if (!player.getActivePotionEffects().contains(falling)) {

                                player.addPotionEffect(falling);

                            }

                        }

                    }

                }

            }

        }

    }

}
