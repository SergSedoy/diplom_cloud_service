package ru.netology.diplom_cloud_service.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Token {
    @JsonProperty("auth-token")
    private final String authToken;
    @JsonIgnore
    private final User user;

    public Token(User user) {
        this.user = user;
        this.authToken = generateToken(this.user);
    }

    public String getAuthToken() {
        return authToken;
    }

    public User getUser(){
        return user;
    }

    private String generateToken(User user) {
        Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, "javadiplom")
                .compact();
    }

    @Override
    public String toString() {
        return "auth-token = '" + authToken + '\'' +
                ", user=" + user +
                '}';
    }
}
