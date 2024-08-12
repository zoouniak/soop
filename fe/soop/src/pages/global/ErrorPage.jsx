import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import '../../styles/InfoPage.css';

export const ErrorPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { message } = location.state || { message: "Something went wrong." };

  return (
    <div className="centered-container">
      <div className="centered-text">
        <p>{message}</p>
        <button onClick={() => navigate("/")}>홈 화면으로</button>
      </div>
    </div>
  );
};
