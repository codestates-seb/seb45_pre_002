import "./ForgotPassword.css";
import React, { useState } from "react";
import SendRecoveryAlert from "../component/SendRecoveryAlert";

function ForgotPassword() {
	const [recoveryEmail, setRecoveryEmail] = useState("");
	const [successMailAlert, setSuccessMailAlert] = useState(false);

	function sendRecoveryEmail() {
		setSuccessMailAlert(true);

		fetch("https://test.com/email", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				"ngrok-skip-browser-warning": "69420",
			},
			body: JSON.stringify({ email: recoveryEmail }),
		}).then((res) => {
			if (res.status === "200") {
				setSuccessMailAlert(true);
			}
		});
	}

	return (
		<div className="container">
			{successMailAlert ? (
				<SendRecoveryAlert
					recoveryEmail={recoveryEmail}
					setSuccessMailAlert={setSuccessMailAlert}
				/>
			) : (
				<>
					<div className="forgot-description">
						<p>
							비밀번호를 잊으셨나요?
							<br />
							이메일을 보내주시면 복구메일을 보내드리겠습니다.
						</p>
					</div>
					<div className="email-container">
						<span>Email</span>
						<input
							type="email"
							name="recovery-email"
							value={recoveryEmail}
							placeholder="stack@overflow.com"
							onChange={(e) => setRecoveryEmail(e.target.value)}
						/>
					</div>
					<button
						className="send-email-button"
						onClick={() => sendRecoveryEmail()}>
						Send recovery email
					</button>
				</>
			)}
		</div>
	);
}
export default ForgotPassword;
