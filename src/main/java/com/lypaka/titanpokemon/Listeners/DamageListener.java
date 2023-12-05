package com.lypaka.titanpokemon.Listeners;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DamageListener {

    @SubscribeEvent
    public void onDamage (LivingDamageEvent event) {

        if (event.getEntityLiving() instanceof ServerPlayerEntity) {

            if (event.getSource() == DamageSource.FALL) {

                ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();
                if (player.isPotionActive(Effects.JUMP_BOOST)) {

                    event.setCanceled(true);

                }

            }

        }

    }

}
