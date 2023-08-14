import "./SignUpButton.css";
import { Link } from "react-router-dom";

function SignUpButton() {
	return (
		<button
			id="signup"
			type="button">
			<Link to="/signup">Sign up</Link>
		</button>
	);
}

export default SignUpButton;
