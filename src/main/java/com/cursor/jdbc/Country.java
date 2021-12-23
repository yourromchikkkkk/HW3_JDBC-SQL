package com.cursor.jdbc;


public class Country {
    int id;
    String countryName;

    public Country(int id, String country) {
        this.id = id;
        this.countryName = country;
    }

    public String toString() {
        return "Country{" + id + ", " + countryName + "}";
    }
}
