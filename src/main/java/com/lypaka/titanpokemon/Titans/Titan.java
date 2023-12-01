package com.lypaka.titanpokemon.Titans;

import java.util.List;

public abstract class Titan {

    private final List<String> species;
    private final String ability;

    public Titan (List<String> species, String ability) {

        this.species = species;
        this.ability = ability;

    }

    public List<String> getSpecies() {

        return this.species;

    }

    public String getAbility() {

        return this.ability;

    }

}
