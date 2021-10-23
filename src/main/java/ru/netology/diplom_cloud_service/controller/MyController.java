package ru.netology.diplom_cloud_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.ServerException;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.Token;
import ru.netology.diplom_cloud_service.pojo.User;
import ru.netology.diplom_cloud_service.repository.CloudRepositoryImp;
import ru.netology.diplom_cloud_service.service.CloudServiceImpl;

import java.util.List;

@RestController
//@RequestMapping("/cloud")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class MyController {

    private final CloudServiceImpl cloudServiceImpl;
    private final CloudRepositoryImp repository;

    public MyController(CloudServiceImpl cloudServiceImpl, CloudRepositoryImp repository) {
        this.cloudServiceImpl = cloudServiceImpl;
        this.repository = repository;
    }

    @GetMapping("/")
    public ResponseEntity<String> greeting() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello!");
    }

    @PostMapping("/login")
    public String loging(@RequestBody User user) {
//        Properties properties = new Properties();

//        System.out.println(properties.getProperty("email", "wid@mail.ru"));
//        return ResponseEntity.status(200).header("auth-token", "pass" + (int)(Math.random() * 1000)).build();
//        return properties.getProperty("qwert");
        //todo проверка user в базе
        user.setToken(new Token().getAuthToken());
        return user.getToken();
    }

    @PostMapping("/logout")
    public HttpStatus logout() {
        //TODO удалить токен
        return HttpStatus.OK;
    }

    @PostMapping("/file")
    public HttpStatus uploadFile(@RequestHeader ("auth-token") String auth_token, MultipartFile file) {
        String token = repository.getToken(auth_token);
        if (token.isEmpty())
            throw new UnauthorizedException("Unauthorized error");
        // TODO сохранить файл
//        cloudService.uploadFile();
        return HttpStatus.OK;
    }

    @DeleteMapping("/file")
    public HttpStatus delFile(@RequestParam("name") String fileName) {
        // TODO удалить файл
        return HttpStatus.OK;
    }

    @GetMapping("/file")
    public String getFile(@RequestParam("in") String in, @RequestParam("name") String fileName) {
        // TODO вернуть файл
        return "get request GET!";
    }

    @PutMapping("/file")
    public HttpStatus editFile(@RequestParam("name") String fileName) {
        // TODO перезаписать файл
        return HttpStatus.OK;
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> getListFile(Integer limit) {
//        cloudService.getListFile(limit);
        // TODO вернуть список файлов
        return ResponseEntity.status(HttpStatus.OK).body(cloudServiceImpl.getListFile(limit));
    }

    @ExceptionHandler(InputException.class)
    public ResponseEntity<String> inputException(InputException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> unauthorException(UnauthorizedException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> serverException(ServerException e) {
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

}
