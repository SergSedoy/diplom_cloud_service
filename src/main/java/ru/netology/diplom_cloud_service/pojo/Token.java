package ru.netology.diplom_cloud_service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    @JsonProperty("auth-token")
    private final String authToken;
    @JsonIgnore
    private final User user;
    private static int count = 0;

    public Token(User user) {
        this.user = user;
        this.authToken = "auth-token #" + ++count;
    }

    public String getAuthToken() {
        return authToken;
    }

    public User getUser(){
        return user;
    }
}
