import axios from "axios";
import { Cookies } from "react-cookie";

const cookie = new Cookies();

// axios 인스턴스 생성
export const apiClient = axios.create({
  baseURL: `${process.env.REACT_APP_BASE_URL}`,
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",

  },
});