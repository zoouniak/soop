import apiClient from "../util/axios";

export const cancelReservation = async (reservationId) => {
  try {
    const response = await apiClient.post("/reservation/cancel", {
      reservationId: reservationId,
    });
    return response.data; // 서버 응답 데이터를 반환
  } catch (error) {
    throw new Error("예약 취소 요청 중 오류가 발생했습니다.");
  }
};
