import React from "react";
import { useNavigate } from "react-router-dom";
import image1 from "../../assets/home1.jpg";

export const ProductThumbnail = ({ product }) => {
  const navigate = useNavigate();

  const handleProductClick = () => {
    navigate(`/product/${product.id}`);
  };
  return (
    <div className="product" onClick={handleProductClick}>
      <img src={image1} alt={product.name} />
      <h2>{product.name}</h2>
      <p>{product.description}</p>
      <p>{product.price}</p>
    </div>
  );
};

