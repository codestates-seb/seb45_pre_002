import React, { useState } from "react";
import "./VerificationComponent.css";
import { useNavigate } from "react-router-dom";

// test recovery code
const testCode = "111111"; // 메일로 보낸 인증번호

function VerificationNumber() {
	fetch("API요청 URL", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			"ngrok-skip-browser-warning": "69420",
		},
		body: JSON.stringify(),
	});
}

function VerificationComponent() {
	const [verificationCode, setVerificationCode] = useState(Array(6).fill(""));
	const [isValid, setIsValid] = useState(false);
	const [warning, setWarning] = useState(false);

	const navigate = useNavigate();

	const handleVerificationChange = (index, value) => {
		const newCode = [...verificationCode];
		newCode[index] = value;
		setVerificationCode(newCode);

		//FIXME : 입력값이 숫자인지 아닌지 확인
		if (newCode.every((digit) => /^\d$/.test(digit))) {
			setIsValid(true);
		} else {
			setIsValid(false);
		}
	};

	const handleSubmit = (event) => {
		event.preventDefault();

		const code = verificationCode.join("");
		if (isValid && testCode === code) {
			// 여기에서 검증 로직을 수행하거나 서버에 인증번호를 보내 검증합니다.
			setWarning(false);
			// FIXME : 비밀번호 변경페이지로 경로수정
			navigate("/");
		} else {
			//입력칸을 비우고 경고메세지 표시
			setWarning(true);
			setVerificationCode(Array(6).fill(""));
		}
	};
	return (
		<div className="verification-container">
			<form onSubmit={handleSubmit}>
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
					type="submit"
					disabled={!isValid}>
					확인
				</button>
			</form>
		</div>
	);
}

export default VerificationComponent;