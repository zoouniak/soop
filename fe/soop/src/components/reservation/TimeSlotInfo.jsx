import React from "react";

export const TimeSlotInfo = ({ timeSlotInfo }) => {
  if (!timeSlotInfo) return null;

  return (
    <div>
      <h2>예약 시간</h2>
      <p>날짜 : {timeSlotInfo.date}</p>
      <p>시간: {timeSlotInfo.startedAt}</p>
    </div>
  );
};
