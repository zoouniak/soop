import axios from "axios";
import { Cookies } from "react-cookie";

const cookie = new Cookies();

// axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: `${process.env.REACT_APP_BASE_URL}`,
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",

  },
  withCredentials:true,
});

apiClient.interceptors.request.use(
    (config) => {
      // 로컬 스토리지에서 토큰 가져오기
      const token = localStorage.getItem("accessToken");
  
      if (token) {
        config.headers["Authorization"] = `Bearer ${token}`;
      }

      return config;
    },
    (error) => Promise.reject(error)
  );

export default apiClient;