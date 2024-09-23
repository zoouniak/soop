import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import apiClient  from "../../util/axios"; 
import '../../styles/InfoPage.css';

export const SocialRedirection = ({ text, redirectUri }) => {
  const navigate = useNavigate();
  const JWT_EXPIRE_TIME = 24 * 3600 * 1000; // 만료 시간 (24시간 밀리 초로 표현)

  useEffect(() => {
    // URL에서 'code' 파라미터를 추출
    const code = new URL(window.location.href).searchParams.get("code");

    // 인가 코드를 백엔드로 전송
    apiClient
      .post(
        redirectUri,
        { code },
       
      )
      .then(onLoginSuccess)
      .catch((error) => {
        console.log(error);
        onLoginFail(error);
      });
  }, []);

  const onSilentRefresh = () => {
    apiClient
      .post(
        "/login/extend"
      )
      .then(onLoginSuccess)
      .catch((error) => {
        onLoginFail(error);
      });
  };

  const onLoginSuccess = (response) => {
    localStorage.setItem("accessToken", response.headers["access-token"]);
    localStorage.setItem("login", true);

    setTimeout(onSilentRefresh, JWT_EXPIRE_TIME - 60000);
    navigate("/");
  };

  const onLoginFail = (error) => {
    navigate("/error", {
      state: { message: "로그인에 실패하였습니다. 다시 시도해주세요." },
    });
  };

  return (
    <div className="centered-container">
      <div className="centered-text">{text}</div>
    </div>
  );
};
