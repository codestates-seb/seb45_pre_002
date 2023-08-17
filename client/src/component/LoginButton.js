import { Link } from "react-router-dom";
import "./LoginButton.css";
import { useNavigate } from "react-router-dom";

function LoginButton() {

	const navigate = useNavigate();

	return (
		<button
			id="loginBtn"
			type="button"
			onClick={()=>{navigate('/login')}}>Log in</button>
	);
}

export default LoginButton;
