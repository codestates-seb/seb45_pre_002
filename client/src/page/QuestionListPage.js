import React, { useEffect, useState } from "react";
import "./QuestionListPage.css";
import QuestionListContents from "../component/QuestionListContents";
import QuestionListHeader from "../component/QuestionListHeader";
import SidebarLeft from "../component/SidebarLeft";

// FIXME
import mockData from "../data/mockData.json";

function QuestionList() {
	// FIXME
	const [questionData, setQuestionData] = useState(mockData);

	// FIXME : 화면이 렌더링되면 미리보기 정보를 가져오게
	// useEffect(() => fetchQuestions(), []);

	// 질문리스트 불러오는 함수
	function fetchQuestions() {
		fetch("https://test.com/questions", {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
			},
		})
			.then((response) => {
				response.json();
			})
			.then((data) => {
				setQuestionData({ ...questionData /* FIXME : 서버에서 받아온 정보*/ });
			});
	}

	return (
		<div className="question-list--page">
			<SidebarLeft />
			<div className="question-list--container">
				<QuestionListHeader questionDataLength={questionData.length} />
				<QuestionListContents questionData={questionData} />
			</div>
		</div>
	);
}

export default QuestionList;

/* 
{
  "question_id": 1,
  "title": "질문 제목",
  "body": "질문 내용",
  "view_count": 0,
  "created_at": "yyyy-MM-dd HH:mm:ss",
  "modified_at": "yyyy-MM-dd HH:mm:ss"
} */
