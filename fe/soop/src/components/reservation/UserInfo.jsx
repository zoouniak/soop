import React, { useState } from "react";
import  apiClient  from "../../util/axios";

export const UserInfo = ({ userInfo }) => {
  // 연락처 입력 상태 관리
  const [phone, setPhone] = useState(userInfo?.phone || "");
  const [isEditing, setIsEditing] = useState(!userInfo?.phone);

  const handleInputChange = (e) => {
    setPhone(e.target.value);
  };

  const handleSave = async () => {
    try {
      // 서버로 연락처 저장 요청
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
    </div>
  );
};
