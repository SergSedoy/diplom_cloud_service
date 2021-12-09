package ru.netology.diplom_cloud_service.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom_cloud_service.exception.ServerException;
import ru.netology.diplom_cloud_service.exception.InputException;
import ru.netology.diplom_cloud_service.exception.UnauthorizedException;
import ru.netology.diplom_cloud_service.pojo.ResponseException;
import ru.netology.diplom_cloud_service.pojo.CloudFile;
import ru.netology.diplom_cloud_service.service.CloudServiceImpl;

import java.util.List;

@RestController
public class CloudController {

    private final CloudServiceImpl cloudServiceImpl;

    public CloudController(CloudServiceImpl cloudServiceImpl) {
        this.cloudServiceImpl = cloudServiceImpl;
    }

    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader("auth-token") String authToken) {
        cloudServiceImpl.uploadFile(file);
        return new ResponseEntity<>("Success upload", HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<String> delFile(@RequestParam("filename") String fileName, @RequestHeader("auth-token") String authToken) {
//        cloudServiceImpl.checkToken(authToken);
        cloudServiceImpl.delFile(fileName);
        return new ResponseEntity<>("Success deleted", HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> getFile(@RequestParam("filename") String fileName, @RequestHeader("auth-token") String authToken) {
//        cloudServiceImpl.checkToken(authToken);
        return new ResponseEntity<>(cloudServiceImpl.getFile(fileName), HttpStatus.OK);
    }

    @PutMapping("/file")
    public ResponseEntity<String> editFile(@RequestParam("filename") String oldFileName, @RequestHeader("auth-token") String authToken, @RequestBody CloudFile forNewName) {

//        cloudServiceImpl.checkToken(authToken);
        cloudServiceImpl.editFile(oldFileName, forNewName.getFileName());
        return ResponseEntity.ok("Success upload");
    }

    @GetMapping("/list")
    public ResponseEntity<List<CloudFile>> getListFile(@RequestParam("limit") Integer limit, @RequestHeader("auth-token") String authToken) {
//        cloudServiceImpl.checkToken(authToken);
        return ResponseEntity.status(200).body(cloudServiceImpl.getListFile(limit));
    }

    @ExceptionHandler(InputException.class)
    public ResponseEntity<ResponseException> inputException(InputException e) {
        final ResponseException responseException = new ResponseException(e.getMessage(), e.getId());
        return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseException> unauthorException(UnauthorizedException e) {
        final ResponseException responseException = new ResponseException(e.getMessage(), e.getId());
        return new ResponseEntity<>(responseException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ResponseException> serverException(ServerException e) {
        final ResponseException responseException = new ResponseException(e.getMessage(), e.getId());
        return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
