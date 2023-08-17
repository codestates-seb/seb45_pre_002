import React from "react";
import "./AskQuestion.css";
import { StacksEditor } from "@stackoverflow/stacks-editor";
// Don’t forget to include the styles as well
// import "@stackoverflow/stacks-editor/styles.css";

function AskQuestion() {
  return (
    <div className="page-container">
      <div className="sidebar sidebar-left"></div>
      <div className="content1">
        <div className="instructions">
          <h2>Writing a good question</h2>
          <p>
            You’re ready to ask a programming-related question and this form
            will help guide you through the process.
          </p>
          <p>
            Looking to ask a non-programming question? See the topics here to
            find a relevant site.
          </p>

          <h3>Steps</h3>
          <ol>
            <li>Summarize your problem in a one-line title.</li>
            <li>Describe your problem in more detail.</li>
            <li>Describe what you tried and what you expected to happen.</li>
            <li>
              Add “tags” which help surface your question to members of the
              community.
            </li>
            <li>Review your question and post it to the site.</li>
          </ol>
        </div>
        <div id="editor-example-1"></div>
      </div>
      <div className="sidebar sidebar-right"></div>
    </div>
  );
}

export default AskQuestion;
