import React from "react";
import Header from "../../components/global/Header";
import { ProductList } from "../../components/product/ProductList";
import Footer from "../../components/global/Footer";

export const ProductListPage = () => {
  return (
      <div>
        <Header />
        <main>
          <ProductList />
        </main>
        <Footer />
      </div>
  );
};

