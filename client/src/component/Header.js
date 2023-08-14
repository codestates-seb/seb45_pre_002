import React from "react";
import "./Header.css"; // 헤더 스타일링을 위한 CSS 파일
import SignUpButton from "./SignUpButton";
import LoginButton from "./LoginButton";

function Header() {
  return (
    <div className="header-container">
      <header className="header">
        <div className="header-questionBtn">
          <span>Question</span>
        </div>
        <div className="header-logo">
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/02/Stack_Overflow_logo.svg/1600px-Stack_Overflow_logo.svg.png"
            alt="logo"
          />
        </div>
        <div className="header-nav">
          <ul>
            <li>
              <span>About</span>
            </li>
            <li>
              <span>Products</span>
            </li>
            <li>
              <span>For Team</span>
            </li>
          </ul>
        </div>
        <div className="header-search-form">
          <span>
            <i className="fa-solid fa-magnifying-glass"></i>
          </span>
          <input className="search" type="text" placeholder="Search..." />
        </div>
        <div className="header-login-signup">
          <LoginButton />
          <SignUpButton />
        </div>
      </header>
    </div>
  );
}

export default Header;
