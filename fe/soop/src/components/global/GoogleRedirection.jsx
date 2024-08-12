import React from "react";
import { SocialRedirection } from "./SocialRedirection";

export const GoogleRedirection = () => {
  return (
    <SocialRedirection
      text="Google 로그인 중..."
      redirectUri = {process.env.REACT_APP_GOOGLE_REDIRECT}
    />
  );
};
