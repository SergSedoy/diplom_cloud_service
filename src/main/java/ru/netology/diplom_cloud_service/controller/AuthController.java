package ru.netology.diplom_cloud_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.service.CloudServiceImpl;

import java.util.Map;

@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(CloudServiceImpl.class);
    private final CloudServiceImpl cloudServiceImpl;

    public AuthController(CloudServiceImpl cloudServiceImpl) {
        this.cloudServiceImpl = cloudServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        LOG.info("вошли в login");
        return ResponseEntity.ok(cloudServiceImpl.login(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("auth-token") String authToken) {
        return new ResponseEntity<>(cloudServiceImpl.logout(), HttpStatus.OK);
    }
}
