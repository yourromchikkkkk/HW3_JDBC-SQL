package com.cursor.jdbc;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;
import java.util.ArrayList;

public class DBSelection {
    private String URL = "jdbc:mysql://localhost:3306/dbtest";
    private String USERNAME = "admin";
    private String PASSWORD = "29.11.2002";
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DBSelection() {

        try {
            Driver driver = new SQLServerDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void closeDBSelection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users;");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                int cityId = resultSet.getInt(4);
                users.add(new User(id, login, password, cityId));
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select max(id) from users");
            resultSet.next();
            int count = resultSet.getInt(1);
            if (userId > count || userId < 1) {
                throw new IllegalArgumentException();
            } else {
                preparedStatement = connection.prepareStatement("select * from users where id = ?;");
                preparedStatement.setInt(1, userId);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                int cityId = resultSet.getInt(4);
                user = new User(id, login, password, cityId);

                resultSet.close();
                preparedStatement.close();
                statement.close();
                return user;
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            return user;
        }

    }

    public int getIdByCountryName(String countryName) {
        int id = 0;
        try {
            preparedStatement = connection.prepareStatement("select id from countries where country_name = ?");
            preparedStatement.setString(1, countryName);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        }
    }

    public ArrayList<City> getAllCitiesByCountryName (String countryName) {
        int id = getIdByCountryName(countryName);
        ArrayList<City> cities = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from cities where country_id = ?;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int cityId = resultSet.getInt(1);
                int countryId = id;
                String cityName = resultSet.getString(3);
                cities.add(new City(cityId, countryId, cityName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public int getIdByCityName(String cityName) {
        int id = 0;
        try {
            preparedStatement = connection.prepareStatement("select id from cities where city_name = ?");
            preparedStatement.setString(1, cityName);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        }
    }

    public ArrayList<User> getAllUsersFromCity(String cityName) {
        int id = getIdByCityName(cityName);
        ArrayList<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("select * from users where city_id = ?;");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                int cityId = resultSet.getInt(4);
                users.add(new User(id, login, password, cityId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
