package com.cursor.jdbc;

public class User {
    int id;
    String userLogin;
    String userPassword;
    int cityId;

    public User(int id, String login, String password, int cityId) {
        this.id = id;
        this.userLogin = login;
        this.userPassword = password;
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "User{" + id + ", " + userLogin + ", " + userPassword + ", " + cityId + "}";
    }

}
