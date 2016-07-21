package com.bogdan.camel.model;

public class Vegetable extends Grocery {
    public Vegetable(String name, String countryOfProvenience) {
        super(name, countryOfProvenience);
    }

    @Override
    public String getName() {
        return name + "(vegetable) from " + countryOfProvenience;
    }
}
