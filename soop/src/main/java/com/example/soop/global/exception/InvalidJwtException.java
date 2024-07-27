package com.example.soop.global.exception;

public class InvalidJwtException extends AuthException{
    public InvalidJwtException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
