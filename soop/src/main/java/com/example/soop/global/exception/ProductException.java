package com.example.soop.global.exception;

public class ProductException extends RuntimeException{
    private final int code;
    private final String message;

    public ProductException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getErrorCode();
        this.message = exceptionCode.getMessage();
    }
}
