package com.lypaka.titanpokemon.Titans;

import java.util.List;

public abstract class Titan {

    private final String id;
    private final List<String> species;
    private final String ability;

    public Titan (String id, List<String> species, String ability) {

        this.id = id;
        this.species = species;
        this.ability = ability;

    }

    public String getID() {

        return this.id;

    }

    public List<String> getSpecies() {

        return this.species;

    }

    public String getAbility() {

        return this.ability;

    }

}
