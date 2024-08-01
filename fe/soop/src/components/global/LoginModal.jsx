import React from "react";
import "../../styles/LoginModal.css"; // 스타일 적용
import kakao from "../../assets/kakao.png";
import google from "../../assets/google.png";

const LoginModal = ({ onClose }) => {
  // 카카오 로그인 클릭 핸들러
  const link = `${process.env.REACT_APP_KAKAO_URL}`;
  const handleKakaoLogin = () => {
    window.location.href = link;
  };

  // 구글 로그인 클릭 핸들러
  const handleGoogleLogin = () => {
    window.open("/auth/google", "_blank");
  };

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <button className="close-button" onClick={onClose}>
          &times;
        </button>
        <h2>로그인</h2>
        <div className="login-options">
          <button onClick={handleKakaoLogin} className="login-option">
            <img src={kakao} alt="Kakao" className="login-logo" />
            카카오 로그인
          </button>
          <button onClick={handleGoogleLogin} className="login-option">
            <img src={google} alt="Google" className="login-logo" />
            구글 로그인
          </button>
        </div>
      </div>
    </div>
  );
};

export default LoginModal;
