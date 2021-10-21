package ru.netology.diplom_cloud_service.exception;

public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
}
