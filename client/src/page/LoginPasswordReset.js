import React, { useState, useEffect } from "react";
import "./LoginPasswordReset.css";
import { useNavigate } from "react-router-dom";

function LoginPasswordReset() {
	const [newPassword, setNewPassword] = useState("");
	const [confirmNewPassword, setConfirmNewPassword] = useState("");
	const [memberId, setMemberId] = useState(""); // 사용자 ID를 저장하는 state
	const navigate = useNavigate();

	// 사용자 ID를 얻어오는 함수
	const fetchMemberId = async () => {
		try {
			const response = await fetch("https://40ea-61-101-53-142.ngrok-free.app/get-member-id-endpoint");
			const data = await response.json();
			setMemberId(data.memberId);
		} catch (error) {
			console.error("Error fetching member ID:", error);
		}
	};

	useEffect(() => {
		fetchMemberId(); // 컴포넌트가 마운트되었을 때 사용자 ID를 가져옴
	}, []);

	const handleOkClick = async () => {
		if (newPassword && newPassword === confirmNewPassword) {
			try {
				const endpoint = `https://40ea-61-101-53-142.ngrok-free.app/members/${memberId}/change-password`;
				const response = await fetch(endpoint, {
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					body: JSON.stringify({
						newPassword: newPassword
					})
				});
	
				if (response.ok) {
					// Success: Password updated on the server
					alert("비밀번호가 변경되었습니다");
				} else {
					// Handle error cases here
					alert("비밀번호 변경에 실패하였습니다.");
				}
			} catch (error) {
				console.error("Error updating password:", error);
			}
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

export default LoginPasswordReset