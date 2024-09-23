import React from "react";
import { useNavigate, useLocation } from "react-router-dom";
import {WalletBalance} from "../../components/user/WalletBalance"; 
import {ReservationInfo} from "../../components/reservation/ReservationInfo";
import "../../styles/ReservationSuccess.css"
export const ReservationSuccessPage = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const { reservationInfo } = location.state; 

  const handleGoHome = () => {
    navigate("/"); 
  };

  return (
    <div className="reservation-success-page">
      <h1>예약 완료</h1>
      <p>예약이 성공적으로 완료되었습니다!</p>

      <ReservationInfo reservationInfo={reservationInfo} />
      <WalletBalance />

      <button onClick={handleGoHome}>홈으로</button>
    </div>
  );
};


