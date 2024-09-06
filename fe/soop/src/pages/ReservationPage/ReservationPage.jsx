import React from "react";
import { useLocation } from "react-router-dom";
import {useReservationData} from "../../hook/useReservationData";

// 컴포넌트 가져오기
import {ProductInfo} from "../../components/reservation/ProductInfo";
import {TimeSlotInfo} from "../../components/reservation/TimeSlotInfo"
import {UserInfo} from "../../components/reservation/UserInfo";

export const ReservationPage = () => {
  const location = useLocation();
  const { productId, timeSlotId } = location.state;

  // 커스텀 훅을 통해 데이터를 가져옴
  const { productInfo, timeSlotInfo, userInfo, loading, error } = useReservationData(productId, timeSlotId);

  if (loading) {
    return <div>로딩 중...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h1>예약 페이지</h1>
      
      <ProductInfo productInfo={productInfo} />
      <TimeSlotInfo timeSlotInfo={timeSlotInfo} />
      <UserInfo userInfo={userInfo} />
      
    </div>
  );
};
