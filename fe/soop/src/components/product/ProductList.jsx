import React, { useState, useEffect, useCallback } from "react";
import useProducts from "../../hook/useProducts";
import {ProductThumbnail} from "./ProductThumbnail";
import "../../styles/ProductList.css";

export const ProductList = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const { products, totalPages, loading, error } = useProducts(currentPage);

  const handlePreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage((prevPage) => prevPage - 1);
    }
  };

  const handleNextPage = () => {
    if (currentPage < totalPages) {
      setCurrentPage((prevPage) => prevPage + 1);
    }
  };

  const generatePageNumbers = () => {
    const pages = [];
    for (let i = 1; i <= totalPages; i++) {
      pages.push(i);
    }
    return pages;
  };

  if (loading) {
    return <p>Loading products...</p>;
  }

  if (error) {
    return <p>Error loading products: {error.message}</p>;
  }

  return (
    <div id="product-container">
      <div id="product-wrapper">
      {products.length > 0 ? (
        products.map((product) => (
          <ProductThumbnail key={product.id} product={product} />
        ))
      ) : (
        <p>상품이 존재하지 않습니다.</p>
      )}
      </div>

      <div className="pagination">
        <span
          onClick={handlePreviousPage}
          className={currentPage === 1 ? "disabled" : ""}
        >
          &lt;&lt;
        </span>
      {generatePageNumbers().map((page) => (
        <span
        key={page}
        onClick={() => setCurrentPage(page)}
        className={page === currentPage ? "active" : ""}
        >
        {page}
        </span>
      ))}
    <span
    onClick={handleNextPage}
    className={currentPage === totalPages ? "disabled" : ""}
    >
      &gt;&gt;
    </span>
    </div>
  </div>
  );
};

