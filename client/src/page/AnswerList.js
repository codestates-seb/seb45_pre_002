import React, { useEffect, useState } from "react";
import "./AnswerList.css";
import answerDummy from "./AnswerDummy";
import { useDispatch, useSelector } from "react-redux";
import { handleVote } from "../reducers";
import VoteComponent from "../component/VoteComponent";

function AnswerList() {
	const dispatch = useDispatch();
	const voteCounts = useSelector((state) => state.vote.voteCounts);
	const [answers, setAnswers] = useState([]);

	const [commentInput, setCommentInput] = useState("");

	function addComment(answerId) {
		const commentBody = commentInput; // 여기서 댓글 내용을 가져옵니다.
		const commentData = {
			answerId: answerId,
			body: commentBody,
			// 기타 필요한 정보...
		};

		fetch("https://80c2-61-101-53-142.ngrok-free.app/answers", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(commentData),
		})
			.then((response) => {
				if (!response.ok) {
					throw new Error("네트워크 응답이 올바르지 않습니다.");
				}
				return response.json();
			})
			.then((data) => {
				// 댓글 추가 후, 상태 업데이트 또는 UI 갱신
				// 예를 들어, 추가된 댓글을 현재 댓글 리스트에 추가할 수 있습니다.
			})
			.catch((error) => {
				console.error("댓글을 추가하는데 실패했습니다:", error.message);
			});
	}

	// useSelector()

	const [answerPostTime, setAnswerPostTime] = useState(null);

	// 답변을 불러오는 함수
	useEffect(() => {
		fetch("https://80c2-61-101-53-142.ngrok-free.app/answers", {
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				"ngrok-skip-browser-warning": "69420",
			},
		})
			.then((response) => {
				if (response.status !== 200) {
					// <- 이 부분 수정
					throw new Error("네트워크 응답이 올바르지 않습니다.");
				}
				return response.json();
			})
			.then((data) => {
				setAnswers(data.answers);
			})
			.catch((error) => {
				console.error("답변 리스트를 가져오는데 실패했습니다:", error.message);
			});
	}, []);

	const combinedAnswers = [...answers, ...answerDummy];

	const updateVoteCount = (id, value) => {
		dispatch({
			type: "UPDATE_VOTE_COUNT",
			payload: {
				id,
				value,
			},
		});
	};

	return (
		<>
			{combinedAnswers.map((answer) => (
				<div
					className="answerList-container"
					key={answer.question_id}>
					<div className="answer-vote-container">
						<i
							className="fa-solid fa-caret-up "
							id="vote-up"
							onClick={() => updateVoteCount(answer.question_id, 1)}></i>
						<div className="answer-vote-count">
							{voteCounts[answer.question_id] || 0}
						</div>
						<i
							className="fa-solid fa-caret-down "
							id="vote-down"
							onClick={() => updateVoteCount(answer.question_id, -1)}></i>
					</div>
					<div className="answer-content-profile-container">
						<div className="answer-content">{answer.body}</div>
						<button className="answer-share-button">share</button>
						<div className="answer-profile-container-upper">
							<div className="answer-container-blank"></div>
							<div className="answer-profile-container">
								<div className="answer-profile">
									{" "}
									{answerPostTime
										? new Date(answerPostTime).toLocaleString()
										: `${answer.created_at}에 답변됨`}
								</div>
								<div className="answer-pic-container">
									<img
										className="answer-profile-pic"
										src="https://source.unsplash.com/1600x900/"
										alt="Profile"
									/>
									<div className="answer-profile-username">username</div>
								</div>
							</div>
						</div>
						<div className="comments-section">
							{answer.comments &&
								answer.comments.map((comment) => (
									<div
										key={comment.member_id}
										className="comment">
										<span className="comment-author">{comment.username}</span>
										<span className="comment-body">{comment.body}</span>
										<span className="comment-date">{comment.created_at}</span>
									</div>
								))}
							<div className="add-comment">
								<input
									type="text"
									placeholder="댓글을 입력하세요..."
									value={commentInput}
									onChange={(e) => setCommentInput(e.target.value)}
								/>
								<button onClick={() => addComment(answer.member_id)}>
									댓글 달기
								</button>
							</div>
						</div>
					</div>
				</div>
			))}
		</>
	);
}

export default AnswerList;
