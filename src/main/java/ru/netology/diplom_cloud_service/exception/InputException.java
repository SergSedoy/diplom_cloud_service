package ru.netology.diplom_cloud_service.exception;

public class InputException extends RuntimeException{
    private int id;

    public InputException(String message, int id) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
