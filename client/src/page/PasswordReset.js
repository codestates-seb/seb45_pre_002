import React from "react";
import "./PasswordReset.css";
import { Link } from "react-router-dom";

function PasswordReset() {
  return (
    <div className="container">
      <div className="content">
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
          <button id="okBtn" type="button">
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

export default PasswordReset;