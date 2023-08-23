import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import "./AskQuestion.css";
import { StacksEditor } from "@stackoverflow/stacks-editor";
import "@stackoverflow/stacks-editor/dist/styles.css";
import "@stackoverflow/stacks";
import "@stackoverflow/stacks/dist/css/stacks.css";

function AskQuestion() {
	const [title, setTitle] = useState("");
	const [content, setContent] = useState("");
	const [isValid, setIsValid] = useState(false);
	const editorContainerRef = useRef(null);
	const stacksEditorRef = useRef(null);
	const navigate = useNavigate();

	useEffect(() => {
		if (editorContainerRef.current && !stacksEditorRef.current) {
			stacksEditorRef.current = new StacksEditor(
				editorContainerRef.current,
				"Text only",
				{}
			);

			return () => {
				if (stacksEditorRef.current) {
					stacksEditorRef.current.destroy();
					stacksEditorRef.current = null;
				}
			};
		}
	}, []);

	useEffect(() => {
		setIsValid(title.trim().length > 0 && content.trim().length > 0);
	}, [title, content]);

	const handlePostClick = async () => {
		if (isValid) {
			try {
				const response = await fetch("https://40ea-61-101-53-142.ngrok-free.app/questions", {
					method: "POST",
					headers: {
						"Content-Type": "application/json"
					},
					body: JSON.stringify({
						title: title,
						content: content
					})
				});
	
				if (response.ok) {
					// Success: Data posted to the server
					alert("질문이 성공적으로 등록되었습니다.");
					navigate("/login"); // 페이지 이동
				} else {
					// Handle error cases here
					alert("질문 등록에 실패하였습니다.");
				}
			} catch (error) {
				console.error("Error posting question:", error);
			}
		} else {
			alert("제목과 내용을 모두 입력하세요.");
		}
	};

	return (
		<div className="AskQuestion-container">
			<div className="AskQuestionSidebar">{/* Sidebar content */}</div>
			<div className="AskQuestionContent">
				<h1 id="AskTitle">Ask a public question</h1>
				<div className="AskQuestionInstructions">
					<h2>Writing a good question</h2>
					<p>
						You’re ready to ask a programming-related question and this form will
						help guide you through the process.
					</p>{" "}
					<p>
						Looking to ask a non-programming question? See the topics here to find a
						relevant site.
					</p>
					<h3>Steps</h3>
					<ol>
						<li>Summarize your problem in a one-line title.</li>
						<li>Describe your problem in more detail.</li>
						<li>Describe what you tried and what you expected to happen.</li>
						<li>Review your question and post it to the site.</li>
					</ol>
				</div>
				<div className="QuestionTitle">
					<form className="d-flex gy4 fd-column">
						<div className="flex--item">
							<label
								className="d-block s-label"
								htmlFor="example-item">
								Title
								<p className="s-description mt2">
									Be specific and imagine you’re asking a question to another person.
								</p>
							</label>
						</div>
						<div className="questionTitle">
							<input
								className="s-input"
								id="example-item"
								type="text"
								placeholder="e.g. is there an R function for finding the index of an element in a vector?"
								value={title}
								onChange={(e) => setTitle(e.target.value)}
							/>
						</div>
					</form>
				</div>
				<div
					ref={editorContainerRef}
					id="editor-container"></div>
				<button
					id="QPost"
					onClick={handlePostClick}
					disabled={!isValid}>
					Post
				</button>
			</div>
			<div className="sidebar-right"></div>
		</div>
	);
}

export default AskQuestion;