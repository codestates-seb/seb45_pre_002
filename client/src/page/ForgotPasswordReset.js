//로그인 불가 상태에서 비밀번호 생각나지 않을 때: 비밀번호 초기화 셋팅

import React from "react";
import "./ForgotPasswordReset.css";
import { Link } from "react-router-dom";

function ForgotPasswordReset() {
  const handleOkClick = () => {
    alert("비밀번호 변경이 완료되었습니다.");
  };

  return (
    <div className="reset-container">
      <div className="reset-content">
        <input
          type="password"
          className="password-input"
          id="currentPassword"
          placeholder="Current Password"
        />
        <input
          type="password"
          className="password-input"
          id="newPassword"
          placeholder="New Password"
        />
        <input
          type="password"
          className="password-input"
          id="confirmNewPassword"
          placeholder="Confirm New Password"
        />
        <div className="button">
          <button id="okBtn" type="button" onClick={handleOkClick}>
            <Link to="/login">Ok</Link>
          </button>
        </div>
        <div className="cancel-button">
          <button id="cancelBtn" type="button">
            <Link to="/login">Cancel</Link>
          </button>
        </div>
      </div>
    </div>
  );
}

export default ForgotPasswordReset;
