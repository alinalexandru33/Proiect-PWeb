package com.example.project.exception;

public class NotUniqueEmailException extends RuntimeException {

    public NotUniqueEmailException(String message) {
        super(message);
    }
}
