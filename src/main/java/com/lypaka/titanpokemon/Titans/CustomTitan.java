package com.lypaka.titanpokemon.Titans;

import java.util.List;

public class CustomTitan extends Titan {

    private final String name;

    public CustomTitan (String name, List<String> species, String ability) {

        super(species, ability);
        this.name = name;

    }

    public String getName() {

        return this.name;

    }

}
