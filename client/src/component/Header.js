import React from 'react';
import './Header.css'; // 헤더 스타일링을 위한 CSS 파일

function Header() {
  return (
    <header className="header">
      <h1>홈페이지</h1>
      <nav>
        <ul className="nav-list">
          <li><a href="home">홈</a></li>
          <li><a href="home">게시판</a></li>
          <li><a href="home">로그인</a></li>
        </ul>
      </nav>
    </header>
  );
}

export default Header;
