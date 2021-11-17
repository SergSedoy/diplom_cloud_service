package ru.netology.diplom_cloud_service.exception;

public class UnauthorizedException extends RuntimeException{
    private int id;

    public UnauthorizedException(String message, int id){
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
