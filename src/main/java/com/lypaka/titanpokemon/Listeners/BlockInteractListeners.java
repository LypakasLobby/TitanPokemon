package com.lypaka.titanpokemon.Listeners;

import com.lypaka.lypakautils.WorldStuff.WorldMap;
import com.lypaka.titanpokemon.API.TitanAbilityEvent;
import com.lypaka.titanpokemon.ConfigGetters;
import net.minecraft.command.impl.EffectCommand;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

import java.util.Map;

public class BlockInteractListeners {

    @SubscribeEvent
    public void onBlockHit (PlayerInteractEvent.LeftClickBlock event) {

        if (event.getSide() == LogicalSide.CLIENT) return;
        if (event.getHand() != Hand.MAIN_HAND) return;

        PlayerEntity player = event.getPlayer();
        if (player.inventory.mainInventory.toString().contains(ConfigGetters.getDisableItemID("High Jumping"))) return;
        int playerY = player.getPosition().getY() - 1; // -1 to get the Y level of the block that the player is actually standing on
        int eventY = event.getPos().getY();
        if (eventY > playerY) return; // stops the code from firing if the player is shift left clicking a block above their y level (so it only triggers at feet)

        if (player.isSneaking()) {

            if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

                Map<String, Integer> map = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());
                if (map.containsKey("LurkingSteelTitan")) {

                    String worldName = WorldMap.getWorldName(player.world);
                    if (ConfigGetters.isWorldBlacklisted("High Jumping", worldName)) return;
                    TitanAbilityEvent.Activate activateEvent = new TitanAbilityEvent.Activate((ServerPlayerEntity) player, "High Jumping", worldName, true);
                    MinecraftForge.EVENT_BUS.post(activateEvent);
                    if (!activateEvent.isCanceled()) {

                        if (activateEvent.doesBypass()) {

                            int defeatAmount = map.get("LurkingSteelTitan");
                            int duration = Math.min(600, ((defeatAmount * 10) * 20));
                            int amplifier = Math.min(3, defeatAmount);
                            EffectInstance effectInstance = new EffectInstance(Effects.JUMP_BOOST, duration, Math.max(1, amplifier-1));
                            if (!player.getActivePotionEffects().contains(effectInstance)) {

                                player.addPotionEffect(effectInstance);

                            }

                        }

                    }

                }

            }

        }

    }

    @SubscribeEvent
    public void onBlockClick (PlayerInteractEvent.RightClickBlock event) {

        if (event.getSide() == LogicalSide.CLIENT) return;
        if (event.getHand() != Hand.MAIN_HAND) return;

        PlayerEntity player = event.getPlayer();
        if (player.inventory.mainInventory.toString().contains(ConfigGetters.getDisableItemID("Climbing"))) return;
        int playerY = player.getPosition().getY() - 1; // -1 to get the Y level of the block that the player is actually standing on
        int eventY = event.getPos().getY();
        if (eventY <= playerY) return; // stops the code from firing if the player is shift right clicking a block at or below their feet

        if (player.isSneaking()) {

            if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

                Map<String, Integer> map = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());
                if (map.containsKey("FalseDragonTitan")) {

                    String worldName = WorldMap.getWorldName(player.world);
                    if (ConfigGetters.isWorldBlacklisted("Climbing", worldName)) return;
                    TitanAbilityEvent.Activate activateEvent = new TitanAbilityEvent.Activate((ServerPlayerEntity) player, "Climbing", worldName, true);
                    MinecraftForge.EVENT_BUS.post(activateEvent);
                    if (!activateEvent.isCanceled()) {

                        if (activateEvent.doesBypass()) {

                            int defeatAmount = map.get("FalseDragonTitan");
                            int duration = Math.min(600, ((defeatAmount * 2) * 20));
                            int amplifier = Math.min(15, (defeatAmount * 5));
                            EffectInstance effectInstance = new EffectInstance(Effects.LEVITATION, duration, Math.max(1, amplifier-1));

                            String id = player.world.getBlockState(event.getPos()).getBlock().getRegistryName().toString();
                            if (!id.equalsIgnoreCase("minecraft:air")) {

                                if (!player.getActivePotionEffects().contains(effectInstance)) {

                                    player.addPotionEffect(effectInstance);

                                }

                            }

                        }

                    }

                }

            }

        }

    }

}
