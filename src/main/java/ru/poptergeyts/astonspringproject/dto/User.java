package ru.poptergeyts.astonspringproject.dto;

public class User {
    // Логин
    private final String login;

    public String getLogin() {
        return login;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    // Пароль
    public void setPassword(String password) {
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
