import "./QuestionBody.css";
import VoteComponent from "../component/VoteComponent";
import AnswerList from "./AnswerList";

function QuestionBody({ testobj, question_id }) {
  const answerFetch = () => {
    fetch(`https://0251-61-101-53-142.ngrok-free.app/questions/${question_id}/answers`, {
      method: "GET", // GET, POST 등 HTTP 메서드 선택
      headers: {
        "Content-Type": "application/json",
        "ngrok-skip-browser-warning": "69420",
      },
    }).then((response) => response.json());
  };
  return (
    <div className="question-body">
      {testobj.map((item) => {
        return (
          <div key={item.question_id}>
            <VoteComponent vote={item.votes} />
            <div className="question-body--right">
              <p>{item.body}</p>
              {item.answers ? (
                <div>
                  <span>{item.answers.length} Answers</span>
                </div>
              ) : (
                ""
              )}
              <AnswerList />
            </div>
          </div>
        );
      })}
    </div>
  );
}
export default QuestionBody;
/* {
		question_id: 1,
		title:
			"Private Hell 36 Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. ",
		body: "Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justoCurabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.",
		view_count: "9",
		created_at: "2023-07-15 09:49:13",
		modified_at: "2023-01-23T08:03:50Z",
		votes: "34",
		selection: false,
	} */
