import React from "react";
import { SocialRedirection } from "./SocialRedirection";

export const KakaoRedirection = () => {
  return (
    <SocialRedirection
      text="Kakao 로그인 중..."
      redirectUri={process.env.REACT_APP_KAKAO_REDIRECT}
    />
  );
};
