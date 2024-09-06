import React from "react";

export const TimeSlotInfo = ({ timeSlotInfo }) => {
  if (!timeSlotInfo) return null;

  return (
    <div>
      <h2>타임슬롯 정보</h2>
      <p>날짜 : {timeSlotInfo.date}</p>
      <p>시간: {timeSlotInfo.startedAt}</p>
    </div>
  );
};
