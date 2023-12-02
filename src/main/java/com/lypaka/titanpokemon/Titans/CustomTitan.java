package com.lypaka.titanpokemon.Titans;

import java.util.List;

public class CustomTitan extends Titan {

    private final String name;

    public CustomTitan (String name, String id, List<String> species, String ability) {

        super(id, species, ability);
        this.name = name;

    }

    public String getName() {

        return this.name;

    }

}
