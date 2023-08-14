import React from "react";
import "./App.css";
import Header from "./component/Header";
import SignupForm from "./page/SignupForm";
import Footer from "./component/Footer";
// import SidebarLeft from "./component/SidebarLeft";
// import SidebarRight from "./component/SidebarRight";
import { BrowserRouter, Routes, Route} from 'react-router-dom';
import LoginForm from './page/LoginForm';

function App() {
  return (
    <BrowserRouter> {/* Router 컴포넌트를 App 컴포넌트의 최상위 레벨로 이동 */}
    <div className="App">
      <Header />
      <div className="content">
        {/* <SidebarLeft />
        <SidebarRight /> */}
          <Routes>
          <Route path="/login" element={<LoginForm />} />
          <Route path="/signup" element={<SignupForm />} />
          </Routes>
        <Footer />
      </div>
    </div>
  </BrowserRouter>
  );
}

export default App;

