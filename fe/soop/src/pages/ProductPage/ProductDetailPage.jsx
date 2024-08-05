import React from "react";
import Header from "../../components/global/Header";
import { ProductDetail } from "../../components/product/ProductDetail";
import Footer from "../../components/global/Footer";

const ProductPage = () => {
  return (
    <div>
      <div>
        <Header />
        <main>
          <ProductDetail />
        </main>
        <Footer />
      </div>
    </div>
  );
};
export default ProductPage;
