import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

import { useReservationData } from "../../hook/useReservationData";
import { ProductInfo } from "../../components/reservation/ProductInfo";
import { TimeSlotInfo } from "../../components/reservation/TimeSlotInfo";
import { UserInfo } from "../../components/reservation/UserInfo";
import { reserveProduct } from "../../api/reserveApi";
import "../../styles/ReservationPage.css";

export const ReservationPage = () => {
  const location = useLocation();
  const navigate = useNavigate(); 
  const { productId, timeSlotId } = location.state;
  const { productInfo, timeSlotInfo, userInfo, loading, error } = useReservationData(productId, timeSlotId);

  const [isReserving, setIsReserving] = useState(false); 
  const [reservationError, setReservationError] = useState(null);

  const handleReserve = async () => {
    setIsReserving(true);
    setReservationError(null);

    try {
      const response = await reserveProduct({ productId, timeSlotId });
      navigate("/reservation-success", { state: { reservationInfo: response } });
    } catch (error) {
      alert("예약에 실패했습니다.");
      setReservationError("예약에 실패했습니다.");
    } finally {
      setIsReserving(false);
    }
  };

  if (loading) {
    return <div>로딩 중...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className="reservation-page">
      <h1>예약 페이지</h1>
      <div className="reservation-info">
      <ProductInfo productInfo={productInfo} />
      <TimeSlotInfo timeSlotInfo={timeSlotInfo} />
      <UserInfo userInfo={userInfo} />
      </div>

      <div>
        <button onClick={handleReserve} disabled={isReserving}>
          {isReserving ? "예약 진행 중..." : "예약하기"}
        </button>
        {reservationError && <p style={{ color: "red" }}>{reservationError}</p>}
      </div>
      
    </div>
  );
};
