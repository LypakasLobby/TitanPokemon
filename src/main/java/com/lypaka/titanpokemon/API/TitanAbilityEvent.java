package com.lypaka.titanpokemon.API;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public abstract class TitanAbilityEvent extends Event {

    private final ServerPlayerEntity player;
    private final String titanAbility;
    private final String worldName;
    private boolean bypass;

    public TitanAbilityEvent(ServerPlayerEntity player, String titanAbility, String worldName, boolean bypass) {

        this.player = player;
        this.titanAbility = titanAbility;
        this.worldName = worldName;
        this.bypass = bypass;

    }

    @Cancelable
    public static class Activate extends TitanAbilityEvent {

        public Activate (ServerPlayerEntity player, String titanAbility, String worldName, boolean bypass) {

            super(player, titanAbility, worldName, bypass);

        }

    }

    @Cancelable
    public static class Deactivate extends TitanAbilityEvent {

        public Deactivate (ServerPlayerEntity player, String titanAbility, String worldName, boolean bypass) {

            super(player, titanAbility, worldName, bypass);

        }

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public String getTitanAbility() {

        return this.titanAbility;

    }

    public String getWorldName() {

        return this.worldName;

    }

    public boolean doesBypass() {

        return this.bypass;

    }

    public void setBypass (boolean bypass) {

        this.bypass = bypass;

    }

}
