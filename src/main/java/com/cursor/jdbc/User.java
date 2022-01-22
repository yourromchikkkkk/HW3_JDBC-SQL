package com.cursor.jdbc;

public class User {
    private int id;
    private String userLogin;
    private String userPassword;
    private int cityId;

    public User(int id, String login, String password, int cityId) {
        this.id = id;
        this.userLogin = login;
        this.userPassword = password;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getCityId() {
        return cityId;
    }

    @Override
    public String toString() {
        return "User{" + id + ", " + userLogin + ", " + userPassword + ", " + cityId + "}";
    }

}
