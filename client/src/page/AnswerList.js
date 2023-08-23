import React, { useEffect, useState } from "react";
import "./AnswerList.css";
import answerDummy from "./AnswerDummy";
import { useDispatch, useSelector } from "react-redux";
import { handleVote } from "../reducers";
import VoteComponent from "../component/VoteComponent";

function AnswerList({currentMemberId}) {
  const dispatch = useDispatch();
  const voteCounts = useSelector((state) => state.vote.voteCounts);
  const [answers, setAnswers] = useState([]);

  const [commentInput, setCommentInput] = useState({});

  // 상태를 추가하여 수정 중인 답변의 ID와 수정할 내용을 관리합니다.
  const [editingAnswerId, setEditingAnswerId] = useState(null);
  const [editingAnswerContent, setEditingAnswerContent] = useState("");

  // 답변 수정 버튼 클릭 시 호출되는 함수입니다.
  const startEditing = (answerId, initialContent) => {
    setEditingAnswerId(answerId);
    setEditingAnswerContent(initialContent);
  };

  // 수정 완료 버튼 클릭 시 호출되는 함수입니다.
  const finishEditing = (answerId) => {
    // 수정된 내용을 서버에 반영하는 로직을 추가합니다.
    const answer = answers.find((a) => a.answerId === answerId);
    const memberId = answer ? answer.memberId : null;
    updateAnswer(memberId, answerId, editingAnswerContent);
    updateAnswer(answerId, editingAnswerContent);
    setEditingAnswerId(null);
    setEditingAnswerContent("");
  };

  function updateAnswer(memberId, answerId, newBody) {
    const updateData = {
      member_id: memberId,
      body: newBody,
    };

    fetch(`https://40ea-61-101-53-142.ngrok-free.app/answers/${answerId}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updateData),
    })
      .then((res) => {
        if (!(res.status === 200)) {
          throw new Error("네트워크 응답이 올바르지 않습니다.");
        }
        return res.json();
      })
      .then((data) => {
        setAnswers((prevAnswers) =>
          prevAnswers.map((answer) =>
            answer.member_id === memberId
              ? { ...answer, body: data.body }
              : answer
          )
        );
      })
      .catch((error) => {
        console.error("답변수정에 실패하였습니다:", error.message);
      });
  }

  function deleteAnswer(answerId) {
    fetch(`https://40ea-61-101-53-142.ngrok-free.app/answers/${answerId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => {
        if (!(res.status === 200)) {
          throw new Error("네트워크 응답이 올바르지 않습니다.");
        }
        return res.json();
      })
      .then(() => {
        setAnswers((prevAnswers) =>
          prevAnswers.filter((answer) => answer.question_id !== answerId)
        );
      })
      .catch((error) => {
        console.error("답변 삭제에 실패했습니다:", error.message);
      });
  }

  function editComment(answerId, commentId, updatedBody) {
    const updateData = {
      body: updatedBody,
    };

    fetch(
      `https://40ea-61-101-53-142.ngrok-free.app/answer-comments/${commentId}`,
      {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updateData),
      }
    )
      .then((res) => {
        if (!(res.status === 200)) {
          throw new Error("네트워크 응답이 올바르지 않습니다.");
        }
        return res.json();
      })
      .then((data) => {
        setAnswers((prevAnswers) =>
          prevAnswers.map((answer) =>
            answer.question_id === answerId
              ? {
                  ...answer,
                  comments: answer.comments.map((comment) =>
                    comment.comment_id === commentId
                      ? {
                          ...comment,
                          body: data.body,
                          updated_at: data.updated_at,
                        }
                      : comment
                  ),
                }
              : answer
          )
        );
      })
      .catch((error) => {
        console.error("댓글 수정에 실패하였습니다:", error.message);
      });
  }

  function deleteComment(answerId, memberId, commentId) {
    const deleteData = {
      member_id: memberId,
      answer_comment_id: commentId,
    };

    fetch(
      `https://40ea-61-101-53-142.ngrok-free.app/answer-comments`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(deleteData),
      }
    )
      .then((res) => {
        if (!(res.status === 200)) {
          throw new Error("네트워크 응답이 올바르지 않습니다.");
        }
        return res.json();
      })
      .then(() => {
        setAnswers((prevAnswers) =>
          prevAnswers.map((answer) =>
            answer.question_id === answerId
              ? {
                  ...answer,
                  comments: answer.comments.filter(
                    (comment) => comment.comment_id !== commentId
                  ),
                }
              : answer
          )
        );
      })
      .catch((error) => {
        console.error("댓글 삭제에 실패했습니다:", error.message);
      });
  }

  function addComment(answerId, memberId) {
    const commentBody = commentInput[answerId]; // 여기서 댓글 내용을 가져옵니다.
    const commentData = {
    //   question_id: answerId,
    //   member_id: memberId,
      body: commentBody,
      // 기타 필요한 정보...
    };

    fetch("https://40ea-61-101-53-142.ngrok-free.app/answer-comments", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(commentData),
    })
      .then((response) => {
        if (!(response.status === 200)) {
          throw new Error("네트워크 응답이 올바르지 않습니다.");
        }
        return response.json();
      })
      .then((data) => {
        setAnswers((prevAnswers) =>
          prevAnswers.map((answer) =>
            answer.question_id === answerId
              ? { ...answer, comments: [...answer.comments, data] } // data는 서버에서 반환된 새로운 댓글의 정보라고 가정합니다.
              : answer
          )
        );
        // 댓글 추가 후, 상태 업데이트 또는 UI 갱신
        // 예를 들어, 추가된 댓글을 현재 댓글 리스트에 추가할 수 있습니다.
        setCommentInput((prevCommentInput) => {
          let updatedCommentInput = { ...prevCommentInput };
          delete updatedCommentInput[answerId];
          return updatedCommentInput;
        });
      })
      .catch((error) => {
        console.error("댓글을 추가하는데 실패했습니다:", error.message);
      });
  }

  // useSelector()

  const [answerPostTime, setAnswerPostTime] = useState(null);

  // 답변을 불러오는 함수
  useEffect(() => {
    fetch("https://40ea-61-101-53-142.ngrok-free.app/answers", {
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
    // 서버에 투표 상태 업데이트 요청
    fetch(`https://40ea-61-101-53-142.ngrok-free.app/vote/${id}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ value }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("네트워크 응답이 올바르지 않습니다.");
        }
        return response.json();
      })
      .then((data) => {
        if (data.success) {
          console.log("투표 상태가 서버에 성공적으로 업데이트되었습니다.");
        } else {
          console.error(
            "서버에서 투표 상태 업데이트를 처리하는 중 문제가 발생했습니다:",
            data.message || "알 수 없는 오류"
          );
        }
        // 필요한 경우 서버로부터의 응답을 처리
      })
      .catch((error) => {
        console.error("투표 업데이트에 실패했습니다:", error.message);
      });
  };

  const [isPopupOpen, setIsPopupOpen] = useState(false);

  const openPopup = () => {
    setIsPopupOpen(true);
  };

  // 팝업창을 닫기 위한 함수
  const closePopup = () => {
    setIsPopupOpen(false);
  };

  return (
    <>
      {combinedAnswers.map((answer) => (
        <div className="answerList-container" key={answer.question_id}>
          <div className="answer-vote-container">
            <i
              className="fa-solid fa-caret-up "
              id="vote-up"
              onClick={() => updateVoteCount(answer.question_id, 1)}
            ></i>
            <div className="answer-vote-count">
              {voteCounts[answer.question_id] || 0}
            </div>
            <i
              className="fa-solid fa-caret-down "
              id="vote-down"
              onClick={() => updateVoteCount(answer.question_id, -1)}
            ></i>
          </div>
          <div className="answer-content-profile-container">
            <div className="answer-content">
              {editingAnswerId === answer.question_id ? (
                <textarea
                  className="edit-mode"
                  value={editingAnswerContent}
                  onChange={(e) => setEditingAnswerContent(e.target.value)}
                />
              ) : (
                answer.body
              )}
            </div>
            <div>
              <button className="answer-share-button" onClick={openPopup}>
                share
              </button>
              <button
                className="answer-button"
                onClick={() => deleteAnswer(answer.question_id)}
              >
                삭제
              </button>
              <button
                className="answer-button"
                onClick={() => startEditing(answer.question_id, answer.body)}
              >
                수정
              </button>
              {editingAnswerId === answer.question_id && (
                <button
                  className="answer-button"
                  onClick={() => finishEditing(answer.question_id)}
                >
                  수정 완료
                </button>
              )}
              {isPopupOpen && (
                <div className="share-popup-overlay" onClick={closePopup}>
                  <div
                    className="share-popup"
                    onClick={(e) => e.stopPropagation()}
                  >
                    <div className="share-link">
                      http://localhost:3000/questions/2
                    </div>
                    <button className="share-button-close" onClick={closePopup}>
                      공유하기
                    </button>
                  </div>
                </div>
              )}
            </div>
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
                  <div className="answer-profile-username">
                    {answer.username}
                  </div>
                </div>
              </div>
            </div>
            <div className="comments-section">
              {answer.comments &&
                answer.comments.map(
                  (comment, index, memberId, updatedCommentInput) => (
                    <div key={comment.member_id} className="comment-container">
                      <div className="comment-divider"></div>
                      <span className="comment-body">{comment.body}</span>
                      <span className="comment-username">
                        {comment.username}
                      </span>
                      <span className="comment-date">{comment.created_at}</span>
                      {comment.member_id === currentMemberId && (
                        <div className="comment-actions">
                          <button
                            className="comment-action-button"
                            onClick={() =>
                              editComment(
                                answer.question_id,
                                comment.member_id,
                                updatedCommentInput[comment.member_id]
                              )
                            }
                          >
                            수정
                          </button>
                          <span
                            className="comment-action-button"
                            onClick={() =>
                              deleteComment(
                                answer.question_id,
                                comment.comment_id
                              )
                            }
                          >
                            삭제
                          </span>
                        </div>
                      )}
                      {index === answer.comments.length - 1 && (
                        <div className="comment-divider"></div>
                      )}
                    </div>
                  )
                )}

              <div className="add-comment">
                <input
                  className="add-comment-input"
                  type="text"
                  placeholder="Add a comment"
                  value={commentInput[answer.question_id] || ""}
                  onChange={(e) =>
                    setCommentInput({
                      ...commentInput,
                      [answer.question_id]: e.target.value,
                    })
                  }
                />
                <button
                  className="comment-button"
                  onClick={() => addComment(answer.question_id)}
                >
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
