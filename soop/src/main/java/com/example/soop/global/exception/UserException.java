package com.example.soop.global.exception;

public class UserException extends RuntimeException{
    private final int code;
    private final String message;

    public UserException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getErrorCode();
        this.message = exceptionCode.getMessage();
    }
}
