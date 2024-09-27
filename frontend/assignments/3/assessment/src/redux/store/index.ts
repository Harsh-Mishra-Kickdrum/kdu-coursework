import { roomTypeReducer } from './../reducers/roomtypeReducer';
import { addOnsReducer } from './../reducers/addOnsReducer';
import { configureStore } from "@reduxjs/toolkit";
import { dateReducer } from '../reducers/dateReducer';

export const store = configureStore({
  reducer: {
    addonReducer:addOnsReducer,
    dateReducer: dateReducer,
    roomTypeReducer:roomTypeReducer,

  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
