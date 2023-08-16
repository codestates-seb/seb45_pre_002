import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./LoginForm.css";

function LoginForm() {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [errorMessage, setErrorMessage] = useState("");
	const navigate = useNavigate();

	const handleLogin = () => {
		if (!email) {
			alert("이메일을 입력해주세요.");
		} else {
			// 실제 서버에서 데이터를 가져오는 로직
			fetch("실제_서버_엔드포인트", {
				method: "POST", // GET, POST 등 HTTP 메서드 선택
				headers: {
					"Content-Type": "application/json", // 요청 헤더 설정
				},
				body: JSON.stringify({ email, password }), // 요청 바디에 데이터 전달
			})
				.then((response) => response.json())
				.then((data) => {
					if (data.success) {
						// 로그인 성공 시 main 페이지로 이동
						navigate.push("/");
					} else {
						if (data.errorCode === "email") {
							setErrorMessage("유효하지 않은 이메일입니다.");
						} else if (data.errorCode === "password") {
							setErrorMessage("비밀번호가 일치하지 않습니다.");
						} else {
							setErrorMessage("로그인에 실패했습니다.");
						}
					}
				})
				.catch((error) => {
					console.error("서버와의 통신 중 오류 발생:", error);
					setErrorMessage("서버와 통신 중 오류가 발생했습니다.");
				});
		}
	};

	return (
		<div className="container">
			<h1>로그인</h1>
			<div className="email-container">
				<span>Email</span>
				<input
					type="email"
					placeholder="이메일을 입력해주세요."
					value={email}
					onChange={(e) => setEmail(e.target.value)}
				/>
			</div>
			<div className="password-container">
				<div className="password-text">
					<span className="password">Password</span>
					<span
						className="forgot"
						onClick={() => {
							navigate("/forgot");
						}}>
						Forgot password?
					</span>
				</div>
				<input
					type="password"
					placeholder="비밀번호를 입력해주세요."
					value={password}
					onChange={(e) => setPassword(e.target.value)}
				/>
			</div>
			<button
				className="login-button"
				onClick={handleLogin}>
				Log in
			</button>
			{errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
		</div>
	);
}

export default LoginForm;
