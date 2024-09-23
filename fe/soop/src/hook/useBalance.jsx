import { useState } from "react";
import  apiClient  from "../util/axios";

const useWalletBalance = () => {
  const [balance, setBalance] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const walletBalance = async () => {
    setLoading(true);
    try {
      const response = await apiClient.get("/wallet/balance");
      setBalance(response.data.balance);
    } catch (err) {
      setError("잔액 조회 실패");
    } finally {
      setLoading(false);
    }
  };

  return { balance, walletBalance, error, loading };
};

export default useWalletBalance;
