package com.example.soop.global.exception;

public class TimeSlotException extends RuntimeException{
    private final int code;
    private final String message;

    public TimeSlotException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getErrorCode();
        this.message = exceptionCode.getMessage();
    }
}
