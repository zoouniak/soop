import React from "react";
import useWalletBalance from "../../hook/useBalance";
import "../../styles/WalletBalance.css";

export const WalletBalance = () => {
  const { balance, walletBalance, error, loading } = useWalletBalance();

  return (
    <div className="wallet-balance">
      <button onClick={walletBalance} disabled={loading}>
        {loading ? "잔액 조회 중..." : "잔액 조회"}
      </button>
      {error && <p className="error-message">{error}</p>}
      {balance !== null && <p>사용자 잔액: {balance} ETH</p>}
    </div>
  );
};