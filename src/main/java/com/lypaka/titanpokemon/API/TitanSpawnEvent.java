package com.lypaka.titanpokemon.API;

import com.lypaka.titanpokemon.Titans.Titan;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class TitanSpawnEvent extends Event {

    private final ServerPlayerEntity player;
    private final Pokemon pokemon;
    private final Titan titan;
    private int titanLevel;

    public TitanSpawnEvent (ServerPlayerEntity player, Pokemon pokemon, Titan titan, int titanLevel) {

        this.player = player;
        this.pokemon = pokemon;
        this.titan = titan;
        this.titanLevel = titanLevel;

    }

    public ServerPlayerEntity getPlayer() {

        return this.player;

    }

    public Pokemon getPokemon() {

        return this.pokemon;

    }

    public Titan getTitan() {

        return this.titan;

    }

    public int getTitanLevel() {

        return this.titanLevel;

    }

    public void setTitanLevel (int level) {

        this.titanLevel = level;

    }

}
