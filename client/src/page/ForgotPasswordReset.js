import React, { useState } from "react";
import "./ForgotPasswordReset.css";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

// 로그인이 안된 상태에서 비밀번호 변경

function ForgotPasswordReset() {
	const [currentPassword, setCurrentPassword] = useState("");
	const [newPassword, setNewPassword] = useState("");
	const [confirmNewPassword, setConfirmNewPassword] = useState("");
	const navigate = useNavigate();
	const handleOkClick = () => {
		if (!currentPassword) {
			alert("현재 비밀번호를 입력하세요.");
		} else if (newPassword && newPassword === confirmNewPassword) {
			alert("비밀번호가 변경되었습니다.");
		} else if (!newPassword || !confirmNewPassword) {
			alert("새 비밀번호를 입력해주세요.");
		} else {
			alert("비밀번호가 일치하지 않습니다.");
		}
	};

	fetch("https://test.com/members/{member-id}");

	return (
		<div className="reset-container">
			<h1>Change Password</h1>
			<div className="reset-content">
				<p className="reset-text">Current Password</p>
				<input
					type="password"
					className="password-input"
					id="currentPassword"
					placeholder="Current Password"
					value={currentPassword}
					onChange={(e) => setCurrentPassword(e.target.value)}
				/>
				<p className="reset-text">New Password</p>
				<input
					type="password"
					className="password-input"
					id="newPassword"
					placeholder="New Password"
					value={newPassword}
					onChange={(e) => setNewPassword(e.target.value)}
				/>
				<p className="reset-text">Confirm New Password</p>
				<input
					type="password"
					className="password-input"
					id="confirmNewPassword"
					placeholder="Confirm New Password"
					value={confirmNewPassword}
					onChange={(e) => setConfirmNewPassword(e.target.value)}
				/>
				<div className="ResetButton">
					<button
						id="okBtn"
						type="button"
						onClick={handleOkClick}>
						Ok
					</button>
				</div>
				<div className="ResetCancelButton">
					<button
						id="ResetCancelBtn"
						type="button"
						onClick={() => {
							navigate("/");
						}}>
						Cancel
					</button>
				</div>
			</div>
		</div>
	);
}

export default ForgotPasswordReset;
