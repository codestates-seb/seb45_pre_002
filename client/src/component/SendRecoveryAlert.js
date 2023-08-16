import "./SendRecoveryAlert.css";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function SendRecoveryAlert({ recoveryEmail, setSuccessMailAlert }) {
	const [timeLeft, setTimeLeft] = useState(2); // 인증메일 발송 알림 타이머
	const navigate = useNavigate();

	useEffect(() => {
		// 타이머 업데이트 함수
		const updateTimer = () => {
			if (timeLeft > 0) {
				setTimeLeft(timeLeft - 1);
			} else if (timeLeft === 0) {
				setSuccessMailAlert(false);
				navigate("/verify"); // 인증번호 입력창으로 이동
			}
		};

		// 1초마다 타이머 업데이트
		const timerInterval = setInterval(updateTimer, 1000);

		// 컴포넌트 언마운트 시 타이머 클리어
		return () => {
			clearInterval(timerInterval);
		};
	}, [timeLeft, navigate]);

	return (
		<div className="recovery-email-alert">
			<p className="alert-description">
				<i class="fa-regular fa-circle-check"></i>
				{recoveryEmail}로 복구 메일이 성공적으로 전송되었습니다.
				<br />
				<span>이 메세지는 {timeLeft}초 후에 닫힙니다.</span>
			</p>
		</div>
	);
}
export default SendRecoveryAlert;
