package ru.netology.diplom_cloud_service.pojo;

public class Token {
    private String auth_token;
    private String email = "err@mail.ru";
    private static int count = 0;

    public Token() {
        this.auth_token = "auth-token #" + ++count;
    }

    public String getAuthToken() {
        return auth_token;
    }

    public String getEmail() {
        return email;
    }
}
