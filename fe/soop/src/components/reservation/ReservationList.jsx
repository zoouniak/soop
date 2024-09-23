import React from "react";
import { ReservationSummary } from "./ReservationSummary";
import "../../styles/ReservationList.css"; // 스타일 추가

export const ReservationList = ({ reservations }) => {
  return (
    <div className="reservation-list">
      {reservations.length === 0 ? (
        <p>예약 내역이 없습니다.</p>
      ) : (
        reservations.map((reservation) => (
          <ReservationSummary key={reservation.reservationId} reservation={reservation} />
        ))
      )}
    </div>
  );
};
