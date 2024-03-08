// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface TimerState {
  seconds: number;
}

const initialState: TimerState = {
  seconds: 0,
};

export const timerSlice = createSlice({
  name: "timer",
  initialState,
  reducers: {
    /**
     * Increment the value of seconds in the state object by 1.
     *
     * @param {object} state - The state object to be modified
     * @return {void}
     */

    increment: (state) => {
      state.seconds += 1;
    },

    /**
     * Reset the state's seconds to 0.
     *
     * @param {type} state - The state object
     * @return {void}
     */
    
    reset: (state) => {
      state.seconds = 0;
    },
  },
});

export const { increment, reset } = timerSlice.actions;

export default timerSlice.reducer;
