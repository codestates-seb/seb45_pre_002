import React, { useState } from "react";
import "./Pagination.css";
import mockData from "../data/mockData.json";

const itemsPerPage = 10;

function Pagination() {
	const [currentPage, setCurrentPage] = useState(1);

	const startIndex = (currentPage - 1) * itemsPerPage;
	const endIndex = startIndex + itemsPerPage;
	const displayedData = mockData.slice(startIndex, endIndex);

	const handlePageChange = (page) => {
		setCurrentPage(page);
	};

	const pageCount = Math.ceil(mockData.length / itemsPerPage);
	const paginationButtons = [];

	for (let i = 1; i <= pageCount; i++) {
		paginationButtons.push(
			<button
				key={i}
				onClick={() => handlePageChange(i)}>
				{i}
			</button>
		);
	}

	return (
		<div className="pagination-container">
			<ul className="data-list">
				{displayedData.map((dataItem) => (
					<li
						key={dataItem.question_id}
						className="data-item">
						{dataItem.title}
					</li>
				))}
			</ul>
			<div className="pagination">{paginationButtons}</div>
		</div>
	);
}

export default Pagination;
