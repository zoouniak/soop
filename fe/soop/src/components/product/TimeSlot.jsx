import React, { useState } from "react";
import "../../styles/TimeSlot.css";

export const TimeSlot = ({ startedAt, isAvailable, isSelected, onClick }) => {
  return (
    <button
      className={`timeslot ${isAvailable ? "available" : "unavailable"} ${
        isSelected ? "selected" : ""
      }`}
      onClick={onClick}
      disabled={!isAvailable}
    >
      {startedAt}
    </button>
  );
};