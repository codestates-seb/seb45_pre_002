const initialState = {
    voteCounts: {},
  };
  
  export function voteReducer(state = initialState, action) {
    switch (action.type) {
      case 'UPDATE_VOTE_COUNT':
        return {
          ...state,
          voteCounts: {
            ...state.voteCounts,
            [action.payload.id]: (state.voteCounts[action.payload.id] || 0) + action.payload.value,
          },
        };
      default:
        return state;
    }
  }
  
  export default voteReducer;