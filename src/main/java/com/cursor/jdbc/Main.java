package com.cursor.jdbc;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;


import java.sql.*;
import java.util.ArrayList;


public class Main {
    public static String URL = "jdbc:mysql://localhost:3306/dbtest?serverTimezone=UTC";
    public static String USERNAME = "admin";
    public static String PASSWORD = "29.11.2002";

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
