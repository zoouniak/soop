package com.example.soop.login.infra.oauthProvider;

import com.example.soop.login.infra.oauthUserInfo.OAuthUserInfo;

import org.springframework.stereotype.Component;

@Component
public interface OAuthProvider {
     boolean equal(String provider);

     OAuthUserInfo getUserInfo(String code);

}
