package com.lypaka.titanpokemon.Listeners;

import com.lypaka.titanpokemon.ConfigGetters;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
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
        if (player.inventory.mainInventory.toString().contains(ConfigGetters.disableItemID)) return;
        int playerY = player.getPosition().getY() - 1; // -1 to get the Y level of the block that the player is actually standing on
        int eventY = event.getPos().getY();
        if (eventY > playerY) return; // stops the code from firing if the player is shift left clicking a block above their y level (so it only triggers at feet)

        if (player.isSneaking()) {

            if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

                Map<String, Integer> map = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());
                if (map.containsKey("LurkingSteelTitan")) {

                    int defeatAmount = map.get("LurkingSteelTitan");
                    int duration = Math.min(30, (defeatAmount * 10));
                    System.out.println("duration on jump bosot == " + duration);
                    int amplifier = Math.min(30, (defeatAmount * 10));
                    System.out.println("amplifier on jump boost == " + amplifier);
                    EffectInstance effectInstance = new EffectInstance(Effects.JUMP_BOOST, duration, amplifier);
                    if (!player.getActivePotionEffects().contains(effectInstance)) {

                        player.addPotionEffect(effectInstance);

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
        if (player.inventory.mainInventory.toString().contains(ConfigGetters.disableItemID)) return;
        int playerY = player.getPosition().getY() - 1; // -1 to get the Y level of the block that the player is actually standing on
        int eventY = event.getPos().getY();
        if (eventY <= playerY) return; // stops the code from firing if the player is shift right clicking a block at or below their feet

        if (player.isSneaking()) {

            if (ConfigGetters.playerTitanMap.containsKey(player.getUniqueID().toString())) {

                Map<String, Integer> map = ConfigGetters.playerTitanMap.get(player.getUniqueID().toString());
                if (map.containsKey("FalseDragonTitan")) {

                    int defeatAmount = map.get("FalseDragonTitan");
                    int duration = Math.min(30, (defeatAmount * 10));
                    int amplifier = Math.min(15, (defeatAmount * 5));
                    EffectInstance effectInstance = new EffectInstance(Effects.LEVITATION, duration, amplifier);

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
