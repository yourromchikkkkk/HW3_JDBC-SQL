package com.cursor.jdbc;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        DBSelection dbSelection = new DBSelection();

        System.out.println("dbSelection.getAllUsers()");
        ArrayList<User> users = dbSelection.getAllUsers();
        for (User val : users) {
            System.out.println(val);
        }

        System.out.println("\ndbSelection.getUserById(3)");
        User user = dbSelection.getUserById(3);
        System.out.println(user);

        System.out.println("\ndbSelection.getIdByCountryName(\"Poland\")");
        int polandId = dbSelection.getIdByCountryName("Poland");
        System.out.println(polandId);

        System.out.println("\ndbSelection.getAllCitiesByCountryName(\"USA\")");
        ArrayList<City> cities = dbSelection.getAllCitiesByCountryName("USA");
        for (City val : cities) {
            System.out.println(val);
        }

        System.out.println("\ndbSelection.getAllUsersFromCity(\"Lviv\")");
        users = dbSelection.getAllUsersFromCity("Lviv");
        for (User val : users) {
            System.out.println(val);
        }

    }
}
