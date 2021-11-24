package ru.netology.diplom_cloud_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.diplom_cloud_service.pojo.Token;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.service.CloudServiceImpl;

@RestController
public class AuthController {
    private final CloudServiceImpl cloudServiceImpl;

    public AuthController(CloudServiceImpl cloudServiceImpl) {
        this.cloudServiceImpl = cloudServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody User user) {
        return ResponseEntity.ok(cloudServiceImpl.login(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("auth-token") String authToken) {
        cloudServiceImpl.checkToken(authToken);
        return new ResponseEntity<>(cloudServiceImpl.logout(), HttpStatus.OK);
    }
}
