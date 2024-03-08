import React from "react";
import { Timer } from "./features/timer/Timer";
import { AutoFocusInput } from "./components/AutoFocusInput/AutoFocusInput";
import LongContentPage from "./components/LongContentPage/LongContentPage"; 
import "./App.css";

/**
 * React functional component for the App.
 *
 * @return {JSX.Element} The rendered React element.
 */

const App: React.FC = () => {
  return (
    <div className="App">
      <h1 className="App__title">UseRef and Scroll to Top Demo</h1>
      <AutoFocusInput />
      <Timer />
      <LongContentPage />
    </div>
  );
};

export default App;
