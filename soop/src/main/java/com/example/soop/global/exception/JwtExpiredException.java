package com.example.soop.global.exception;

public class JwtExpiredException extends AuthException{
    public JwtExpiredException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
