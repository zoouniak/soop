package com.example.soop.login.infra.oauthProvider;


import com.example.soop.global.exception.AuthException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.soop.global.exception.ExceptionCode.NOT_SUPPORT_PROVIDER;


@Component
public class OAuthProviders {
    private final List<OAuthProvider> strategies;

    public OAuthProviders(List<OAuthProvider> strategies) {
        this.strategies = strategies;
    }

    public OAuthProvider mapping(final String providerName) {
        return strategies.stream().filter(
                provider -> provider.equal(providerName)
        ).findFirst().orElseThrow(() -> new AuthException(NOT_SUPPORT_PROVIDER));
    }
}
