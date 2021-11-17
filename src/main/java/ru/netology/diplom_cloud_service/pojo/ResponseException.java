package ru.netology.diplom_cloud_service.pojo;

public class ResponseException {
    private String message;
    private int id;

    public ResponseException(){

    }

    public ResponseException(String message, int id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}
