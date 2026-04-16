package org.example._0_implementing_rest_services;

public class Country {
    private String name;
    private int population;


    public static Country of(String name, int population) {
        Country country = new Country();
        country.setNamee(name);
        country.setPopulation(population);
        return country;
    }


    public String getNamee() {
        return name;
    }

    public void setNamee(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
