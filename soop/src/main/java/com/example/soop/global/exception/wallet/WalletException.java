package com.example.soop.global.exception.wallet;

import com.example.soop.global.exception.ExceptionCode;

public class WalletException  extends RuntimeException {
    private final int code;
    private final String message;

    public WalletException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getErrorCode();
        this.message = exceptionCode.getMessage();
    }
}
