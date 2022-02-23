package com.cursor.jdbc;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBSelection {
    final private String URL = "jdbc:mysql://localhost:3306/dbtest";
    final private String USERNAME = "admin";
    final private String PASSWORD = "29.11.2002";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        final String sql = "select id, user_login, user_password, city_id from users;";
        try (
                final Statement statement = getConnection().createStatement();
                final ResultSet resultSet = statement.executeQuery(sql)
                ) {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("user_login"),
                        resultSet.getString("user_password"),
                        resultSet.getInt("city_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = null;
        final String sql = "select id, user_login, user_password, city_id from users where id = ?;";
        try(
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("user_login"),
                        resultSet.getString("user_password"),
                        resultSet.getInt("city_id"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int getIdByCountryName(String countryName) {
        int id = 0;
        final String sql = "select id from countries where country_name = ?";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ) {
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<City> getAllCitiesByCountryName (String countryName) {
        ArrayList<City> cities = new ArrayList<>();
        final String sql = "SELECT\n" +
                "    cities.id as city_id, cities.city_name as city_name, cities.country_id as city_country_id\n" +
                "FROM cities\n" +
                "INNER JOIN countries ON countries.id=cities.country_id where country_name = ?;";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ) {
            preparedStatement.setString(1, countryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cities.add(new City(resultSet.getInt("city_id"),
                        resultSet.getInt("city_country_id"),
                        resultSet.getString("city_name")));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public int getIdByCityName(String cityName) {
        int id = 0;
        final String sql = "select id from cities where city_name = ?";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ) {
            preparedStatement.setString(1, cityName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<User> getAllUsersFromCity(String cityName) {
        final int id = getIdByCityName(cityName);
        ArrayList<User> users = new ArrayList<>();
        final String sql = "SELECT\n" +
                "    users.id as user_id, users.user_login as user_login, users.user_password as user_password, users.city_id as city_id\n" +
                "FROM users\n" +
                "        INNER JOIN cities on users.city_id = cities.id where city_id = ?;";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("user_id"),
                        resultSet.getString("user_login"),
                        resultSet.getString("user_password"),
                        resultSet.getInt("city_id")));
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}
