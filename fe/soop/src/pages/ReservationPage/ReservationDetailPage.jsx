import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Header from "../../components/global/Header";
import { getReservation } from "../../api/getReservationApi"; // API 호출 함수
import { ReservationInfo } from "../../components/reservation/ReservationInfo";
import "../../styles/ReservationDetailPage.css"; // 스타일 파일

export const ReservationDetailPage = () => {
  const { id } = useParams(); // URL에서 예약 ID 가져오기
  const [reservationInfo, setReservationInfo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // 예약 상세 정보를 가져오는 함수
    const fetchReservationDetails = async () => {
      try {
        const response = await getReservation(id); // 서버에서 예약 정보 가져오기
        setReservationInfo(response.data);
      } catch (err) {
        setError("예약 정보를 불러오는 중 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };

    fetchReservationDetails();
  }, [id]);

  if (loading) {
    return <div>로딩 중...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
    <Header/>
    <div className="reservation-detail-page">
      <h1>예약 상세 정보</h1>
       <ReservationInfo reservationInfo={reservationInfo} />
    </div>
    </div>
  );
};
