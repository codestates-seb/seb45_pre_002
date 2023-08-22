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

	const handlePostClick = () => {
		// 여기서 유효성 검사 또는 다른 필요한 로직을 수행합니다.
		// 그리고 나서 페이지 이동을 처리합니다.
		// 대상 경로로 이동하도록 수정
		navigate("/login", {
			state: { title, content },
		});
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
/* import React, { useRef, useEffect } from "react";
import { StacksEditor } from "@stackoverflow/stacks-editor";

import "@stackoverflow/stacks-editor/dist/styles.css";
import "@stackoverflow/stacks";
import "@stackoverflow/stacks/dist/css/stacks.css";

function AskQuestion() {
	const editorRef = useRef(null);

	useEffect(() => {
		if (editorRef.current) {
			new StacksEditor(editorRef.current, "", {});
		}
	});

	return (
		<div
			id="editor"
			ref={editorRef}></div>
	);
}

export default AskQuestion; */
