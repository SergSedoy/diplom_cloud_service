package ru.netology.diplom_cloud_service.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.ServerException;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.service.CloudServiceImpl;

import java.util.List;

@RestController
//@RequestMapping("/cloud")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class MyController {

    private final CloudServiceImpl cloudServiceImpl;

    public MyController(CloudServiceImpl cloudServiceImpl) {
        this.cloudServiceImpl = cloudServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok("Hello!!!");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loging(@RequestBody User user) {
//        Properties properties = new Properties();
//        return ResponseEntity.status(200).header("auth-token", "pass" + (int)(Math.random() * 1000)).build();
//        return properties.getProperty("max@mail.ru");
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("\"auth-token\"" + ": " + "\"" + cloudServiceImpl.loging(user) + "\"");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("auth-token") String authToken) {
        cloudServiceImpl.checkToken(authToken);
        return new ResponseEntity<>(cloudServiceImpl.logout(), HttpStatus.OK);
    }

    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("auth-token") String authToken) {
        cloudServiceImpl.checkToken(authToken);
        cloudServiceImpl.uploadFile(file);
        return new ResponseEntity<>("Success upload", HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<String> delFile(@RequestParam("name") String fileName, @RequestHeader("auth-token") String authToken) {
        cloudServiceImpl.checkToken(authToken);
        cloudServiceImpl.delFile(fileName);
        return new ResponseEntity<>("Success deleted", HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> getFile(@RequestParam("name") String fileName, @RequestHeader("auth-token") String authToken) {
        cloudServiceImpl.checkToken(authToken);
        return new ResponseEntity<>(cloudServiceImpl.getFile(fileName), HttpStatus.OK);
    }

    @PutMapping("/file")
    public ResponseEntity<String> editFile(@RequestParam("name") String oldFileName, @RequestHeader("auth-token") String authToken, @RequestBody String newFileName) {
        cloudServiceImpl.checkToken(authToken);
        cloudServiceImpl.editFile(oldFileName, newFileName);
        return ResponseEntity.ok("Success upload");
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> getListFile(@RequestParam("limit") Integer limit, @RequestHeader("auth-token") String authToken) {
        cloudServiceImpl.checkToken(authToken);
        return ResponseEntity.status(200).body(cloudServiceImpl.getListFile(limit));
    }

    @ExceptionHandler(InputException.class)
    public ResponseEntity<String> inputException(InputException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> unauthorException(UnauthorizedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> serverException(ServerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
