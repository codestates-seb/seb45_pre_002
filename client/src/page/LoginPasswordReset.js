//로그인이 되어있는 상태: 사용자가 비밀번호 변경을 원하는 셋팅

import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./LoginPasswordReset.css";

function LoginPasswordReset() {
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");
  const [message, setMessage] = useState(""); // 새로운 상태 추가

  const handleOkClick = () => {
    if ( newPassword && (newPassword === confirmNewPassword) ) {
      alert("비밀번호 변경이 완료되었습니다.");
      // 여기에서 비밀번호 변경 로직을 처리하거나 페이지를 이동시킬 수 있습니다.
    } 
    if (!newPassword || !confirmNewPassword) {
        alert("새 비밀번호를 입력해주세요")
    }
    else {
      alert("비밀번호가 일치하지 않습니다");
    }
  };

  return (
    <div className="reset-container">
      <div className="reset-content">
        <p className="reset-text">비밀번호 변경</p>
        <input
          type="password"
          className="password-input"
          id="newPassword"
          placeholder="New Password"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
        />
        <input
          type="password"
          className="password-input"
          id="confirmNewPassword"
          placeholder="Confirm New Password"
          value={confirmNewPassword}
          onChange={(e) => setConfirmNewPassword(e.target.value)}
        />
        <p className="reset-requirements">
          비밀번호 8자리 이상 16자리 이하 & 영문(대, 소문자 구분), 특수문자,
          숫자 중 2가지 이상 조합, 공백 불가
        </p>
        <div className="button">
          <button id="okBtn" type="button" onClick={handleOkClick}>
            <Link to="/login">Ok</Link>
          </button>
        </div>
        <p className="message">{message}</p> {/* 메시지 출력 */}
      </div>
    </div>
  );
}

export default LoginPasswordReset;
