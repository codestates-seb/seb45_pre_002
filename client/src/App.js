import React from "react";
import "./App.css";
import Header from "./component/Header";
import SignupForm from "./page/SignupForm";
import Footer from "./component/Footer";
import SidebarLeft from "./component/SidebarLeft";
import SidebarRight from "./component/SidebarRight";

function App() {
  return (
    <div className="App">
      <Header />
      <div className="content">
        <SidebarLeft />
        <SidebarRight />
        <p>안녕</p>
        <SignupForm />
      </div>
      <Footer />

    </div>
  );
}

export default App;
