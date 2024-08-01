import React, { useState, useRef, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import "react-calendar/dist/Calendar.css";
import "../../styles/StyledCalendar.css";
import {useTimeSlots} from "../../hook/useTimeSlots";
import {TimeSlot} from "./TimeSlot";
import Calendar from "react-calendar";

export const BookingCalendar = ({productId}) => {
  const today = new Date();
  const dateCache = useRef({}); // 캐시를 위한 ref

  const [selectedDate, setSelectedDate] = useState(null);
  const [selectedTimeSlot, setSelectedTimeSlot] = useState(null);
  const { timeSlots, loading, error } = useTimeSlots(selectedDate, dateCache);

  const navigate = useNavigate();

  const renderTimeSlots = () => {
    if (!selectedDate) return <p>날짜를 선택해주세요.</p>;
    if (loading) return <p>로딩 중</p>;
    if (error) return <p>시간대 로딩에 실패하였습니다. {error.message}</p>;
    if (timeSlots.length === 0)
      return <p>예약 가능한 시간대가 없습니다. 다른 날짜를 선택해주세요</p>;

    return (
      <div>
        {timeSlots.map((slot) => (
          <TimeSlot
            key={slot.id}
            startedAt={slot.startedAt}
            isAvailable={slot.isAvailable}
            isSelected={selectedTimeSlot === slot}
            onClick={() => handleTimeSlotClick(slot)}
          />
        ))}
      </div>
    );
  };

  // 이전 날짜 달력에서 disable
  const disablePastDates = ({ date, view }) => {
    if (view === "month") {
      return date < today;
    }
    return false;
  };

  // 날짜 선택
  const handleDateChange = (date) => {
    setSelectedTimeSlot(null);
    console.log(date)
    setSelectedDate(date);
  };

  // 타임슬롯 선택
  const handleTimeSlotClick = (slot) => {
    if (slot.isAvailable) {
      console.log(slot);
      setSelectedTimeSlot(slot);
    }
  };

  const handleReservation = () => {
    if(!selectedDate){
      alert("날짜를 선택해주세요.");
      return;
    }
    if (!selectedTimeSlot) {
      alert("시간대를 선택해주세요.");
      return;
    }
    console.log(`예약된 시간: ${selectedDate.toISOString().split("T")[0]} ${selectedTimeSlot.id}`);

    // 예약 페이지로 이
     navigate(`/reserve/${productId}/${selectedTimeSlot.id}`);
  };

  return (
    <div className="calendar-wrapper">
      <Calendar tileDisabled={disablePastDates} onChange={handleDateChange} />
      <div className="timeslots-wrapper">{renderTimeSlots()}</div>
      <button className="reserve-button" onClick={handleReservation}>예약하기</button>
    </div>
  );
};


