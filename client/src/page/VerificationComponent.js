import React, { useState, useEffect } from "react";
import "./VerificationComponent.css";
import { useNavigate } from "react-router-dom";

// test recovery code
const testCode = "111111"; // 메일로 보낸 인증번호

function VerificationNumber(codeNum) {
	fetch("https://test.com/email/auth-code", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			"ngrok-skip-browser-warning": "69420",
		},
		body: JSON.stringify({ code: codeNum }),
	}).then((res) => {
		if (res.status === "200") {
			// 다음페이지로 이동
		}
	});
}

function VerificationComponent() {
	const [verificationCode, setVerificationCode] = useState(Array(6).fill(""));
	const [isValid, setIsValid] = useState(false);
	const [warning, setWarning] = useState(false);
	const initialTime = 90;
	const [time, setTime] = useState(initialTime);
	const navigate = useNavigate();

	// 인증번호 유효시간
	useEffect(() => {
		if (time > 0) {
			const interval = setInterval(() => {
				setTime(time - 1);
			}, 1000);
			return () => clearInterval(interval);
		}
	}, [time]);

	const formattedTime = new Date(time * 1000).toISOString().substring(14, 19);

	// 입력값이 모두 숫자인지 판별하는 함수
	const handleVerificationChange = (index, value) => {
		const newCode = [...verificationCode];
		newCode[index] = value;
		setVerificationCode(newCode);

		//FIXME : 입력값이 숫자인지 아닌지 확인
		const isCodeNumber = newCode.every((item) => isNaN(parseInt(item)));
		if (!isCodeNumber) {
			setIsValid(true);
		} else {
			setIsValid(false);
		}
	};

	// [확인]버튼 클릭시
	const handleSubmit = (event) => {
		event.preventDefault();
		const code = verificationCode.join("");
		if (isValid && testCode === code) {
			// 여기에서 검증 로직을 수행하거나 서버에 인증번호를 보내 검증합니다.
			VerificationNumber(code);
			setWarning(false);

			// 비밀번호 변경페이지로 이동
			navigate("/changepw");
		} else {
			//입력칸을 비우고 경고메세지 표시
			setWarning(true);
			setVerificationCode(Array(6).fill(""));
		}
	};

	// [재발급] 버튼 클릭시
	const handleResendClick = async () => {
		// 여기에 새로운 인증번호를 요청하는 로직을 추가하세요
		await fetch("API요청 URL", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				"ngrok-skip-browser-warning": "69420",
			},
			body: JSON.stringify(),
		});

		// 응답 처리를 필요에 맞게 구현하세요

		// 이전 인증번호를 초기화하고 상태 재설정
		setVerificationCode(Array(6).fill(""));
		setIsValid(false);
		setWarning(false);
		console.log("재발급버튼 클릭");
		// 타이머를 초기값으로 재설정
		setTime(initialTime);
	};

	return (
		<div className="verification-container">
			<form onSubmit={handleSubmit}>
				<span className={time !== 0 ? "timer" : "timer-end"}>{formattedTime}</span>
				<p className={warning ? "warning-message" : ""}>
					{warning
						? "인증번호가 다릅니다."
						: "메일로 전송된 인증번호를 입력해주세요."}
				</p>
				<div className="verification-input--container">
					{verificationCode.map((digit, index) => (
						<input
							className="verification-input"
							key={index}
							type="text"
							maxLength="1"
							value={digit}
							onChange={(e) => handleVerificationChange(index, e.target.value)}
						/>
					))}
				</div>
				<button
					className="verification-button"
					type="button"
					disabled={time !== 0 && !isValid}
					onClick={time === 0 ? handleResendClick : handleSubmit}>
					{time !== 0 ? "확인" : "재발급"}
				</button>
			</form>
		</div>
	);
}

export default VerificationComponent;
