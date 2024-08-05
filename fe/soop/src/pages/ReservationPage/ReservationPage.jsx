import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";

export const ReservationPage = () => {
  const location = useLocation();
  const { productId, timeSlotId } = location.state || {};
  const [productInfo, setProductInfo] = useState(null);
  const [timeSlotInfo, setTimeSlotInfo] = useState(null);
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    // Fetch product and time slot info from the server
    if (productId && timeSlotId) {
      fetch(`/api/product/${productId}`)
        .then(response => response.json())
        .then(data => setProductInfo(data));

      fetch(`/api/timeslot/${timeSlotId}`)
        .then(response => response.json())
        .then(data => setTimeSlotInfo(data));
    }

    // Fetch user info from the server
    fetch("/api/userinfo")
      .then(response => response.json())
      .then(data => setUserInfo(data));
  }, [productId, timeSlotId]);

  if (!productInfo || !timeSlotInfo || !userInfo) {
    return <div>Loading...</div>; // Display loading state while fetching data
  }

  return (
    <div>
      <h1>예약 정보</h1>
      <div>
        <h2>상품 정보</h2>
        <p>{productInfo.name}</p>
        <p>{productInfo.description}</p>
      </div>
      <div>
        <h2>시간대 정보</h2>
        <p>{timeSlotInfo.startTime} - {timeSlotInfo.endTime}</p>
      </div>
      <div>
        <h2>사용자 정보</h2>
        <p>이름: {userInfo.name}</p>
        <p>연락처: {userInfo.contact || "연락처를 입력해주세요."}</p>
        {!userInfo.contact && (
          <form>
            <input type="text" placeholder="연락처 입력" />
            <button type="submit">제출</button>
          </form>
        )}
      </div>
    </div>
  );
};

