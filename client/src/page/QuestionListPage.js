import React, { useEffect, useState } from "react";
import "./QuestionListPage.css";
// import QuestionListContents from "../component/QuestionListContents";
import QuestionListHeader from "../component/QuestionListHeader";
import SidebarLeft from "../component/SidebarLeft";
import Pagination from "../component/Pagination";


// ... 다른 코드 ...

function QuestionList() {
	const [questionData, setQuestionData] = useState([]);  // 초기값을 배열로 변경
  
	useEffect(() => {
	  fetchQuestions();
	}, []);
  
	function fetchQuestions() {
	  fetch(
		"https://40ea-61-101-53-142.ngrok-free.app/questions?pageNumber=1&pageSize=10",
		{
		  method: "GET",
		  headers: {
			"Content-Type": "application/json",
			"ngrok-skip-browser-warning": "69420",
		  },
		}
	  )
		.then((response) => response.json())
		.then((data) => {
		  setQuestionData(data.questions); // API에서 반환되는 questions만 상태에 저장
		});
	}
  
	return (
	  <div className="question-list--page">
		<SidebarLeft />
		<div className="question-list--container">
		  {/* 주석 해제 후 사용 가능 */}
		  {/* <QuestionListHeader questionDataLength={questionData.length} /> */}
		  {/* <QuestionListContents questionData={questionData} /> */}
		  <Pagination questionData={questionData} />
		</div>
	  </div>
	);
  }
  
  export default QuestionList;
  

// function QuestionList() {
//   const [questionData, setQuestionData] = useState({ questions: [] }); // 초기값 설정

//   // FIXME : 화면이 렌더링되면 미리보기 정보를 가져오게
//   useEffect(() => fetchQuestions(), []);

//   // 질문리스트 불러오는 함수
//   function fetchQuestions() {
//     fetch(
//       "https://40ea-61-101-53-142.ngrok-free.app/questions?pageNumber=1&pageSize=10",
//       {
//         method: "GET",
//         headers: {
//           "Content-Type": "application/json",
//           "ngrok-skip-browser-warning": "69420",
//         },
//       }
//     )
//       .then((response) => response.json())
//       .then((questions) => {
//         setQuestionData({ ...questionData, questions: [...questionData.questions]});
// 		console.log(questionData)
//       });
//   }

//   return (
//     <div className="question-list--page">
//       <SidebarLeft />
//       <div className="question-list--container">
		
//         {/* <QuestionListHeader
//           questionDataLength={questionData.questions.length} /> */}
//         {/* <QuestionListContents questionData={questionData} /> */}
//         {/* <Pagination questionData={questionData.questions} /> */}
//       </div>
//     </div>
//   );
// }

// export default QuestionList;


// import React, { useEffect, useState } from "react";
// import "./QuestionListPage.css";
// // import QuestionListContents from "../component/QuestionListContents";
// import QuestionListHeader from "../component/QuestionListHeader";
// import SidebarLeft from "../component/SidebarLeft";
// import Pagination from "../component/Pagination";

// function QuestionList() {
//   const [questionData, setQuestionData] = useState({});

//   // FIXME : 화면이 렌더링되면 미리보기 정보를 가져오게
//   useEffect(() => fetchQuestions(), [questionData]);

//   // 질문리스트 불러오는 함수
//   function fetchQuestions() {
//     fetch(
//       "https://40ea-61-101-53-142.ngrok-free.app/questions?pageNumber=1&pageSize=10",
//       {
//         method: "GET",
//         headers: {
//           "Content-Type": "application/json",
//           "ngrok-skip-browser-warning": "69420",
//         },
//       }
//     )
//       .then((response) => {
//         response.json();
//       })
//       .then((questions) => {
//         setQuestionData({ ...questionData, questions });
//       });
//   }

//   return (
//     <div className="question-list--page">
//       <SidebarLeft />
//       <div className="question-list--container">
//         <QuestionListHeader
//           questionDataLength={questionData.questions.length}
//         />
//         {/* <QuestionListContents questionData={questionData} /> */}
//         <Pagination questionData={questionData.questions} />
//       </div>
//     </div>
//   );
// }

// export default QuestionList;
