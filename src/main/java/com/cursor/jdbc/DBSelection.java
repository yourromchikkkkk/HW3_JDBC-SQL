package com.cursor.jdbc;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;
import java.util.ArrayList;

public class DBSelection {
    private String URL = "jdbc:mysql://localhost:3306/dbtest";
    private String USERNAME = "admin";
    private String PASSWORD = "29.11.2002";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        final String sql = "select id, userLogin, userPassword, city_id from users;";
        try (
                final Statement statement = getConnection().createStatement();
                final ResultSet resultSet = statement.executeQuery(sql)
                ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("userLogin");
                String password = resultSet.getString("userPassword");
                int cityId = resultSet.getInt("city_id");
                users.add(new User(id, login, password, cityId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = null;
        final String sql = "select id, userLogin, userPassword, city_id from users where id = ?;";
        try(
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            String login = resultSet.getString("userLogin");
            String password = resultSet.getString("userPassword");
            int cityId = resultSet.getInt("city_id");
            user = new User(id, login, password, cityId);
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
            resultSet.next();
            id = resultSet.getInt("id");
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<City> getAllCitiesByCountryName (String countryName) {
        final int id = getIdByCountryName(countryName);
        ArrayList<City> cities = new ArrayList<>();
        final String sql = "select id, city_name from cities where country_id = ?;";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int cityId = resultSet.getInt("id");
                int countryId = id;
                String cityName = resultSet.getString("city_name");
                cities.add(new City(cityId, countryId, cityName));
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
            resultSet.next();
            id = resultSet.getInt("id");
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public ArrayList<User> getAllUsersFromCity(String cityName) {
        final int id = getIdByCityName(cityName);
        ArrayList<User> users = new ArrayList<>();
        final String sql = "select id, userLogin, userPassword, city_id from users where city_id = ?;";
        try (
                final PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
                ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String login = resultSet.getString("userLogin");
                String password = resultSet.getString("userPassword");
                int cityId = resultSet.getInt("city_id");
                users.add(new User(userId, login, password, cityId));
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}
