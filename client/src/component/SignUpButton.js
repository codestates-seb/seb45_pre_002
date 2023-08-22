import "./SignUpButton.css";
import { Link } from "react-router-dom";

function SignUpButton({ loginState, setLoginState }) {
	return (
		<Link to={loginState ? "/" : "/signup"}>
			<button
				id="signup"
				type="button"
				onClick={(e) =>
					e.target.innerText === "Log out" ? setLoginState(false) : null
				}>
				{loginState ? "Log out" : "Sign up"}
			</button>
		</Link>
	);
}

export default SignUpButton;
