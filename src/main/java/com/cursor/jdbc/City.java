package com.cursor.jdbc;

public class City {
    private int id;
    private int countryId;
    private String cityName;

    public City(int id, int countryId, String cityName) {
        this.id = id;
        this.countryId = countryId;
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCityName() {
        return cityName;
    }

    public String toString() {
        return "City{" + id + ", " + countryId + ", " + cityName + "}";
    }
}
