import "./QuestionListContents.css";
import { Link } from "react-router-dom";

function QuestionListContents({ questionData }) {
	return (
		<div className="question-list--items">
			{questionData.map((item) => {
				// 질문 내용이 280글자가 넘어가면 나머지 내용은 ...으로 표시하는 함수
				const bodyTextFilter = (text) => {
					const maxLength = 280;
					if (text.length <= maxLength) {
						return text;
					}
					return text.slice(0, maxLength) + "...";
				};

				return (
					<div
						className="-list-items"
						key={item.question_id}>
						<div className="-list-item--left">
							<span
								className={`preview-votes ${
									item.votes === "0" ? "" : "preview-bolder"
								}`}>
								{item.votes} votes
							</span>

							{item.selection ? (
								<span className="preview-answers selection">
									<i class="fa-solid fa-check"></i>answers
								</span>
							) : (
								<span className="preview-answers">answers</span>
							)}

							<span
								className={`preview-views ${
									item.view_count !== "0"
										? "preview-bolder preview-views--orange "
										: ""
								}`}>
								{parseInt(item.view_count, 10)} views
							</span>
						</div>
						<div className="-list-item--right">
							<h3 className="-item--right--title">
								{/* FIXME :  question_id를 가진페이지로 이동해야 한다. 그리고 해당페이지는 제목, 질문 등 정보를 가져야 함.*/}
								<Link to={`/questions/${item.question_id}`}>{item.title}</Link>
							</h3>
							<p className="-item--right--preview">{bodyTextFilter(item.body)}</p>
							<p className="-item--right--username">
								{"userName"} {item.created_at}
							</p>
						</div>
					</div>
				);
			})}
		</div>
	);
}

export default QuestionListContents;

/* 
{
	"question_id": 1,
	"title": "Private Hell 36",
	"body": "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.",
	"view_count": "9",
	"created_at": "2023-07-15 09:49:13",
	"modified_at": "2023-01-23T08:03:50Z",
	"votes": "081",
	"selection": false
}
*/
