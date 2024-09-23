package com.example.soop.login.dto.response;

public record AuthTokens(
        String accessToken,
        String refreshToken
) {
}
