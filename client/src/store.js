import { configureStore } from "@reduxjs/toolkit";
import { voteReducer } from "./reducers";
import thunk from "redux-thunk"; // redux-thunk 미들웨어를 가져옵니다.

export default configureStore({
	reducer: {
		vote: voteReducer,
	},
	middleware: [thunk], // 미들웨어로 redux-thunk를 사용합니다.
});
