// src/store/rootReducer.ts
import { combineReducers } from "@reduxjs/toolkit";
import productsReducer from "../features/products/productsSlice";


const rootReducer = combineReducers({
  products: productsReducer,
});

export type RootState = ReturnType<typeof rootReducer>;

export default rootReducer;
