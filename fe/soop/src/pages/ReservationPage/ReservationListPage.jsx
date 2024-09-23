import React, { useEffect, useState } from "react";

import Header from "../../components/global/Header";
import { ReservationList } from "../../components/reservation/ReservationList";
import { getReservations } from "../../api/getReservationsApi"; // API 함수 가져오기
import "../../styles/ReservationListPage.css"; // 스타일 추가

export const ReservationListPage = () => {
  const [reservations, setReservations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // 예약 내역 데이터를 가져오는 함수
    const loadReservations = async () => {
      try {
        const data = await getReservations(); // API 호출
        setReservations(data);
      } catch (err) {
        setError(err.message); // 오류 메시지 설정
      } finally {
        setLoading(false);
      }
    };

    loadReservations();
  }, []);

  if (loading) {
    return <div>로딩 중...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <Header/>
    <div className="reservation-list-page">
      <h1>예약 내역</h1>
      <ReservationList reservations={reservations} />
    </div>
    </div>
  );
};
