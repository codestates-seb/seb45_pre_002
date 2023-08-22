import React, { useState, useEffect } from "react";
import "./QuestionListHeader.css";
import AskButton from "./AskButton";

function QuestionListHeader({ questionDataLength }) {
	// FIXME : 질문 갯수
	const [questionAmount, setQuestionAmount] = useState(1);

	useEffect(() => setQuestionAmount(questionDataLength), [questionDataLength]);

	const formatAmount = questionAmount.toLocaleString(); // 자릿수 표시

	return (
		<div className="question-list--header">
			<div className="-list--header-title">
				<h1>All Questions</h1>
				<div>
					<AskButton />
				</div>
			</div>
			<div className="-list--header-sub">
				<div className="sub-left">
					<span>{formatAmount} questions</span>
				</div>
				<div className="sub-right">
					<div className="question-navigation">
						<ul>
							<li>Newest</li>
							<li>Active</li>
							<li>Bountied</li>
							<li>Unanswered</li>
							<li>
								More<i class="fa-solid fa-caret-down filter-icon"></i>
							</li>
						</ul>
					</div>
					<div className="filterBtn">
						<button id="filterBtn">
							<i class="fa-solid fa-filter filter-icon"></i>Filter
						</button>
					</div>
				</div>
			</div>
		</div>
	);
}
export default QuestionListHeader;
