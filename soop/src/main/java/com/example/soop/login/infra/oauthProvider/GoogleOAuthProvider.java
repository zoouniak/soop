package com.example.soop.login.infra.oauthProvider;


import com.example.soop.global.exception.AuthException;
import com.example.soop.login.infra.OAuthAccessToken;
import com.example.soop.login.infra.oauthUserInfo.GoogleOAuthUserInfo;
import com.example.soop.login.infra.oauthUserInfo.OAuthUserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


import static com.example.soop.global.exception.ExceptionCode.FAIL_GET_USERINFO;
import static com.example.soop.global.exception.ExceptionCode.INVALID_AUTHORIZE_CODE;
import static org.springframework.http.HttpMethod.POST;

@Component
public class GoogleOAuthProvider implements OAuthProvider{
    public static final String providerName = "google";
    private static final String prefix = "${oauth.provider.google.";

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String tokenUri;
    private final String userUri;

    public GoogleOAuthProvider(@Value(prefix + "client-id}") String clientId,
                              @Value(prefix + "client-secret}") String clientSecret,
                              @Value(prefix + "redirect-uri}") String redirectUri,
                              @Value(prefix + "token-uri}") String tokenUri,
                              @Value(prefix + "user-uri}") String userUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenUri = tokenUri;
        this.userUri = userUri;
    }

    @Override
    public boolean equal(String provider) {
        return providerName.equals(provider);
    }

    @Override
    public OAuthUserInfo getUserInfo(final String code) {
        // google에 access token 요청
        String accessToken = getAccessToken(code);
        // access token으로 사용자 정보 요청
        final ResponseEntity<GoogleOAuthUserInfo> userInfo = new RestTemplate().exchange(
                userUri,
                HttpMethod.GET,
                getUserInfoRequestEntity(accessToken),
                GoogleOAuthUserInfo.class
        );

        // 사용자 정보 반환
        return Optional.ofNullable(userInfo.getBody())
                .orElseThrow(() -> new AuthException(FAIL_GET_USERINFO));
    }

    private String getAccessToken(final String code) {
        final HttpEntity<MultiValueMap<String, String>> accessTokenRequestEntity = getAccessTokenRequestEntity(code);
        final ResponseEntity<OAuthAccessToken> accessToken = new RestTemplate().exchange(tokenUri, POST, accessTokenRequestEntity, OAuthAccessToken.class);

        return Optional.ofNullable(accessToken.getBody())
                .orElseThrow(() -> new AuthException(INVALID_AUTHORIZE_CODE))
                .accessToken();
    }

    private HttpEntity<MultiValueMap<String, String>> getAccessTokenRequestEntity(final String code) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");
        return new HttpEntity<>(params, headers);
    }

    private HttpEntity<MultiValueMap<String, String>> getUserInfoRequestEntity(final String token) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(token);
        return new HttpEntity<>(headers);
    }


}
