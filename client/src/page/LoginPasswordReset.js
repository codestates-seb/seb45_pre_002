import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./LoginPasswordReset.css";
import { useNavigate } from "react-router-dom";

// 서버로 변경된 비밀번호 업데이트하는 fetch 함수 추가

function LoginPasswordReset() {
	const [newPassword, setNewPassword] = useState("");
	const [confirmNewPassword, setConfirmNewPassword] = useState("");
	const navigate = useNavigate();

	const handleOkClick = () => {
		if (newPassword && newPassword === confirmNewPassword) {
			alert("비밀번호가 변경되었습니다");
		} else if (!newPassword || !confirmNewPassword) {
			alert("새 비밀번호를 입력해주세요.");
		} else {
			alert("비밀번호가 일치하지 않습니다.");
		}
	};

	return (
		<div className="LoginReset-container">
			<h1 id="changePassword">Change Password</h1>
			<div className="LoginReset-content">
				<p className="LoginReset-text">New Password</p>
				<input
					type="password"
					className="LoginPassword-input"
					id="newPassword"
					placeholder="New Password"
					value={newPassword}
					onChange={(e) => setNewPassword(e.target.value)}
				/>
				<p className="LoginReset-text">Confirm New Password</p>
				<input
					type="password"
					className="LoginPassword-input"
					id="confirmNewPassword"
					placeholder="Confirm New Password"
					value={confirmNewPassword}
					onChange={(e) => setConfirmNewPassword(e.target.value)}
				/>
				<p className="reset-requirements">
					비밀번호 8자리 이상 16자리 이하
					<br />
					영문(대, 소문자 구분), 특수문자,
					<br />
					숫자 중 2가지 이상 조합, 공백 불가
				</p>
				<div className="LoginResetButton">
					<button
						id="LoginResetokBtn"
						type="button"
						onClick={handleOkClick}>
						Ok
					</button>
				</div>
			</div>
		</div>
	);
}

export default LoginPasswordReset;
