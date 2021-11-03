package ru.netology.diplom_cloud_service.pojo;

public class Token {
    private final String auth_token;
    private static int count = 0;
    private final User user;

    public Token(User user) {
        this.user = user;
        this.auth_token = "auth-token #" + ++count;
    }

    public String getAuthToken() {
        return auth_token;
    }

    public User getUser(){
        return user;
    }
}
