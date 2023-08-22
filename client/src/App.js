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
import ForgotPasswordReset from "./page/ForgotPasswordReset";
// import SidebarLeft from "./component/SidebarLeft";

function App() {
	const initialLoginState = localStorage.getItem("loginState") === "true";
	const initialUserName = localStorage.getItem("userName") || "defaultUserName";

	const [loginState, setLoginState] = useState(initialLoginState);
	const [userName, setUserName] = useState(initialUserName);
	console.log(loginState);

	useEffect(() => {
		localStorage.setItem("loginState", loginState);
		localStorage.setItem("userName", userName);
	}, [loginState, userName]);

	return (
		<BrowserRouter>
			<div className="App">
				<Header
					loginState={loginState}
					setLoginState={setLoginState}
					userName={userName}
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
									setUserName={setUserName}
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
							element={
								<MyInfo
									userName={userName}
									setUserName={setUserName}
								/>
							}></Route>
						<Route
							path="/changepw"
							element={
								loginState ? <LoginPasswordReset /> : <ForgotPasswordReset />
							}></Route>
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
