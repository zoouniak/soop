// src/hooks/useProducts.js
import { useState, useEffect } from "react";
import  apiClient  from "../util/axios";

const useProducts = (currentPage) => {
  const [products, setProducts] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchProducts = async () => {
    try {
      setLoading(true);
      const response = await apiClient.get(`/products?page=${currentPage}`);
      setProducts(response.data.productList || []);
      setTotalPages(response.data.totalPage);
    } catch (error) {
      setError(error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, [currentPage]);

  return { products, totalPages, loading, error };
};

export default useProducts;