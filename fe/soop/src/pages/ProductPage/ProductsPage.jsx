import React from "react";
import Header from "../../components/global/Header";
import { ProductList } from "../../components/product/ProductList";
import Footer from "../../components/global/Footer";

const ProductPage = () => {
  return (
    <div>
      <div>
        <Header />
        <main>
          <ProductList />
        </main>
        <Footer />
      </div>
    </div>
  );
};
export default ProductPage;
