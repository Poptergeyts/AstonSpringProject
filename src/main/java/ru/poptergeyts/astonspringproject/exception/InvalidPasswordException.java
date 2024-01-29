package ru.poptergeyts.astonspringproject.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends CustomException{
    public InvalidPasswordException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid password.");
    }
}
