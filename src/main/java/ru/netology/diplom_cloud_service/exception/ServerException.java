package ru.netology.diplom_cloud_service.exception;

public class ServerException extends RuntimeException {
    private int id;

    public ServerException(String message, int id) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
