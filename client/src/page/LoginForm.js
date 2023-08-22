import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./LoginForm.css";

//내일 확인할 것
// Access-Control-Allow-Origin: *

// Access-Control-Allow-Methods: POST, GET, OPTIONS
// Access-Control-Allow-Headers: Content-Type, Authorization
// Access-Control-Allow-Credentials: true

function LoginForm() {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [errorMessage, setErrorMessage] = useState("");
	const [authorization, setAuthorization] = useState("");
	const navigate = useNavigate();
	const headers = new Headers({
		Authorization: authorization,
		"Content-Type": "application/json",
	});

	const handleLogin = () => {
		navigate("/question-list");
		// if (!email) {
		// 	alert("이메일을 입력해주세요.");
		// } else {
		// 	// 실제 서버에서 데이터를 가져오는 로직
		// 	fetch("https://0251-61-101-53-142.ngrok-free.app/members/login", {
		// 		method: "POST", // GET, POST 등 HTTP 메서드 선택
		// 		headers,
		// 		body: JSON.stringify({ email, password }), // 요청 바디에 데이터 전달
		// 	})
		// 		// .then((response) => response.json())

		// 		.then((res) => {
		// 			if (res.status === 200) {
		// 				const authorizationHeader = res.headers.get("Authorization");
		// 				setAuthorization(authorizationHeader);
		// 				const contentTypeHeader = res.headers.get("Content-Type");
		// 				console.log(authorizationHeader, contentTypeHeader);
		// 				//   alert("로그인 성공!");
		// 				// navigate("/question-list");
		// 				// .then((response) => {
		// 				// 	if (response.email && response.password) {
		// 				// 		// 로그인 성공 시 main 페이지로 이동
		// 			} else {
		// 				if (res.status === 401) {
		// 					alert("로그인에 실패했습니다.");
		// 				}
		// 			}
		// 		})
		// 		.catch((error) => {
		// 			console.error("서버와의 통신 중 오류 발생:", error);
		// 			setErrorMessage("서버와 통신 중 오류가 발생했습니다.");
		// 		});
		// }
	};

	const jwtTestfunc = () => {
		fetch("https://0251-61-101-53-142.ngrok-free.app/members/jwtTest", {
			method: "POST",
			headers,
		}).then((res) =>
			res.status === 200 ? console.log("오케이") : console.log("not 오케이")
		);
	};

	return (
		<div className="container">
			<h1>로그인</h1>
			<div className="email-container">
				<span>Email</span>
				<input
					type="email"
					name="email"
					placeholder="이메일을 입력해주세요."
					value={email}
					onChange={(e) => setEmail(e.target.value)}
					// onChange={handleChange}
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
					name="password"
					placeholder="비밀번호를 입력해주세요."
					value={password}
					onChange={(e) => setPassword(e.target.value)}
					// onChange={handleChange}
				/>
			</div>
			<button
				className="login-button"
				onClick={handleLogin}>
				Log in
			</button>
			<button
				onClick={jwtTestfunc}
				className="jwt-button">
				JWT TEST
			</button>
			{/* {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>} */}
		</div>
	);
}

export default LoginForm;
