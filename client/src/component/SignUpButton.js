import "./SignUpButton.css";
import { Link } from "react-router-dom";

function SignUpButton({ loginState }) {
	return (
		<button
			id="signup"
			type="button">
			{loginState ? <Link to="/">Log out</Link> : <Link to="/signup">Sign up</Link>}
		</button>
	);
}

export default SignUpButton;
