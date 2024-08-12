import React from "react";
import { useNavigate } from "react-router-dom";
import Header from "../../components/global/Header";
import Footer from "../../components/global/Footer";
import "../../styles/MyPage.css"; // CSS 파일 임포트

export const MyPage = () => {
  const navigate = useNavigate();

  return (
    <div>
      <Header />
      <div className="mypage-container">
        <h2>마이페이지</h2>
        <div className="button-group">
          <div onClick={() => navigate("/wallet")} className="button">
            지갑 조회
          </div>
          <div onClick={() => navigate("/posts")} className="button">
            게시글 조회
          </div>
          <div onClick={() => navigate("/reservations")} className="button">
            예약 내역 조회
          </div>
          <div onClick={() => navigate("/profile-edit")} className="button">
            개인정보 수정
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};
