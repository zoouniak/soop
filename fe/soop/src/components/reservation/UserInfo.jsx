import React, { useState } from "react";
import apiClient from "../../util/axios";
import {WalletBalance} from "../../components/user/WalletBalance"; 
import { reserveProduct } from "../../api/reserveApi";

export const UserInfo = ({ userInfo }) => {
  const [phone, setPhone] = useState(userInfo?.phone || "");
  const [isEditing, setIsEditing] = useState(!userInfo?.phone);
  
  
  // 예약 상태 관리
  const [isReserving, setIsReserving] = useState(false);
  const [reservationError, setReservationError] = useState(null);
  const [reservationSuccess, setReservationSuccess] = useState(false);

  const handleInputChange = (e) => {
    setPhone(e.target.value);
  };

  const handleSave = async () => {
    try {
      const response = await apiClient.post("/user/phone", { phone });
      console.log("연락처 저장 완료:", response.data);
      setIsEditing(false); // 저장 완료 후, 편집 모드를 비활성화
    } catch (error) {
      console.error("연락처 저장 실패:", error);
    }
  };

  return (
    <div>
      <h2>사용자 정보</h2>
      <p>사용자 이름: {userInfo.name}</p>

      {isEditing ? (
        <div>
          <label>연락처 입력: </label>
          <input
            type="text"
            value={phone}
            onChange={handleInputChange}
            placeholder="연락처를 입력하세요"
          />
          <button onClick={handleSave}>확인</button>
        </div>
      ) : (
        <p>연락처: {userInfo.phone || phone}</p>
      )}

<WalletBalance />
    </div>
  );
};
