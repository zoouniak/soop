import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Redirection = ({ text }) => {
  const navigate = useNavigate();

  useEffect(() => {
    // URL에서 'code' 파라미터를 추출
    const code = new URL(window.location.href).searchParams.get("code");
    console.log(code);

    // 인가 코드를 백엔드로 전송
    axios
      .post(`${process.env.REACT_APP_KAKAO_REDIRECT}`, { code })
      .then((response) => {
        console.log(response.data); // 응답 데이터를 콘솔에 출력

        // 받은 응답에서 필요한 정보를 localStorage에 저장

        localStorage.setItem("accessToken", response.headers["access-token"]);
        localStorage.setItem("login", true);

        // 로그인 성공 페이지로 이동
        navigate("/");
      })
      .catch((error) => {
        console.error("Login failed:", error);
        // 로그인 실패 처리 로직 추가 가능
      });
  }, [navigate]);

  return (
    <div className="centered-container">
      <div className="centered-text">{text}</div>

      {/* Inline CSS */}
      <style>
        {`
        .centered-container {
          position: fixed; /* 페이지 스크롤과 상관없이 화면 중앙에 고정 */
          top: 50%; /* 페이지 상단에서 중앙으로 */
          left: 50%; /* 페이지 왼쪽에서 중앙으로 */
          transform: translate(-50%, -50%); /* 중앙으로 정확히 이동 */
          width: 100%;
          height: 100%;
          display: flex; /* 플렉스박스 사용 */
          justify-content: center; /* 수평 중앙 정렬 */
          align-items: center; /* 수직 중앙 정렬 */
          background-color: rgba(0, 0, 0, 0.5); /* 반투명 배경 */
        }

        .centered-text {
          color: white; /* 텍스트 색상 */
          font-size: 24px; /* 폰트 크기 */
          text-align: center; /* 텍스트 중앙 정렬 */
          padding: 20px; /* 패딩 */
          background: rgba(0, 0, 0, 0.8); /* 텍스트 배경 */
          border-radius: 8px; /* 모서리 둥글기 */
        }
      `}
      </style>
    </div>
  );
};

export default Redirection;
