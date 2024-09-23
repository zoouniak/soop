import { useState, useEffect } from "react";
import  apiClient  from "../util/axios"; // API 클라이언트 설정

const useProduct = (id) => {
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await apiClient.get(`/product/${id}`);
        setProduct(response.data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [id]);

  return { product, loading, error };
};

export default useProduct;
