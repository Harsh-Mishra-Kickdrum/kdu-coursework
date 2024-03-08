// Timer.tsx
// This component implements a simple timer functionality using React and Redux Toolkit.

// Importing necessary hooks and styles.
import "./Timer.scss";
import React, { useEffect, useRef } from "react";
import { useAppDispatch, useAppSelector } from "../../app/hook";
import { increment, reset } from "./timerSlice";

// Defining the Timer component as a functional component with TypeScript.
export const Timer: React.FC = () => {
  // Using the useDispatch hook to dispatch actions.
  const dispatch = useAppDispatch();
  // Accessing the current timer value from the global state using useSelector.
  const seconds = useAppSelector((state) => state.timer.seconds);
  // Creating a ref to store the interval ID for later clearance.
  const intervalRef = useRef<number | null>(null);

  // Setting up an effect to handle the timer functionality.
  useEffect(() => {
    // Setting up an interval to increment the timer value every second.
    intervalRef.current = window.setInterval(() => {
      dispatch(increment());
    }, 1000);

    // Cleanup function to clear the interval when the component unmounts or the dependencies change.
    return () => {
      if (intervalRef.current !== null) clearInterval(intervalRef.current);
    };
  }, [dispatch]); // Dependency array includes dispatch to ensure the effect runs correctly if dispatch changes.

  // Rendering the timer display and a reset button.
  return (
    <div className="Timer">
      <span className="Timer__seconds">{seconds} seconds</span>
      <button className="reset-btn" onClick={() => dispatch(reset())}>
        Reset
      </button>
    </div>
  );
};
