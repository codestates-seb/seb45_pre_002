import React, { useState } from "react";

const VerificationComponent = () => {
	const [verificationCode, setVerificationCode] = useState(Array(6).fill(""));
	const [isValid, setIsValid] = useState(false);

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
		if (isValid) {
			const code = verificationCode.join("");
			// 여기에서 검증 로직을 수행하거나 서버에 인증번호를 보내 검증합니다.
			console.log("Verification code is valid:", code);
		} else {
			console.log("Invalid verification code");
		}
	};

	return (
		<div>
			<h2>인증번호 검증</h2>
			<form onSubmit={handleSubmit}>
				<div>
					{verificationCode.map((digit, index) => (
						<input
							key={index}
							type="text"
							maxLength="1"
							value={digit}
							onChange={(e) => handleVerificationChange(index, e.target.value)}
						/>
					))}
				</div>
				<button
					type="submit"
					disabled={!isValid}>
					확인
				</button>
			</form>
		</div>
	);
};

export default VerificationComponent;
