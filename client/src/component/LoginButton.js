import { Link } from "react-router-dom";
import "./LoginButton.css";

function LoginButton() {
	return (
		<button
			id="loginBtn"
			type="button">
			<Link to="/login">Log in</Link>
		</button>
	);
}

export default LoginButton;
