import React, { useState } from "react";
import "./VoteComponent.css";

function VoteComponent({ vote }) {
	const [votes, setVotes] = useState(Number(vote) || 0); // 초기값으로 props로 받은 vote를 설정

	const handleVoteUp = () => {
		setVotes(votes + 1); // 상태 업데이트로 투표 증가
	};

	const handleVoteDown = () => {
		setVotes(votes - 1); // 상태 업데이트로 투표 감소
	};

	return (
		<div className="vote-container">
			<i
				className="fa-solid fa-caret-up "
				id="vote-up"
				onClick={handleVoteUp}></i>
			<span id="votes">{votes}</span>
			<i
				className="fa-solid fa-caret-down "
				id="vote-down"
				onClick={handleVoteDown}></i>
		</div>
	);
}

export default VoteComponent;
