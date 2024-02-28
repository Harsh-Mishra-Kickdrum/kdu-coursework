// redux/reducers/rootReducer.ts
import { combineReducers } from "redux";
import todoReducer from "./todoSlice"; 
import searchReducer from "./searchSlice";

const rootReducer = combineReducers({
  todos: todoReducer,
  search: searchReducer,
  
});

export default rootReducer;
