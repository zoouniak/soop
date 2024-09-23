import apiClient from "../util/axios";

// 예약 내역을 가져오는 API
export const getReservations = async () => {
  try {
    const response = await apiClient.get("/reservation/my");
    return response.data;
  } catch (error) {
    throw new Error("예약 내역을 불러오는 중 오류가 발생했습니다.");
  }
};
