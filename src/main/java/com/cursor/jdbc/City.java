package com.cursor.jdbc;

public class City {
    int id;
    int countryId;
    String cityName;

    public City(int id, int countryId, String cityName) {
        this.id = id;
        this.countryId = countryId;
        this.cityName = cityName;
    }

    public String toString() {
        return "City{" + id + ", " + countryId + ", " + cityName + "}";
    }
}
