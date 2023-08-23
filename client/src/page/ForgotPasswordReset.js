import React, { useState } from "react";
import "./ForgotPasswordReset.css";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

function ForgotPasswordReset() {
    const [currentPassword, setCurrentPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmNewPassword, setConfirmNewPassword] = useState("");
    const [fetchedData, setFetchedData] = useState(null); // Fetch로 가져온 데이터를 저장하는 state
    const navigate = useNavigate();

    const handleOkClick = async () => {
        if (!currentPassword) {
            alert("현재 비밀번호를 입력하세요.");
        } else if (newPassword && newPassword === confirmNewPassword) {
            try {
                // ... 비밀번호 변경 요청을 처리하는 코드
                
                // 비밀번호 변경 성공 시 데이터 가져오기
                const dataResponse = await fetch("https://40ea-61-101-53-142.ngrok-free.app/some-data-endpoint");
                // https://test.com/members/{member-id}
				const fetchedData = await dataResponse.json();
                setFetchedData(fetchedData);

                // 데이터 업데이트 요청 보내기
                const memberId = "123"; // 예시로 임의의 회원 ID를 사용
                const dataUpdateEndpoint = `https://40ea-61-101-53-142.ngrok-free.app/members/${memberId}`;
				// https://test.com/members/{member-id}
                const dataUpdateResponse = await fetch(dataUpdateEndpoint, {
                    method: "PUT", // 이 부분은 실제 서버 요구사항에 맞게 변경해야 합니다.
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(fetchedData) // 업데이트할 데이터를 서버로 보냄
                });

                if (dataUpdateResponse.ok) {
                    alert("비밀번호 변경 및 데이터 업데이트가 완료되었습니다.");
                } else {
                    alert("데이터 업데이트에 실패하였습니다.");
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