
import { combineReducers } from "@reduxjs/toolkit";
import { roomTypeReducer } from "./roomtypeReducer";
import { dateReducer } from "./dateReducer";
import { addOnsReducer } from "./addOnsReducer";


const rootReducer = combineReducers({
  roomType: roomTypeReducer,
  date: dateReducer,
  addOns: addOnsReducer,
});

export type RootState = ReturnType<typeof rootReducer>;

export default rootReducer;
