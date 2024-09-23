import React from "react";
import Header from "../../components/global/Header";
import { WalletBalance } from "../../components/user/WalletBalance";
import "../../styles/BalancePage.css"

export const BalancePage = () => {
  return (
<div>
    <Header/>
    <div className="balance-page">
      <h1 className="balance-title">잔액 조회</h1>
     
      <div className="balance-content">
      버튼을 눌러 잔액을 확인하세요.
        <WalletBalance />
      </div>
    </div>
    </div>
  );
};
