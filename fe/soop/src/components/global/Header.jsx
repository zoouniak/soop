import React, { useState } from "react";
import { Cookies } from "react-cookie";
import { useNavigate } from "react-router-dom";
import "../../styles/Header.css";
import LoginModal from "./LoginModal";
import logo from "../../assets/logo.jpg"; // Soop 로고 이미지 파일 경로

const Header = () => {
  const navigate = useNavigate();
  const cookie = new Cookies();
  const nav = useNavigate();
  const isLogin = localStorage.getItem("login");

  // 모달 상태 관리
  const [isModalOpen, setIsModalOpen] = useState(false);
  
  const products=() =>{
    nav("/products")
  }

  // 로그아웃 실행 함수
  const logout = () => {
    localStorage.clear();
    nav("/");
  };

  const myPage = () =>{
    nav("/mypage")
  }

  // 로그인 클릭 핸들러
  const onLoginClick = () => {
    setIsModalOpen(true);
  };

  // 모달 닫기 핸들러
  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  return (
    <header className="header">
       <img
        src={logo}
        alt="Soop Logo"
        className="logo"
        onClick={() => navigate("/")}
       
      />
      <nav className="nav">
        <a className="nav-item" onClick={products}>
          촬영예약
        </a>
        {isLogin ? (
         <>
         <a className="nav-item" onClick={myPage}>
           마이페이지
         </a>
          <a className="nav-item" onClick={logout}>
          로그아웃
        </a>
        </>
        ) : (
          <a className="nav-item" onClick={onLoginClick}>
            로그인
          </a>
        )}
      </nav>
      {isModalOpen && <LoginModal onClose={handleCloseModal} />}
    </header>
  );
};

export default Header;

