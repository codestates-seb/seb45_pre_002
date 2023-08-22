import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import AskButton from "../component/AskButton";

import "./Question.css";
import QuestionBody from "./QuestionBody";
import Answer from "./Answer";

const testobj = [
	{
		question_id: 1,
		title:
			"Private Hell 36 Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. ",
		body: "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justoCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.",
		view_count: "9",
		created_at: "2023-07-15 09:49:13",
		modified_at: "2023-01-23T08:03:50Z",
		votes: "34",
		selection: false,
	},
];

function Question() {
	// 현재페이지의 question_id를 가져오는 Hook
	const { question_id } = useParams();

	const [testData, setTestData] = useState({});

	console.log(question_id);

	/* const questionFetch = () => {
		fetch(`https://test.com/questions/${question_id}`, {
			method: "GET", // GET, POST 등 HTTP 메서드 선택
			headers: {
				"Content-Type": "application/json",
				"ngrok-skip-browser-warning": "69420",
			},
		}).then((response) => response.json());
	}; */

	return (
		<div className="question-container">
			<div className="question-container--header">
				<div className="question-title--left">
					<h1 className="question-title">{testobj[0].title}</h1>
					<span>Asked {testobj[0].created_at}</span>
					<span>viewed {testobj[0].view_count}</span>
				</div>
				<div className="question-title--right">
					<AskButton />
				</div>
			</div>
			<div className="question-container--body">
				<QuestionBody
					testobj={testobj}
					question_id={question_id}
				/>
				<Answer />
			</div>
		</div>
	);
}

export default Question;
