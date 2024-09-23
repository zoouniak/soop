import React from "react";
import { BookingCalendar } from "./BookingCalendar";
import { useParams } from "react-router-dom";
import useProduct from "../../hook/useProduct";
import "../../styles/ProductDetail.css";
import image1 from "../../assets/home3.jpg";

export const ProductDetail = () => {
  const { id } = useParams(); // URL 파라미터에서 id를 가져옴.
  const { product, loading, error } = useProduct(id);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;
  if (!product) return <p>No product found</p>;

  const { name, description, price, imageNames } = product;

  return (
    <div>
      <div className="product-detail">
        <div className="detail-area">
          <div className="thumbnail">
            <img src={image1} />
          </div>

          <div className="info-area">
            <div className="info">
              <h2>{name}</h2>
              <p>{description}</p>
              <p> {price} Wei</p>
            </div>

            <BookingCalendar productId={id} />
          </div>
        </div>
      </div>
      <div className="image-gallery">
        {imageNames.map((imageName) => (
          <img
            key={imageName}
            src={`${imageName}`}
            alt={name}
            className="product-image"
          />
        ))}
      </div>
    </div>
  );
};


