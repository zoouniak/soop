import React from "react";

export const ProductInfo = ({ productInfo }) => {
  if (!productInfo) return null;

  return (
    <div>
      <h2>상품 정보</h2>
      <p>상품 이름: {productInfo.name}</p>
      <p>상품 가격: {productInfo.price}</p>
    </div>
  );
};
