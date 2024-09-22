package com.example.soop.global.exception;

public class ReservationException extends RuntimeException{
    private final int code;
    private final String message;

    public ReservationException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getErrorCode();
        this.message = exceptionCode.getMessage();
    }
}
