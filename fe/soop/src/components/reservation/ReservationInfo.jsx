import React from "react";
import "../../styles/ReservationInfo.css"

export const ReservationInfo = ({ reservationInfo }) => {
  return (
    <div className="reservation-info">
      <h3>예약 정보</h3>
      <p>예약 번호 : {reservationInfo.reservationId}</p>
      <p>상품명 : {reservationInfo.productName}</p>
      <p>예약자 이름 : {reservationInfo.userName}</p>
      <p>전화번호 : {reservationInfo.userPhone}</p>
      <p>날짜 : {reservationInfo.date}</p>
      <p>시간 : {reservationInfo.startedAt}</p>
    </div>
  );
};
