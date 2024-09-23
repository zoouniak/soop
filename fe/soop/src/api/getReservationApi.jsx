import apiClient from "../util/axios";

export const getReservation = (id) => {
  return apiClient.get(`/reservation/${id}`);
};
