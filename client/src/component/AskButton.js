import "./AskButton.css";
import { Link } from "react-router-dom";

function AskButton() {
	return (
		<Link to={"/ask"}>
			<button
				id="askBtn"
				type="button">
				Ask Question
			</button>
		</Link>
	);
}

export default AskButton;
