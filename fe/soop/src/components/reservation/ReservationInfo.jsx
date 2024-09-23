import React from "react";
import "../../styles/ReservationInfo.css";

export const ReservationInfo = ({ reservationInfo, onCancel }) => {
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
    </div>
  );
};
