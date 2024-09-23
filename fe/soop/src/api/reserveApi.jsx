import apiClient from "../util/axios";

// 예약 요청 함수
export const reserveProduct = async ({ productId, timeSlotId}) => {
  try {
    const response = await apiClient.post("/reservation/reserve", {
      productId,
      timeSlotId
    });
    return response.data;
  } catch (error) {
    console.error("예약 실패:", error);
    throw error; // 호출한 곳에서 에러를 핸들링 할 수 있도록 throw
  }
};
