package com.bogdan.camel.model;

public abstract class Grocery {
    protected String name;
    protected String countryOfProvenience;

    public Grocery(String name, String countryOfProvenience) {
        this.name = name;
        this.countryOfProvenience = countryOfProvenience;
    }

    abstract String getName();
}
