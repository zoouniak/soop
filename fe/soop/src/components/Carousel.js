import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import image1 from "../assets/home1.jpg"; // 이미지 파일 경로
import image2 from "../assets/home2.jpg"; // 이미지 파일 경로
import image3 from "../assets/home3.jpg"; // 이미지 파일 경로]
import image4 from "../assets/home4.jpg"; // 이미지 파일 경로
import image5 from "../assets/home5.jpg"; // 이미지 파일 경로
import image6 from "../assets/home6.jpg"; // 이미지 파일 경로
import "../styles/Carousel.css";

const images = [image1, image2, image3, image4, image5, image6];

const Carousel = () => {
  const settings = {
    infinite: true,
    speed: 2500,
    slidesToShow: 3,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    draggable: true,
    pauseOnHover: false,
    adaptiveHeight: false,
  };

  return (
    <div className="carousel-container">
      <Slider {...settings}>
        {images.map((image, idx) => (
          <div key={idx}>
            <img
              src={image}
              alt={`Carousel Image ${idx}`}
              className="carousel-image"
            />
          </div>
        ))}
      </Slider>
    </div>
  );
};

export default Carousel;
