package com.lypaka.titanpokemon.API;

import com.lypaka.titanpokemon.Titans.Titan;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class TitanDefeatEvent extends Event {

    private final ServerPlayerEntity player;
    private final Titan titan;
    private final int titanLevel;

    public TitanDefeatEvent (ServerPlayerEntity player, Titan titan, int titanLevel) {

        this.player = player;
        this.titan = titan;
        this.titanLevel = titanLevel;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public Titan getTitan() {

        return this.titan;

    }

    public int getTitanLevel() {

        return this.titanLevel;

    }

}
