import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/HomePage";
import Redirection from "./components/Redirection";
import "./App.css";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />

        <Route
          path="/kakao/redirect"
          element={
            <Redirection text={"로그인 진행중입니다. 잠시만 기다려주세요"} />
          }
        ></Route>
      </Routes>
    </Router>
  );
}

export default App;
