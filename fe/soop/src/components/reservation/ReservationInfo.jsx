import React, { useState, useEffect  } from "react";
import { cancelReservation } from "../../api/cancelReservationApi";
import "../../styles/ReservationInfo.css";

export const ReservationInfo = ({ reservationInfo }) => {
  const [reservationStatus, setReservationStatus] = useState(reservationInfo.status);

  const onCancel = async () => {
    try {

      await cancelReservation(reservationInfo.reservationId);
      
      reservationInfo.status="CANCELED"
      setReservationStatus("CANCELED");

      alert("예약이 성공적으로 취소되었습니다.");
     
      
    } catch (error) {
      alert(error.message || "예약 취소 중 오류가 발생했습니다.");
    }
  };

  return (
    <div className="reservation-info">
     <h2> 예약 번호: {reservationInfo.reservationId}</h2>
      <div className="reservation-details">
       
        <div className="product-container">
       
          <h4>상품 정보</h4>
          <p>상품명: {reservationInfo.productName}</p>
          <p>예약 상태: {reservationInfo.status}</p>
          <p>예약일시: {new Date(reservationInfo.createdAt).toLocaleString()}</p>
        </div>
        <div className="user-container">
          <h4>예약자 정보</h4>
          <p>이름: {reservationInfo.userName}</p>
          <p>전화번호: {reservationInfo.userPhone}</p>
          <p>예약 날짜: {reservationInfo.date}</p>
          <p>예약 시간: {reservationInfo.startedAt}</p>
        </div>
      </div>
      {reservationInfo.status === "RESERVED" && (
        <button className="cancel-button" onClick={onCancel}>
          예약 취소
        </button>
      )}
       {reservationStatus === "CANCELED" && <p>이 예약은 취소되었습니다.</p>}
    </div>
  );
};
