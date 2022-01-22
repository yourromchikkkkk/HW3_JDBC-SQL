package com.cursor.jdbc;


public class Country {
    private int id;
    private String countryName;

    public Country(int id, String country) {
        this.id = id;
        this.countryName = country;
    }

    public int getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public String toString() {
        return "Country{" + id + ", " + countryName + "}";
    }
}
