import React, { useState, useEffect } from "react";
import "./App.css";
import Header from "./component/Header";
import SignupForm from "./page/SignupForm";
import Footer from "./component/Footer";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginForm from "./page/LoginForm";
import ForgotPassword from "./page/ForgotPassword";
import Main from "./page/Main";
import VerificationComponent from "./page/VerificationComponent";
import MyInfo from "./page/MyInfo";
import LoginPasswordReset from "./page/LoginPasswordReset";
import Reply from "./page/Reply";
import AskQuestion from "./page/AskQuestion";
import QuestionListPage from "./page/QuestionListPage";
import Question from "./page/Question";
// import SidebarLeft from "./component/SidebarLeft";

function App() {
	// const initialLoginState = localStorage.getItem("loginState") === "true";

	// const [loginState, setLoginState] = useState(initialLoginState);
	const [loginState, setLoginState] = useState(true);

	useEffect(() => {
		localStorage.setItem("loginState", loginState);
	}, [loginState]);

	return (
		<BrowserRouter>
			<div className="App">
				<Header
					loginState={loginState}
					setLoginState={setLoginState}
				/>
				<div className="content">
					<Routes>
						<Route
							path={loginState ? "/question-list" : "/"}
							element={loginState ? <QuestionListPage /> : <Main />}
						/>

						<Route
							path="/login"
							element={
								<LoginForm
									loginState={loginState}
									setLoginState={setLoginState}
								/>
							}
						/>
						<Route
							path="/signup"
							element={<SignupForm />}
						/>
						<Route
							path="/questions/:question_id"
							element={<Question />}
						/>
						<Route
							path="/forgot"
							element={<ForgotPassword />}></Route>
						<Route
							path="/verify"
							element={<VerificationComponent />}></Route>
						<Route
							path="/myinfo"
							element={<MyInfo />}></Route>
						<Route
							path="/changepw"
							element={<LoginPasswordReset />}></Route>
						<Route
							path="/reply"
							element={<Reply />}></Route>
						<Route
							path="/ask"
							element={<AskQuestion />}></Route>
					</Routes>
				</div>
				<Footer />
			</div>
		</BrowserRouter>
	);
}

export default App;
