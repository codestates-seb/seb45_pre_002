import React, { useEffect, useRef, useState } from "react";
import "./Answer.css";
import { StacksEditor } from "@stackoverflow/stacks-editor";
import "@stackoverflow/stacks-editor/dist/styles.css";
import "@stackoverflow/stacks";
import "@stackoverflow/stacks/dist/css/stacks.css";

function Answer() {

    const editorContainerRef = useRef(null);
    const stacksEditorRef = useRef(null);
    const [postedAnswer, setPostedAnswer] = useState(null);
    const [postTime, setPostTime] = useState(null); // 추가: 시간 상태 추가
    const [member_id, setMemberId] = useState(1); // 예시로 1을 초기값으로 설정하였습니다. 실제로는 사용자 ID나 로그인 정보를 가져와야 합니다.
    const [question_id, setQuestionId] = useState(1); // 현재 질문의 ID를 설정해야 합니다.
    
    

    useEffect(() => {
        if (editorContainerRef.current && !stacksEditorRef.current) {
          stacksEditorRef.current = new StacksEditor(
            editorContainerRef.current,
            "텍스트만 입력해주세요..",
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

  const handlePost = () => {
    const content = stacksEditorRef.current.target.innerText.slice(30);
    console.log(content);

    const currentTime = new Date();
    setPostTime(currentTime);
    
    const requestData = {
      member_id: 7,
      question_id: 7,
      body: content
    }

    fetch('https://80c2-61-101-53-142.ngrok-free.app/answers', {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        return response.json();
    })
    .then(result => {
        if(result.status === 200){
            setPostedAnswer(content);
            window.location.reload();

            // fetchGetAnswers(); 
        } else {
        console.error("Failed to post the answer")}
    })
    .catch(error => {
        console.error("There was a problem with the fetch operation:", error.message);
    })
  }

//   const fetchGetAnswers = () => {
//     fetch('https://80c2-61-101-53-142.ngrok-free.app/answers/2', {
//         method: 'GET',
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     })
//     .then(response => {
//         if (response.status !== 200) {
//             throw new Error("네트워크 응답이 올바르지 않습니다.");
//         }
//         return response.json();
//     })
//     .then(data => {
//         setAnswers(data);
//     })
//     .catch(error => {
//         console.error("답변 리스트를 가져오는데 실패했습니다:", error.message);
//     });
// }

  return (
    <div className="Answer-container">
      <div className="AskQuestionSidebar">{/* Sidebar content */}</div>
      <div className="AskQuestionContent">
        <h1 id="AnswerTitle">Your Answer</h1>
         <div ref={editorContainerRef} className="answer-editor" id="editor-container"></div>
        <button id="APost" onClick={handlePost}>Post Your Answer</button>
      </div>
      <div className="sidebar-right"></div>
    </div>
  );
}

export default Answer

