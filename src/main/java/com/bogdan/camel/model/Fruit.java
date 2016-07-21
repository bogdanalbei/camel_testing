package com.bogdan.camel.model;

public class Fruit extends Grocery {
    public Fruit(String name, String countryOfProvenience) {
        super(name, countryOfProvenience);
    }

    @Override
    public String getName() {
        return name + "(fruit) from " + countryOfProvenience;
    }
}
