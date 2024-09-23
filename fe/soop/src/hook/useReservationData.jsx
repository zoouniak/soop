import { useState, useEffect } from "react";
import  apiClient  from "../util/axios";

// 커스텀 훅 - 상품 정보, 타임슬롯 정보, 사용자 정보 로드
export const useReservationData = (productId, timeSlotId) => {
  const [productInfo, setProductInfo] = useState(null);
  const [timeSlotInfo, setTimeSlotInfo] = useState(null);
  const [userInfo, setUserInfo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchReservationData = async () => {
      try {
        // 동시에 상품 정보, 타임슬롯 정보, 사용자 정보 요청
        const [productRes, timeSlotRes, userRes] = await Promise.all([
            apiClient.get(`/product/${productId}`),
            apiClient.get(`/timeslot/${timeSlotId}`),
            apiClient.get("/user/me"), // 로그인한 사용자 정보
        ]);

        // 각각의 데이터 저장
        setProductInfo(productRes.data);
        setTimeSlotInfo(timeSlotRes.data);
        setUserInfo(userRes.data);
      } catch (err) {
        setError("데이터를 불러오는 데 실패했습니다.");
      } finally {
        setLoading(false);
      }
    };

    fetchReservationData();
  }, [productId, timeSlotId]);

  return { productInfo, timeSlotInfo, userInfo, loading, error };
};
