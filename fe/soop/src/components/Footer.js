import React from "react";
import "../styles/Footer.css";
import insta from "../assets/insta.png"; // 이미지 파일 경로

const Footer = () => {
  return (
    <footer className="footer">
      <p>
        <a href="https://www.instagram.com/soop.foto">
          <img src={insta} className="footer-sns" />
        </a>
      </p>
      <p>subin@naver.com | 010.9586.9077</p>
    </footer>
  );
};

export default Footer;
