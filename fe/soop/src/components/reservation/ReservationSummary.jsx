import React from "react";
import { useNavigate } from "react-router-dom";
import "../../styles/ReservationSummary.css";

export const ReservationSummary = ({ reservation }) => {
  const navigate = useNavigate();

  const handleReservationDetails = () => {
    navigate(`/reservation/${reservation.reservationId}`);
  };

  return (
    <div className="reservation-summary">
      <p>상품명: {reservation.productName}</p>
      <p>예약 날짜: {new Date(reservation.createdAt).toLocaleString()}</p>
      <p>상태: {reservation.status}</p>
      <button onClick={handleReservationDetails}>예약 상세</button>
    </div>
  );
};
