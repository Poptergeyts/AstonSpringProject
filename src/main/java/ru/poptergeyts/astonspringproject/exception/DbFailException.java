package ru.poptergeyts.astonspringproject.exception;

import org.springframework.http.HttpStatus;

public class DbFailException extends CustomException{
    public DbFailException() {
        super(HttpStatus.BAD_GATEWAY, "Database failure");
    }
}
