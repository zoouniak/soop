import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/HomePage";
import { KakaoRedirection } from "./components/global/KakaoRedirection";
import { GoogleRedirection } from "./components/global/GoogleRedirection";
import { ProductListPage } from "./pages/ProductPage/ProductListPage";
import ProductDetailPage from "./pages/ProductPage/ProductDetailPage";
import { ReservationPage } from "./pages/ReservationPage/ReservationPage";
import { ErrorPage } from "./pages/global/ErrorPage";
import { MyPage } from "./pages/MyPage/MyPage";
import "./App.css";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/error" element={<ErrorPage />} />
        <Route path="/products" element={<ProductListPage />} />
        <Route path="/product/:id" element={<ProductDetailPage />} />
        <Route path="/kakao/redirect" element={<KakaoRedirection />} />
        <Route path="/google/redirect" element={<GoogleRedirection />} />
        <Route path="/reserve" element={<ReservationPage />} />
        <Route path="/mypage" element={<MyPage />} />
      </Routes>
    </Router>
  );
}

export default App;
