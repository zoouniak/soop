import React from "react";
import { useNavigate } from "react-router-dom";
import Header from "../../components/global/Header";
import Footer from "../../components/global/Footer";
import "../../styles/MyPage.css";

export const MyPage = () => {
  const navigate = useNavigate();

  return (
    <div>
      <Header />
      <div className="mypage-container">
        <h2>마이페이지</h2>
        <div className="button-group">

          <div onClick={() => navigate("/my/balance")} className="area">
            잔액 조회
            <p>고객님의 지갑 잔액을 조회할 수 있습니다.</p>
          </div>
        
          <div onClick={() => navigate("/my/posts")} className="area">
           게시글 조회
           <p>고객님이 작성한 글을 관리할 수 있습니다.</p>
         </div>
          <div onClick={() => navigate("/my/reservations")} className="area">
            예약 내역 조회
            <p>고객님께서 예약하신 예약 내역을 조회할 수 있습니다.</p>
         </div>
         <div onClick={() => navigate("/my/profile")} className="area">
              개인정보 수정
              <p>고객님의 개인정보를 관리하는 공간입니다.</p>
        </div>
      </div>
    </div>
    <Footer />
  </div>
  );
};
