import "./SignUpButton.css";
import { Link } from "react-router-dom";

function SignUpButton({ loginState }) {
    return (
        <Link to={loginState ? "/" : "/signup"}>
            <button
                id="signup"
                type="button">
                {loginState ? "Log out" : "Sign up"}
            </button>
        </Link>
    );
}

export default SignUpButton;

